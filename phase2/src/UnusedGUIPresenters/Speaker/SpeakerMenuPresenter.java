package UnusedGUIPresenters.Speaker;


import UnusedGUIPresenters.Interfaces.ISpeakerMenu;
// import javafx.scene.control.Label;


public class SpeakerMenuPresenter implements ISpeakerMenu {


//    @FXML
//    private Button toEventsFromSpeaker;
//
//    @FXML
//    private Button toMessagingFromSpeaker;
//
//    @FXML
//    private Button signOut;
//
//    private  ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//
//    public SpeakerMenuPresenter(){
//
//    }
//
//    @FXML
//    private void initialize(){
//        toEventsFromSpeaker.setText("Events");
//        toEventsFromSpeaker.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toEventsFromSpeaker.setOnAction(event -> {
//            try {
//                goToEvents();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        toMessagingFromSpeaker.setText("Messaging");
//        toMessagingFromSpeaker.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        toMessagingFromSpeaker.setOnAction(event -> {
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
//    }
//
//    /**
//     * Redirects user to the event menu
//     * @throws IOException
//     */
//    private void goToEvents() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerEventMenuView.fxml"));
//        Stage stage = (Stage) toEventsFromSpeaker.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        SpeakerEventMenuPresenter speakerEventMenuPresenter = loader.getController();
//        speakerEventMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//
//    }
//
//    /**
//     * Redirects user to the messaging menu
//     * @throws IOException
//     */
//    private void goToMessaging() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerMessagingMenuView.fxml"));
//        Stage stage = (Stage) toMessagingFromSpeaker.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        SpeakerMessagingMenuPresenter speakerMessagingMenuPresenter = loader.getController();
//        speakerMessagingMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    /**
//     * Redirects user to the login menu
//     * @throws IOException
//     */
//    private void signOut() throws IOException{
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.programGenerator = masterSystem.getProgramGenerator();
//    }


}

