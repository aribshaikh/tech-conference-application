package Controllers;

import NewUI.OrganizerPresenterTextUI;
import UseCases.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods that are specific to actions that an Organizer is allowed to do. The methods in this class
 * collaborate with UseCase classes.
 * @author Ashwin
 */
public class OrganizerMenuController implements CommandHandler{

    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private UserEventController userEventController;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessengerMenuController messengerMenuController;
    private ConversationMenuController conversationMenuController;
    private OrganizerPresenterTextUI organizerPresenterTextUI;
    private PollController pollController;


    public OrganizerMenuController(OrganizerManager organizerManager,
                                   SpeakerManager speakerManager,
                                   AccountHandler accountHandler, EventManager eventManager,
                                   UserEventController userEventController, RoomManager roomManager,
                                   OrganizerPresenterTextUI organizerPresenterTextUI,
                                   MessengerMenuController messengerMenuController, ConversationManager conversationManager,
                                   ConversationMenuController conversationMenuController, PollController pollController){

        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;
        this.organizerPresenterTextUI = organizerPresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;
        this.pollController = pollController;

    }


    /**
     * Private helper method that takes in the "organizer type" and handles the HALF the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param username: username of the currently logged in user
     */
    public boolean handleCommand(String username) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "0":
                return false;
            case "1": {
                organizerPresenterTextUI.askForUserType();
                String userType = scanner.nextLine();
                organizerPresenterTextUI.listOfUsers(userType);
                }
                return true;
            case "2": {
                organizerPresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                organizerPresenterTextUI.promptForTime();
                String time = scanner.nextLine();
                if (!speakerManager.isSpeaker(speakerName)) {
                    organizerPresenterTextUI.notASpeaker();
                    return true;
                }
                boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
                if (free) {
                    organizerPresenterTextUI.messageForSpeakerFreeIfFree(time);
                } else {
                    organizerPresenterTextUI.messageForSpeakerFreeIfNotFree(time);
                }
                return true;
            }
            case "3": {
                organizerPresenterTextUI.askForUserType();
                String userType = scanner.nextLine();
                organizerPresenterTextUI.askForUsername();
                String newUsername = scanner.nextLine();
                organizerPresenterTextUI.askForPassword();
                String password = scanner.nextLine();
                boolean err = accountHandler.signup(newUsername, password, userType);
                if (err) {
                    organizerPresenterTextUI.success();
                } else {
                    organizerPresenterTextUI.usernameAlreadyExists();
                }
                return true;
            }
            case "4": {
                String err = organizerAddNewRoom(username);
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                } else {
                    organizerPresenterTextUI.success();
                }
                return true;
            }
            case "5": {
                createEventThroughOrganizer(username);
                return true;
            }
            case "6": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                userEventController.removeCreatedEvent(username, eventName);
                return true;
            }
            case "7": {
                String err = changeSpeakerForEventThroughOrganizer(username);
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                }
                else {
                    organizerPresenterTextUI.success();
                }
                return true;
            }
            case "8": {
                String err = changeEventStartTime(username);
                if(err.equals("YES")) {
                    organizerPresenterTextUI.success();
                }
                else{
                    organizerPresenterTextUI.showError(err);
                }
                return true;
            }
            case "9": {
                List<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
                organizerPresenterTextUI.presentEvents(eventsNotSignedUpFor);
                return true;
            }
            case "10": {
                organizerPresenterTextUI.promptEventWantToAttend();
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                } else {
                    organizerPresenterTextUI.success();
                }
                return true;
            }
            case "11": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventName);
                organizerPresenterTextUI.presentNoLongerAttendingEvent(eventName);
                return true;
            }
            case "12": {
                organizerPresenterTextUI.presentEvents(organizerManager.getEventsAttending(username));
                return true;
            }
            case "13": {
                organizerPresenterTextUI.promptAttendeeID();
                String attendeeID = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                boolean err = messengerMenuController.organizerSendMessageToSingle(username, attendeeID, content, "attendee");
                if (err) {
                    organizerPresenterTextUI.success();
                } else {
                    organizerPresenterTextUI.failure();
                }
                return true;
            }
            case "14": {
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "attendee");
                return true;
            }
            case "15": {
                organizerPresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToSingle(username, speakerName, content, "speaker");
                return true;
            }
            case "16": {
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "speaker");
                return true;
            }
            case "17": {
                viewAndReplyInConversations(username);
                return true;
            }
            case "18": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String message = scanner.nextLine();
                if (!eventManager.isEvent(eventName)) {
                    organizerPresenterTextUI.showError("EDE");
                    return true;
                }
                boolean messageByEvent = messengerMenuController.organizerMessageByEvent(username, eventName, message);
                if (messageByEvent) {
                    organizerPresenterTextUI.success();
                    return true;
                }
                organizerPresenterTextUI.failure();
                return true;
            }
            case "19": {
                pollController.runPollFunctionality(username);
                return true;
            }
        }
        return true;
    }

    /**
     * Allows organizer to view conversation, and send a reply
     * @param username Username of organizer
     */
    public void viewAndReplyInConversations(String username){

        Scanner scanner = new Scanner(System.in);
        Integer i = 1;
        for (String conversationId : organizerManager.getConversations(username)) {
            List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
            StringBuilder recipients = new StringBuilder();
            organizerPresenterTextUI.convoNumUniqueId(i.toString(), conversationId);
            for (String recipient : recipientsOfConversation) {
                recipients.append(recipient);
                recipients.append(", ");
            }
            organizerPresenterTextUI.presentRecipients(recipients);
            i += 1;
        }
        if (organizerManager.getConversations(username).isEmpty()) {
            organizerPresenterTextUI.noConvo();
            return;
        }
        organizerPresenterTextUI.promptConvoNumber();
        String conversationNumber = scanner.nextLine();
        String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
        List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
        organizerPresenterTextUI.presentMessageInConvo(messagesInThisConversation);
        organizerPresenterTextUI.promptToReply();
        String reply = scanner.nextLine();
        if (!reply.equals("r")) {
            return;
        }
        organizerPresenterTextUI.messageprompt();
        String content = scanner.nextLine();
        conversationMenuController.reply(username, conversationIdFinal, content);

    }


    /**
     * Checks if the speaker with <speakerName> is free at <time>
     * @param speakerName username of the speaker involved (param_type: String)
     * @param time time at which we want to check availability of the speaker (param_type: String)
     * @param duration duration for which the speaker is expected to be free (param_type: int)
     * @return String:
     * "SDE" - Speaker Doesn't Exist
     * "NO" - Speaker is not free at the time
     * "YES" - Speaker is free at that time
     */
    public String checkIfSpeakerFreeAtTimeFor(String speakerName, String time, int duration){

        if (!speakerManager.isSpeaker(speakerName)) {
            return "SDE"; //Refer to TextUserInterface
        }
        int k = 0;
        int availabilityFlag = 0;
        for(int i = 1; i < 13; i++){
            if(Integer.toString(i).equals(time)){
                k = i;
                break;
            }
        }
        for(int i = 0; i < duration; i++) {
            if(speakerManager.isSpeakerFreeAtTime(speakerName, Integer.toString(k))){
                availabilityFlag++;
            }
            k++;
        }
        if(k==duration){
            return "YES";
        }
        else{
            return "NO";
        }

    }

    /**
     * By the end of the execution of this method, the Entities.Organizer with username <username> would have
     * created a room if there was no roomId conflict.
     * @author Ashwin Karthikeyan
     * @param username: the username of the Entities.Organizer who wants to create a new room (param_type: String)
     * @return : "RAE" - room already exists
     *           "ODE" - organizer doesn't exist
     */
    public String organizerAddNewRoom(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();
        organizerPresenterTextUI.promptForRoomCapacity();
        String capacitys = scanner.nextLine();
        int capacity = Integer.parseInt(capacitys);

        organizerPresenterTextUI.promptForProjectorExist();
        String proj = scanner.nextLine();
        boolean hasProjector = proj.equals("Y");

        organizerPresenterTextUI.askForAudioSystem();
        String answer = scanner.nextLine();
        boolean hasAudioSystem = answer.equals("Y");

        organizerPresenterTextUI.askForPowerSockets();
        String powsoc = scanner.nextLine();
        int powerSockets = Integer.parseInt(powsoc);
        // RAE - room already exists
        if(organizerManager.isOrganizer(username)){
            if(!roomManager.createRoom(roomId, capacity, hasProjector, hasAudioSystem, powerSockets)){
                return "RAE";
            }
            return "YES";
        }
        return "ODE";
    }


    /**
     * Change speaker for an Event. The Event will be removed and then a new event will be created. This means attendees
     * should register the event again once this method has been called. (This is to make sure that the attendees
     * are aware of the speaker change and still choose to attend the event)
     * @param username username of the organizer through whom this change is happening
     * @return String
     * "EDE" - Event Doesn't Exist
     * "SDE" - Speaker Doesn't Exist
     * "NO" - A speaker was not free at the event's time
     *  -- createEventInRoom return Strings --
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * "ESOT" - Event Scheduling Over Time - the event cannot be scheduled at this time because it would have to
     *                   run past 5PM.
     * "YES" - change was successful
     */
    public String changeSpeakerForEventThroughOrganizer(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        List<String> speakerNames = new ArrayList<>();

        organizerPresenterTextUI.numOfSpeakers();
        String numS = scanner.nextLine();
        int num = Integer.parseInt(numS);

        organizerPresenterTextUI.promptForEventSpeakers();
        for (int i = 0; i<num; i++) {
            String speaker = scanner.nextLine();
            speakerNames.add(speaker);
        }

        String eventTime = eventManager.getStartTime(eventName);
        int duration = eventManager.getDuration(eventName);

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();

        String speakerErr;
        if(!eventManager.isEvent(eventName)){
            return "EDE"; //Refer to TextUserInterface
        }
        String eventStartTime = eventManager.getStartTime(eventName);
        int eventDuration = eventManager.getDuration(eventName);
        int eventCapacity = eventManager.getEventCapacity(eventName);
        String subjectLine = eventManager.getEventSubjectLine(eventName);
        for(String speakerName: speakerNames) {
            speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventStartTime, eventDuration);
            if(!speakerErr.equals("YES")){
                return speakerErr;
            }
        }
        userEventController.removeCreatedEvent(username, eventName);
        String err = userEventController.createEventInRoom(username, eventName, eventStartTime, eventDuration, eventCapacity, speakerNames, roomId, subjectLine);
        if (!err.equals("YES"))
            return err; //Refer to TextUserInterface
        else {
            return "YES"; //Refer to TextUserInterface
        }

    }

    /**
     * Changes the start time of an event.
     * @param username Username of the organizer through whom this change is happening
     * @return String
     * "EDE" - Event Doesn't Exist
     * "YES" - Successful
     * "SDE" - Speaker Doesn't Exist
     *  -- createEventInRoom return Strings --
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * "ESOT" - Event Scheduling Over Time - the event cannot be scheduled at this time because it would have to
     *                   run past 5PM.
     */
    public String changeEventStartTime(String username) {

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        organizerPresenterTextUI.newTimeForEvent();
        String eventTime = scanner.nextLine();
        int duration = eventManager.getDuration(eventName);

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();
        String speakerErr;
        if (eventManager.isEvent(eventName)) {
            List<String> speakerNames = eventManager.getSpeakerEvent(eventName);
            int eventDuration = eventManager.getDuration(eventName);
            int eventCapacity = eventManager.getEventCapacity(eventName);
            String subjectLine = eventManager.getEventSubjectLine(eventName);
            userEventController.removeCreatedEvent(username, eventName);
            for (String speakerName : speakerNames) {
                speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventTime, eventDuration);
                if (!speakerErr.equals("YES")) {
                    return speakerErr;
                }
            }
            String err = userEventController.createEventInRoom(username, eventName, eventTime, eventDuration, eventCapacity, speakerNames, roomId, subjectLine);
            if (err.equals("YES")) {
                return "YES";
            } else {
                return err;
            }
        } else {
            return "EDE";
        }

    }

    /**
     * Method allows organizer to create an event
     * @param username Username of the organizer through whom this change is happening
     */
    public void createEventThroughOrganizer(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        organizerPresenterTextUI.promptForEventTime();
        String eventTime = scanner.nextLine();
        organizerPresenterTextUI.promptForEventDuration();
        String durationS = scanner.nextLine();
        organizerPresenterTextUI.promptForEventCapacity();
        String capacityS = scanner.nextLine();
        int duration = Integer.parseInt(durationS);
        int capacity = Integer.parseInt(capacityS);

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomNum = scanner.nextLine();
        organizerPresenterTextUI.promptForSubjectLine();
        String subject = scanner.nextLine();
        List<String> speakers = new ArrayList<>();

        organizerPresenterTextUI.numOfSpeakers();
        String nums = scanner.nextLine();
        int num = Integer.parseInt(nums);

        organizerPresenterTextUI.promptForEventSpeakers();
        for(int i = 0; i<num; i++) {
            speakers.add(scanner.nextLine());
        }
        String err = userEventController.createEventInRoom(username, eventName, eventTime, duration, capacity, speakers, roomNum, subject);
        if (!err.equals("YES")) {
            organizerPresenterTextUI.showError(err);
        }
        else {
            organizerPresenterTextUI.success();
        }

    }

    /**
     * Method allows organizer to see rooms with specifications, such as audio system, projector, power socket
     * @param eventTime Start Time of event
     * @param duration Duration of event
     */
    private void eventSpec(String eventTime, int duration){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.askForSpecifications();
        String audioSys = scanner.nextLine();
        boolean hasAudioSystem = audioSys.equals("YES");
        String projector = scanner.nextLine();
        boolean hasProjector = projector.equals("YES");
        String powerSocket = scanner.nextLine();
        int powerSockets = Integer.parseInt(powerSocket);

        List<String> rooms = roomManager.roomsWithRequirements(hasAudioSystem, hasProjector, powerSockets, eventTime, duration);
        if(rooms != null) {
            organizerPresenterTextUI.roomMatchingSpecificationsAre(rooms);
        }
        else {
            organizerPresenterTextUI.noRoomMatch();
        }
    }

}