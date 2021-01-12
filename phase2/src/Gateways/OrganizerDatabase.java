package Gateways;

import Gateways.Interfaces.IOrganizerDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * Class to get Organizer Collection from the database and performing actions on the user database
 * @author Akshat Ayush
 */
public class OrganizerDatabase extends UserDatabase implements IOrganizerDatabase {

    /**
     * Constructor to initialize the mongo client to be used by the organizer database
     *
     * @param mongoClient: object of mongo client
     */
    public OrganizerDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    /**
     * Method to return the organizer data stored in the database
     *
     * @return List of Map where each map represents the data associated with a organizer entity
     */
    @Override
    public List<Map<String, List<String>>> getOrganizers() {
        List<Map<String, List<String>>> organizerList = new ArrayList<>();
        List<Document> organizerDocuments = userCollection.find(eq("userType", "organizer")).into(new ArrayList<>());
        for (Document organizerDocument : organizerDocuments) {
            Map<String, List<String>> organizer = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = organizerDocument.getString("username");
            credentials.add(username);
            credentials.add(organizerDocument.getString("password"));
            organizer.put("credentials", credentials);
            organizer.put("contacts", getContactsForUser(username));
            organizer.put("conversations", getConversationsForUser(username));
            organizer.put("eventsAttending", getEventsAttendingForUser(username));
            organizerList.add(organizer);
        }
        return organizerList;
    }

    /**
     * Method to save the organizer data from the program into the database
     *
     * @param organizerList: List of map where each map represents the data associated with a organizer entity
     */
    @Override
    public void saveOrganizerList(List<Map<String, List<String>>> organizerList) {
        List<Document> organizerDocumentList = new ArrayList<>();
        for (Map<String, List<String>> organizer : organizerList) {
            List<String> credentials = organizer.get("credentials");
            Document organizerDoc = new Document();
            organizerDoc.append("username", credentials.get(0));
            organizerDoc.append("password", credentials.get(1));
            organizerDoc.append("contacts", organizer.get("contacts"));
            organizerDoc.append("conversations", organizer.get("conversations"));
            organizerDoc.append("eventsAttending", organizer.get("eventsAttending"));
            organizerDoc.append("userType", "organizer");

            organizerDocumentList.add(organizerDoc);
        }
        userCollection.deleteMany(eq("userType", "organizer"));
        if(!organizerDocumentList.isEmpty())
            userCollection.insertMany(organizerDocumentList);
    }

}