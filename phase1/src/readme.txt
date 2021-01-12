@author Khoa Pham, Akshat Ayush, Juan Yi Loke

Walkthrough of the Program:

    When we run the program, we load the data from a .ser file. If the file does not exist, a new instance of the
    conference system is created. This new instance of the conference system contains an organizer account.
    The organizer account has username "admin" and password "admin". The program saves the data when a user using the
    program logs out or exits the program completely.

    Suppose you are in the stage of either logging in or signing up, you would have to interact with the text prompts
    that you receive in the command line by typing in the exact integer option that is shown in the menu prompt. Note
    that whatever input that you type in must be exact. Below are some preconditions for the program.

    Preconditions:
        - accounts cannot have their usernames and passwords changed after creation
        - event names and user names are unique strings
        - all IDs are stored as strings
        - an account created in the program cannnot be deleted
        - user can’t remove people from their contacts list
        - users must type exact integers as inputs when making choices
        - only attendees are allowed to sign up for the program
        - the creation of a speaker's accounnt and organizer's account can only be done through organizer accounts

    Below are some supported functions that each type of user (speaker, organizer, attendee) are able to do.

    Supported functions:
        - Any Attendee/Organizer can see and reply to messages from other User
        - Any Attendee/Organizer can add other Users to list of people they can message
        - Any Attendee/Organizer can send a message to all their contacts
        - Any Attendee/Organizer can send a message to some of their contacts
        - Any Attendee/Organizer can send a message to one of their contacts
        - Any Attendee/Organizer can sign up for a particular event
        - Any Attendee/Organizer can cancel their reservation for a particular event
        - Any Attendee/Organizer can see all the events
        - Any Attendee can see all their participating events
        - Client can create accounts with appropriate username and password of various user types and sign in to
          the conference
        - Any Organizer can message everyone who is signed up for a particular event
        - Any Organizer can create an event
        - Any Organizer can remove an event
        - Any Organizer can send a message to all user types
        - Any Organizer can send a message to a single user type
        - Any Speaker can send a message to all attendees of a talk
        - Any Speaker can send a message to all attendees of multiple talks
        - Any Attendee can send a message to another attendee in their "friendlist"

    Lastly, below are a set of instructions that one could use as reference when using the program.
    In a nutshell, the process of using the program is as follows:

        1) Login/Signup Process:
            A user will receive a prompt asking if the user wants to login or signup and show the according menu
                - Login: enter username and password and wait for them to be checked
                - Signup: choose type of user to sign up (Organizer, Speaker, Attendee), then choose the username and
                 password and wait to check if the username is taken (choose different username!)
            According to the user account type, the appropriate menu of options would then be shown.


        2) Account Menu Process:
            There will be 3 types of menus that can be displayed based on the type of user.


            I) OrganizerMenu Process (20 options):
                CONFERENCE FUNCTIONS:
                1: List of all attendees in the conference
                2: List of all organizers in the conference
                3: List of all speakers in the conference
                4: Check if a speaker has an event at a certain time
                5: Create a new organizer account
                6: Create speaker account
                EVENT FUNCTIONS
                7: Add a room into the system
                8: Create new event or Schedule speaker for new event
                9: Change speaker for an event (Warning: once this option is chosen, the given event name will be removed.
                                               All attendees of the event should register again for this event )
                10: Change time of an event (Warning: once this option is chosen, the given event name will be removed,
                 and a new event will be created at your chosen time. All attendees of the event should register
                  again for this event
                11: Show events that I haven't signed up for
                12: Sign up for an event
                13: Cancel spot in an event
                14: See schedule of events signed up for
                MESSAGING FUNCTIONS: [Note: Since you are an organizer, you can send a message to any
                                      attendee/speaker/organizer]
                15: Send message to an attendee
                16: Send message to all attendees
                17: Send message to a speaker
                18: Send message to all speakers
                19: View Conversations
                20: Send message to everyone attending an event

            II) AttendeeMenu Process (8 options):
                EVENT FUNCTIONS:
                1: Available events to sign up for
                2: Sign up for an event
                3: Cancel spot in an event
                4: See schedule of event signed up for
                MESSAGING FUNCTIONS:
                5: Send message to an attendee
                6: Send message to a speaker of a talk
                7: View all conversations
                8: Add another attendee to friend list

            III) SpeakerMenu Process (5 options):
                EVENT FUNCTIONS:
                1: View list of talks to be given
                MESSAGING FUNCTIONS:
                2: Message all attendees signed up for a talk
                3: Message all attendees attending multiple talks
                4: Message an attendee attending a talk
                5: View Conversations

    By following the instructions printed out and ensuring that the user inputs are following the preconditions,
    the program will execute all the specifications required in Phase 1. Once a user signs out or exit, the program
    should save and a user would be able to come back and work from where they left off.

    I hope this readme.txt is instructive. Have fun tinkering with our program!

Best wishes,
Akshat, Arib, Ashwin, Juan Yi, Khoa, Peter, Vladimir

:))