package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents information related to an Entities.Organizer. An Entities.Organizer is a Entities.User. This is Serializable class.
 * @author Ashwin Karthikeyan
 * @see User
 */
public class Organizer extends User implements Serializable {

    private ArrayList<String> eventsAttending;

    public Organizer(String username, String password){

        super(username, password);
        eventsAttending = new ArrayList<>();

    }

    /**
     * returns an ArrayList of event titles of events that this Entities.Organizer object is attending
     * @return ArrayList of events that this Entities.Organizer object is attending
     */
    public ArrayList<String> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * Stores a list of event titles that this Entities.Organizer object is attending in this object
     * @param eventsAttending : A list of event titles that this Entities.Organizer object is attending (param_type :ArrayList<String>)
     */
    public void setEventsAttending(ArrayList<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}