package Controllers.UnusedControllers;

import UseCases.ConversationManager;
import UseCases.OrganizerManager;
import UseCases.SpeakerManager;

import java.util.List;

public class OrganizerMessagingDashboardMenuController {
    private final OrganizerManager organizerManager;
    private final ConversationManager conversationManager;

    public OrganizerMessagingDashboardMenuController(OrganizerManager organizerManager, ConversationManager conversationManager) {
        this.organizerManager = organizerManager;
        this.conversationManager = conversationManager;
    }

    public List<String> getConversations(String username){
        return organizerManager.getConversations(username);
    }
    public List<String> getConvoParticipants(String convoID){
        return conversationManager.getConvoParticipants(convoID);
    }
}
