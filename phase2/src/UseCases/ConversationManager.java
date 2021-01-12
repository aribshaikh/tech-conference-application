package UseCases;

import Entities.Conversation;
import Entities.Message;
import Gateways.Interfaces.IConversationDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class stores and updates all the conversation in the system, as well as send information about those conversations to appropriate classes
 * This includes the following responsibilities:
 * - adding and storing conversations in the program
 * - searching and getting conversations based on their id
 * - returning data from the conversations based off of their id
 * - setting the conversations' roots
 * @author Peter Bilski
 * @see Conversation
 */
public class ConversationManager {
    private List<Conversation> allConversations;

    /**
     * Helper that generates the participants of a conversation based off of the message at its root
     * @param message the message that we need the participants for
     * @return the List of participants for the conversation
     */
    private List<String> participants(Message message){
        List<String> participantList = new ArrayList<>();
        participantList.add(message.getSender());
        participantList.addAll(message.getRecipients());

        return participantList;
    }

    /**
     * Creates and adds a new conversation to the list of all conversations based off of a list of participants
     * @param participants the list of participants
     * @return the id of the conversation
     */
    public String createNewConversation(List<String> participants){

        Conversation convo = new Conversation(participants, "");

        allConversations.add(convo);

        return convo.getId();
    }

    /**
     * Returns the id at the root of the conversation with the given id
     * @param convoId the id of the conversation which we want the root of
     * @return the id of the root of the conversation
     */
    public String getConvoRoot(String convoId){
        for(Conversation c: allConversations){
            if(c.getId().equals(convoId)){
                return c.getConvoRoot();
            }
        }
        return null;
    }

    /**
     * Sets the root of the conversation with the given id
     * @param convoId the id of the conversation of which we are setting the root for
     * @param messageId the id of the message we want to set as the root
     */
    public void setConvoRoot(String convoId, String messageId) {
        for(Conversation c: allConversations){
            if(c.getId().equals(convoId)){
                c.setConvoRoot(messageId);
            }
        }
    }

    //VLAD ADDED

    /**
     * Returns the conversation with the specified id
     * @param convoId the id of the conversation to be returned
     * @return the conversation that has the id, or null if such a conversation does not exist
     */
    private Conversation getConversation(String convoId){
        for(Conversation convo: allConversations){
            if (convo.getId().equals(convoId)){
                return convo;
            }
        }
        return null;
    }

    /**
     * Returns the participants of the conversation with the specified id
     * @param convoId the id of the convo of which the participants are to be returned
     * @return the List of the ids of the participants of the convo
     */
    public List<String> getConvoParticipants(String convoId){
        for(Conversation c: allConversations){
            if(c.getId().equals(convoId)){
                return c.getParticipants();
            }
        }
        return null;
    }

    /**
     * Checks if a conversation with the specified id is in the program
     * @param convoId the id which we want to verify
     * @return true if there is such a conversation, false if there is not
     */
    public boolean isConversation(String convoId){
        return getConversation(convoId) != null;
    }


    IConversationDatabase conversationDatabase;
    /**
     * a constructor that creates a UseCases.ConversationManager object that stores a list of all conversations and
     * creates an instance of the constructorDatabase.
     */
    public ConversationManager(IConversationDatabase conversationDatabase){
        allConversations =  new ArrayList<>();
        this.conversationDatabase = conversationDatabase;
    }



    /**
     * Loads the data being stored by Conversation entities in the database into a Conversation entity and stores every
     * Conversation entity into the allConversations list which is a list of Conversation entities.
     *
     * @author Juan Yi Loke
     */
    public void loadFromDatabase() {

        List<Map<String, List<String>>> conversationList = conversationDatabase.getConversationList();

        for(Map<String, List<String>> conversation: conversationList){
            String convoRoot = conversation.get("convoRoot").get(0);
            String id = conversation.get("id").get(0);
            List<String> ListOfParticipants = conversation.get("participants");

            Conversation newConversation = new Conversation(ListOfParticipants, convoRoot);
            newConversation.setId(id); // might be buggy, gotta check.
            allConversations.add(newConversation);
        }
    }

    /**
     * Stores the data being stored by the Conversation entities in the list allConversations in a
     * List<String, List<String>> data structure to be stored in the database system.
     *
     * @author Juan Yi Loke
     */
    public void saveToDatabase(){

        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for (Conversation Conversation : allConversations) {

            String convoRoot = Conversation.getConvoRoot();
            String id = Conversation.getId();
            List<String> ListOfParticipants = Conversation.getParticipants();

            List<String> convoRootTemp = new ArrayList<>();
            List<String> idTemp = new ArrayList<>();

            convoRootTemp.add(convoRoot);
            idTemp.add(id);

            Map<String, List<String>> resultingConversation = new HashMap<>();
            resultingConversation.put("convoRoot", convoRootTemp);
            resultingConversation.put("id", idTemp);
            resultingConversation.put("participants", ListOfParticipants);

            resultingList.add(resultingConversation);
        }
        conversationDatabase.saveConversationList(resultingList);
    }


}


