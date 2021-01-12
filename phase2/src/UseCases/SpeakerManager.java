package UseCases;

import Entities.Speaker;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import Gateways.Interfaces.ISpeakerDatabase;

/**
 * This class is responsible for keeping track of all Entities.Speaker objects (Speakers at the tech-conference)
 * and allowing certain functionality.
 *
 * Responsibilities:
 *  - stores a list of Entities.Speaker objects
 *  - creates a new Entities.Speaker and adding it to the list of Entities.Speaker objects
 *  - adds a contact for the speaker
 *  - adds a conversation to the speakers list of conversations
 *  - gets a list of all contacts for a given Entities.Speaker
 *  - gets a list of all talks for a given Entities.Speaker
 *  - gets a list of all conversations for a given Entities.Speaker
 *  - gets a list of all Entities.Speaker usernames
 *  - validates a Entities.Speaker's password
 *  - validates a Entities.Speaker's availability
 *  - validates if a given username is an actual Entities.Speaker username
 *  - removes a talk from a Entities.Speaker's list of talks
 *
 * UseCases.SpeakerManager contains two methods to support this:
 *  - gets a Entities.Speaker
 *  - gets a list of all Speakers
 * @author Vladimir Caterov
 * @see Speaker
 */
public class SpeakerManager {

    private final List<Speaker> speakers;
    ISpeakerDatabase speakerDatabase;


    /**
     * a constructor that creates a UseCases.SpeakerManager object that stores a list of all speakers and creates an
     * instance of the speakerDatabase.
     */
    public SpeakerManager(ISpeakerDatabase speakerDatabase){
        speakers = new ArrayList<>();
        this.speakerDatabase = speakerDatabase;
    }


    /**
     * Creates a Entities.Speaker object and adds it to the list of all Entities.Speaker objects.
     * @param username: The username of a Entities.Speaker wanting to be created
     * @param password: The password of a Entities.Speaker wanting to be created
     * @return boolean: Returns a boolean validating that a Entities.Speaker has been created
     * @see Speaker
     */
    public boolean createSpeaker(String username, String password){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username))
            {
                return false;
            }
        }
        speakers.add(new Speaker(username,password));
        return true;
    }

    /**
     * Updates the contact information of a Entities.Speaker to include a new contact
     * @param speakerUsername: The username of a given Entities.Speaker
     * @param otherUsername: The username of the contact wished to be added
     * @return boolean: Returns a boolean validating that a contact has been added to a Entities.Speaker's list of contacts
     */
    public boolean addContact(String speakerUsername, String otherUsername){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else {
            List<String> contacts = getContactsForSpeaker(speakerUsername);
            if (!contacts.contains(otherUsername)) {
                contacts.add(otherUsername);
                speaker.setContacts(contacts);
                return true;
            }
            return true;
        }
    }


    /**
     * Updates a Entities.Speaker object's list of all given talks with a new talk stored as a map with
     * event time as the key and event name as the value. Returns true if the talk was added. Else returns false.
     * @param speakerUsername: The username of a given Entities.Speaker
     * @param eventTime: The time of a given event
     * @param eventName: The name of a given event
     * @return boolean: Return a boolean validating that a talk has been added to a Entities.Speaker's list of talks
     */
    public boolean addTalkToListOfTalks(String speakerUsername, String eventTime, String eventName){


        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else{
            Map<String, String> listOfTalks = getListOfTalks(speakerUsername);
            Boolean addable = true;
            if (listOfTalks.containsKey(eventTime)){
                addable = false;
            }
            if(addable){
                listOfTalks.put(eventTime, eventName);
                speaker.setListOfTalks(listOfTalks);
            }
            return addable;
        }
    }

    /**
     * Updates a Entities.Speaker object's list of all conversations with a new conversation. Returns true if
     * the conversation was added successfully. Else returns false.
     * @param username: The username of a given Entities.Speaker
     * @param conversation: A new conversation to be created
     * @return boolean: Returns a boolean validating that a conversation has been added to a Entities.Speaker's list of conversations
     */
    public boolean addConversation(String username, String conversation){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return false;
        }
        else{
            List<String> conversations = speaker.getConversations();
            conversations.add(conversation);
            speaker.setConversations(conversations);
            return true;
        }
    }

    private Speaker getSpeaker(String username){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username)){
                return speaker;
            }
        }
        return null;
    }

    private List<Speaker> getAllSpeakers(){
        return speakers;
    }

    /**
     * Returns a list of contact usernames that are available for the Entities.Speaker with given username to message.
     * @param username: The username of a given Entities.Speaker
     * @return List <String>: Returns an List containing strings the represent the contacts
     * of a given Entities.Speaker
     */
    public List<String> getContactsForSpeaker(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return null;
        }
        return speaker.getContacts();
    }

    /**
     * Returns a a list of all events (NOTE* events are stored as a Map with key as event time
     * and value as event Name) for a given Entities.Speaker with specified username.
     * @param username: The username of a given Entities.Speaker
     * @return List <Map <String, String>>: Returns an List containing Maps with key as event time
     * and value as event Name
     */
    public Map<String, String> getListOfTalks(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return null;
        }
        return speaker.getListOfTalks();
    }

    /**
     * Returns a list of all conversations for a given Entities.Speaker with specified username
     * @param username: The username of a given Entities.Speaker
     * @return List <String>: Returns an List of strings containing conversation ids
     * that a Entities.Speaker has
     */
    public List<String> getConversations(String username){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return null;
        }
        return speaker.getConversations();
    }

    /**
     * Return a list of all Entities.Speaker identifiers (Entities.Speaker usernames)
     * @return List <String>: Returns an List of String objects where each String is a Entities.Speaker id
     * for each Entities.Speaker
     */
    public List<String> getAllSpeakerIds(){
        List<String> speakerIds = new ArrayList<>();
        for (Speaker speaker: speakers){
            speakerIds.add(speaker.getUserId());
        }
        return speakerIds;
    }

    /**
     * Validates if password is the password of a Entities.Speaker with given username. Returns true if a given speaker password
     * is associated with a speaker. Else returns false.
     * @param username: The username of a given Entities.Speaker
     * @param password: The password of a given Entities.Speaker
     * @return boolean: Returns a boolean validating that a given password is the password
     * associated with a speaker password
     */
    public boolean checkPassword(String username, String password){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return false;
        }
        else{
            return speaker.getPassword().equals(password);
        }
    }

    /**
     * Validates if a given speaker with specified username is free at a given time
     * @param username: The username of a given Entities.Speaker
     * @param time: A given time in the conference
     * @return boolean: Returns a boolean validating that a speaker is free at a given time
     */
    public boolean isSpeakerFreeAtTime(String username, String time){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return false;
        }
        else{
            return (!speaker.getListOfTalks().containsKey(time));
//            for (Map<String, String> talk: speaker.getListOfTalks()){
//                if (talk.containsKey(time)) {
//                    free = false;
//                    break;
//                }
//            }
//            return free;
        }
    }

    /**
     * Validates if username is the id of a Entities.Speaker object. Returns true if a given speaker username is associated
     * with a speaker. Else returns false.
     * @param username: The username of a given Entities.Speaker
     * @return boolean: Returns a boolean validating that a given username is associated with a Entities.Speaker
     */
    public boolean isSpeaker(String username){
        if (getAllSpeakerIds().contains(username)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * If a talk from a Entities.Speaker object's listOfTalks has been successfully removed, returns true.
     * Else returns false.
     *
     * @param speakerUsername: The username of a given Entities.Speaker
     * @param eventTime: The time of a selected event
     * @param eventName: The name of a selected event
     * @return boolean: Returns a boolean validating that an event has been removed from a
     * Entities.Speaker list of events
     */

    public boolean removeTalkFromListOfTalks(String speakerUsername, String eventTime, String eventName){
//        Map<String, String> selectedTalk = new HashMap<>();
//        selectedTalk.put(eventTime, eventName);

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else {
            Map<String, String> listOfTalks = getListOfTalks(speakerUsername);
            return listOfTalks.remove(eventTime, eventName);

//            if(listOfTalks.contains(selectedTalk)){
//                listOfTalks.remove(selectedTalk);
//                speaker.setListOfTalks(listOfTalks);
//                return true;
//            }

        }

    }

    public List<String> seeAllEventNamesForSpeaker(String speakerUsername){

        Map<String, String> listOfTalks = getListOfTalks(speakerUsername);
        List<String> masterList = new ArrayList<>();

        for(Map.Entry<String, String> event: listOfTalks.entrySet()){
            masterList.add(event.getValue());
        }


        return masterList;
    }

    /**
     * Loads the data being stored by Speaker entities in the database into a Speaker entity and stores every Speaker
     * entity into the speakers list which is a list of Speaker entities.
     *
     * @author Juan Yi Loke
     */
    public void loadFromDatabase() {
        List<Map<String, List<String>>> listOfSpeakers = speakerDatabase.getSpeakers();

        for(Map<String, List<String>> speaker: listOfSpeakers){
            String username = speaker.get("credentials").get(0);
            String password = speaker.get("credentials").get(1);
            List<String> listOfConversations = speaker.get("conversations");
            List<String> listOfContacts = speaker.get("contacts");
            List<String> eventNames = speaker.get("eventNames");
            List<String> eventTimes = speaker.get("eventTimes");
            Speaker newSpeaker =  new Speaker(username, password);
            newSpeaker.setContacts(listOfContacts);
            newSpeaker.setConversations(listOfConversations);
            speakers.add(newSpeaker);
            if (!eventTimes.isEmpty()){
                for(int i = 0 ; i < eventTimes.size(); i++){
                    addTalkToListOfTalks(username, eventTimes.get(i), eventNames.get(i));
                }
            }
//            System.out.println(eventTimes.size());
//            System.out.println(eventTimes.get(0));
//            System.out.println(eventNames.get(0));

        }


    }

    /**
     * Stores the data being stored by Speaker entities in the list speakers in a List<String, List<String>> data
     * structure to be stored in the database system.
     *
     * @author Juan Yi Loke
     */
    public void saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for (Speaker Speaker: speakers) {

            String username = Speaker.getUserId();
            String password = Speaker.getPassword();
            List<String> credentialsTemp = new ArrayList<>();
            credentialsTemp.add(username);
            credentialsTemp.add(password);

            List<String> conversationTemp = Speaker.getConversations();
            List<String> contactsTemp = Speaker.getContacts();

            Map<String, String> listOfTalks = Speaker.getListOfTalks();
            List<String> eventNames = new ArrayList<>();
            List<String> eventTimes = new ArrayList<>();

            for (String eventTime: listOfTalks.keySet()){
                eventTimes.add(eventTime);
                eventNames.add(listOfTalks.get(eventTime));
            }

            Map<String, List<String>> resultingSpeaker = new HashMap<>();
            resultingSpeaker.put("credentials", credentialsTemp);
            resultingSpeaker.put("conversations", conversationTemp);
            resultingSpeaker.put("contacts", contactsTemp);
            resultingSpeaker.put("eventNames", eventNames);
            resultingSpeaker.put("eventTimes", eventTimes);

            resultingList.add(resultingSpeaker);
        }
        speakerDatabase.saveSpeakerList(resultingList);
    }
}
