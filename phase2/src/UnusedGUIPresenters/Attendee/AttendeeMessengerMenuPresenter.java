package UnusedGUIPresenters.Attendee;

public class AttendeeMessengerMenuPresenter {


//    @FXML
//    private TextField recipientIDs;
//    @FXML
//    private TextArea content;
//    @FXML
//    private Button sendMessage;
//    @FXML
//    private Button goBack;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Label welcome;
//    @FXML
//    private CheckBox allRecipients;
//    @FXML
//    private Label convoRecipients;
//
//
//    private MasterSystem masterSystem;
//    private MessengerMenuController messengerMenuController;
//    private LoginMenuController loginMenuController;
//    private SceneHandler sceneHandler;
//    private ConversationMenuController conversationMenuController;
//    private ProgramGenerator programGenerator;
//
//    public AttendeeMessengerMenuPresenter(){
//
//    }
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.messengerMenuController = masterSystem.getMessengerMenuController();
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.sceneHandler = masterSystem.getSceneHandler();
//        this.conversationMenuController = masterSystem.getConversationMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
//
//        try {
//            setPrivilegesAttendee();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        sendMessage.setOnAction(event -> {
//            try {
//                if (!content.getText().equals("") && !recipientIDs.getText().equals("")){
//                    sendMessage();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        signOut.setOnAction(event -> {
//            try {
//                signOut();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @FXML
//    private void initialize(){
//
//        convoRecipients.setText("");
//        convoRecipients.setVisible(false);
//
//        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        sendMessage.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//    }
//
//    private void goBack() throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeConversationMenuView.fxml"));
//        Stage stage = (Stage) goBack.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeConversationMenuPresenter attendeeConversationMenuPresenter = loader.getController();
//        attendeeConversationMenuPresenter.setMasterSystem(masterSystem);
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
//
//    private void setPrivilegesAttendee() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeConversationMenuView.fxml"));
//        Scene scene = new Scene(loader.load());
//
//        if (!sceneHandler.getScene().equals(scene)) {
//            allRecipients.setOnAction(event -> {
//                recipientIDs.setDisable(allRecipients.isSelected());
//            });
//            recipientIDs.setOnAction(event -> {
//                allRecipients.setDisable(recipientIDs.getText().equals(""));
//            });
//        }
//        else {
//            convoRecipients.setVisible(true);
//            convoRecipients.setText("Convo Recipients: "+ conversationMenuController.getConversationInformation());
//            recipientIDs.setDisable(true);
//            allRecipients.setDisable(true);
//        }
//
//    }
//    private void sendMessage() throws IOException {
//
//        if (allRecipients.isSelected()){
//            String sender = loginMenuController.getCurrUsername();
//            String message = content.getText();
//            for (String recipient: messengerMenuController.getUsersToMessage(sender)){
//                String recipientType = messengerMenuController.getAccountType(recipient);
//                messengerMenuController.attendeeSendMessage(sender, recipient, message, recipientType);
//            }
//            goBack();
//        }
//        if (!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("")){
//            String recipientID = recipientIDs.getText();
//            String sender = loginMenuController.getCurrUsername();
//            String message = content.getText();
//            String receiverType = messengerMenuController.getAccountType(recipientID);
//            if(messengerMenuController.attendeeSendMessage(sender, recipientID, message, receiverType)){
//                goBack();
//            }
//            else{
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Something went wrong");
//                alert.setHeaderText("Something went wrong");
//                alert.setContentText("Please look into it");
//                recipientIDs.clear();
//                recipientIDs.setDisable(false);
//                allRecipients.setDisable(false);
//            }
//            goBack();
//        }
//        if (convoRecipients.isVisible()){
//            conversationMenuController.reply(loginMenuController.getCurrUsername(),
//                    conversationMenuController.getCurrentConversationID(), content.getText());
//        }
//    }
}
