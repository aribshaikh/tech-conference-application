package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface IConversationDatabase {

    List<Map<String, List<String>>> getConversationList();
    void saveConversationList(List<Map<String, List<String>>> conversationList);

}