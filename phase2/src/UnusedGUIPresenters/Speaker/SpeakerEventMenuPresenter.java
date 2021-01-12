package UnusedGUIPresenters.Speaker;

import static javafx.collections.FXCollections.observableArrayList;

public class SpeakerEventMenuPresenter {

//    @FXML
//    private Button goBack;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Label welcome;
//    @FXML
//    private VBox layout;
//    @FXML
//    private ListView<String> eventlist;
//
//    private UserEventController userEventController;
//    private LoginMenuController loginMenuController;
//    private ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    public SpeakerEventMenuPresenter(){
//
//    }
//
//    @FXML
//    private void initialize() {
//
//        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        goBack.setOnAction(event -> {
//            try {
//                goBack();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        signOut.setOnAction(event -> {
//            try {
//                signOut();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * Allows user to go back to previous meny
//     * @throws IOException
//     */
//    private void goBack() throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerMenuView.fxml"));
//        Stage stage = (Stage) goBack.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }
//
//    /**
//     * Allows user to sign out - redirects user back to login menu
//     * @throws IOException
//     */
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
//    /**
//     * Calls methods in usereventcontroller to allow user to see the current list of events
//     */
//    private void seeListofEvents(){
//        eventlist.getItems().clear();
//        String speakerID = loginMenuController.getCurrUsername();
//        List<String> events = userEventController.seeListOfEventsForSpeaker(speakerID);
//
//        ObservableList<String> eventslist = FXCollections.observableArrayList(events);
//        ListView<String> eventlist = new ListView<String>(eventslist);
//        layout.getChildren().addAll(eventlist);
//
//    }
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.userEventController = masterSystem.getUserEventController();
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
//        seeListofEvents();
//    }

}
