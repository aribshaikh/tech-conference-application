package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface IEventDatabase {

    List<Map<String, List<String>>> getEventList();
    void saveEventList(List<Map<String, List<String>>> eventList);
}