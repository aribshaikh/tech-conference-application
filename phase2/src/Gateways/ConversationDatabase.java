package Gateways;

import Gateways.Interfaces.IConversationDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to get Conversation Collection from the database and performing actions on the conversation database
 * @author Akshat Ayush
 */
public class ConversationDatabase implements IConversationDatabase {

    private final MongoCollection<Document> conversationCollection;

    /**
     * Constructor to initialize the mongo client, database and the conversation collection to be used by
     * the conversation database
     * @param mongoClient: object of mongo client
     */
    public ConversationDatabase(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("conference-database");
        this.conversationCollection = database.getCollection("conversations");
    }

    /**
     * Method to return the conversation data stored in the database
     * @return List of Map where each map represents the data associated with a conversation entity
     */
    @Override
    public List<Map<String, List<String>>> getConversationList() {
        List<Map<String, List<String>>> conversationList = new ArrayList<>();
        List<Document> conversationDocs = conversationCollection.find().into(new ArrayList<>());
        for(Document conversationDoc: conversationDocs) {
            Map<String, List<String>> conversation = new HashMap<>();
            conversation.put("convoRoot", conversationDoc.getList("convoRoot", String.class));
            conversation.put("id", conversationDoc.getList("id", String.class));
            conversation.put("participants", conversationDoc.getList("participants", String.class));

            conversationList.add(conversation);
        }
        return conversationList;
    }

    /**
     * Method to save the conversation data from the program into the database
     * @param conversationList: List of map where each map represents the data associated with a conversation entity
     */
    @Override
    public void saveConversationList(List<Map<String, List<String>>> conversationList) {
        List<Document> conversationDocs = new ArrayList<>();
        for(Map<String, List<String>> conversation: conversationList) {
            Document conversationDoc = new Document();
            conversationDoc.append("convoRoot", conversation.get("convoRoot"));
            conversationDoc.append("id", conversation.get("id"));
            conversationDoc.append("participants", conversation.get("participants"));

            conversationDocs.add(conversationDoc);
        }
        conversationCollection.deleteMany(new Document());
        if(!conversationDocs.isEmpty())
            conversationCollection.insertMany(conversationDocs);
    }
}
