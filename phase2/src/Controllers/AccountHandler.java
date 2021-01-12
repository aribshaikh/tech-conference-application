package Controllers;

import UseCases.AdminManager;
import UseCases.AttendeeManager;
import UseCases.OrganizerManager;
import UseCases.SpeakerManager;


/**
 * This class is responsible for creating accounts of all user types
 * with a unique username and password and allowing a user to sign in
 * to the conference using their username and password. This class
 * also returns the account type of a given user.
 * @author Akshat Ayush, Khoa Pham
 * @see AttendeeManager
 * @see OrganizerManager
 * @see SpeakerManager
 * @see AdminManager
 */
public class AccountHandler {
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final AdminManager adminManager;

    /**
     * A constructor to create an object of Controllers.AccountHandler
     *
     * @param attendeeManager: an object of UseCases.AttendeeManager class
     * @param organizerManager: an object of UseCases.OrganizerManager class
     * @param speakerManager: an object of UseCases.SpeakerManager class
     * @param adminManager: an object of UseCases.AdminManager class
     */
    public AccountHandler(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                          SpeakerManager speakerManager, AdminManager adminManager) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
    }

    /**
     *Create a user of a specific account type if a user with the
     *same username does not already exist.
     *Implemented Factory Design Pattern
     *
     * @param username: username inputted by the user
     * @param password: password inputted by the user
     * @param accountType: account type of the user
     * @return boolean representing successful signup of a new account
     */
    public boolean signup(String username, String password, String accountType) {
        switch(accountType) {
            case "organizer":
                if(attendeeManager.isAttendee(username) || speakerManager.isSpeaker(username) || adminManager.isAdmin(username))
                    return false;
                return organizerManager.createOrganizer(username, password);
            case "attendee":
                if(organizerManager.isOrganizer(username) || speakerManager.isSpeaker(username)|| adminManager.isAdmin(username))
                    return false;
                return attendeeManager.createAttendee(username, password);
            case "speaker":
                if(organizerManager.isOrganizer(username) || attendeeManager.isAttendee(username)|| adminManager.isAdmin(username))
                    return false;
                return speakerManager.createSpeaker(username, password);
            default:
                return false;
        }
    }

    /**
     *Login the user with the given username and password if a user with
     *the given username exists and return the account type of the user
     *if login is successful.
     * @author Akshat Ayush, Khoa Pham
     * @param username: username inputted by the user
     * @param password: password inputted by the user
     * @return String representing the account type of the user logging in.
     * "attendee", "organizer", "speaker", "admin" for the type of user,
     * null if the user with the given username does not exist
     */
    public String login(String username, String password) {
        if(attendeeManager.checkPassword(username, password))
            return "attendee";
        else if(organizerManager.checkPassword(username, password))
            return "organizer";
        else if(speakerManager.checkPassword(username, password))
            return "speaker";
        else if(adminManager.checkPassword(username, password))
            return "admin";
        else
            return null;
    }

    /**
     * Return the account type of the provided username
     * @author Vladimir
     * @param username: username inputted by the user
     * @return String representing the account type of the user that may or may not be logged in.
     * "attendee", "organizer", "speaker", "admin" for the type of user,
     * Returns the string "DNE" if the user with the given username does not exist
     */
    public String getAccountType(String username) {
        if(attendeeManager.isAttendee(username))
            return "attendee";
        else if(organizerManager.isOrganizer(username))
            return "organizer";
        else if(speakerManager.isSpeaker(username))
            return "speaker";
        else if(adminManager.isAdmin(username))
            return "admin";
        else
            return "DNE";
    }

}