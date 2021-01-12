package Entities;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents information related to an Entities.Poll.
 * @author Ashwin Karthikeyan
 * @see User
 */
public class Poll {

    private final String pollId;
    private final String eventPasscode;
    private final String pollMessage;
    private final List<String> pollOptions;
    private List<Integer> pollOptionVotes;
    private List<String> alreadyVoted;


    public Poll(String pollId, String eventPasscode, String pollMessage, List<String> pollOptions, List<Integer> pollOptionVotes, List<String> alreadyVoted){

        this.pollId = pollId;
        this.eventPasscode = eventPasscode;
        this.pollMessage = pollMessage;
        this.pollOptions = pollOptions;
        this.pollOptionVotes = pollOptionVotes;
        this.alreadyVoted = alreadyVoted;

    }

    /**
     * returns a String pollMessage that represents the poll message.
     * @return String pollMessage that this Entities.Poll contains.
     */
    public String getPollMessage(){
        return pollMessage;
    }

    /**
     * returns a List<String> pollOptions that represents the options for a poll.
     * @return List<String> pollOptions that this Entities.Poll contains.
     */
    public List<String> getPollOptions(){
        return pollOptions;
    }

    /**
     * returns a List<String> alreadyVoted that represents a list of usernames of users who have already voted for a poll.
     * @return List<String> alreadyVoted that this Entities.Poll contains.
     */
    public List<String> getAlreadyVoted(){
        return alreadyVoted;
    }

    /**
     * takes in a List<String> alreadyVoted which is a list of usernames of users who have already voted and sets the
     * variable of Entities.Poll alreadyVoted to this list of usernames of users.
     */
    public void setAlreadyVoted(List<String> alreadyVoted){
        this.alreadyVoted = alreadyVoted;
    }

    /**
     * returns a String eventPasscode that represents the passcode for gaining access to all the available polls of a
     * particular event.
     * @return String eventPasscode that this Entities.Poll contains.
     */
    public String getEventPasscode(){
        return eventPasscode;
    }

    /**
     * returns a String pollId that represents the unique identifier for a particular poll.
     * @return String pollId that this Entities.Poll contains.
     */
    public String getPollId(){
        return pollId;
    }

    /**
     * returns a List<Integer> that represents the number of votes for a particular poll.
     * @return List<Integer> pollOptionVotes that this Entities.Poll contains.
     */
    public List<Integer> getPollOptionVotes(){
        return pollOptionVotes;
    }

    /**
     * takes in a List<Integer> pollOptionVotes which is a list of number of votes for a particular poll and sets the
     * variable of Entities.Poll pollOptionVotes to this list of number of votes for the particular poll.
     */
    public void setPollOptionVotes(List<Integer> pollOptionVotes){
        this.pollOptionVotes = pollOptionVotes;
    }

}
