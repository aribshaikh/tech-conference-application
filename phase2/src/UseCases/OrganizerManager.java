package UseCases;

import Entities.Organizer;
import Gateways.Interfaces.IOrganizerDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for keeping track of and enabling proper use of all Entities.Organizer objects
 * (all organizers in the conference).
 * This is done by limiting the set of manipulations that can be done to the Entities.Organizer objects.
 * The only manipulations/uses that are allowed are:
 *      - creating new Entities.Organizer object
 *      - adding other Users to an Entities.Organizer's list of contacts
 *      - add a new conversation to an Entities.Organizer's list of participating conversations
 *      - get the list of contacts of a given Entities.Organizer
 *      - check if a Entities.User is a contact of an Entities.Organizer
 *      - get a conversation that an Entities.Organizer has
 *      - add an event to the list of events an Entities.Organizer is attending
 *      - remove an event from the list of events that an Entities.Organizer attends
 *      - check if an Entities.Organizer is attending an event
 *      - get list of titles of events that an Entities.Organizer is attending
 *      - check the password of a given Entities.Organizer
 * The class UseCases.OrganizerManager has:
 *      - a method to check if there is an Entities.Organizer object with a given username
 *      - get all Entities.Organizer usernames
 * This is Serializable class.
 * @author Ashwin Karthikeyan
 * @see Organizer
 */
public class OrganizerManager {

    private final List<Organizer> organizerList;

    IOrganizerDatabase organizerDatabase;

    /**
     * a constructor that creates a UseCases.OrganizerManager object that stores a list of all organizers and creates an
     * instance of the organizerDatabase.
     */
    public OrganizerManager(IOrganizerDatabase organizerDatabase){
        this.organizerDatabase = organizerDatabase;
        organizerList = new ArrayList<>();
    }

    /**
     * if the username </username> already exists, doesn't create a new Entities.Organizer object with that
     * </username> and returns false.
     * Else creates an Entities.Organizer object and updates the list of all Entities.Organizer objects to
     * reflect this creation and returns true if an Entities.Organizer is created.
     * @param username: the username to be assigned to this possibly new Entities.Organizer (param_type: String)
     * @param password: the password to be assigned to this possibly new Entities.Organizer (param_type: String)
     * @return true if and only if new organizer was created
     */
    public boolean createOrganizer(String username, String password) {

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)){
                return false;
            }
        }
        Organizer newOrganizer = new Organizer(username, password);
        organizerList.add(newOrganizer);
        return true;

    }

    /**
     * returns false if Entities.Organizer object with given username </organizerUsername> doesn't exists.
     * returns true if contactUsername is in the contactList of Entities.Organizer by
     * the end of the functions execution.
     * @param organizerUsername: the username in the Organzier object that we intend to manipulate (param_type: String)
     * @param contactUsername: the username of the Entities.Attendee/Entities.Organizer/Entities.Speaker Object that we
     * intend to add as a contact of Entities.Organizer object with username </organizerUsername>
     * @return : false if Entities.Organizer object with given username </organizerUsername> doesn't exists.
     *          true if contactUsername is in the contactList of Entities.Organizer by
     *          the end of the functions execution.
     */
    public boolean addContact(String organizerUsername, String contactUsername) {

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else {
            List<String> newContacts = organizer.getContacts();
            if (!(isContact(organizer, contactUsername))) {
                newContacts.add(contactUsername);
                organizer.setContacts(newContacts);
                return true;
            }
            return true;
        }
    }

    private Organizer getOrganizer(String username){

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)) {
                return organizer;
            }
        }

        return null;

    }

    /**
     * If Entities.Organizer object with given username </username> doesn't exist, then returns null.
     * Else returns the list of usernames in Organizers contacts.
     * @param username: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @return : null if no Entities.Organizer object with username </username> exists.
     *          Else, List<String> of contacts of Entities.Organizer object with username </username>.
     */
    public List<String> getContactList(String username){
    //     returns null if organizer not found or organizer has no contacts
        Organizer organizer = getOrganizer(username);
        if(organizer == null){
            return null;
        }
        else {
            return(organizer.getContacts());
        }
    }

    private boolean isContact(Organizer organizer, String contactUsername){

        for(String contact: organizer.getContacts()){
            if(contactUsername.equals(contact)) {
                return true;
            }
        }

        return false;

    }

    /**
     * If Entities.Organizer object with given username </organizerUsername> exists, then it adds </conversationID> to that
     * Entities.Organizer object's List of conversation IDs.
     * @param organizerUsername: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @param conversationID: the ID of the conversation that is intended to be added as a conversation with Entities.Organizer
     *                      object with username </organizerUsername>
     */
    public void addConversation(String organizerUsername, String conversationID){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer != null){
            List<String> newConversations = organizer.getConversations();
            newConversations.add(conversationID);
            organizer.setConversations(newConversations);
        }
    }

    /**
     * If Entities.Organizer object with given username </organizerUsername> exists, then it returns the list of all Strings that
     * Entities.Organizer object holds as conversation IDs.
     * @param organizerUsername: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @return : If Entities.Organizer with username </organizerUsername> doesn't exist, returns null. Else returns
     *         the IDs of the conversations that the Entities.Organizer object with username </organizerUsername> holds
     */
    public List<String> getConversations(String organizerUsername){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return null;
        }
        else{
            return organizer.getConversations();
        }
    }

    /**
     * If Entities.Organizer object with given username </organizerUsername> doesn't exist or </organizerPassword>
     * doesn't match with the password of that Entities.Organizer object's password, then this returns returns false.
     * Returns true if and only if </organizerPassword> matches with the password of the password of Entities.Organizer object
     * with username </organizerUsername>
     * @param organizerUsername: the username in the Organzier object that we intend to manipulate (param_type: String)
     * @param organizerPassword: the string that we need to compare to this Entities.Organizer object's password (param_type: String)
     * @return : true if and only if </organizerPassword> matches with the password of the password of Entities.Organizer object
     *           with username </organizerUsername>
     */
    public boolean checkPassword(String organizerUsername, String organizerPassword){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else{
            return organizer.getPassword().equals(organizerPassword);
        }

    }

    /**
     * If Entities.Organizer object with given username </organizerUsername> doesn't exist, then this method returns false.
     * Returns true if and only if Entities.Organizer object with username </organizerUsername> exists
     * @param organizerUsername: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @return : true if and only if and Entities.Organizer object with username </organizerUsername> exists.
     */
    public boolean isOrganizer(String organizerUsername){

        Organizer organizer = getOrganizer(organizerUsername);
        return organizer != null;
    }

    /**
     * If Entities.Organizer object with given username </username> exists, then that Entities.Organizer object will have
     * </eventTitle> in its list of event titles of events attending by the end of this method's execution.
     * @param username: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event that this Entities.Organizer object is newly going to attend (param_type: String)
     */
    public void addAttendingEvent(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer != null){
            if (isAttending(username, eventTitle).equals("NO")) {
                List<String> eventsAttending = organizer.getEventsAttending();
                eventsAttending.add(eventTitle);
                organizer.setEventsAttending(eventsAttending);
            }
        }
    }

    /**
     * If Entities.Organizer object with given username </username> doesn't exist, then method returns "ODE". Else, returns "YES"
     * if </eventTitle> is in the list of event titles of events that the Entities.Organizer object is attending, and
     * returns no if the Entities.Organizer object doesn't have </eventTitle> in its list of event titles of events attending
     * @param username: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event to check if it is in the list of event titles of
     *                 events that this Entities.Organizer object is attending (param_type: String)
     * @return : "ODE" - Entities.Organizer doesn't exist
     *           "NO" - Entities.Organizer is currently not registered to attend event with title </eventTitle>
     *           "YES" - Entities.Organizer is currently registered to attend event with title </eventTitle>
     */
    public String isAttending(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer == null){
            return "ODE";
        }
        else {
            for (String event : organizer.getEventsAttending()) {
                if (event.equals(eventTitle)) {
                    return "YES";
                }
            }
        }

        return "NO";

    }

    /**
     * If Entities.Organizer object with given username </username> exists, then that Entities.Organizer object will not have
     * </eventTitle> in its list of event titles of events attending by the end of this method's execution.
     * @param username: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event that this Entities.Organizer object is not going to attend (param_type: String)
     */
    public void removeAttendingEvent(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer != null) {
            if (isAttending(username, eventTitle).equals("YES")) {
                List<String> eventsAttending = organizer.getEventsAttending();
                eventsAttending.remove(eventTitle);
                organizer.setEventsAttending(eventsAttending);
            }
        }

    }

    /**
     * if Entities.Organizer with username </username> exists, then
     * This method returns the events titles of the events that the Entities.Organizer object with username </username> stores.
     * This list of titles is the list of event titles of events that this organizer attends.
     * Else, this method returns null.
     * @param username: the username in the Entities.Organizer object that we intend to manipulate (param_type: String)
     * @return : if Entities.Organizer object with username </username> exists then
     *          List<String> of event titles that the Entities.Organizer object with username </username> stores.\
     *          Else, null
     */
    public List<String> getEventsAttending(String username){

        Organizer organizer = getOrganizer(username);
        if(organizer != null) {
            return organizer.getEventsAttending();
        }
        return null;
    }

    /**
     * Returns List<String> of usernames of all Entities.Organizer objects stored in this object of UseCases.OrganizerManager.
     * This method is intended for use in message related fields.
     * @return : List<String> of usernames of all Entities.Organizer objects stored in this object of UseCases.OrganizerManager.
     */
    public List<String> getAllOrganizerIds(){

        List<String> organizerUsernames = new ArrayList<>();
        for(Organizer organizer: organizerList){
            organizerUsernames.add(organizer.getUserId());
        }
        return organizerUsernames;

    }

    /**
     * Loads the data being stored by Organizer entities in the database into a Organizer entity and stores every
     * Organizer entity into the organizersList list which is a list of Organizer entities.
     *
     * @author Juan Yi Loke
     */
    public void loadFromDatabase() {
        List<Map<String, List<String>>> listOfOrganizers = organizerDatabase.getOrganizers();

        for(Map<String, List<String>> organizer: listOfOrganizers){
            List<String> listOfEventsAttending = organizer.get("eventsAttending");
            List<String> listOfContacts = organizer.get("contacts");
            List<String> listOfConversations = organizer.get("conversations");
            String username = organizer.get("credentials").get(0);
            String password = organizer.get("credentials").get(1);
            Organizer newOrganizer =  new Organizer(username, password);
            newOrganizer.setEventsAttending(listOfEventsAttending);
            newOrganizer.setContacts(listOfContacts);
            newOrganizer.setConversations(listOfConversations);
            organizerList.add(newOrganizer);
        }

    }

    /**
     * Stores the data being stored by Organizer entities in the list organizersList in a List<String, List<String>>
     * data structure to be stored in the database system.
     *
     * @author Juan Yi Loke
     */
    public void saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for (Organizer Organizer : organizerList) {

            String username = Organizer.getUserId();
            String password = Organizer.getPassword();
            List<String> credentialsTemp = new ArrayList<>();
            credentialsTemp.add(username);
            credentialsTemp.add(password);

            List<String> conversationTemp = Organizer.getConversations();
            List<String> contactsTemp = Organizer.getContacts();
            List<String> eventListTemp = Organizer.getEventsAttending();

            Map<String, List<String>> resultingAttendee = new HashMap<>();
            resultingAttendee.put("credentials", credentialsTemp);
            resultingAttendee.put("conversations", conversationTemp);
            resultingAttendee.put("contacts", contactsTemp);
            resultingAttendee.put("eventsAttending", eventListTemp);

            resultingList.add(resultingAttendee);
        }
        organizerDatabase.saveOrganizerList(resultingList);
    }


}