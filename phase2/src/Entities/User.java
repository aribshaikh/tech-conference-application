package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * this class stores all info relating to an Entities.User
 * and provides getters to extract those info
 * Entities.User is an abstract class -- no instantiation is allowed
 * Entities.User serves as a parent for a few other classes
 * @author Khoa Pham
 * A few notes to consider:
 *    * disallows changes in username, password for now
 *    * all IDs are stored as strings!
 *    * getters and setters for username, password?
 *    * can attendee remove someone from their contacts list?
 *    * can attendees delete their account?
 */
public abstract class User {
    private final String username; // disallows changes in username for now
    private final String password; // disallows changes in password for now
    private List<String> contacts = new ArrayList<>();
    private List<String> conversations = new ArrayList<>();

    /**
     * a constructor for subclasses to call
     * creating a Entities.User object is DISALLOWED!
     * @param username: the username of this Entities.User
     * @param password: the password of this Entities.User
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * update the list of conversation that this Entities.User participates in
     * @param conversations: the new list of conversations to update to (param_type: List<String>)
     */
    public void setConversations(List<String> conversations) {
        this.conversations = conversations;
    }

    /**
     * return the list of conversations this Entities.User participates in
     * @return List<String> of conversations
     */
    public List<String> getConversations() {
        return conversations;
    }


    /**
     * return the password used by this Entities.User
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the list of contacts this Entities.User has already connected to
     * @return List<String> contacts
     */
    public List<String> getContacts() {
        return contacts;
    }

    /**
     * return the username (userId) of this Entities.User
     * @return String username
     */
    public String getUserId() {
        return username;
    }


    /**
     * update the list of contacts that this Entities.User connects to
     * @param contacts: the new list of conversations to update to (param_type: List<String>)
     */
    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }


}
