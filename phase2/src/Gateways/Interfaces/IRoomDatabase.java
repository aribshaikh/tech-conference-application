package Gateways.Interfaces;

import java.util.List;
import java.util.Map;

public interface IRoomDatabase {

    List<Map<String, List<String>>> getRooms();
    void saveRoomList(List<Map<String, List<String>>> roomList);
}