package UseCases;

import Entities.Poll;
import Entities.Room;
import Gateways.Interfaces.IPollDatabase;

import java.util.*;

/**
 * This class is the class that keeps track of all the polls that can be used by events for this conference.
 * This class also restricts the uses/manipulations of rooms.
 * @author Ashwin Karthikeyan
 * @see Poll
 */
public class PollManager {

    private List<Poll> polls;

    /**
     * a constructor that creates a UseCases.PollManager object that stores a list of all polls
     */
    public PollManager(List<Poll> polls){

        this.polls = polls;

    }

    /**
     * a method that returns all the available polls of a particular event
     * @param eventPasscode: the username to be assigned to this possibly new Entities.Organizer (param_type: String)
     * @return List<String> of the available polls for an event
     */
    public List<String> getPollsForEvent(String eventPasscode){

        List<String> requiredPolls = new ArrayList<>();
        for(Poll poll: polls){
            if(poll.getEventPasscode().equals(eventPasscode)){
                requiredPolls.add(poll.getPollId());
            }
        }
        return requiredPolls;

    }

    /**
     * a method takes in a String that represents a pollId and a String that represents an event and returns the Poll
     * entity for the particular poll of the event.
//     * @param eventPasscode: the username to be assigned to this possibly new Entities.Organizer (param_type: String)
     * @return List<String> of the available polls for an event
     */
    private Poll getPoll(String pollId, String event){

        for(Poll poll: polls){
            if(poll.getPollId().equals(pollId) && poll.getEventPasscode().equals(event)){
                return poll;
            }
        }
        return null;

    }


    public void voteInPoll(String username, String pollId, String event, int option){

        Poll poll = getPoll(pollId, event);
        if(poll != null) {
            List<String> alreadyVoted = poll.getAlreadyVoted();
            if (!alreadyVoted.contains(username) && option <= poll.getPollOptions().size()) {
                alreadyVoted.add(username);
                poll.setAlreadyVoted(alreadyVoted);
                List<Integer> currVotes = poll.getPollOptionVotes();
                int currVoteForOption = currVotes.get(option - 1);
                currVoteForOption = currVoteForOption + 1;
                currVotes.set(option - 1, currVoteForOption);
                poll.setPollOptionVotes(currVotes);
            }
        }

    }


    public boolean addNewPoll(String pollId, String eventPasscode, String pollMessage, List<String> pollOptions, List<String> alreadyVoted){

        List<String> pollIds = getPollsForEvent(eventPasscode);
        for(String pollId1: pollIds){
            if(pollId.equals(pollId1)){
                return false;
            }
        }
        Poll newPoll = new Poll(pollId, eventPasscode, pollMessage, pollOptions, new ArrayList<Integer>(), alreadyVoted);
        polls.add(newPoll);
        return true;

    }


    public List<String> getAllPollsInfoForEvent(String username, String event) {

        List<String> requiredPolls = new ArrayList<>();
        List<String> polls = getPollsForEvent(event);
        Poll poll;
        int i = 1;
        int j;
        for (String pollId : polls) {
            poll = getPoll(pollId, event);
            j = 1;
            StringBuilder pollOptions = new StringBuilder("\n");
            for (String option : poll.getPollOptions()) {
                pollOptions.append(j);
                pollOptions.append(": ");
                pollOptions.append(option);
                pollOptions.append("\n");
                j++;
            }
            if (poll.getAlreadyVoted().contains(username)) {
                requiredPolls.add(i + " :\n" + "\nPoll ID: " + pollId + "\nEvent-Polling-Passcode: " + event + "\nPoll Options: " + pollOptions + pollPercentPerOption(poll));
            }
            else{
                requiredPolls.add(i + " :\n" + "\nPoll ID: " + pollId + "\nEvent-Polling-Passcode: " + event + "\nPoll Options: " + pollOptions);
            }
        }
        return requiredPolls;

    }


    private StringBuilder pollPercentPerOption(Poll poll){

        StringBuilder pollPercentages = new StringBuilder("\n");
        int percentage;
        int sum = 0;
        List<Integer> pollOptionVotes = poll.getPollOptionVotes();
        int j = 1;
        for(Integer i: pollOptionVotes){
            sum += i;
        }
        for(Integer i: pollOptionVotes){
            percentage = (i/sum)*100;
            pollPercentages.append((j + 1));
            pollPercentages.append(": ");
            pollPercentages.append(percentage);
            pollPercentages.append("\n");
        }
        return pollPercentages;

    }

    /**
     * a constructor that creates a UseCases.PollManager object that stores a list of all polls and creates an
     * instance of the IPollDatabase.
     */
    IPollDatabase pollDatabase;
    public PollManager(IPollDatabase pollDatabase){
        polls = new ArrayList<>();
        this.pollDatabase = pollDatabase;
    }


    /**
     * Loads the data being stored by Poll entities in the database into a Poll entity and stores every
     * Poll entity into the polls list which is a list of Poll entities.
     *
     * @author Ashwin (based on the work of Juan Yi Loke)
     */
    public void loadFromDatabase() {

        List<Map<String, List<String>>> pollList = pollDatabase.getPollList();

        for(Map<String, List<String>> poll: pollList){
            String pollId = poll.get("pollId").get(0);
            String eventPasscode = poll.get("eventPasscode").get(0);
            String pollMessage =  poll.get("pollMessage").get(0);
            List<String> pollOptions = poll.get("pollOptions");
            List<String> pollOptionVotesString = poll.get("pollOptionVotes");
            List<Integer> pollOptionVotes = new ArrayList<>();
            for(String option: pollOptionVotesString){
                pollOptionVotes.add(Integer.parseInt(option));
            }
            List<String> alreadyVoted = poll.get("alreadyVoted");

            Poll newPoll = new Poll(pollId, eventPasscode, pollMessage, pollOptions, pollOptionVotes, alreadyVoted);
            polls.add(newPoll);
        }
    }

    /**
     * Stores the data being stored by the Poll entities in the list allMessages in a List<String, List</String>>
     * data structure to be stored in the database system.
     *
     * @author Ashwin (Based on methods of Juan Yi Loke)
     */
    public void saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for (Poll poll : this.polls) {

            String pollId = poll.getPollId();
            String eventPasscode = poll.getEventPasscode();
            String pollMessage = poll.getPollMessage();
            List<String> pollOptions = poll.getPollOptions();
            List<Integer> pollOptionVotesInteger = poll.getPollOptionVotes();
            List<String> alreadyVoted = poll.getAlreadyVoted();
            List<String> pollOptionVotes = new ArrayList<>();
            for(Integer i: pollOptionVotesInteger){
                pollOptionVotes.add(i.toString());
            }

            Map<String, List<String>> resultingPoll = new HashMap<>();
            resultingPoll.put("eventPasscode", Collections.singletonList(eventPasscode));
            resultingPoll.put("pollMessage", Collections.singletonList(pollMessage));
            resultingPoll.put("pollOptions", pollOptions);
            resultingPoll.put("pollOptionVotes", pollOptionVotes);
            resultingPoll.put("pollId", Collections.singletonList(pollId));
            resultingPoll.put("alreadyVoted", alreadyVoted);

            resultingList.add(resultingPoll);
        }
        pollDatabase.savePollList(resultingList);
    }

}