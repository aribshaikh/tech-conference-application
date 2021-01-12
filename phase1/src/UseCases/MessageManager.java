package UseCases;

import Entities.Message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class stores and updates all the messages in the system, as well as send information about those messages to appropriate classes
 * This includes the following responsibilities:
 * - adding messages (both single and multiple recipients) to the program
 * - adding replies to messages
 * - searching and returning messages based on their id
 * - getting various data about messages based off of their id
 * @author Peter Bilski
 * @see Message
 */
public class MessageManager implements Serializable {
    private ArrayList<Message> allMessages;

    /**
     * Constructor for a UseCases.MessageManager, initializes the list of messages to be empty
     */
    public MessageManager(){
        allMessages = new ArrayList<>();
    }

    /**
     * Creates a method with a single recipient and adds it to the list of all messages
     * @param senderId the username of the sender
     * @param recipientId the username of the recipient
     * @param content the content of the message
     * @param convoID the id of the conversation that stores this method
     * @return the id of the message
     */
    public String sendMessageSingle(String senderId, String recipientId, String content, String convoID){
        Message message = new Message(senderId, recipientId, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    /**
     * Creates a method with multiple recipient and adds it to the list of all messages
     * @param senderId the username of the sender
     * @param recipientIds the usernames of the recipients
     * @param content the content of the message
     * @param convoID the id of the conversation that stores this method
     * @return the id of the message
     */
    public String sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content, String convoID){
        Message message = new Message(senderId, recipientIds, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    /**
     * Adds a reply to the message specified by messageId
     * @param senderId the id of the sender of the reply
     * @param recipientIds the ids of the recipients
     * @param content the content of the reply message
     * @param messageId the message to which the reply is being made
     * @return true if the reply was succesfully added, false otherwise
     */
    public boolean addReply(String senderId, ArrayList<String> recipientIds, String content, String messageId){
        Message message = getMessage(messageId);
        if(message == null){
            return false;
        }
        Message newMessage = new Message(senderId, recipientIds, content, message.getConvoID());
        message.setReply(newMessage.getId());
        allMessages.add(newMessage);
        return true;
    }

    /**
     * Returns a message from allMessages based on its id
     * @param messageId the id of the message to be searched for
     * @return the message object if there is a corresponding message, null if there is not
     */
    public Message getMessage(String messageId){
        for(Message m: allMessages){
            if(m.getId().equals(messageId)){
                return m;
            }
        }
        return null;
    }

    /**
     * Returns the id of the reply to the message with the specified id
     * @param messageId the id of the message we are getting the reply for
     * @return the id of the reply
     */
    public String getReply(String messageId){
        if(getMessage(messageId) != null) {
            return getMessage(messageId).getReply();
        }
        else {
            return null;
        }
    }

    /**
     * Returns the username of the sender of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the username of the sender
     */
    public String getSender(String messageId){
        return getMessage(messageId).getSender();
    }

    /**
     * Returns the content of the sender of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the content of the message
     */
    public String getContent(String messageId){
        return getMessage(messageId).getContent();
    }

    /**
     * Returns the time of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the time of the message
     */
    public String getTime(String messageId){
        return getMessage(messageId).getTime().toString();
    }

}
