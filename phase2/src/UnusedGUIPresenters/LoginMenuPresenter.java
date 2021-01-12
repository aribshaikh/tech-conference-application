package UnusedGUIPresenters;

public class LoginMenuPresenter{


//    private LoginMenuController loginMenuController;
//    private AccountHandler accountHandler;
//
//    @FXML
//    public TextField usernameField;
//    @FXML
//    private PasswordField passwordField;
//    @FXML
//    private Button loginButton;
//    @FXML
//    private Button signUpFromLogin;
//    private MasterSystem masterSystem;
//
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
////        this.loginMenuController = masterSystem.getLoginMenuController();
////        this.accountHandler = masterSystem.getAccountHandler();
//        loginButton.setOnAction(event -> {
//            try {
//                callUserMenu();
//            } catch (IOException | NullPointerException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    @FXML
//    private void initialize(){
//        //login panel
//        loginButton.setText("Login");
//
//        loginButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        signUpFromLogin.setText("Sign Up");
//        signUpFromLogin.setOnAction(event -> {
//            try {
//                returnToSignUp();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        signUpFromLogin.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//    }
//
//    @FXML
//    private void callUserMenu() throws IOException, NullPointerException {
//        String accountType = accountHandler.login(usernameField.getText(), passwordField.getText());
//
//        switch (accountType) {
//            case "attendee":
//                loginMenuController.setCurrUsername(usernameField.getText());
//                showAttendeeMenu();
//            case "organizer":
//                loginMenuController.setCurrUsername(usernameField.getText());
//                showOrganizerMenu();
//            case "speaker":
//                loginMenuController.setCurrUsername(usernameField.getText());
//                showSpeakerMenu();
////                case "admin":
////                    loginMenuController.setCurrUsername(usernameField.getText());
////                    showAdminMenu(usernameField.getText());
//        }
//
//    }
//
//    private void returnToSignUp() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SignUpMenuView.fxml"));
//        Stage stage = (Stage) signUpFromLogin.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        SignUpMenuPresenter signUpMenuPresenter = loader.getController();
//        signUpMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//
//
//
//    public void showAttendeeMenu() throws IOException, NullPointerException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMenuView.fxml"));
//        Stage stage = (Stage) loginButton.getScene().getWindow();
//        Scene attendeeMenuScene = new Scene(loader.load());
//
//        AttendeeMenuPresenter attendeeMenuPresenter = loader.getController();
//        attendeeMenuPresenter.setMasterSystem(masterSystem);
//
//        stage.setScene(attendeeMenuScene);
//    }
//
//
//
//    public void showOrganizerMenu() throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
//        try {
//            Stage stage = (Stage) loginButton.getScene().getWindow();
//            Scene organizerMenuScene = new Scene(loader.load());
//            stage.setScene(organizerMenuScene);
//            OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
//            organizerMenuPresenter.setMasterSystem(masterSystem);
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
//        organizerMenuPresenter.setMasterSystem(masterSystem);
//
//
//
//    }
//
//
//    public void showSpeakerMenu() throws IOException, NullPointerException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerMenuView.fxml"));
//        try {
//            Stage stage = (Stage) loginButton.getScene().getWindow();
//            Scene speakerMenuScene = new Scene(loader.load());
//            stage.setScene(speakerMenuScene);
//            SpeakerMenuPresenter speakerMenuPresenter = loader.getController();
//            speakerMenuPresenter.setMasterSystem(masterSystem);
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }

//    public void showAdminMenu(String username) throws IOException{
//        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/UI/Admin/AdminMenuView.fxml"));
//            Stage stage = (Stage) loginButton.getScene().getWindow();
//        Scene speakerMenuScene = new Scene(loader.load());
//
//        AdminMenuPresenter adminMenuPresenter = loader.getController();
//        adminMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(speakerMenuScene);
//    }
}
