package Gateways;

import Gateways.Interfaces.IPollDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to get Poll Collection from the database and performing actions on the polls database
 * @author Ashwin
 */
public class PollDatabase implements IPollDatabase {

    private final MongoCollection<Document> pollCollection;

    /**
     * Constructor to initialize the mongo client, database and the poll collection to be used by
     * the poll database
     * @param mongoClient: object of mongo client
     */
    public PollDatabase(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("conference-database");
        this.pollCollection = database.getCollection("polls");
    }

    /**
     * Method to return the poll data stored in the database
     * @return List of Map where each map represents the data associated with a poll entity
     */
    @Override
    public List<Map<String, List<String>>> getPollList() {
        List<Map<String, List<String>>> pollList = new ArrayList<>();
        List<Document> pollDocs = pollCollection.find().into(new ArrayList<>());
        for(Document pollDoc: pollDocs) {
            Map<String, List<String>> poll = new HashMap<>();
            poll.put("pollId", pollDoc.getList("pollId", String.class));
            poll.put("eventPasscode", pollDoc.getList("eventPasscode", String.class));
            poll.put("pollMessage", pollDoc.getList("pollMessage", String.class));
            poll.put("pollOptions", pollDoc.getList("pollOptions", String.class));
            poll.put("pollOptionVotes", pollDoc.getList("pollOptionVotes", String.class));
            poll.put("alreadyVoted", pollDoc.getList("alreadyVoted", String.class));

            pollList.add(poll);
        }
        return pollList;
    }

    /**
     * Method to save the poll data from the program into the database
     * @param pollList: List of map where each map represents the data associated with a poll entity
     */
    @Override
    public void savePollList(List<Map<String, List<String>>> pollList) {
        List<Document> pollDocs = new ArrayList<>();
        for(Map<String, List<String>> poll: pollList) {
            Document pollDoc = new Document();
            pollDoc.append("pollId", poll.get("pollId"));
            pollDoc.append("eventPasscode", poll.get("eventPasscode"));
            pollDoc.append("pollMessage", poll.get("pollMessage"));
            pollDoc.append("pollOptions", poll.get("pollOptions"));
            pollDoc.append("pollOptionVotes", poll.get("pollOptionVotes"));
            pollDoc.append("alreadyVoted", poll.get("alreadyVoted"));

            pollDocs.add(pollDoc);
        }
        pollCollection.deleteMany(new Document());
        if(!pollDocs.isEmpty())
            pollCollection.insertMany(pollDocs);
    }

}
