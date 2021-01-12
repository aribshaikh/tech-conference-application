package Controllers.UnusedControllers;

import UseCases.AttendeeManager;
import UseCases.EventManager;

import java.util.ArrayList;
import java.util.List;

public class AttendeeEventMenuController {

    private final AttendeeManager attendeeManager;
    private final EventManager eventManager;

    public AttendeeEventMenuController(AttendeeManager attendeeManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.eventManager = eventManager;
    }

    public List<String> seeAttendableEvents(String attendee) {
        List<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        List<String> eventIdsAll = eventManager.getEventNamesList();
        List<String> eventsAttendable = new ArrayList<>();
        for (String eventId : eventIdsAll) {
            if (!eventIdsAttending.contains(eventId)) {
                // "id [info]"
                eventsAttendable.add(eventId + " " + eventManager.getEventInfo(eventId));
            }
        }
        return eventsAttendable;
    }

    public List<String> getListOfAllAttendedEvents(String attendeeUsername){
        return attendeeManager.getEventsAttending(attendeeUsername);
    }
    public void addAttendedEvent(String attendeeUsername, String event){
        getListOfAllAttendedEvents(attendeeUsername).add(event);
    }


}
