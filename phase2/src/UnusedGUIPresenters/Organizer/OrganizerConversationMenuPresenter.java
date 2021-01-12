package UnusedGUIPresenters.Organizer;

public class OrganizerConversationMenuPresenter {
//    @FXML
//    private Label username;
//    @FXML
//    private Button goBack;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Label description;
//    @FXML
//    private ListView<Label> messages;
//    @FXML
//    private Button reply;
//
//    private MasterSystem masterSystem;
//    private  ProgramGenerator programGenerator;
//    private  LoginMenuController loginMenuController;
//    private  ConversationMenuController conversationMenuController;
//    private  SceneHandler sceneHandler;
//
//    public OrganizerConversationMenuPresenter(){
//
//    }
//
//    @FXML
//    private void initialize(){
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
//
//        reply.setText("Reply");
//        reply.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        reply.setOnAction(event -> {
//            try {
//                reply();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * Loads the messages onto GUI by calling the methods in conversationMenuController
//     */
//    private void loadMessages(){
//        String currentConversationID = conversationMenuController.getCurrentConversationID();
//        List<String> messages1 = conversationMenuController.orderedMessagesInConvo(currentConversationID);
//        for (String message: messages1){
//            Label messageLabel = new Label();
//            messageLabel.setText(message);
//            messages.getItems().add(messageLabel);
//        }
//    }
//
//    /**
//     * Switches scene to the messengermenuview to allow the user to reply to the given conversation
//     * @throws IOException
//     */
//    private void reply() throws IOException {
//        sceneHandler.storeScene(reply.getScene());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessengerMenuView.fxml"));
//        Stage stage = (Stage) reply.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }
//
//    /**
//     * Allows user to go back to the previous menu
//     * @throws IOException
//     */
//    private void goBack() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessagingMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }
//
//    /**
//     * Allows user to sign out and redirect to the login menu
//     * @throws IOException
//     */
//    private void signOut() throws IOException {
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        MasterSystem masterSystem = programGenerator.readFromDatabase();
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.conversationMenuController = masterSystem.getConversationMenuController();
//        this.sceneHandler = masterSystem.getSceneHandler();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        username.setText(loginMenuController.getCurrUsername());
//        description.setText(conversationMenuController.getConversationInformation());
//        loadMessages();
//    }

}
