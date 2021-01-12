package Gateways;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class to get the user collection from the database and performing actions that are common for all types
 * of users.
 * @author Akshat Ayush
 */
public abstract class UserDatabase {

    protected final MongoCollection<Document> userCollection;


    /**
     * Constructor to initialize the mongo client, database and the collection to be used by the user database
     * @param mongoClient: object of a mongo client
     */
    public UserDatabase(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("conference-database");
        this.userCollection = database.getCollection("users");
    }


    /**
     * Method to get a list of contacts of a given user from the database assuming the username exists in the database
     * @param username: username of the user whose list of contacts is to retrieved
     * @return List of strings representing the contacts of the user
     */
    public List<String> getContactsForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("contacts", String.class);
    }


    /**
     * Method to get a list of conversations of a given user from the database assuming the username
     * exists in the database
     * @param username: username of the user whose list of conversations is to retrieved
     * @return List of strings representing the conversations of the user
     */
    public List<String> getConversationsForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("conversations", String.class);
    }


    /**
     * Method to get a list of events attending for a given user from the database assuming the username exists
     * in the database
     * @param username: username of the user whose list of events attending is to retrieved
     * @return List of strings representing the events attending of the user
     */
    public List<String> getEventsAttendingForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("eventsAttending", String.class);
    }


}
