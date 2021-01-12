package Controllers;

import UseCases.*;

import java.io.Serializable;
import java.util.*;

/**
 * This class is responsible for taking input and implementing all logic/actions related to a user and events.
 * The following manipulations a user can work on are:
 * - enrol an Entities.Organizer in an event
 * - enrol an Entities.Attendee in an event
 * - enrol a Entities.User in event
 * - cancel enrolment for Entities.User
 * - view list of available events
 * - create an event
 * - remove an event
 * @author Ashwin Karthikeyan, Arib Shaikh, Khoa Pham, Vladimir
 *
 */
public class UserEventController implements Serializable {

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final EventManager eventManager;
    private final RoomManager roomManager;


    public UserEventController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, RoomManager roomManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;

    }

    /**
     * enroll an Entities.Organizer with </username> to an Entities.Event </eventName>
     * If event with </eventName> exists,
     * check room capacity and enrol that Entities.Attendee to that Entities.Event
     * @author Ashwin Karthikeyan
     * @param username: the username of an Entities.Organizer to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return "AE" - Already attending the event
     *         "EDE" - Entities.Event doesn't exist
     *         "EFC" - Entities.Event at full capacity
     *         "YES" - Entities.Organizer has newly been registered for this event
     */
    private String enrolOrganizerInEvent(String username, String eventName){

        if (eventManager.isEvent(eventName)){
            if (organizerManager.isAttending(username, eventName).equals("YES")) {
                return "AE";
            }
            String roomId = eventManager.getRoomNumber(eventName);
            int capacity = roomManager.getCapacityOfRoom(roomId);
            ArrayList<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
            if (attendeesOfEvent.size() < capacity) {
                String erMessage = eventManager.reserveAttendee(eventName, username);
                if (erMessage.equals("YES")) {
                    organizerManager.addAttendingEvent(username, eventName);
                    return "YES";
                }
                return erMessage;
            }
            return "EFC";
        }
        return "EDE";
    }

    /**
     * enroll an Entities.Attendee with </username> to an Entities.Event </eventName>
     * If event with </eventName> and Entities.Attendee with </username> exist,
     * check room capacity and enrol that Entities.Attendee to that Entities.Event
     * @author Khoa Pham
     * @param username: the username of an Entities.Attendee to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return : "AE" - Already attending event
     *           "EDE" - Entities.Event doesn't exist
     *           "EFC" - Entities.Event at full capacity
     *           "YES" - Entities.Attendee has newly been registered for this event
     */
    private String enrolAttendeeInEvent(String username, String eventName) {
        // ent.
        if (eventManager.isEvent(eventName)) {
            if (attendeeManager.isAttending(username, eventName)) {
                return "AE";
            }
            String roomId = eventManager.getRoomNumber(eventName);
            int capacity = roomManager.getCapacityOfRoom(roomId);
            ArrayList<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
            if (attendeesOfEvent.size() < capacity) {
                String erMessage = eventManager.reserveAttendee(eventName, username);
                if (erMessage.equals("YES")) {
                    attendeeManager.addAttendingEvent(username, eventName);
                    return "YES";
                }
                return erMessage;
            }
            return "EFC";
        }
        return "EDE";
    }

    /**
     * enroll Entities.User (Entities.Organizer/Entities.Attendee) with </username> to an Entities.Event </eventName>
     * by calling enrolOrganizerInEvent() or enrolAttendeeInEvent()
     * @author Ashwin Karthikeyan
     * @param username: the username of a Entities.User (Entities.Organizer/Entities.Attendee) to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return : "AE" - Already attending event
     *           "EDE" - Entities.Event doesn't exist
     *           "EFC" - Entities.Event at full capacity
     *           "YES" - Entities.Attendee has newly been registered for this event
     */
    public String enrolUserInEvent(String username, String eventName){

        if(organizerManager.isOrganizer(username)){
            return enrolOrganizerInEvent(username, eventName);
        }
        else if(attendeeManager.isAttendee(username)){
            return enrolAttendeeInEvent(username, eventName);
        }
        return "UDE";

    }

    /**
     * By the end of the execution of this method, the Entities.User (Entities.Organizer/Entities.Attendee) with username </username> is no longer
     * attending the event with title </eventName>.
     * @author Khoa Pham, Ashwin Karthikeyan
     * @param username: the username of the Entities.User who wants to cancel
     *                  reservation for an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     */
    public void cancelSeatForUser(String username, String eventName){

        if(eventManager.isEvent(eventName)){
            if(attendeeManager.isAttendee(username)){
                if(attendeeManager.isAttending(username, eventName)) {
                    attendeeManager.removeAttendingEvent(username, eventName);
                    eventManager.removeAttendee(eventName, username);
                }
            }
            else if(organizerManager.isOrganizer(username)){
                if(organizerManager.isAttending(username, eventName).equals("YES")) {
                    organizerManager.removeAttendingEvent(username, eventName);
                    eventManager.removeAttendee(eventName, username);
                }
            }
        }

    }

    /**
     * By the end of the execution of this method, the Entities.Organizer with username </organizerUsername> would have
     * created a room with Id </roomId> and capacity </capacity>
     * @author Ashwin Karthikeyan
     * @param organizerUsername: the username of the Entities.Organizer who wants to create a new room (param_type: String)
     * @param roomId: an ID for the new room that the Entities.Organizer wants to create (param_type: String)
     * @param capacity: the capacity of the new room being created (param_type: int)
     * @return : "RAE" - room already exists
     *           "ODE" - organizer doesn't exist
     */
    public String organizerAddNewRoom(String organizerUsername, String roomId, int capacity){
        // RAE - room already exists
        if(organizerManager.isOrganizer(organizerUsername)){
            if(!roomManager.createRoom(roomId, capacity)){
                return "RAE";
            }
            return "YES";
        }
        return "ODE";
    }

    /**
     * If Entities.Organizer with given username </username> exists, then this method returns the list of event titles that
     * @param username username of Entities.Organizer
     * @return List of Events that this Entities.Organizer is not attending
     */
    public ArrayList<String> getOrganizerEventsNotAttending(String username) {

        ArrayList<String> eventsNotSignedUpFor = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            eventsNotSignedUpFor.add(event);
        }
        if(organizerManager.isOrganizer(username)) {
            if (organizerManager.getEventsAttending(username) != null) {
                for (String event : organizerManager.getEventsAttending(username)) {
                    eventsNotSignedUpFor.remove(event);
                }
            }
        }
        return eventsNotSignedUpFor;
    }
    /**
     * Allows an organizer to create an event, by adding it to the list of events, as well as the list of speaker talks
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * @param eventTime: time of event
     * @param speakerName: name of speaker
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * @return Strings of the values listed above
     */
    public String createEvent(String organizerName, String eventName, String eventTime, String speakerName){
        ArrayList<String> allowedTimes = new ArrayList<String>();
        allowedTimes.add("9");
        allowedTimes.add("10");
        allowedTimes.add("11");
        allowedTimes.add("12");
        allowedTimes.add("1");
        allowedTimes.add("2");
        allowedTimes.add("3");
        allowedTimes.add("4");
        allowedTimes.add("5");

        if(organizerManager.isOrganizer(organizerName)){

            if(allowedTimes.contains(eventTime)){

                if(speakerManager.isSpeakerFreeAtTime(speakerName, eventTime)){
                    String roomNumber = roomManager.occupyRoomFreeAt(eventTime);

                    if(!roomNumber.equals("-")){
                        speakerManager.addTalkToListOfTalks(speakerName,eventTime,eventName);
                        roomManager.occupyRoomAt(roomNumber,eventTime);
                        return eventManager.addEvent(eventName,eventTime,roomNumber,speakerName);
                    }
                    else{
                        return "RO";
                    }

                }
                else{
                    return "STC";
                }
            }
            else{
                return "ETC";
            }

        }
        else{
            return "ODE";
        }

    }

    /**
     * Allows an organizer to remove a created event, also removes it from the list of talks of the speaker, and list
     * of attending events for Organizers and Attendees
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * "EDE" - Entities.Event Doesn't Exist
     * "ODE" - Entities.Organizer Doesn't Exist
     * "YES" - Request Successful
     * @return String of the values listed above
     * @author aribshaikh
     */
    public String removeCreatedEvent(String organizerName,String eventName) {
        if (organizerManager.isOrganizer(organizerName)) {
            if (eventManager.isEvent(eventName)) {
                String speakerUserName = eventManager.getSpeakerEvent(eventName);
                String eventTime = eventManager.getEventTime(eventName);
                String roomId = eventManager.getRoomNumber(eventName);

                eventManager.removeEvent(eventName);
                speakerManager.removeTalkFromListOfTalks(speakerUserName, eventTime, eventName);
                for(String attendeeID: attendeeManager.getAllAttendeeIds()){
                    attendeeManager.removeAttendingEvent(attendeeID, eventName);
                }
                for(String organizerID: organizerManager.getAllOrganizerIds()){
                    organizerManager.removeAttendingEvent(organizerID, eventName);
                }
                roomManager.freeRoomAt(roomId, eventTime);
                return "YES";

            } else {
                return "EDE";
            }
        } else {
            return "ODE";
        }
    }

    /**
     * return the list of all events
     * call eventManager to perform!
     * @author Khoa Pham
     * @return Hashtable<String, ArrayList<String>> eventsWithInfo
     */
    public Hashtable<String, ArrayList<String>> seeAllEventsWithInfo() {
        return eventManager.getAllEventsWithInfo();
    }

    /**
     * allow an Entities.Attendee to see the list of all their participating events
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Entities.Attendee whose list of
     *                participating events is returned (param_type: String)
     * @return Hashtable<String, ArrayList<String>> eventsWithInfo
     */
    public Hashtable<String, ArrayList<String>> seeParticipatingEvents(String attendee) {
        ArrayList<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        Hashtable<String, ArrayList<String>> events = new Hashtable<>();
        for (String eventId : eventIdsAttending) {
            events.put(eventId, eventManager.getEventInfo(eventId));
        }
        return events;
    }


    /**
     * allow an Entities.Attendee to see the list of all their available to signup events
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Entities.Attendee whose list of
     *          all their available to signup events is returned (param_type: String)
     * @return Hashtable<String, ArrayList<String>> eventsWithInfo
     */
    public Hashtable<String, ArrayList<String>> seeAttendableEvents(String attendee) {
        ArrayList<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        ArrayList<String> eventIdsAll = eventManager.getEventNamesList();
        Hashtable<String, ArrayList<String>> eventsAttendable = new Hashtable<>();
        for (String eventId : eventIdsAll) {
            if (!eventIdsAttending.contains(eventId)) {
                eventsAttendable.put(eventId, eventManager.getEventInfo(eventId));
            }
        }
        return eventsAttendable;
    }

    /**
     * Allows the speaker to see the list of events they are hosting with event name and time
     * @param speakerUsername : username of speaker
     * @return : Returns the list of events they are hosting (param_type: ArrayList<String>)
     */
    public ArrayList<String> seeListOfEventsForSpeaker(String speakerUsername){
        ArrayList<HashMap<String, String>> listOfTalks = speakerManager.getListOfTalks(speakerUsername);

       ArrayList<String> masterList = new ArrayList<>();

       for (HashMap<String, String> talk: listOfTalks){
           for(String eventName: talk.values()){
               String eventTime = eventManager.getEventTime(eventName);
               masterList.add("(Entities.Event Name: " + eventName + ", " + "Entities.Event Time: " + eventTime + ")");
           }

       }
        return masterList;
    }

    /**
     * Can see the event information
     * @param speakerUsername : name of speaker
     * @return : list of talks for the speaker (param_type: ArrayList<HashMap<String, String>>)
     */
    private ArrayList<HashMap<String, String>> seeAllEventsForSpeaker(String speakerUsername){
        return speakerManager.getListOfTalks(speakerUsername);
    }

    /**
     * Can see all the event names for the speaker
     * @param speakerUsername : name of speaker
     * @return list of all event names of talks (param_type: ArrayList<String>)
     */
    public ArrayList<String> seeAllEventNamesForSpeaker(String speakerUsername){
        ArrayList<HashMap<String, String>> listOfTalks = seeAllEventsForSpeaker(speakerUsername);
        ArrayList<String> listOfNamedTalks = new ArrayList<>();
        for(HashMap<String, String> talk: listOfTalks){
            Collection<String> talkname1 =  talk.values();
            for (String talk3: talkname1){
                listOfNamedTalks.add(talk3);
            }
        }
        return listOfNamedTalks;
    }



}