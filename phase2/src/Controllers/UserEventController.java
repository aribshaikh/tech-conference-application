package Controllers;

import UseCases.*;

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
public class UserEventController {

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
     * Allows an organizer to create an event in a particular room. This has to happen once the organizer chooses a room
     * from a displayed list of eligible rooms for the event.
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * @param startTime: time of event
     * @param speakerName: name of speaker
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * "ESOT" - Event Scheduling Over Time - the event cannot be scheduled at this time because it would have to
     *                   run past 5PM.
     * @return Strings of the values listed above
     */
    public String createEventInRoom(String organizerName, String eventName, String startTime, int duration, int eventCapacity, List<String> speakerName, String roomNumber, String subjectLine){
        List<String> allowedTimes = eventManager.getAllowedTimes();

        for(String speakerUsername: speakerName){
            if(!speakerManager.isSpeaker(speakerUsername)){
                System.out.println("SDE");

                return "SDE";
            }
        }

        if(organizerManager.isOrganizer(organizerName)){

            if(allowedTimes.contains(startTime)){
                int index = allowedTimes.indexOf(startTime);
                if(index + duration <= allowedTimes.size()) {
                    for (String speaker : speakerName) {
                        for(int i = 0; i < duration; i++) {
                            if (!speakerManager.isSpeakerFreeAtTime(speaker, allowedTimes.get(index + i))) {
                                System.out.println(allowedTimes.get(index + i));
                                System.out.println("STC");
                                return "STC";
                            }

                        }

                    }

                }
                else{
                    System.out.println("ESOT");

                    return "ESOT";
                }

                int roomCapacity = roomManager.getCapacityOfRoom(roomNumber);
                if(eventCapacity <= roomCapacity) {
                        // changes from here

                    for (String speaker : speakerName) {
                        for (int i = 0; i < duration; i++) {
                            speakerManager.addTalkToListOfTalks(speaker, allowedTimes.get(index + i), eventName);
                        }
                    }
                    roomManager.occupyRoomAt(roomNumber,startTime, duration);
                    System.out.println("YES");
                    return eventManager.addEvent(eventName, startTime, duration, roomNumber, eventCapacity, speakerName, subjectLine);
                }
                else{
                    System.out.println("ECF");

                    return "ECF";
                }

            }
            else{
                System.out.println("ETC");

                return "ETC";
            }
        }
        else{
            System.out.println("ODE");

            return "ODE";
        }

    }


    /**
     * Allows an organizer to remove a created event, also removes it from the list of talks of the speaker, and list
     * of attending events for Organizers and Attendees. It also relieves the room at the event's time.
     * @param organizerName : name of organizer
     * @param eventName : name of event
     * "EDE" - Entities.Event Doesn't Exist
     * "ODE" - Entities.Organizer Doesn't Exist
     * "YES" - Request Successful
     * @author aribshaikh
     */
    public void removeCreatedEvent(String organizerName, String eventName) {
        List<String> allowedTimes = eventManager.getAllowedTimes();


        if (organizerManager.isOrganizer(organizerName)) {
            if (eventManager.isEvent(eventName)) {
                List<String> speakerUserName = eventManager.getSpeakerEvent(eventName);
                String startTime = eventManager.getStartTime(eventName);
                int index = allowedTimes.indexOf(startTime);
                int duration = eventManager.getDuration(eventName);
                String roomId = eventManager.getRoomNumber(eventName);

                eventManager.removeEvent(eventName);

                for(String speaker: speakerUserName){
                    for (int i = 0; i < duration; i++) {
                        speakerManager.removeTalkFromListOfTalks(speaker, allowedTimes.get(index+ i), eventName);
                    }
                }

                for(String attendeeID: attendeeManager.getAllAttendeeIds()){
                    attendeeManager.removeAttendingEvent(attendeeID, eventName);
                }

                for(String organizerID: organizerManager.getAllOrganizerIds()){
                    organizerManager.removeAttendingEvent(organizerID, eventName);
                }
                roomManager.freeRoomAt(roomId, startTime, duration);

            }
        }
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
            List<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
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
     * If Entities.Organizer with given username </username> exists, then this method returns the list of event titles that
     * @param username username of Entities.Organizer
     * @return List of Events that this Entities.Organizer is not attending
     */
    public List<String> getOrganizerEventsNotAttending(String username) {

        List<String> eventsNotSignedUpFor = new ArrayList<>(eventManager.getAllEventTitles());
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
        if (eventManager.isEvent(eventName)) {
            if (attendeeManager.isAttending(username, eventName)) {
                return "AE";
            }
            //String roomId = eventManager.getRoomNumber(eventName);
            //int capacity = roomManager.getCapacityOfRoom(roomId);
            int capacity = eventManager.getEventCapacity(eventName);
            List<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
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
     * allow an Entities.Attendee to see the list of all their available to signup events
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Entities.Attendee whose list of
     *          all their available to signup events is returned (param_type: String)
     * @return Map<String, List<String>> eventsWithInfo
     */
    public Map<String, List<String>> seeAttendableEvents(String attendee) {
        List<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        List<String> eventIdsAll = eventManager.getEventNamesList();
        Map<String, List<String>> eventsAttendable = new Hashtable<>();
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
     * @return : Returns the list of events they are hosting (param_type: List<String>)
     */
    public List<String> seeListOfEventsForSpeaker(String speakerUsername){
        Map<String, String> listOfTalks = speakerManager.getListOfTalks(speakerUsername);

        List<String> masterList = new ArrayList<>();


       for(Map.Entry<String, String> event: listOfTalks.entrySet()){
           String eventTime = eventManager.getStartTime(event.getKey());
           masterList.add("(Entities.Event Name: " + event.getValue() + ", " + "Entities.Event Time: " + eventTime + ")");
       }


        return masterList;
    }

    /**
     * Can see all the event names for the speaker
     * @param speakerUsername : name of speaker
     * @return list of all event names of talks (param_type: List<String>)
     */
    public List<String> seeAllEventNamesForSpeaker(String speakerUsername){
        Map<String, String> listOfTalks = speakerManager.getListOfTalks(speakerUsername);

        List<String> masterList = new ArrayList<>();

        for(Map.Entry<String, String> event: listOfTalks.entrySet()){
            masterList.add("(Entities.Event Name: " +  event.getValue() + ")");
        }


        return masterList;
    }

}