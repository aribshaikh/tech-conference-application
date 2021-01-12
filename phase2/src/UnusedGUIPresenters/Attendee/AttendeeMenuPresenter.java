package UnusedGUIPresenters.Attendee;


import UnusedGUIPresenters.Interfaces.IAttendeeMenu;

public class AttendeeMenuPresenter implements IAttendeeMenu {
//    @FXML
//    private Button toEventsFromAttendee;
//
//    @FXML
//    private Button toMessagingFromAttendee;
//
//    @FXML
//    private Button signOut;
//
//    @FXML
//    private Label welcome;
//
//    private LoginMenuController loginMenuController;
//    private ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    public AttendeeMenuPresenter(){
//
//    }
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        welcome.setText("Welcome!" + loginMenuController.getCurrUsername() + "!");
//
//    }
//
//    @FXML
//    public void initialize(){
//        toEventsFromAttendee.setText("Events");
//        toEventsFromAttendee.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toEventsFromAttendee.setOnAction(event -> {
//            try {
//                goToEvents();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        toMessagingFromAttendee.setText("Messaging");
//        toMessagingFromAttendee.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toMessagingFromAttendee.setOnAction(event -> {
//            try {
//                goToMessaging();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        signOut.setText("Sign Out");
//        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        signOut.setOnAction(event -> {
//            try {
//                signOut();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//
//    }
//
//    private void goToEvents() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeEventMenuView.fxml"));
//        Stage stage = (Stage) toEventsFromAttendee.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeEventMenuPresenter attendeeEventMenuPresenter = loader.getController();
//        attendeeEventMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//
//    }
//    private void goToMessaging() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMessagingMenuView.fxml"));
//        Stage stage = (Stage) toMessagingFromAttendee.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeMessagingMenuPresenter attendeeMessagingMenuPresenter = loader.getController();
//        attendeeMessagingMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void signOut() throws IOException{
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }




}