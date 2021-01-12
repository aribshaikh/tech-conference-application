package Controllers.UnusedControllers;

import UseCases.*;

import java.util.List;

public class SpeakerMessagingDashboardMenuController {

    private final SpeakerManager speakerManager;
    private final ConversationManager conversationManager;


    public SpeakerMessagingDashboardMenuController(SpeakerManager speakerManager, ConversationManager conversationManager){
        this.speakerManager = speakerManager;
        this.conversationManager = conversationManager;
    }
    public List<String> getConversations(String username){
        return speakerManager.getConversations(username);
    }
    public List<String> getConvoParticipants(String convoID){
        return conversationManager.getConvoParticipants(convoID);
    }


}
