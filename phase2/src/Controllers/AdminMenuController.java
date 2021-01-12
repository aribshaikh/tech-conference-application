package Controllers;

import NewUI.AdminPresenterTextUI;
import NewUI.ErrorHandler;
import UseCases.*;
import java.util.*;

/**
 * This class is responsible for taking input and implementing all logic/actions related to an admin.
 * The following manipulations include:
 * - Admin ability to sign out
 * - Admin ability to delete empty events
 * - Admin ability to report another user
 * @author Khoa Pham, Vladimir Caterov
 * @see AttendeeManager
 * @see OrganizerManager
 * @see SpeakerManager
 * @see AdminManager
 * @see EventManager
 * @see AdminPresenterTextUI
 * @see MessengerMenuController
 */

public class AdminMenuController implements CommandHandler{

    private final EventManager eventManager;
    private final AdminPresenterTextUI adminPresenterTextUI;
    private final ErrorHandler errorHandler;
    private final MessengerMenuController messengerMenuController;
    private final AccountHandler accountHandler;

    /**
     * A constructor for creating an AdminMenuController.
     */
    public AdminMenuController(EventManager eventManager, AdminPresenterTextUI adminPresenterTextUI,
                               MessengerMenuController messengerMenuController, AccountHandler accountHandler, ErrorHandler errorHandler){

        this.eventManager = eventManager;
        this.adminPresenterTextUI = adminPresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.accountHandler = accountHandler;
        this.errorHandler = errorHandler;
    }
    /**
     * This method allows admins to select between options from 0, 1, or 2 where 0 signs out the user,
     * 1 deletes all empty events, and 2 allows admins to report a given user by their username.
     * The following manipulations include:
     * @param username: the username of the admin signed in
     * @return boolean: True if the admin is remaining logged in, false if the admin wants to sign out
     */
    public boolean handleCommand(String username){
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch(option) {
            case "0":
                return false;
            case "1":
                deleteEventsWithoutAttendee();
                adminPresenterTextUI.emptyEventsDeleted();
                return true;
            case "2":
                adminPresenterTextUI.promptForUsername();
                String user = scanner.nextLine();
                String userType = accountHandler.getAccountType(user);
                if (messengerMenuController.adminSendMessage(username, user, "You Have Been Reported. Do not respond to this message", userType)){
                    errorHandler.success();
                    return true;
                }
                else {
                    errorHandler.showError("UDE");
                    return true;
                }
        }
        return true;
    }


    /**
     * Allow an Admin to delete events with no Attendees
     * call eventManager to perform!
     * This option should only be accessible from inside the Admin login!
     * Only one Admin can perform this task
     * @author Khoa Pham
     */
    public void deleteEventsWithoutAttendee() {
        List<String> allEmptyEvents = eventManager.getEmptyEvents();
        for (String event : allEmptyEvents) {
            eventManager.removeEvent(event);
        }
    }

}
