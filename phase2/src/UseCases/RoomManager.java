package UseCases;

import Entities.Room;
import Gateways.Interfaces.IRoomDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the class that keeps track of all the rooms that can be used for this conference. This is Serializable class.
 * This class also restricts the uses/manipulations of rooms.
 * @author Ashwin Karthikeyan
 * @see Room
 */
public class RoomManager {

    private final List<Room> rooms;

    /**
     * a constructor that creates a UseCases.SpeakerManager object that stores a list of all speakers and creates an
     * instance of the speakerDatabase.
     */
    IRoomDatabase roomDatabase;
    public RoomManager(IRoomDatabase roomDatabase) {
        rooms = new ArrayList<>();
        this.roomDatabase = roomDatabase;
    }

    /**
     * Create a new room with id </roomId> and capacity </capacity>
     * @param roomId room ID of the room that is intended to be created (param_type: String)
     * @param capacity capacity of this room (param_type: int)
     * @return true if and only if there is no room with </roomId> and a room with </roomId> and capacity </capacity>
     */
    public boolean createRoom(String roomId, int capacity, boolean hasProjector, boolean hasAudioSystem, int powerSockets){

        if(isRoom(roomId)){
            return false;
        }
        else{
            Room newRoom = new Room(roomId, capacity, new ArrayList<>(), hasProjector, hasAudioSystem, powerSockets);
            rooms.add(newRoom);
            return true;
        }

    }

    /**
     * removes room with </roomId> from the list of stored rooms in this object
     * @param roomId room ID of the room that is intended to be removed (param_type: String)
     */
    public void removeRoom(String roomId){

        if(isRoom(roomId)){
            Room room = getRoom(roomId);
            rooms.remove(room);
        }

    }

    /**
     * Check if there is a room with id </roomId> exists in this object.
     * @param roomId : (param_type: String)
     * @return true if and only if there exists a room with id </roomId>
     */
    public boolean isRoom(String roomId){

        return getRoom(roomId) != null;

    }

    /**
     * Return the room with id </roomId>
     * @param roomId : id of the Entities.Room we want (param_type: String)
     * @return : the Entities.Room object with id </roomId>
     */
    private Room getRoom(String roomId){

        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Check if a room is occupied at a given time </time>
     * @param roomId : room id of the room we intend to check (param_type: String)
     * @param time : time at which we want to check if the room is occupied. (param_type: String)
     * @return true if and only if a room with </roomId> exists and is occupied at </time>
     */
    public boolean isRoomOccupiedAt(String roomId, String time){
    // true only if room with 'roomId' exists and that room is occupied at 'time'
        Room room =  getRoom(roomId);
        if(room != null){
            List<String> occupiedTimes = room.getOccupiedTimes();
            return occupiedTimes.contains(time);
        }
        return false;

    }


    /**
     * Check if a room is occupied at a given time </time> for duration </duration>
     * @param roomId : room id of the room we intend to check (param_type: String)
     * @param time : time at which we want to check if the room is occupied. (param_type: String)
     * @param duration: duration for which we check availability (param_type: int)
     * @return true if and only if a room with </roomId> exists and is occupied at </time>
     */
    public boolean isRoomOccupiedAtTimeForDuration(String roomId, String time, int duration){
        // true only if room with 'roomId' exists and that room is occupied at 'time' for 'duration' hours
        int k = 0;
        int occupancyFlag = 0;
        for(int i = 1; i < 13; i++){
            if(Integer.toString(i).equals(time)){
                k = i;
                break;
            }
        }
        for(int i = 0; i < duration; i++) {
            if(isRoomOccupiedAt(roomId, Integer.toString(k))){
                occupancyFlag++;
            }
            k++;
        }
        return k==duration;

    }

    /**
     * Books room with </roomId> for an event at </time>
     * @param roomId : id of the room that we intend to occupy (param_type: String)
     * @param time : time at which we want to occupy the room (param_type: String)
     * @return : true if and only if room with </roomId> exists and the room is occupied at </time> by
     *           the end of the method execution.
     */
    private boolean occupyRoomAtTime(String roomId, String time){

        Room room = getRoom(roomId);
        if(room != null && (!isRoomOccupiedAt(roomId, time))){
            List<String> occupiedTimes = room.getOccupiedTimes();
            occupiedTimes.add(time);
            room.setOccupiedTimes(occupiedTimes);
            return true;
        }
        return false;

    }

    /**
     * Relieves the booking of the room with </roomId> at </time>
     * @param roomId : id of the Entities.Room we want to free at </time> (param_type: String)
     * @param time : time at which we want to reliev this room (param_type: String)
     */
    private void freeRoomAtTime(String roomId, String time){

        Room room = getRoom(roomId);
        if(room != null && isRoomOccupiedAt(roomId, time)){
            List<String> occupiedTimes = room.getOccupiedTimes();
            occupiedTimes.remove(time);
            room.setOccupiedTimes(occupiedTimes);
        }

    }

    /**
     * Returns the capacity of the room with room id </roomId>
     * @param roomId : id of the room (param_type: String)
     * @return capacity of the room with id </roomId> (ret_type: int)
     */
    public int getCapacityOfRoom(String roomId){
    // returns -1 if no room with roomId exists. Otherwise, it returns room capacity.
        Room room = getRoom(roomId);
        if(room != null){
            return room.getCapacity();
        }
        return -1;

    }

    /**
     * Occupies the room with </roomId> starting at </time> for </duration> hours
     * @param roomId: room Id of the room that we intend to occupy
     * @param time: time at which we want to occupy a room
     * @param duration: duration for which the room needs to be occupied
     */
    public void occupyRoomAt(String roomId, String time, int duration){

        int timeInt;
        String tempTime = time;
        for (int i = 0; i < duration; i++) {
            occupyRoomAtTime(roomId, tempTime);
            timeInt = Integer.parseInt(tempTime);
            timeInt = timeInt + 1;
            tempTime = Integer.toString(timeInt);
        }
    }

    /**
     * Checks if a room that is free at </time>
     * @param time: The starting time at which we want to check if a room is free
     * @param duration: duration for which we want to check the availability of the room
     * @return "-" if no room is free at time
     *         id of a room that is free at time
     */
    public String checkRoomFreeAt(String time, int duration) {

        int flag = 0;
        int timeInt;
        String roomId;
        String tempTime = time;
        for (Room room : rooms) {
            for (int i = 0; i < duration; i++) {
                if (!(room.getOccupiedTimes().contains(tempTime))) {
                    flag = flag + 1;
                }
                timeInt = Integer.parseInt(tempTime);
                timeInt = timeInt + 1;
                tempTime = Integer.toString(timeInt);
            }
            if (flag == duration){
                roomId = room.getRoomId();
                return roomId;
            }
        }
        return "-";
    }

    /**
     * Frees the room with </roomId> starting at </time> for </duration> hours
     * @param roomId: room Id of the room that we intend to occupy
     * @param time: time at which we want to occupy a room
     * @param duration: duration for which the room needs to be occupied
     */
    public void freeRoomAt(String roomId, String time, int duration){

        int timeInt;
        String tempTime = time;
        for (int i = 0; i < duration; i++) {
            freeRoomAtTime(roomId, tempTime);
            timeInt = Integer.parseInt(tempTime);
            timeInt = timeInt + 1;
            tempTime = Integer.toString(timeInt);
        }
    }

    /**
     * Returns the List of all room Ids that satisfy the requirements for an event as provided by the parameters.
     * @param hasAudioSystem  Does the ideal room for this event have an Audio System (param_type: boolean)
     * @param hasProjector Does the ideal room for this event have a projector (param_type: boolean)
     * @param powerSockets How many power sockets does this event require (param_type: int)
     * @param time The start time of this event (param_type: String)
     * @param duration The duration of this event in hours (param_type: int)
     * @return List<String> of room IDs that satisfy the requirements
     */
    public List<String> roomsWithRequirements(boolean hasAudioSystem, boolean hasProjector, int powerSockets, String time, int duration) {

        List<String> rooms = getAllRoomIds();
        List<String> eligibleRooms = new ArrayList<>();
        for (String roomId : rooms) {
            Room room = getRoom(roomId);
            if (!hasAudioSystem && room != null) {
                if (!hasProjector) {
                    if (room.getPowerSockets() >= powerSockets && !isRoomOccupiedAtTimeForDuration(roomId, time, duration)) {
                        eligibleRooms.add(room.getRoomId());
                    }
                }
                else if (room.hasProjector()) {
                    if (room.getPowerSockets() >= powerSockets && !isRoomOccupiedAtTimeForDuration(roomId, time, duration)) {
                        eligibleRooms.add(room.getRoomId());
                    }
                }
            }
            else if (room != null && room.hasAudioSystem() ) {
                if (!hasProjector) {
                    if (room.getPowerSockets() >= powerSockets && !isRoomOccupiedAtTimeForDuration(roomId, time, duration)) {
                        eligibleRooms.add(room.getRoomId());
                    }
                }
                else if (room.hasProjector()) {
                    if (room.getPowerSockets() >= powerSockets && !isRoomOccupiedAtTimeForDuration(roomId, time, duration)) {
                        eligibleRooms.add(room.getRoomId());
                    }
                }
            }
        }
        return eligibleRooms;

    }

    /**
     * Get all Ids of Rooms that are stored in this manager.
     * @return List<String> of room Ids.
     */
    private List<String> getAllRoomIds(){

        List<String> roomIds = new ArrayList<>();
        for(Room room: rooms){
            roomIds.add(room.getRoomId());
        }
        return roomIds;

    }

    /**
     * Loads the data being stored by Room entities into a Room entity and stores every Room entity into the
     * rooms list which is a list of Room entities.
     *
     * @author Juan Yi Loke
     */
    public void loadFromDatabase() {
        List<Map<String, List<String>>> listOfRooms = roomDatabase.getRooms();

        for(Map<String, List<String>> room: listOfRooms){
            List<String> roomInfo = room.get("roomInfo");
            String roomId = roomInfo.get(0);
            int capacity = Integer.parseInt(roomInfo.get(1));
            List<String> occupiedTimes = room.get("occupiedTimes");
            boolean hasProjector = Boolean.parseBoolean(roomInfo.get(2));
            boolean hasAudioSystem = Boolean.parseBoolean(roomInfo.get(3));
            int powerSockets = Integer.parseInt(roomInfo.get(4));
            Room newRoom = new Room(roomId, capacity, occupiedTimes, hasProjector, hasAudioSystem, powerSockets);
            this.rooms.add(newRoom);
        }
    }

    /**
     * Stores the data being stored by Room entities in the list rooms in a List<String, List<String>> data
     * structure to be stored in the database system.
     *
     * @author Juan Yi Loke
     */
    public void saveToDatabase() {
        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for(Room room: this.rooms) {
            Map<String, List<String>> tempRoom = new HashMap<>();
            List<String> roomInfo = new ArrayList<>();
            roomInfo.add(room.getRoomId());
            roomInfo.add(String.valueOf(room.getCapacity()));
            roomInfo.add(Boolean.toString(room.hasProjector()));
            roomInfo.add(Boolean.toString(room.hasAudioSystem()));
            roomInfo.add(String.valueOf(room.getPowerSockets()));
            tempRoom.put("roomInfo", roomInfo);
            tempRoom.put("occupiedTimes", room.getOccupiedTimes());

            resultingList.add(tempRoom);
        }
        roomDatabase.saveRoomList(resultingList);
    }

}