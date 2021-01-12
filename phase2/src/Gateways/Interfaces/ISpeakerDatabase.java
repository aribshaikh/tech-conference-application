package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface ISpeakerDatabase {

    List<Map<String, List<String>>> getSpeakers();
    void saveSpeakerList(List<Map<String, List<String>>> speakerList);
}