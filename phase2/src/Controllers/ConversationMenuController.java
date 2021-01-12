package Controllers;

import UseCases.ConversationManager;
import UseCases.MessageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling replying to a specific conversation and also ordering
 * messages in a given conversation.
 * The following methods include:
 * - Reply: Allow replying to a given conversation with a message
 * - OrderedMessagesInConvo: orders the messages in a conversation by passing in a select ID
 * @see ConversationManager
 * @see MessageManager
 */
public class ConversationMenuController {

    private final ConversationManager conversationManager;
    private final MessageManager messageManager;

    public ConversationMenuController(ConversationManager conversationManager, MessageManager messageManager){
        this.conversationManager = conversationManager;
        this.messageManager = messageManager;
    }
    /**
     * Allows a user to reply to a conversation they are in
     * @param senderId: the id of the sender
     * @param convoId: the id of the convo to which they are replying
     * @param content: the content of the reply
     * @return boolean: true if reply was sent, false otherwise
     */
    public boolean reply(String senderId, String convoId, String content){
        if(!conversationManager.isConversation(convoId)){
            return false;
        }
        String current = conversationManager.getConvoRoot(convoId);
        while(messageManager.getReply(current) != null){
            current = messageManager.getReply(current);
        }
        List<String> recipients = conversationManager.getConvoParticipants(convoId);
        recipients.remove(senderId);
        messageManager.addReply(senderId, recipients, content, current);
        return true;
    }

    /**
     * Returns a List of all the messages in a conversation, formatted for display
     * @param convoId: the id of the convo
     * @return List of formatted strings in a conversation
     */
    public List<String> orderedMessagesInConvo(String convoId) {
        List<String> rawMessages = new ArrayList<>();
        String current = conversationManager.getConvoRoot(convoId);
        rawMessages.add(current);
        while (messageManager.getReply(current) != null) {
            current = messageManager.getReply(current);
            rawMessages.add(current);
        }
        List<String> formattedMessages = new ArrayList<>();
        for (String messageId : rawMessages) {
            String message = messageManager.getSender(messageId) + ": " + messageManager.getContent(messageId) + " (" + messageManager.getTime(messageId) + ")";
            formattedMessages.add(message);
        }
        return formattedMessages;

    }

}
