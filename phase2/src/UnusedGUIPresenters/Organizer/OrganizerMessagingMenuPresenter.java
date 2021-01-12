package UnusedGUIPresenters.Organizer;

import Controllers.*;
import Gateways.ProgramGenerator;
import UnusedGUIPresenters.LoginMenuPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class OrganizerMessagingMenuPresenter {

//    @FXML
//    private ListView<HBox> conversations;
//    @FXML
//    private Label username;
//    @FXML
//    private Button messenger;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Button goBack;
//
//    private  ConversationMenuController conversationMenuController;
//    private OrganizerMessagingDashboardMenuController organizerMessagingDashboardMenuController;
//    private  LoginMenuController loginMenuController;
//    private  ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    public OrganizerMessagingMenuPresenter(){
//    }
//
//
//    @FXML
//    private void initialize(){
//
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
//        goBack.setText("Go back");
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
//    }
//
//    /**
//     * Loads all conversations of the user by interacting with conversationmenucontroller
//     */
//    private void loadConversations(){
//        List<String> conversationIDs = organizerMessagingDashboardMenuController.getConversations(loginMenuController.getCurrUsername());
//        int i = 0;
//        for (String conversationID: conversationIDs){
//            List<String> recipientsOfConversation = organizerMessagingDashboardMenuController.getConvoParticipants(conversationID);
//            Label count = new Label();
//            count.setText("Conversation ID " + conversationID + ";");
//            Label participants = new Label();
//            StringBuilder recipients = new StringBuilder();
//            for (String recipient: recipientsOfConversation){
//                recipients.append(recipient);
//                recipients.append(", ");
//            }
//            participants.setText("Participants" + recipients);
//            Button viewConversation = new Button("View Conversation");
//            int finalI = i;
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
//            i+=1;
//        }
//    }
//
//    /**
//     * Redirects user to speakerconversationmenuview which shows all conversations of user
//     * @throws IOException
//     */
//    private void viewConversation() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerConversationMenuView.fxml"));
//        Stage stage = (Stage) conversations.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerConversationMenuPresenter organizerConversationMenuPresenter = loader.getController();
//        organizerConversationMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    /**
//     * Redirects user back to messengermenuview
//     * @throws IOException
//     */
//    private void goToMessenger() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessengerMenuView.fxml"));
//        Stage stage = (Stage) messenger.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerMessengerMenuPresenter organizerMessengerMenuPresenter = loader.getController();
//        organizerMessengerMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    /**
//     * Redirects user to login menu view
//     * @throws IOException
//     */
//
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
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.conversationMenuController = masterSystem.getConversationMenuController();
//        this.organizerMessagingDashboardMenuController = masterSystem.getOrganizerMessagingDashboardController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        username.setText(loginMenuController.getCurrUsername());
//        loadConversations();
//    }
//
//    public void goBack() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
//        organizerMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
}
