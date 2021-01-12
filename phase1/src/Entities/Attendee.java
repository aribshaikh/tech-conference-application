package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class stores all info relating to an Entities.Attendee
 * and provides getters to extract those info
 * Entities.Attendee is a subclass of Entities.User
 * @author Khoa Pham
 * @see User
 * A few notes to consider:
 *    * disallows changes in username, password for now
 */
public class Attendee extends User implements Serializable {
    private ArrayList<String> eventsAttending = new ArrayList<>();

    /**
     * a constructor to create an Entities.Attendee object
     *
     * @param username: the username of this Entities.Attendee
     * @param password: the password of this Entities.Attendee
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

    /**
     * return the list of all events that this Entities.Attendee is participating
     * @return ArrayList<String> participating events
     */
    public ArrayList<String> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * update the list of all events that this Entities.Attendee is participating
     * @param eventsAttending: the new list of all participating events (param_type: ArrayList<String>)
     */
    public void setEventsAttending(ArrayList<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}
