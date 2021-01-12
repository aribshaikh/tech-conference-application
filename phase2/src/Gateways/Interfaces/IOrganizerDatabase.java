package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface IOrganizerDatabase {

    List<Map<String, List<String>>> getOrganizers();
    void saveOrganizerList(List<Map<String, List<String>>> organizerList);

}