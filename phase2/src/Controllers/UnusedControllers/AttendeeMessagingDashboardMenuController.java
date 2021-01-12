package Controllers.UnusedControllers;

import UseCases.*;

import java.util.List;

public class AttendeeMessagingDashboardMenuController {

    private final AttendeeManager attendeeManager;
    private final ConversationManager conversationManager;


    public AttendeeMessagingDashboardMenuController(AttendeeManager attendeeManager, ConversationManager conversationManager){
        this.attendeeManager = attendeeManager;
        this.conversationManager = conversationManager;
    }
    public List<String> getConversations(String username){
        return attendeeManager.getConversations(username);
    }
    public List<String> getConvoParticipants(String convoID){
        return conversationManager.getConvoParticipants(convoID);
    }


}
