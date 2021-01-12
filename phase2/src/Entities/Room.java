package Entities;

import java.util.List;

/**
 * This class stores the information related to rooms. This is Serializable class.
 * - capacity of the room
 * - room ID
 * - times at which the room is used for an event
 * @author Ashwin Karthikeyan
 */
public class Room {

    private final String roomId;
    private final int capacity;
    private List<String> occupiedTimes;
    private boolean projector;
    private int powerSockets;
    private boolean audioSystem;

    /**
     * Parameterized Constructor that creates an object.
     * @param roomId (param_type: String)
     * @param capacity (param_type: int)
     * @param occupiedTimes (param_type: List<String>)
     * @param hasProjector (param_type: boolean)
     * @param hasAudioSystem (param_type: boolean)
     * @param powerSockets (param_type: int)
     */
    public Room(String roomId, int capacity, List<String> occupiedTimes, boolean hasProjector, boolean hasAudioSystem, int powerSockets){

        this.roomId = roomId;
        this.capacity = capacity;
        this.occupiedTimes = occupiedTimes;
        this.projector = hasProjector;
        this.audioSystem = hasAudioSystem;
        this.powerSockets = powerSockets;

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
     * @return : List<String> of all times at which an event takes place in this room
     */
    public List<String> getOccupiedTimes() {
        return occupiedTimes;
    }

    /**
     * Set the values for times at which this room has a scheduled event
     * @param occupiedTimes: list of times at which the room has an event (param_type: List<String>)
     */
    public void setOccupiedTimes(List<String> occupiedTimes) {
        this.occupiedTimes = occupiedTimes;
    }

    /**
     * Return the true if this room has an Audio System.
     * @return: true if this room has an Audio System
     */
    public boolean hasAudioSystem() {
        return audioSystem;
    }

    /**
     * Return the true if this room has a Projector.
     * @return: true if this room has a Projector
     */
    public boolean hasProjector() {
        return projector;
    }

    /**
     * Returns the number of power sockets in the room
     * @return : number of power sockets in the room (ret_type int)
     */
    public int getPowerSockets() {
        return powerSockets;
    }
}
