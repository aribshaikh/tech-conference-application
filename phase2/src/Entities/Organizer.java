package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents information related to an Entities.Organizer. An Entities.Organizer is a Entities.User. This is Serializable class.
 * @author Ashwin Karthikeyan
 * @see User
 */
public class Organizer extends User {

    private List<String> eventsAttending;

    public Organizer(String username, String password){

        super(username, password);
        eventsAttending = new ArrayList<>();

    }

    /**
     * returns an List of event titles of events that this Entities.Organizer object is attending
     * @return List of events that this Entities.Organizer object is attending
     */
    public List<String> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * Stores a list of event titles that this Entities.Organizer object is attending in this object
     * @param eventsAttending : A list of event titles that this Entities.Organizer object is attending (param_type :List<String>)
     */
    public void setEventsAttending(List<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}