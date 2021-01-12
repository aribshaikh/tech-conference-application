package NewUI;

import UseCases.*;

/**
 * The class containing all the Admin specific prompts and text to display
 * with regards to the actions that Admin can perform
 */
public class AdminPresenterTextUI extends TextUI {
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public AdminPresenterTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                                AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                                EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }

    /**
     * Displays the main menu for the admin with the given username
     * Option 0: Signs the admin out
     * Option 1: Allows the admin to delete all events that are empty (no-attendees)
     * Option 2: Allows the admin to report a user provided they enter a correct username
     * @param username the username of the admin user
     */
    public void adminmenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("1: Delete empty events");
        System.out.println("2: Report User");
        System.out.println("\n0: Sign-out");
    }

    /**
     * Displays a message to inform that empty events have been deleted
     */
    public void emptyEventsDeleted() {
        System.out.println("All events without attendees were deleted");
    }

    /**
     * Prompts for the id of the message to be deleted
     */
    public void messageID() {
        System.out.println("Please enter the message id you want to delete");
    }

    /**
     * Displays a message to inform that the message with the given id was deleted
     * @param messageID the id of the deleted message
     */
    public void messageDeleted(String messageID) {
        System.out.println("message with id " + messageID + " was deleted");
    }

    /**
     * Prompts for the username of the user to report
     */
    public void promptForUsername(){
        System.out.println("Please enter the username of the user you wish to report");
    }
}
