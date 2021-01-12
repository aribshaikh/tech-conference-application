package Controllers;

import Gateways.ProgramGenerator;
import NewUI.*;
import UseCases.*;
import java.util.Scanner;


/**
 * Class that stores the instances of use case and controller classes and controls
 * the flow of the program by getting user input and and using other controllers to
 * execute tasks and display results using the UI.
 * @author Akshat Ayush
 */
public class MasterSystem {



    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    private EventManager eventManager;
    private PollManager pollManager;
    private RoomManager roomManager;

    private ConversationManager conversationManager;
    private MessageManager messageManager;


    private ConversationMenuController conversationMenuController;
    private EventsSearchEngine eventsSearchEngine;
    private MessengerMenuController messengerMenuController;

    private AccountHandler accountHandler;

    private CommandHandler userController;

    private UserEventController userEventController;
    private PollController pollController;

    private ProgramGenerator programGenerator;

    private AdminPresenterTextUI adui;
    private OrganizerPresenterTextUI oui;
    private AttendeePresenterTextUI aui;
    private SpeakerPresenterTextUI sui;
    private TextUI ui;
    private PollUI pui;
    private ErrorHandler errorHandler;
    private LandingMenu landingMenu;

    private UserFactory userFactory;


    /**
     * Constructor method to initialize a new MasterSystem instance with the instances of the use case classes
     * with data loaded from the database
     * @param attendeeManager: instance of AttendeeManager
     * @param organizerManager: instance of OrganizerManager
     * @param speakerManager: instance of SpeakerManager
     * @param adminManager: instance of AdminManager
     * @param messageManager: instance of MessageManager
     * @param conversationManager: instance of ConversationManager
     * @param eventManager: instance of EventManager
     * @param roomManager: instance of RoomManager
     */
    public MasterSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                        AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                        EventManager eventManager, RoomManager roomManager, ProgramGenerator programGenerator, PollManager pollManager) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.messageManager = messageManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.pollManager = pollManager;
        this.programGenerator = programGenerator;
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager, adminManager);
        this.userEventController = new UserEventController(attendeeManager, organizerManager, speakerManager, eventManager, roomManager);
        this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager, organizerManager, speakerManager, eventManager, accountHandler, conversationManager, adminManager);
        this.conversationMenuController = new ConversationMenuController(conversationManager, messageManager);

        this.adui = new AdminPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.aui = new AttendeePresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.oui = new OrganizerPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.sui = new SpeakerPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.ui = new TextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.pui = new PollUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager,
                eventManager,roomManager);
        this.landingMenu = new LandingMenu();
        this.errorHandler = new ErrorHandler();

        this.pollController = new PollController(pollManager, speakerManager, pui);

        this.userFactory = new UserFactory(attendeeManager, organizerManager, speakerManager, adminManager, accountHandler, eventManager,
                messageManager, userEventController, roomManager, aui, oui, sui, adui, messengerMenuController, conversationManager,
                conversationMenuController, errorHandler, pollController);

        this.eventsSearchEngine = new EventsSearchEngine(eventManager, ui);
    }


    /**
     * A run method that is responsible for the flow of the program by taking user input,
     * using controllers to execute actions and displaying the result using the UI.
     * Interacts with the LandingMenuPresenter to present a landing menu for the user to select options from:
     * Option 0: Quits the program and saves any information to a database
     * Option 1: Logging in the user provided that they enter correct information when prompted
     * Option 2: Sign up a user (attendee) provided that the user name entered does not already exist
     * Once the user is logged in, they will be prompted their appropriate user menu and each menu (Presenter/View)
     * has its own associated controller that handles all the input in their respective menus. After the user logs out,
     * the system then writes to the database and prompts the landing menu again
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String currentUsername = null;
        String currentAccountType = null;
        boolean loggedIn = false;

        while(!loggedIn) {

            String tempUsername;
            String tempPassword;
            String tempAccountType;

            landingMenu.landingmenu();
            String landingOption = scanner.nextLine();

            switch (landingOption) {
                case "0":
                    programGenerator.writeToDatabase();
                    return;
                case "1":
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    tempAccountType = accountHandler.login(tempUsername, tempPassword);

                    if (tempAccountType != null) {
                        currentUsername = tempUsername;
                        currentAccountType = tempAccountType;
                        loggedIn = true;
                    } else {
                        ui.showPrompt("LF");
                    }
                    break;


                case "2":
                    ui.signupmenu();
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    if (accountHandler.signup(tempUsername, tempPassword, "attendee")) {
                        ui.showPrompt("UC");
                    } else {
                        ui.showPrompt("SF");
                    }
                    break;
                case "3":
                    ui.searchEngineMenu();
                    String inputParameter = scanner.nextLine();
                    String[] parameters = inputParameter.split(",");
                    for(int i = 0; i < parameters.length; i++){
                        parameters[i] =  parameters[i].trim();
                    }
                    eventsSearchEngine.eventSearchWithNumericParameters(parameters);
                    break;
                default:
                    errorHandler.showError("INO");
            }

            while(loggedIn) {
                boolean inMenu = true;
                this.userController = userFactory.getUserController(currentAccountType);
                switch(currentAccountType) {
                    case "attendee":
                        while(inMenu){
                            aui.attendeemenu(currentUsername);
                            inMenu = userController.handleCommand(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.writeToDatabase();
                        break;

                    case "organizer":
                        while(inMenu){
                            oui.organizermenu(currentUsername);
                            inMenu = userController.handleCommand(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.writeToDatabase();
                        break;
                    case "speaker":
                        while(inMenu){
                            sui.speakermenu(currentUsername);
                            inMenu = userController.handleCommand(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.writeToDatabase();
                        break;
                    case "admin":
                        while(inMenu){
                            adui.adminmenu(currentUsername);
                            inMenu = userController.handleCommand(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.writeToDatabase();
                        break;
                }
            }
        }
    }

}
