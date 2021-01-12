package UnusedGUIPresenters.Attendee;

public class AttendeeMessagingMenuPresenter {
//    @FXML
//    private ListView<HBox> conversations;
//    @FXML
//    private Label welcome;
//    @FXML
//    private Button messenger;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Button goBack;
//
//    private  LoginMenuController loginMenuController;
//    private  AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController;
//    private  ConversationMenuController conversationMenuController;
//    private  ProgramGenerator programGenerator;
//
//    private MasterSystem masterSystem;
//
//    public AttendeeMessagingMenuPresenter(){
//
//    }
//
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.conversationMenuController = masterSystem.getConversationMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        this.attendeeMessagingDashboardMenuController = masterSystem.getAttendeeMessagingDashboardMenuController();
//        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
//        loadConversations();
//    }
//
//
//    @FXML
//    private void initialize(){
//        messenger.setText("Messenger");
//        messenger.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        messenger.setOnAction(event -> {
//            try {
//                goToMessenger();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        });
//        signOut.setText("Sign Out");
//        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        signOut.setOnAction(event -> {
//            try {
//                signOut();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        });
//        goBack.setText("Go Back");
//        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        goBack.setOnAction(event -> {
//            try {
//                goBack();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        });
//
//
//    }
//
//    private void loadConversations(){
//        conversations.getItems().clear();
//        List<String> conversationIDs = attendeeMessagingDashboardMenuController.getConversations(loginMenuController.getCurrUsername());
//        for (String conversationID: conversationIDs){
//            List<String> recipientsOfConversation = attendeeMessagingDashboardMenuController.getConvoParticipants(conversationID);
//            Label count = new Label();
//            count.setText("Conversation ID: " + conversationID + ";");
//            Label participants = new Label();
//            StringBuilder recipients = new StringBuilder();
//            for (String recipient: recipientsOfConversation){
//                recipients.append(recipient);
//                recipients.append(", ");
//            }
//            participants.setText("Participants: " + recipients);
//            Button viewConversation = new Button("View Conversation");
//            viewConversation.setOnAction(event -> {
//                try {
//                    conversationMenuController.setCurrentConversationID(conversationID);
//                    conversationMenuController.setConversationInformation(participants.getText());
//                    viewConversation();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            HBox hBox = new HBox(count, participants, viewConversation);
//            hBox.setSpacing(10);
//            conversations.getItems().add(hBox);
//        }
//    }
//
//    private void viewConversation() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeConversationMenuView.fxml"));
//        Stage stage = (Stage) conversations.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeConversationMenuPresenter attendeeConversationMenuPresenter = loader.getController();
//        attendeeConversationMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void goToMessenger() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMessengerMenuView.fxml"));
//        Stage stage = (Stage) messenger.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeMessengerMenuPresenter attendeeMessengerMenuPresenter = loader.getController();
//        attendeeMessengerMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//    private void goBack() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMenuView.fxml"));
//        Stage stage = (Stage) goBack.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeMenuPresenter attendeeMenuPresenter = loader.getController();
//        attendeeMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void signOut() throws IOException {
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }

}
