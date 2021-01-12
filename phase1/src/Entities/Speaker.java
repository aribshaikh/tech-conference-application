package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores all the information relating to a Entities.Speaker
 * and provides a single getter and setter to extract and replace
 * the information
 * Entities.Speaker is a subclass of Entities.User
 * @author Vladimir Caterov
 * @see User
 */
public class Speaker extends User implements Serializable {
    private ArrayList<HashMap<String, String>> listOfTalks;

    /**
     * A constructor that creates a Entities.Speaker object
     *
     * @param username: the username of this Entities.Speaker
     * @param password: the password of this Entities.Speaker
     */
    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new ArrayList<>();
    }

    /**
     * Returns the list of all talks that this Entities.Speaker is speaking at
     * @return ArrayList<HashMap<String, String>>: Returns an ArrayList of HashMaps containing a reference to an
     * event time as the key and event name as the value
     */
    public ArrayList<HashMap<String, String>> getListOfTalks() {
        return listOfTalks;
    }
    /**
     * Updates the list of all events that this Entities.Speaker is speaking at
     * @param listOfTalks: The new list of all talks(param_type: HashMap<String, String>)
     * @return void
     */
    public void setListOfTalks(ArrayList<HashMap<String, String>> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}