package UnusedGUIPresenters.Organizer;

public class OrganizerMenuPresenter {

//    private LoginMenuController loginMenuController;
//    private ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    @FXML
//    private Label username;
//
//    @FXML
//    private Button toEventsFromOrganizer;
//
//    @FXML
//    private Button toMessagingFromOrganizer;
//
//    @FXML
//    private Button signOutButton;
//
//    @FXML
//    private Button toConferenceFromOrganizer;
//
//    @FXML
//    private Button createEventButton;
//
//    public void initialize(){
//        toEventsFromOrganizer.setText("Events");
//        toEventsFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toEventsFromOrganizer.setOnAction(event -> {
//            try {
//                goToEvents();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        toMessagingFromOrganizer.setText("Messaging");
//        toMessagingFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toMessagingFromOrganizer.setOnAction(event -> {
//            try {
//                goToMessaging();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        toConferenceFromOrganizer.setText("Conference");
//        toConferenceFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toConferenceFromOrganizer.setOnAction(event -> {
//            try {
//                goToConference();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        signOutButton.setText("Sign Out");
//        signOutButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        signOutButton.setOnAction(event -> {
//            try {
//                signOut();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    private void goToEvents() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerEventMenuView.fxml"));
//        Stage stage = (Stage) toEventsFromOrganizer.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerEventMenuPresenter organizerEventMenuPresenter = loader.getController();
//        organizerEventMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//    private void goToMessaging() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessagingMenuView.fxml"));
//        Stage stage = (Stage) toMessagingFromOrganizer.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerMessagingMenuPresenter organizerMessagingMenuPresenter = loader.getController();
//        organizerMessagingMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void goToConference() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerConferenceMenuView.fxml"));
//        Stage stage = (Stage) toConferenceFromOrganizer.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerConferenceMenuPresenter organizerConferenceMenuPresenter = loader.getController();
//        organizerConferenceMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    @FXML
//    private void goToEventCreation() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerEventCreationMenuView.fxml"));
//        Stage stage = (Stage) createEventButton.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerEventCreationMenuPresenter organizerEventCreationMenuPresenter = loader.getController();
//        organizerEventCreationMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void signOut() throws IOException {
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOutButton.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    public void setMasterSystem(MasterSystem masterSystem) {
//        this.masterSystem = masterSystem;
//        this.programGenerator = masterSystem.getProgramGenerator();
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        username.setText(loginMenuController.getCurrUsername());
//    }
}
