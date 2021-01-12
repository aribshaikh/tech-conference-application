package UnusedGUIPresenters.Organizer;

public class OrganizerEventCreationMenuPresenter {
//    private RoomManager roomManager;
//    private OrganizerMenuController organizerMenuController;
//    private UserEventController userEventController;
//    private ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    @FXML
//    public Label username;
//
//    @FXML
//    public Label whatsOnDisplay;
//
//    @FXML
//    public CheckBox audioSystemCheck;
//
//    @FXML
//    public CheckBox projectorCheck;
//
//    @FXML
//    public ChoiceBox<String> startTimeChoices;
//
//    @FXML
//    public ComboBox<String> durationChoices;
//
//    @FXML
//    public Button findRoomButton;
//
//    @FXML
//    public TextField eventNameField;
//
//    @FXML
//    public TextField capacityField;
//
//    @FXML
//    public ListView<String> selectedSpeakerDisplay;
//    ObservableList<String> speakers = FXCollections.observableArrayList();
//
//    @FXML
//    public Button findSpeakerButton;
//
//    @FXML
//    public TextField roomNumberDisplay;
//
//    @FXML
//    public Button createEventButton;
//
//    @FXML
//    public Button backButton;
//
//    @FXML
//    public Button signOutButton;
//
//    @FXML
//    public TextField powerSocketField;
//
//    @FXML
//    public ListView<String> displayListView;
//
//    public OrganizerEventCreationMenuPresenter(){
//
//    }
//
//    public void initialize(){
//
//        selectedSpeakerDisplay.getItems().clear();
//        selectedSpeakerDisplay.setItems(speakers);
//        ObservableList<String> startTimeChoiceList = FXCollections.observableArrayList("9", "10", "11", "12", "1", "2", "3", "4");
//        ObservableList<String> durationChoiceList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8");
//        startTimeChoices.setItems(startTimeChoiceList);
//        durationChoices.setItems(durationChoiceList);
//
//        backButton.setOnAction(event -> {
//            try {
//                goBack();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        signOutButton.setOnAction(event -> {
//            try {
//                signOut();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @FXML
//    private void displayRooms(){
//        if(powerSocketField.getText() == null || startTimeChoices.getValue() == null || durationChoices.getValue() == null){
//            resetMenu();
//        }
//        displayListView.getItems().clear();
//        ObservableList<String> rooms = FXCollections.observableArrayList();
//        rooms.addAll(roomManager.roomsWithRequirements(audioSystemCheck.isSelected(), projectorCheck.isSelected(),
//                Integer.parseInt(powerSocketField.getText()), startTimeChoices.getValue(), Integer.parseInt(durationChoices.getValue())));
//        displayListView.setItems(rooms);
//        displayListView.setOnMouseClicked(event -> displayToRoomNumber());
//    }
//
//    private void displayToRoomNumber(){
//        roomNumberDisplay.clear();
//        roomNumberDisplay.setText(displayListView.getSelectionModel().getSelectedItem());
//    }
//
//    @FXML
//    private void displaySpeakers(){
//        displayListView.getItems().clear();
//        ObservableList<String> speakers = FXCollections.observableArrayList();
//        speakers.addAll(organizerMenuController.listOfUsers("speaker"));
//        displayListView.setItems(speakers);
//        displayListView.setOnMouseClicked(event -> addSpeaker());
//    }
//
//
//    private void addSpeaker() {
//        String selectedSpeaker = displayListView.getSelectionModel().getSelectedItem();
//        displayListView.getItems().remove(selectedSpeaker);
//        selectedSpeakerDisplay.getItems().add(selectedSpeaker);
//    }
//
//    @FXML
//    private void removeSelectedSpeaker(){
//        String selectedSpeaker = selectedSpeakerDisplay.getSelectionModel().getSelectedItem();
//        selectedSpeakerDisplay.getItems().remove(selectedSpeaker);
//        displayListView.getItems().add(selectedSpeaker);
//    }
//
//    @FXML
//    private void createEvent(){
//        String result = userEventController.createEventInRoom(username.getText(), eventNameField.getText(),
//                startTimeChoices.getValue(), Integer.parseInt(durationChoices.getValue()), Integer.parseInt(capacityField.getText()),
//                selectedSpeakerDisplay.getItems(), roomNumberDisplay.getText());
//        resetMenu();
//    }
//
//    @FXML
//    private void goBack() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
//        organizerMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    @FXML
//    private void signOut() throws IOException {
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOutButton.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        MasterSystem masterSystem = programGenerator.readFromDatabase();
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//
//    private void resetMenu(){
//        roomNumberDisplay.clear();
//        roomNumberDisplay.setPromptText("Room number");
//        eventNameField.clear();
//        eventNameField.setPromptText("Event name");
//        capacityField.clear();
//        capacityField.setPromptText("Capacity");
//        audioSystemCheck.setSelected(false);
//        projectorCheck.setSelected(false);
//        powerSocketField.clear();
//        selectedSpeakerDisplay.getItems().clear();
//    }
//
//    public void setMasterSystem(MasterSystem masterSystem) {
//        this.masterSystem = masterSystem;
//        this.roomManager = masterSystem.getRoomManager();
//        this.organizerMenuController = masterSystem.getOrganizerMenuController();
//        this.userEventController = masterSystem.getUserEventController();
//        LoginMenuController loginMenuController = masterSystem.getLoginMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        username.setText(loginMenuController.getCurrUsername());
//    }
}
