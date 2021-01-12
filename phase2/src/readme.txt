@authors Peter, Khoa, Vladimir, Arib, Johnny, Akshat, Ashwin


Used Database:
- mongo-java-driver-3.12.17.jar

Included:
- design.pdf

Description:
Phase 2 of our program extends Phase 1 with the following extensions:

    - Mandatory Extensions:
        - Multi-Speaker, Single Speaker, No-SpeakerEvents, Variable event time length measured in hours,
        - Events can be cancelled by at least one organizer
        - Included an Admin that can delete events without any attendees and can report other users in the conference
        - Organizers can create Speaker accounts, Attendee accounts, and Organizer accounts.
          Organizer can not create another Admin account due to design choice of only creating one Admin account
        - Each event has a maximum number of people who can attend it. This amount can be set when the event is created
          and also changed later, by an organizer. Your program should check the maximum capacity for the room where the
          event will be held, and prevent the number of attendees from going above the room's capacity.

    - Optional Extensions:
        1) Event Constraints: Add additional constraints to the scheduling for various types of events (e.g. technology
        requirements for presentations, tables vs rows of chairs). When organizers are creating events, they can see a
        suggested list of rooms that fit the requirements of their event.

        2) Event Search Engine: Alternatively, if you just want the program to print the schedule to the screen,
        then users should be able to request a schedule by at least three of: day, by speaker, by time
        (all 3-4 pm talks on all days), or just the ones they have signed up for, or "liked" events
        (where you have to enable users to "like" events)."

        3) Database: Use a database to store the information from your program through specific gateway class(es) so
        that you do not violate Clean Architecture and still have an Entity layer.

        4) GUI: Replace your text UI with a Graphic User Interface (GUI), which should follow the Model-View-Presenter
        architecture. Scrapped due to unexpected issues but still included

        5) Polling System: Allows users to view all polls and their poll information by inputting a select pass-code.
        Allows a variety of methods to be handled such as entering by poll ID and pass code. Allows poll information
        to be handled such as questions, answers and results.


    Note: Had to reformat to use Text UnusedUI instead of GUI due to unexpected errors between certain controllers
    interacting with the presenter classes.
************************************************************************************************************************

    Below are a set of instructions that one could use as reference when using the program.
        In a nutshell, the process of using the program is as follows:

            1) Login/Signup Process:
                A user will receive a prompt asking if the user wants to login or signup and show the according menu
                    - Login: enter username and password and wait for them to be checked
                    - Signup: Choose the username and password and wait to check if the username
                      is taken (choose different username!). Once signed up, the user will be taken to the initial
                      landing menu
                According to the user account type, the appropriate menu of options would then be shown.
            2) Events SearchEngine Process:

            3) Account Menu Process:
                There are 4 types of menus that can be displayed based on the type of user.

                I) OrganizerMenu Process (20 options):
                    CONFERENCE FUNCTIONS:
                    1: List of all users (Organizer, Attendee, Speaker) in the conference. (When selected
                    the user (organizer) is prompted which account type they wish to view)
                    2: Check if a speaker has an event at a certain time
                    3: Create a new account (of any type)

                    EVENT FUNCTIONS
                    4: Add a room into the system
                    5: Create new event or Schedule speaker for new event
                    6: Remove an event
                    7: Change speaker for an event (Warning: once this option is chosen, the given event name will
                    be removed. All attendees of the event should register again for this event.)
                    8: Change time of an event (Warning: once this option is chosen, the given event name will be
                    removed, and a new event will be created at your chosen time. All attendees of the event should,
                    register again for this event.)
                    9: Show events that I haven't signed up for
                    10: Sign up for an event
                    11: Cancel spot in an event
                    12: See schedule of events signed up for

                    MESSAGING FUNCTIONS: [Note: Since you are an organizer, you can send a message to any
                                          attendee/speaker/organizer]
                    13: Send message to an attendee
                    14: Send message to all attendees
                    15: Send message to a speaker
                    16: Send message to all speakers
                    17: View Conversations
                    18: Send message to everyone attending an event

                    POLLING FUNCTIONS:
                    19. Enter Polling menu

                    0: Sign-out

                II) AttendeeMenu Process (10 options):
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

                    POLLING FUNCTIONS:
                    9: Enter Polling Menu

                    0: Sign Out

                III) SpeakerMenu Process (7 options):
                    EVENT FUNCTIONS:
                    1: View list of talks to be given

                    MESSAGING FUNCTIONS:
                    2: Message all attendees signed up for a talk
                    3: Message all attendees attending multiple talks
                    4: Message an attendee attending a talk
                    5: View Conversations

                    POLLING FUNCTIONS:
                    6: Enter Polling Menu

                    0: Sign Out

                IV) AdminMenu Process (3 options)
                    FUNCTIONS:
                    1: Delete empty events
                    2: Report User

                    0: Sign Out

            4) Polling Menu Process (2 options):
                    FUNCTIONS:
                    1: View poll information for all polls with an event-polling-passcode
                    2: Create new poll

        By following the instructions printed out and ensuring that the user inputs are following the preconditions,
        the program will execute all the specifications required in Phase 1. Once a user signs out or exit, the program
        should save and a user would be able to come back and work from where they left off.

        We hope this readme.txt is instructive. Have fun tinkering with our program!




