package UnusedGUIPresenters;


public class SignUpMenuPresenter{

//    private SignUpMenuController signUpMenuController;
//    private ProgramGenerator programGenerator;
//    @FXML
//    private TextField createUsername;
//    @FXML
//    private PasswordField createPassword;
//    @FXML
//    private Button signUp;
//    @FXML
//    private Button toLoginFromSignUp;
//    private MasterSystem masterSystem;
//
//
//
//   public SignUpMenuPresenter(){
//
//   }
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//       this.signUpMenuController = masterSystem.getSignUpMenuController();
//       this.programGenerator = masterSystem.getProgramGenerator();
//    }
//    @FXML
//    public void initialize(){
//        signUp.setText("Sign Up");
//        signUp.setOnAction(event -> { signUpAttendee(); });
//        signUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        toLoginFromSignUp.setText("Login");
//        toLoginFromSignUp.setOnAction(event -> {
//            try {
//                returnToLogin();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        toLoginFromSignUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        createUsername.setPromptText("Username");
//        createPassword.setPromptText("Password");
//
//    }
//
//    public void returnToLogin() throws IOException {
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) toLoginFromSignUp.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//
//    private void signUpAttendee(){
//        if (signUpMenuController.signUp(createUsername.getText(), createPassword.getText())){
//            try {
//                returnToLogin();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            invalidUser();
//        }
//   }
//
//    public void invalidUser() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Something went wrong");
//        alert.setHeaderText("Username and/or Password is not valid");
//        alert.setContentText("Please look into it");
//        createUsername.clear();
//        createPassword.clear();
//        createUsername.setPromptText("Username");
//        createPassword.setPromptText("Password");
//
//    }

}