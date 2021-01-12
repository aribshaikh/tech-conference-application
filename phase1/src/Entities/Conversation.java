package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The entity that represents a message thread between users
 * Has setters and getters for all private variables
 * Each conversation has an id determined by the unix timestamp when it was created
 * @author Peter Bilski
 */
public class Conversation implements Serializable {
    private ArrayList<String> participants;
    private String convoRoot;
    private String id;

    /**
     * A private helper that generates the id for the conversation
     * @return the String that is the id of the conversation
     */
    private String generateID(){
        long timestamp = System.currentTimeMillis();
        return "c" + timestamp;
    }

    /**
     * A constructor for a conversation
     * @param participants the participants in the conversatoin
     * @param convoRoot the id of the message that started the convo
     */
    public Conversation(ArrayList<String> participants, String convoRoot){
        this.participants = participants;
        this.convoRoot = convoRoot;
        id = generateID();
    }

    /**
     * Returns the list of participants in the conversation
     * @return list of participant usernames
     */
    public ArrayList<String> getParticipants(){ return participants; }

    /**
     * Returns the conversation's root message's id
     * @return convoRoot
     */
    public String getConvoRoot(){ return convoRoot; }

    /**
     * Sets the root of the conversation to id
     * @param id the new root of the conversation
     */
    public void setConvoRoot(String id){ convoRoot = id; }

    /**
     * Returns the id of the conversation
      * @return the id of the convo
     */
    public String getId(){ return id; }
}
