package NewUI;

import UseCases.*;

import java.util.List;
import java.util.Map;

public class AttendeePresenterTextUI extends TextUI{

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public AttendeePresenterTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                                   AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                                   EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager,
                eventManager, roomManager);
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.conversationManager = conversationManager;
        this.messageManager = messageManager;

    }



    /**
     * Print out a set of functions that an attendee is able to do
     * @author Juan Yi Loke
     * @param username: the username of the user that is being prompted
     */
    public void attendeemenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("1: Available events to sign up for");
        System.out.println("2: Sign up for an event");
        System.out.println("3: Cancel spot in an event");
        System.out.println("4: See schedule of events signed up for");

        System.out.println("\nMESSAGING FUNCTIONS:");
        System.out.println("5: Send message to an attendee");
        System.out.println("6: Send message to a speaker of a talk");
        System.out.println("7: View all conversations");
        System.out.println("8: Add another attendee to friend list");

        System.out.println("\nPOLLING OPTIONS: ");
        System.out.println("9. Enter Polling menu");
        System.out.println("\n0: Sign-out");
    }

//    public void presentEventsNotSignedUpFor(Map<String, List<String>> eventsNotSignedUpFor){
//        for (String event: eventsNotSignedUpFor.keySet()) {
//            System.out.println(event);
//            for (String eventInfo: eventsNotSignedUpFor.get(event))
//                System.out.println(eventInfo);
//        }
//    }

    public void promptForSpeakerUsername(){
        System.out.println("Please enter the speaker's username");
    }

    public void promptForMessageToSend(){
        System.out.println("Please enter the message that you want to send");
    }

    public void promptEventNameToAdd() {
        System.out.println("Please enter the title of the event you want to attend " +
                "(exactly as it appears on the list of titles displayed)");
    }

    public void promptEventTitleCancel(){
        System.out.println("Please enter the event title you wish to cancel reservation");
    }

    public void promptNoLongerAttending(String event){
        System.out.println("You are no longer attending " + event);
    }

    public void presentEventsAttending(List<String> eventsAttending){
        for (String event: eventsAttending){
            System.out.println("Event Title:" + event + "\nTime:" +
                    eventManager.getStartTime(event) + "\nRoom: " +
                    eventManager.getRoomNumber(event) + "\nSpeaker: " +
                    eventManager.getSpeakerEvent(event) + "\nDuration: " +
                    eventManager.getDuration(event));
        }
    }

    public void promptAttendeeID(){
        System.out.println("Please enter attendee ID");
    }

    public void promptMessageToSend(){
        System.out.println("Please enter the message that you want to send");
    }

    public void convoNumUniqueId(String i, String conversationId){
        System.out.println("Conversation Number " + i + "\n" + "Uniqueness Identifier: " + conversationId);
    }

    public void presentRecipients(StringBuilder recipients){
        System.out.println("Recipients: " + recipients);
    }

    public void noConvo(){
        System.out.println("You have no conversations");
    }

    public void promptConvoNumber(){
        System.out.println("Choose a conversation number");
    }

    public void presentMessageInConvo(List<String> messagesInConvo) {
        for (String s : messagesInConvo) {
            System.out.println(s);
        }
    }

    public void promptAttendeeUsernameAdded(){
        System.out.println("Please enter the name of the attendee to be added");
    }

    public void promptToReply(){
        System.out.println("Enter \"r\" to reply in this conversation." +
                " [Any other input will exit this menu]");
    }

    public void promptMessageToSent(){
        System.out.println("Please enter the name of the attendee to be added");
    }

    public void friendContactAlreadyExist(String friendName){
        System.out.println("Attendee " + friendName +" already exist in the contact list");
    }



}
