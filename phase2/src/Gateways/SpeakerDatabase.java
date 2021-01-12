package Gateways;

import Gateways.Interfaces.ISpeakerDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * Class to get speaker Collection from the database and performing actions on the user database
 * @author Akshat Ayush
 */
public class SpeakerDatabase extends UserDatabase implements ISpeakerDatabase {

    /**
     * Constructor to initialize the mongo client to be used by the speaker database
     *
     * @param mongoClient: object of mongo client
     */
    public SpeakerDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    /**
     * Method to return the speaker data stored in the database
     *
     * @return List of Map where each map represents the data associated with a speaker entity
     */
    @Override
    public List<Map<String, List<String>>> getSpeakers() {
        List<Map<String, List<String>>> speakerList = new ArrayList<>();
        List<Document> speakerDocuments = userCollection.find(eq("userType", "speaker")).into(new ArrayList<>());
        for(Document speakerDocument: speakerDocuments) {
            Map<String, List<String>> speaker = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = speakerDocument.getString("username");
            credentials.add(username);
            credentials.add(speakerDocument.getString("password"));
            speaker.put("credentials", credentials);
            speaker.put("contacts", getContactsForUser(username));
            speaker.put("conversations", getConversationsForUser(username));
            speaker.put("eventNames", getEventNamesForSpeaker(username));
            speaker.put("eventTimes", getEventTimesForSpeaker(username));
            speakerList.add(speaker);
        }
        return speakerList;
    }

    /**
     * Method to save the speaker data from the program into the database
     *
     * @param speakerList: List of map where each map represents the data associated with a speaker entity
     */
    @Override
    public void saveSpeakerList(List<Map<String, List<String>>> speakerList) {
        List<Document> speakerDocumentList = new ArrayList<>();
        for (Map<String, List<String>> speaker : speakerList) {
            List<String> credentials = speaker.get("credentials");
            Document speakerDoc = new Document();
            speakerDoc.append("username", credentials.get(0));
            speakerDoc.append("password", credentials.get(1));
            speakerDoc.append("contacts", speaker.get("contacts"));
            speakerDoc.append("conversations", speaker.get("conversations"));
            speakerDoc.append("eventNames", speaker.get("eventNames"));
            speakerDoc.append("eventTimes", speaker.get("eventTimes"));
            speakerDoc.append("userType", "speaker");

            speakerDocumentList.add(speakerDoc);
        }
        userCollection.deleteMany(eq("userType", "speaker"));
        if(!speakerDocumentList.isEmpty())
            userCollection.insertMany(speakerDocumentList);
    }

    private List<String> getEventTimesForSpeaker(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("eventTimes", String.class);
    }

    public List<String> getEventNamesForSpeaker(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("eventNames", String.class);
    }

}
