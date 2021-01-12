package Controllers;


import NewUI.AttendeePresenterTextUI;
import NewUI.ErrorHandler;
import UseCases.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible for taking input and implementing all logic/actions related to an admin.
 * The following manipulations include:
 *  - Attendee ability to sign out
 *  - Displays the available events to sign up for
 *  - Allows the attendee to sign up for an event
 *  - Allows an attendee to cancel their spot in an event
 *  - Allows an attendee to see a schedule they are signed up for
 *  - Allows an attendee to message another attendee
 *  - Allows an attendee to message a speaker of a given talk
 *  - Allows an attendee to view all their conversations
 *  - Adds another attendee to their friend list
 *  - Allows an attendee to enter the polling menu
 * @author Ashwin, Vladimir Caterov
 * @see AdminManager
 * @see AttendeeManager
 * @see OrganizerManager
 * @see SpeakerManager
 * @see EventManager
 * @see AccountHandler
 * @see RoomManager
 * @see UserEventController
 * @see MessengerMenuController
 * @see ConversationMenuController
 * @see PollController
 * @see AttendeePresenterTextUI
 * @see ErrorHandler
 */
public class AttendeeMenuController implements CommandHandler{

    private AttendeeManager attendeeManager;
    private UserEventController userEventController;
    private AttendeePresenterTextUI attendeePresenterTextUI;
    private MessengerMenuController messengerMenuController;
    private ConversationManager conversationManager;
    private ConversationMenuController conversationMenuController;
    private ErrorHandler errorHandler;
    private PollController pollController;

    public AttendeeMenuController(AttendeeManager attendeeManager, UserEventController userEventController,
                                  AttendeePresenterTextUI attendeePresenterTextUI, MessengerMenuController messengerMenuController,
                                  ConversationManager conversationManager, ConversationMenuController conversationMenuController,
                                  ErrorHandler errorHandler, PollController pollController){

        this.attendeeManager = attendeeManager;

        this.userEventController = userEventController;
        this.attendeePresenterTextUI = attendeePresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;
        this.errorHandler = errorHandler;
        this.pollController = pollController;
    }




    /**
     * This method allows attendees to select between several options.
     * The following manipulations include:
     * 0. Signs the user out
     * 1. Displays the available events to sign up for
     * 2. Allows the attendee to sign up for an event
     * 3. Allows an attendee to cancel their spot in an event
     * 4. Allows an attendee to see a schedule they are signed up for
     * 5. Allows an attendee to message another attendee
     * 6. Allows an attendee to message a speaker of a given talk
     * 7. Allows an attendee to view all their conversations
     * 8. Adds another attendee to their friend list
     * 9. Allows an attendee to enter the polling menu
     * @param username: the username of the admin signed in
     * @return boolean: True if the admin is remaining logged in, false if the admin wants to sign out
     */
    public boolean handleCommand(String username) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch(option) {
            case "0":
                return false;
            case "1": {
                Map<String, List<String>> eventsToSignUpWithInfo = userEventController.seeAttendableEvents(username);
                if (eventsToSignUpWithInfo.isEmpty()) {
                    attendeePresenterTextUI.present("No events to sign up for");
                } else {
                    for (String event : eventsToSignUpWithInfo.keySet()) {
                        attendeePresenterTextUI.present(event);
                        for (String info : eventsToSignUpWithInfo.get(event))
                            attendeePresenterTextUI.present(info);
                    }
                }

                attendeePresenterTextUI.present("\n\n");
                return true;
            }
            case "2": {
                attendeePresenterTextUI.promptEventNameToAdd();
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    errorHandler.showError(err);
                } else {
                    errorHandler.success();
                }
                return true;
            }
            case "3": {
                attendeePresenterTextUI.promptEventTitleCancel();
                String eventname = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventname);
                attendeePresenterTextUI.promptNoLongerAttending(eventname);
                return true;
            }
            case "4": {
                List<String> eventsAttending = attendeeManager.getEventsAttending(username);
                attendeePresenterTextUI.presentEventsAttending(eventsAttending);
            }
                return true;
            case "5": {
                attendeePresenterTextUI.promptAttendeeID();
                String attendeeID = scanner.nextLine();
                attendeePresenterTextUI.promptMessageToSend();
                String content = scanner.nextLine();
                boolean error = messengerMenuController.attendeeSendMessage(username, attendeeID, content, "attendee");
                if (error) {
                    attendeePresenterTextUI.success();
                } else {
                    attendeePresenterTextUI.fail();
                }
                return true;
            }
            case "6": {
                attendeePresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                attendeePresenterTextUI.promptForMessageToSend();
                String message = scanner.nextLine();
                boolean error1 = messengerMenuController.attendeeSendMessage(username, speakerName, message, "speaker");
                if (error1) {
                    attendeePresenterTextUI.success();
                } else {
                    attendeePresenterTextUI.fail();
                }
                return true;
            }
            case "7": {
                int i = 1;
                for (String conversationId : attendeeManager.getConversations(username)) {
                    List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                    StringBuilder recipients = new StringBuilder();
                    String k = Integer.toString(i);
                    attendeePresenterTextUI.convoNumUniqueId(k, conversationId);
                    for (String recipient : recipientsOfConversation) {
                        recipients.append(recipient);
                        recipients.append(", ");
                    }
                    attendeePresenterTextUI.presentRecipients(recipients);
                    i += 1;
                }
                if (attendeeManager.getConversations(username).isEmpty()) {
                    attendeePresenterTextUI.noConvo();
                    return true;
                }
                attendeePresenterTextUI.promptConvoNumber();
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = attendeeManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
                attendeePresenterTextUI.presentMessageInConvo(messagesInThisConversation);
                attendeePresenterTextUI.promptToReply();
                String reply = scanner.nextLine();
                if (!reply.equals("r")) {
                    return true;
                }
                attendeePresenterTextUI.promptMessageToSend();
                String contents = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, contents);
                break;
            }
            case "8": {
                attendeePresenterTextUI.promptAttendeeUsernameAdded();
                String friendName = scanner.nextLine();
                if (!attendeeManager.isAttendee(friendName)) {
                    errorHandler.showError("UDE");
                    return true;
                }
                String errorCode = attendeeManager.aAddContactB(username, friendName);
                if (errorCode.equals("No"))
                    attendeePresenterTextUI.friendContactAlreadyExist(friendName);
                else
                    attendeePresenterTextUI.success();
                return true;
            }
            case "9":{
                pollController.runPollFunctionality(username);
            }
            default: {
                attendeePresenterTextUI.showError("INO");
            }
        }
        return true;
    }
}
