package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * this class stores all info relating to an Attendee
 * and provides getters to extract those info
 * Attendee is a subclass of User
 * @author Khoa Pham
 * @see User
 * A few notes to consider:
 *    * disallows changes in username, password for now
 */
public class Attendee extends User {
    private List<String> eventsAttending = new ArrayList<>();

    /**
     * a constructor to create an Attendee object
     * @param username: the username of this Attendee
     * @param password: the password of this Attendee
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

    /**
     * return the list of all events that this Attendee is participating
     * @return List<String> participating events
     */
    public List<String> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * update the list of all events that this Attendee is participating
     * @param eventsAttending: the new list of all participating events (param_type: List<String>)
     */
    public void setEventsAttending(List<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}

//Map<String, List<String>>
//    credentials: <username, password>
//    conversations: <>
//    eventsAttending: <>
//    contacts: <>
