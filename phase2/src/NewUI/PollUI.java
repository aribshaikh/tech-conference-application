package NewUI;

import UseCases.*;

import java.util.List;

/**
 * Contains all the presenter methods for the UI commands related to the Polls
 */
public class PollUI extends TextUI {

    public PollUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                       AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                       EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }

    /**
     * Presents the options to the user related to polls
     */
    public void option() {
        System.out.println("Enter the option number of your required action");
        System.out.println("1. View poll information for all polls with an event-polling-passcode");
        System.out.println("2. Create new poll");
    }

    /**
     * Prompts the user to enter the passcode for the event poll
     */
    public void pw() {
        System.out.println("Enter event-polling-passcode");
    }

    /**
     * Presents the formatted poll information to the user
     * @param formattedPollInfo
     */
    public void formalPollInfo(List<String> formattedPollInfo) {
        for (String s : formattedPollInfo)
            System.out.println(s);
    }

    /**
     * Prompts the user to enter a new poll
     */
    public void newPoll() {
        System.out.println("Enter a new Poll id");
    }

    /**
     * Prompts the user to enter a poll question
     */
    public void question() {
        System.out.println("Enter poll question/message");
    }

    /**
     * Prompts the user to enter the number of choices for answers
     */
    public void answers() {
        System.out.println("Enter number of choices for answers");
    }

    /**
     * Promps the user to enter the options
     */
    public void enterOpt() {
        System.out.println("Enter the options, avoid enter key or \\n");
    }

    /**
     * Presents Poll ID already exists message to user
     */
    public void fail() {
        System.out.println("Poll ID already exists for this event-polling-passcode");
    }

    /**
     * Prompts user to enter a valid option
     */
    public void optionWarn() {
        System.out.println("Please enter a valid option");
    }

    /**
     * Prompts the user if they want to vote in a poll
     */
    public void vote() {
        System.out.println("Do you want to vote in a poll? (Enter YES if you want to vote. Enter anything else if you want to exit this menu)");
    }

    /**
     * Prompts the user to enter poll id of poll
     */
    public void yes() {
        System.out.println("Please enter the Poll Id of the poll in which you want to vote (Note: If you can see the result of the poll, you have already voted in the poll)");
    }

    /**
     * Prompts the user to enter the option number of vote choice
     */
    public void choice() {
        System.out.println("Enter the option number that you want to vote for");
    }

    /**
     * Prompts the user to re-enter the menu, in order to view the result
     */
    public void result() {
        System.out.println("To view the result, please re-enter this menu");
    }
}
