package NewUI;

import UseCases.*;

import java.util.List;

/**
 * Contains all the presenter methods related to speakers, which include the following:
 * - Speaker Menu Display
 * - Display of events attending wrt speaker
 * - Prompting speaker to enter attendee ID for messaging
 * - Reply prompts
 */
public class SpeakerPresenterTextUI extends TextUI{
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public SpeakerPresenterTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                                  AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                                  EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }

    /**
     * print out a set of functions that a speaker is able to do
     * @author Juan Yi Loke
     * @param username: the username of the user that is being prompted
     */
    public void speakermenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("1: View list of talks to be given");

        System.out.println("\nMESSAGING FUNCTIONS:");
        System.out.println("2: Message all attendees signed up for a talk");
        System.out.println("3: Message all attendees attending multiple talks");
        System.out.println("4: Message an attendee attending a talk");
        System.out.println("5: View Conversations");

        System.out.println("\nPOLLING OPTIONS: ");
        System.out.println("6. Enter Polling menu");
        System.out.println("\n0: Sign-out");
    }

    /**
     * Displays all the relevant event information for a given event
     * @param event : name of event
     * @param info : information of event
     */
    public void eventDisplay(String event, List<String> info) {
        System.out.println(event);
        for (String i: info)
            System.out.println(i);
    }

    /**
     * Displays the list of events
     * @param eventList : list of events
     */
    public void seeEventList(List<String> eventList){
        for (String e: eventList)
            System.out.println(e);
    }

    /**
     *  Prompting speaker to enter attendee ID for messaging
     */
    public void getAttendeeToMess() {
        System.out.println("Please enter the username of the Attendee you wish to message:");
    }

    /**
     * Prompts speaker to reply to conversation
     */
    public void rep() {
        System.out.println("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
    }

    /**
     * Presents a list of type l
     * @param l : list
     */
    public void list(List<String> l) {
        for (String s : l) {
            System.out.println(s);
        }
    }

}
