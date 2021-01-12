package Gateways;

import Gateways.Interfaces.IAttendeeDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * Class to get Attendee Collection from the database and performing actions on the user database
 * @author Akshat Ayush
 */
public class AttendeeDatabase extends UserDatabase implements IAttendeeDatabase {

    /**
     * Constructor to initialize the mongo client to be used by the attendee database
     *
     * @param mongoClient: object of mongo client
     */
    public AttendeeDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    /**
     * Method to return the attendee data stored in the database
     *
     * @return List of Map where each map represents the data associated with a attendee entity
     */
    @Override
    public List<Map<String, List<String>>> getAttendees() {
        List<Map<String, List<String>>> attendeeList = new ArrayList<>();
        List<Document> attendeeDocuments = userCollection.find(eq("userType", "attendee")).into(new ArrayList<>());
        for (Document attendeeDocument : attendeeDocuments) {
            Map<String, List<String>> attendee = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = attendeeDocument.getString("username");
            credentials.add(username);
            credentials.add(attendeeDocument.getString("password"));
            attendee.put("credentials", credentials);
            attendee.put("contacts", getContactsForUser(username));
            attendee.put("conversations", getConversationsForUser(username));
            attendee.put("eventsAttending", getEventsAttendingForUser(username));
            attendeeList.add(attendee);
        }
        return attendeeList;
    }

    /**
     * Method to save the attendee data from the program into the database
     *
     * @param attendeeList: List of map where each map represents the data associated with a attendee entity
     */
    @Override
    public void saveAttendeeList(List<Map<String, List<String>>> attendeeList) {
        List<Document> attendeeDocumentList = new ArrayList<>();
        for (Map<String, List<String>> attendee : attendeeList) {
            List<String> credentials = attendee.get("credentials");
            Document attendeeDoc = new Document();
            attendeeDoc.append("username", credentials.get(0));
            attendeeDoc.append("password", credentials.get(1));
            attendeeDoc.append("contacts", attendee.get("contacts"));
            attendeeDoc.append("conversations", attendee.get("conversations"));
            attendeeDoc.append("eventsAttending", attendee.get("eventsAttending"));
            attendeeDoc.append("userType", "attendee");

            attendeeDocumentList.add(attendeeDoc);
        }
        userCollection.deleteMany(eq("userType", "attendee"));
        if(!attendeeDocumentList.isEmpty())
            userCollection.insertMany(attendeeDocumentList);
    }

}
