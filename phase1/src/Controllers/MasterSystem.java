package Controllers;

import UI.TextUserInterface;
import UseCases.*;
import Gateways.ProgramGenerator;

import java.io.Serializable;
import java.util.*;

/**
 * Class that stores the instances of use case and controller classes and controls
 * the flow of the program by getting user input and and using other controllers to
 * execute tasks and display results using the UI.
 * @author Akshat Ayush
 */
public class MasterSystem implements Serializable {

    private final TextUserInterface ui;

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;

    private final EventManager eventManager;
    private final RoomManager roomManager;

    private final ConversationManager conversationManager;
    private final MessageManager messageManager;

    private final AccountHandler accountHandler;


    private final UserMessageController userMessageController;
    private final UserEventController userEventController;

    private final ProgramGenerator programGenerator;

    /**
     * Constructor method to initialize a new Controllers.MasterSystem instance in case
     * deserializing from the file fails. A newly created Controllers.MasterSystem adds an
     * organizer with username: admin and password: admin to the conference as
     * users can't create organizer accounts themselves.
     */
    public MasterSystem() {
        this.ui = new TextUserInterface();
        this.attendeeManager = new AttendeeManager();
        this.organizerManager = new OrganizerManager();
        this.speakerManager = new SpeakerManager();
        this.eventManager = new EventManager();
        this.roomManager = new RoomManager();
        this.conversationManager = new ConversationManager();
        this.messageManager = new MessageManager();
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager);

        this.userMessageController = new UserMessageController(attendeeManager, organizerManager,
                speakerManager, eventManager, conversationManager, messageManager);
        this.userEventController = new UserEventController(attendeeManager, organizerManager,
                speakerManager, eventManager, roomManager);
        this.programGenerator = new ProgramGenerator();

        /* Create an organizer account when a new Controllers.MasterSystem object is created
        * to allow for the conference to have at least one organizer*/
        accountHandler.signup("admin", "admin", "organizer");
    }

    /**
     * A method that is responsible for the flow of the program by taking user input,
     * using controllers to execute actions and displaying the result using the UI.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String currentUsername = null;
        String currentAccountType = null;
        boolean loggedIn = false;

        while(!loggedIn) {

            String tempUsername;
            String tempPassword;
            String tempAccountType;

            ui.landingmenu();
            String landingOption = scanner.nextLine();

            switch (landingOption) {
                case "0":
                    programGenerator.saveToFile(this, "conference_system");
                    return;
                case "1":
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    tempAccountType = accountHandler.login(tempUsername, tempPassword);

                    if (tempAccountType != null) {
                        currentUsername = tempUsername;
                        currentAccountType = tempAccountType;
                        loggedIn = true;
                    } else {
                        ui.showPrompt("LF");
                    }
                    break;

                case "2":
                    ui.signupmenu();
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    if (accountHandler.signup(tempUsername, tempPassword, "attendee")) {
                        ui.showPrompt("UC");
                    } else {
                        ui.showPrompt("SF");
                    }
                    break;

                default:
                    ui.showError("INO");
            }

            while(loggedIn) {

                switch(currentAccountType) {
                    case "attendee":
                        ui.attendeemenu(currentUsername);
                        break;
                    case "organizer":
                        ui.organizermenu(currentUsername);
                        break;
                    case "speaker":
                        ui.speakermenu(currentUsername);
                        break;
                }

                String option = scanner.nextLine();
                if (option.equals("0")) {
                    loggedIn = false;
                    currentUsername = null;
                    programGenerator.saveToFile(this, "conference_system");
                } else {
                    userCommandHandler(option, currentUsername, currentAccountType);
                }
            }
        }

    }

    /**
     * Private helper method that takes in the account type of the user and and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     *
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     * @param userType: account type of the currently logged in user ("attendee", "organizer", "speaker")
     */
    private void userCommandHandler(String option, String username, String userType) {

        Scanner scanner = new Scanner(System.in);
        switch(userType) {
            case "attendee":
                switch(option) {
                    case "1":
                        Hashtable<String, ArrayList<String>> eventsNotSignedUpFor = userEventController.seeAttendableEvents(username);
                        for (String event : eventsNotSignedUpFor.keySet()) {
                            ui.present(event);
                            for (String eventInfo : eventsNotSignedUpFor.get(event))
                                ui.present(eventInfo);
                        }
                        ui.present("\n\n");
                        break;
                    case "2":
                        ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
                        String eventName = scanner.nextLine();
                        String err = userEventController.enrolUserInEvent(username, eventName);
                        if (!err.equals("YES")) {
                            ui.showError(err);
                        } else {
                            ui.present("Successful");
                        }
                        break;
                    case "3":
                        ui.present("Please enter the event's name");
                        String eventname = scanner.nextLine();
                        userEventController.cancelSeatForUser(username, eventname);
                        ui.present("You are no longer attending " + eventname);
                        break;
                    case "4":
                        for (String event : attendeeManager.getEventsAttending(username))
                            ui.present("Event Title: " + event + "\nTime: " + eventManager.getEventTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                        break;
                    case "5":
                        ui.present("Please enter attendee ID");
                        String attendeeID = scanner.nextLine();
                        ui.present("Please enter the message that you want to send");
                        String content = scanner.nextLine();
                        boolean error = userMessageController.attendeeSendMessage(username, attendeeID, content, "attendee");
                        if (error) {
                            ui.present("Successful");
                        } else {
                            ui.present("Something went wrong");
                        }
                        break;
                    case "6":
                        ui.present("Please enter the speaker's username");
                        String speakerName = scanner.nextLine();
                        ui.present("Please enter the message that you want to send");
                        String message = scanner.nextLine();
                        userMessageController.organizerSendMessageToSingle(username, speakerName, message, "speaker");
                        boolean error1 = userMessageController.attendeeSendMessage(username, speakerName, message, "speaker");
                        if (error1) {
                            ui.present("Successful");
                        } else {
                            ui.present("Something went wrong");
                        }
                        break;
                    case "7":
                        Integer i = 1;
                        for (String conversationId : attendeeManager.getConversations(username)) {
                            ArrayList<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                            StringBuilder recipients = new StringBuilder();
                            ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                            for (String recipient : recipientsOfConversation) {
                                recipients.append(recipient);
                                recipients.append(", ");
                            }
                            ui.present("Recipients: " + recipients);
                            i += 1;
                        }
                        if (attendeeManager.getConversations(username).isEmpty()) {
                            ui.present("You have no conversations");
                            break;
                        }
                        ui.present("Choose a Entities.Conversation Number");
                        String conversationNumber = scanner.nextLine();
                        String conversationIdFinal = attendeeManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                        ArrayList<String> messagesInThisConversation = userMessageController.orderedMessagesInConvo(conversationIdFinal);
                        for (String s : messagesInThisConversation) {
                            ui.present(s);
                        }
                        ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                        String reply = scanner.nextLine();
                        if (!reply.equals("r")) {
                            break;
                        }
                        ui.present("Please enter the message you want to send");
                        String contents = scanner.nextLine();
                        userMessageController.reply(username, conversationIdFinal, contents);
                        break;
                    case "8":
                        ui.present("Please enter the name of the attendee to be added");
                        String friendName = scanner.nextLine();
                        if (!attendeeManager.isAttendee(friendName)) {
                            ui.showError("UDE");
                            break;
                        }
                        String errorCode = attendeeManager.aAddContactB(username, friendName);
                        if(errorCode.equals("No"))
                            ui.present("Attendee "+friendName+" already exist in the contact list");
                        else
                            ui.present("Success!");
                        break;
                    default: {
                        ui.showError("INO");
                    }
                }
                break;
            case "organizer":
                if(organizerManager.isOrganizer(username)) {
                    switch (option) {
                        case "1": {
                            for (String attendee : attendeeManager.getAllAttendeeIds()){
                                ui.present(attendee);
                            }
                            break;
                        }
                        case "2": {
                            for (String organizer: organizerManager.getAllOrganizerIds()) {
                                ui.present(organizer);
                            }
                            break;
                        }
                        case "3": {
                            for(String speaker: speakerManager.getAllSpeakerIds()){
                                ui.present(speaker);
                            }
                            break;
                        }
                        case "4": {
                            ui.present("Please enter the speaker's username");
                            String speakerName = scanner.nextLine();
                            ui.present("Please enter the time");
                            String time = scanner.nextLine();
                            if(!speakerManager.isSpeaker(speakerName)){
                                ui.present("Not a speaker");
                                break;
                            }
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
                            if(!allowedTimes.contains(time)){
                                ui.present("Please enter an allowed time");
                                break;
                            }
                            boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
                            if(free){
                                ui.present("No, the speaker doesn't have an event at " + time);
                            }
                            else{
                                ui.present("Yes, the speaker is talking at an event at " + time);
                            }
                            break;
                        }
                        case "5": {
                            ui.present("Please enter the new organizer's username");
                            String organizerUsername = scanner.nextLine();
                            ui.present("Please enter the password for this new organizer");
                            String organizerPassword = scanner.nextLine();
                            boolean err = accountHandler.signup(organizerUsername, organizerPassword, "organizer");
                            if(err){
                                ui.present("Successful");
                            }
                            else{
                                ui.present("The username already exists");
                            }
                            break;
                        }
                        case "6":{
                            ui.present("Please enter new speaker's username");
                            String speakerUsername = scanner.nextLine();
                            ui.present("Please enter password for this speaker");
                            String speakerPassword = scanner.nextLine();
                            if(accountHandler.signup(speakerUsername, speakerPassword, "speaker")){
                                ui.showPrompt("UC");
                            }
                            else {
                                ui.showPrompt("SF");
                            }
                            break;
                        }
                        case "7": {
                            ui.present("Please enter roomID:");
                            String roomID = scanner.nextLine();
                            ui.present("Please enter room capacity");
                            int capacity = scanner.nextInt();
                            String err = userEventController.organizerAddNewRoom(username, roomID, capacity);
                            if (!err.equals("YES")) {
                                ui.showError(err);
                            } else {
                                ui.present("Successful");
                            }
                            break;
                        }
                        case "8": {
                            ui.present("Please enter event name");
                            String eventName = scanner.nextLine();
                            ui.present("Please enter event time");
                            String eventTime = scanner.nextLine();
                            ui.present("Please enter the speaker's username");
                            String speakerName = scanner.nextLine();
                            String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
                            if (!err.equals("YES"))
                                ui.showError(err);
                            else {
                                ui.present("Successful");
                            }
                            break;
                        }
                        case "9": {
                            ui.present("Please enter the event name.");
                            String eventName = scanner.nextLine();
                            ui.present("Please enter new speaker's username");
                            String speakerName = scanner.nextLine();
                            if(!eventManager.isEvent(eventName)){
                                ui.showError("EDE");
                                break;
                            }
                            if(!speakerManager.isSpeaker(speakerName)){
                                ui.showError("SDE");
                                break;
                            }
                            String eventTime = eventManager.getEventTime(eventName);
                            userEventController.removeCreatedEvent(username, eventName);
                            String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
                            if (!err.equals("YES"))
                                ui.showError(err);
                            else {
                                ui.present("Successful");
                            }
                            break;
                        }
                        case "10": {
                            ui.present("Please enter the event name");
                            String eventName = scanner.nextLine();
                            ui.present("Please enter a new time for the event");
                            String eventTime = scanner.nextLine();
                            String speakerName = eventManager.getSpeakerEvent(eventName);
                            userEventController.removeCreatedEvent(username, eventName);
                            if(speakerManager.isSpeaker(speakerName)) {
                                String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
                                if(err.equals("YES")) {
                                    ui.present("Successful");
                                }
                                else{
                                    ui.showError(err);
                                }
                            }
                            else{
                                ui.showError("EDE");
                            }
                            break;
                        }
                        case "11": {
                            ArrayList<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
                            for (String event : eventsNotSignedUpFor)
                                    ui.present("Event Title: " + event + "\nTime: " + eventManager.getEventTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                            break;
                        }
                        case "12": {
                            ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
                            String eventName = scanner.nextLine();
                            String err = userEventController.enrolUserInEvent(username, eventName);
                            if(!err.equals("YES")){
                                ui.showError(err);
                            }
                            else{
                                ui.present("Successful");
                            }
                            break;
                        }
                        case "13": {
                            ui.present("Please enter the event's name");
                            String eventName = scanner.nextLine();
                            userEventController.cancelSeatForUser(username, eventName);
                            ui.present("You are no longer attending " + eventName);
                            break;
                        }
                        case "14": {
                            for (String event: organizerManager.getEventsAttending(username))
                                ui.present("Event Title: " + event + "\nTime: " + eventManager.getEventTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                            break;
                        }
                        case "15": {
                            ui.present("Please enter attendee ID");
                            String attendeeID = scanner.nextLine();
                            ui.present("Please enter the message that you want to send");
                            String content = scanner.nextLine();
                            boolean err = userMessageController.organizerSendMessageToSingle(username, attendeeID, content, "attendee");
                            if(err){
                                ui.present("Successful");
                            }
                            else{
                                ui.present("Something went wrong");
                            }
                            break;
                        }
                        case "16": {
                            ui.present("Please enter the message that you want to send");
                            String content = scanner.nextLine();
                            userMessageController.organizerSendMessageToAll(username, content, "attendee");
                            break;
                        }
                        case "17": {
                            ui.present("Please enter the speaker's username");
                            String speakerName = scanner.nextLine();
                            ui.present("Please enter the message that you want to send");
                            String content = scanner.nextLine();
                            userMessageController.organizerSendMessageToSingle(username, speakerName, content, "speaker");
                            break;
                        }
                        case "18": {
                            ui.present("Please enter the message that you want to send");
                            String content = scanner.nextLine();
                            userMessageController.organizerSendMessageToAll(username, content, "speaker");
                            break;
                        }
                        case "19": {
                            Integer i = 1;
                            for(String conversationId: organizerManager.getConversations(username)) {
                                ArrayList<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                                StringBuilder recipients = new StringBuilder();
                                ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                                for (String recipient: recipientsOfConversation){
                                    recipients.append(recipient);
                                    recipients.append(", ");
                                }
                                ui.present("Recipients: " + recipients);
                                i += 1;
                            }
                            if(organizerManager.getConversations(username).isEmpty()){
                                ui.present("You have no conversations");
                                break;
                            }
                            ui.present("Choose a Entities.Conversation Number");
                            String conversationNumber = scanner.nextLine();
                            String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                            ArrayList<String> messagesInThisConversation = userMessageController.orderedMessagesInConvo(conversationIdFinal);
                            for (String s : messagesInThisConversation) {
                                ui.present(s);
                            }
                            ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                            String reply = scanner.nextLine();
                            if(!reply.equals("r")){
                                break;
                            }
                            ui.present("Please enter the message you want to send");
                            String content = scanner.nextLine();
                            userMessageController.reply(username, conversationIdFinal, content);
                            break;
                        }
                        case "20":{
                            ui.present("Please enter the event name");
                            String eventName = scanner.nextLine();
                            ui.present("Please enter the message that you want to send");
                            String message = scanner.nextLine();
                            if(!eventManager.isEvent(eventName)){
                                ui.showError("EDE");
                                break;
                            }
                            boolean messageByEvent = userMessageController.organizerMessageByEvent(username, eventName, message);
                            if(messageByEvent){
                                ui.present("Sent");
                                break;
                            }
                            ui.present("Something went wrong");
                            break;
                        }
                        default: {
                            ui.showError("INO");
                        }
                    }
                }
                break;
            case "speaker":
                switch(option) {
                    case "1":
                        ui.present(userEventController.seeListOfEventsForSpeaker(username).toString());
                        break;
                    case "2":
                        ui.eventnameprompt();
                        String eventName = scanner.nextLine();
                        ui.messageprompt();
                        String content = scanner.nextLine();
                        userMessageController.speakerMessageByTalk(username, eventName, content);
                        ui.showPrompt("MS");
                        break;
                    case "3":
                        ArrayList<String> listOfTalkNames = userEventController.seeAllEventNamesForSpeaker(username);
                        ui.messageprompt();
                        String content1 = scanner.nextLine();
                        userMessageController.speakerMessageByMultiTalks(username, listOfTalkNames, content1);
                        ui.showPrompt("MMS");
                        break;
                    case "4":
                        ui.present("Please enter the username of the Attendee you wish to message:");
                        String attendeeUsername = scanner.nextLine();
                        ui.messageprompt();
                        String message = scanner.nextLine();
                        ArrayList<String> listOfTalkNames1 = userEventController.seeAllEventNamesForSpeaker(username);
                        boolean err = userMessageController.speakerMessageAttendee(username, listOfTalkNames1, attendeeUsername, message);
                        if(err){
                            ui.present("Successful");
                        }
                        else{
                            ui.present("Something went wrong");
                        }
                        break;
                    case "5":
                        Integer i = 1;
                        for(String conversationId: speakerManager.getConversations(username)) {
                            ArrayList<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                            StringBuilder recipients = new StringBuilder();
                            ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                            for (String recipient: recipientsOfConversation){
                                recipients.append(recipient);
                                recipients.append(", ");
                            }
                            ui.present("Recipients: " + recipients);
                            i += 1;
                        }
                        if(speakerManager.getConversations(username).isEmpty()){
                            ui.present("You have no conversations");
                            break;
                        }
                        ui.present("Choose a Conversation Number");
                        String conversationNumber = scanner.nextLine();
                        String conversationIdFinal = speakerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                        ArrayList<String> messagesInThisConversation = userMessageController.orderedMessagesInConvo(conversationIdFinal);
                        for (String s : messagesInThisConversation) {
                            ui.present(s);
                        }
                        ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                        String reply = scanner.nextLine();
                        if(!reply.equals("r")){
                            break;
                        }
                        ui.present("Please enter the message you want to send");
                        String contents = scanner.nextLine();
                        userMessageController.reply(username, conversationIdFinal, contents);
                        break;
                    default: {
                        ui.showError("INO");
                    }
                }
                break;
        }
    }
}