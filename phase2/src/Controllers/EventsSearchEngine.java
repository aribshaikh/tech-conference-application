package Controllers;

import NewUI.TextUI;
import UseCases.EventManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods that perform actions like searching for events with certain parameters (eg. Speaker's username).
 * It also handles user input for searching by parameters
 * @author Ashwin
 */
public class EventsSearchEngine {

    private final EventManager eventManager;
    private final TextUI textUI;

    public EventsSearchEngine(EventManager eventManager, TextUI textUI){

        this.eventManager = eventManager;
        this.textUI = textUI;

    }

    private String getAllDetailsAsStringForEvent(String event){

        return "Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\nSubject Line: " + eventManager.getEventSubjectLine(event) + "\n";

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName>
     * @param speakerName the speaker's username (param_type: String)
     * @return List</String>
     */
    public List<String> eventsWithSpeaker(String speakerName){

        List<String> eventsWithThisSpeaker = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getSpeakerEvent(event).contains(speakerName)){
                eventsWithThisSpeaker.add(getAllDetailsAsStringForEvent(event));
            }
        }
        return eventsWithThisSpeaker;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with start time </startTime>
     * @param startTime the start time for the searched event (param_type: String)
     * @return List</String>
     */
    public List<String> eventsAtStartTime(String startTime){

        List<String> eventsAtThisStartTime = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getStartTime(event).equals(startTime)){
                eventsAtThisStartTime.add(getAllDetailsAsStringForEvent(event));
            }
        }
        return eventsAtThisStartTime;

    }


    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events that are duration </duration> hours long.
     * @param duration the duration of the events being search for (param_type: int)
     * @return List</String>
     */
    public List<String> eventsForThisDuration(int duration){

        List<String> eventsForThisDuration = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getDuration(event) == duration){
                eventsForThisDuration.add(getAllDetailsAsStringForEvent(event));
            }
        }
        return eventsForThisDuration;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> and duration </duration>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventsWithSpeakerAndDuration(String speakerName, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsWithThisSpeaker){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }


    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> at start time </time>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @return List</String>
     */
    public List<String> eventWithSpeakerAndStartTime(String speakerName, String time){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }


    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events at start time </time> and duration </duration>
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventsWithStartTimeAndDuration(String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        for(String event: eventsAtThisStartTime){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }


    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> at start time </time> and duration </duration> hours.
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventWithSpeakerNameStartTimeAndDuration(String speakerName, String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event) && eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with </keyword> in the subject line.
     * @param keyword keyword to search for in the tagline (param_type: String)
     * @return List</String>
     */
    public List<String> eventsWithSubjectLineContaining(String keyword){

        List<String> requiredEvents = new ArrayList<>();
        List<String> allEventTitles = eventManager.getAllEventTitles();
        String subjectLine;
        for (String event: allEventTitles){
            subjectLine = eventManager.getEventSubjectLine(event);
            if(subjectLine.contains(keyword)){
                requiredEvents.add(getAllDetailsAsStringForEvent(event));
            }
        }
        return requiredEvents;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with </keyword> in the subject line and that start at time </startTime>.
     * @param keyword keyword to search for in the tagline (param_type: String)
     * @param startTime time when the desired event starts (param_type: String)
     * @return List</String>
     */
    public List<String> eventsAtTimeWithSubjectLineContaining(String startTime, String keyword){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsAtThisStartTime = eventsAtStartTime(startTime);
        String subjectLine;
        for (String event: eventsAtThisStartTime){
            subjectLine = eventManager.getEventSubjectLine(event);
            if(subjectLine.contains(keyword)){
                requiredEvents.add(getAllDetailsAsStringForEvent(event));
            }
        }
        return requiredEvents;

    }

    /**
     * Handles the taking of user input in the form of a list that specifies which parameters to search with
     * Calls the appropriate presenter methods to display the list of events searched for
     * @param params array of parameters that specifies which parameters to search with
     */
    public void eventSearchWithNumericParameters(String[] params){
        List<String> parameters = Arrays.asList(params);
        Scanner scanner = new Scanner(System.in);
        String speaker = null;
        String startTime = null;
        String duration = null;
        String subjectLine = null;
        if(parameters.contains("1")){
            textUI.promptForSpeakerUsername();
            speaker = scanner.nextLine();
        }
        if(parameters.contains("2")){
            textUI.promptForEventTime();
            startTime = scanner.nextLine();
        }
        if(parameters.contains("3")){
            textUI.promptForEventDuration();
            duration = scanner.nextLine();
        }
        if(parameters.contains("4")){
            textUI.promptForSubjectLine();
            subjectLine = scanner.nextLine();
        }

        if(speaker != null && startTime == null && duration == null && subjectLine == null){
            List<String> events = eventsWithSpeaker(speaker);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker != null && startTime != null && duration == null && subjectLine == null){
            List<String> events = eventWithSpeakerAndStartTime(speaker, startTime);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker != null && startTime != null && duration != null && subjectLine == null){
            int actualDuration = Integer.parseInt(duration);
            List<String> events = eventWithSpeakerNameStartTimeAndDuration(speaker, startTime, actualDuration);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker != null && startTime == null && duration != null && subjectLine == null){
            int actualDuration = Integer.parseInt(duration);
            List<String> events = eventsWithSpeakerAndDuration(speaker, actualDuration);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker == null && startTime != null && duration != null && subjectLine == null){
            int actualDuration = Integer.parseInt(duration);
            List<String> events = eventsWithStartTimeAndDuration(startTime, actualDuration);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker == null && startTime != null && duration == null && subjectLine == null){
            List<String> events = eventsAtStartTime(startTime);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker == null && startTime == null && duration != null && subjectLine == null){
            int actualDuration = Integer.parseInt(duration);
            List<String> events = eventsForThisDuration(actualDuration);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker == null && startTime != null && duration == null){
            List<String> events = eventsAtTimeWithSubjectLineContaining(startTime, subjectLine);
            textUI.presentEventsFromSearchEngine(events);
        } else if(speaker == null && startTime == null && duration == null && subjectLine != null){
            List<String> events = eventsWithSubjectLineContaining(subjectLine);
            textUI.presentEventsFromSearchEngine(events);
        }
    }
}