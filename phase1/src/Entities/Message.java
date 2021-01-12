package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The entity that represents a message between users in the program
 * Has setters and getters for instance variables where appropriate
 * @author Peter Bilski
 */
public class Message implements Serializable {
    private String sender; //the id of the Entities.User who sent
    private ArrayList<String> recipients = new ArrayList<>(); //list of Ids of Users who received messages
    private String content; //the actual content of the message

    private String id;
    private LocalDateTime time;

    private String convoID;
    private String reply = null;

    /**
     * A private helper that generates the id for the message
     * @return the String that is the id of the message
     */
    private String generateID(){
        long timestamp = System.currentTimeMillis();
        return "m" + timestamp;
    }

    /**
     * The constructor for a message with single recipient
     * @param sender the id of the sender of the message
     * @param recipient the id of the recipient of the message
     * @param content the content of the message
     * @param convoNumber the id of the conversation to which this message belongs
     */
    public Message(String sender, String recipient, String content, String convoNumber){
        this.sender = sender;
        this.recipients.add(recipient);
        this.content = content;
        id = generateID();
        this.time = LocalDateTime.now();
        this.convoID = convoNumber;

    }

    /**
     * The constructor for a message with multiple recipients
     * @param sender the id of the sender of the message
     * @param recipients the list of ids of the recipients of the message
     * @param content the content of the message
     * @param convoNumber the id of the conversation to which this message belongs
     */
    public Message(String sender, ArrayList<String> recipients, String content, String convoNumber){
        this.sender = sender;
        this.recipients.addAll(recipients);
        this.content = content;
        id = generateID();
        this.time = LocalDateTime.now();
        this.convoID = convoNumber;
    }

    /**
     * Getter for the sender of the message
     * @return the id of the sender of the message
     */
    public String getSender(){ return sender; }

    /**
     * Getter for the recipients of the message
     * @return the ArrayList of the ids of the recipients of the message
     */
    public ArrayList<String> getRecipients(){ return recipients; }

    /**
     * Getter for the content of the message
     * @return the content of the message
     */
    public String getContent(){ return content; }

    /**
     * Getter for the id of the message
     * @return the id of the message
     */
    public String getId(){ return id; }

    /**
     * Getter for the time of the message
     * @return the time of the message as a LocalDateTime object
     */
    public LocalDateTime getTime(){ return time; }

    /**
     * Getter for the message id
     * @return the message id
     */
    public String getConvoID(){ return convoID; }

    /**
     * Getter for the id of the reply
     * @return the id of the reply
     */
    public String getReply(){ return reply; }

    /**
     * Setter for the reply of the message
     * @param reply the id of the new reply
     */
    public void setReply(String reply) { this.reply = reply; }
}
