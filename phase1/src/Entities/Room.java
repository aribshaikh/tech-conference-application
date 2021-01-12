package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class stores the information related to rooms. This is Serializable class.
 * - capacity of the room
 * - room ID
 * - times at which the room is used for an event
 * @author Ashwin Karthikeyan
 */
public class Room implements Serializable {

    private final String roomId;
    private final int capacity;
    private ArrayList<String> occupiedTimes;

    /**
     * Parameterized Constructor that creates an object.
     * @param roomId (param_type: String)
     * @param capacity (param_type: int)
     * @param occupiedTimes (param_type: ArrayList<String>)
     */
    public Room(String roomId, int capacity, ArrayList<String> occupiedTimes){

        this.roomId = roomId;
        this.capacity = capacity;
        this.occupiedTimes = occupiedTimes;

    }

    /**
     * Returns the capacity of this room
     * @return : room capacity (ret_type int)
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the room ID of this room
     * @return : String representing room ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Return the times at which this room is used for an event.
     * @return : ArrayList<String> of all times at which an event takes place in this room
     */
    public ArrayList<String> getOccupiedTimes() {
        return occupiedTimes;
    }

    /**
     * Set the values for times at which this room has a scheduled event
     * @param occupiedTimes: list of times at which the room has an event (param_type: ArrayList<String>)
     */
    public void setOccupiedTimes(ArrayList<String> occupiedTimes) {
        this.occupiedTimes = occupiedTimes;
    }

}
