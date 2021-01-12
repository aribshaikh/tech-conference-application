package Gateways;

import Gateways.Interfaces.IMessageDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to get Message Collection from the database and performing actions on the message database
 * @author Akshat Ayush
 */
public class MessageDatabase implements IMessageDatabase {

    MongoCollection<Document> messageCollection;

    /**
     * Constructor to initialize the mongo client, database and the room collection to be used by the message database
     * @param mongoClient: object of mongo client
     */
    public MessageDatabase(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("conference-database");
        this.messageCollection = database.getCollection("messages");
    }

    /**
     * Method to return the message data stored in the database
     * @return List of Map where each map represents the data associated with a message entity
     */
    @Override
    public List<Map<String, List<String>>> getMessageList() {
        List<Map<String, List<String>>> messageList = new ArrayList<>();
        List<Document> messageDocuments = messageCollection.find().into(new ArrayList<>());
        for(Document messageDocument: messageDocuments) {
            Map<String, List<String>> message = new HashMap<>();
            List<String> messageInfo = new ArrayList<>();
            messageInfo.add(messageDocument.getString("id"));
            messageInfo.add(messageDocument.getString("sender"));
            messageInfo.add(messageDocument.getString("content"));
            messageInfo.add(messageDocument.getString("reply"));
            messageInfo.add(messageDocument.getString("time"));
            messageInfo.add(messageDocument.getString("convoID"));
            message.put("messageInfo", messageInfo);
            message.put("recipients", messageDocument.getList("recipients", String.class));
            messageList.add(message);
        }
        return messageList;
    }

    /**
     * Method to save the message data from the program into the database
     * @param messageList: List of map where each map represents the data associated with a message entity
     */
    @Override
    public void saveMessageList(List<Map<String, List<String>>> messageList) {
        List<Document> messageDocumentList = new ArrayList<>();
        for(Map<String, List<String>> message: messageList) {
            List<String> messageInfo = message.get("messageInfo");
            Document messageDoc = new Document();
            messageDoc.append("id", messageInfo.get(0));
            messageDoc.append("sender", messageInfo.get(1));
            messageDoc.append("content", messageInfo.get(2));
            messageDoc.append("reply", messageInfo.get(3));
            messageDoc.append("time", messageInfo.get(4));
            messageDoc.append("convoID", messageInfo.get(5));
            messageDoc.append("recipients", message.get("recipients"));

            messageDocumentList.add(messageDoc);
        }
        messageCollection.deleteMany(new Document());
        if(!messageDocumentList.isEmpty())
            messageCollection.insertMany(messageDocumentList);
    }
}
