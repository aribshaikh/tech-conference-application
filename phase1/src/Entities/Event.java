package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the Entities.Event entity class, creates an Entities.Event object and initiates all values of event. Responsible for the getters and setters of event.
 * @author aribshaikh
 */
public class Event implements Serializable {
    private String eventName;
    private String speakerName;
    private String eventTime;
    private String roomNumber;
    private final ArrayList<String> attendeeList;

    /**
     * A constructor to create the event object
     * @param eventName: name of event
     * @param speakerName: name of speaker
     * @param eventTime: time of event
     * @param roomNumber: room number
     * @param attendeeList: list of attendees
     */
    public Event(String eventName, String speakerName, String eventTime, String roomNumber, ArrayList<String>attendeeList){
        this.eventName = eventName;
        this.speakerName = speakerName;
        this.eventTime = eventTime;
        this.roomNumber = roomNumber;
        this.attendeeList = attendeeList;

    }

    /**
     * gets the name of event
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * returns the name of speaker
     * @return speakerName
     */
    public String getSpeakerName() {
        return speakerName;
    }

    /**
     * returns the time of event
     * @return eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * returns the room number of event
     * @return roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * returns the list of attendees
     * @return attendeeList
     */
    public ArrayList<String> getAttendeeList() {
        return attendeeList;
    }

    /**
     * Changes value of the name of event
     * @param eventName : name of event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Changes value for the speaker name of event
     * @param speakerName
     */
    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    /**
     * Changes value for the event time
     * @param eventTime
     */
    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    /**
     * Changes value for the room number
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


}
