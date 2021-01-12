package Gateways;

import Gateways.Interfaces.IEventDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to get Events Collection from the database and performing actions on the events database
 * @author Akshat Ayush
 */
public class EventDatabase implements IEventDatabase {

    private final MongoCollection<Document> eventCollection;

    /**
     * Constructor to initialize the mongo client, database and the event collection to be used by
     * the event database
     * @param mongoClient: object of mongo client
     */
    public EventDatabase(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("conference-database");
        this.eventCollection = database.getCollection("events");
    }

    /**
     * Method to return the event data stored in the database
     * @return List of Map where each map represents the data associated with a event entity
     */
    @Override
    public List<Map<String, List<String>>> getEventList() {
        List<Map<String, List<String>>> eventList = new ArrayList<>();
        List<Document> eventDocs = eventCollection.find().into(new ArrayList<>());
        for(Document eventDoc: eventDocs) {
            Map<String, List<String>> event = new HashMap<>();
            event.put("eventName", eventDoc.getList("eventName", String.class));
            event.put("speakerName", eventDoc.getList("speakerName", String.class));
            event.put("startTime", eventDoc.getList("startTime", String.class));
            event.put("duration", eventDoc.getList("duration", String.class));
            event.put("roomNumber", eventDoc.getList("roomNumber", String.class));
            event.put("eventCapacity", eventDoc.getList("eventCapacity", String.class));
            event.put("attendeeList", eventDoc.getList("attendeeList", String.class));
            event.put("eventSubjectLine", eventDoc.getList("eventSubjectLine", String.class));

            eventList.add(event);
        }
        return eventList;
    }

    /**
     * Method to save the event data from the program into the database
     * @param eventList: List of map where each map represents the data associated with a event entity
     */
    @Override
    public void saveEventList(List<Map<String, List<String>>> eventList) {
        List<Document> eventDocs = new ArrayList<>();
        for(Map<String, List<String>> event: eventList) {
            Document eventDoc = new Document();
            eventDoc.append("eventName", event.get("eventName"));
            eventDoc.append("speakerName", event.get("speakerName"));
            eventDoc.append("startTime", event.get("startTime"));
            eventDoc.append("duration", event.get("duration"));
            eventDoc.append("roomNumber", event.get("roomNumber"));
            eventDoc.append("eventCapacity", event.get("eventCapacity"));
            eventDoc.append("attendeeList", event.get("attendeeList"));
            eventDoc.append("eventSubjectLine", event.get("eventSubjectLine"));

            eventDocs.add(eventDoc);
        }
        eventCollection.deleteMany(new Document());
        if(!eventDocs.isEmpty())
            eventCollection.insertMany(eventDocs);
    }

}
