package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface IPollDatabase {

    List<Map<String, List<String>>> getPollList();
    void savePollList(List<Map<String, List<String>>> pollList);

}