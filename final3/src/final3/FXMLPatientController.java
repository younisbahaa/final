package final3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLPatientController implements Initializable {

    @FXML
    private AnchorPane button;
    @FXML
    private TextField idField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField genderField;
    @FXML
    private Button addbutton;
    @FXML
    private Button Updatebutton;
    @FXML
    private Button deletebutton;
    private int rowPatient;
    @FXML
    private Button AddAppointment;
    @FXML
    private TableView<Patient> PatientTable;
    @FXML
    private TableColumn<Patient, Integer> idCol;
    @FXML
    private TableColumn<Patient, String> usernameCol;
    @FXML
    private TableColumn<Patient, String> firstnameCol;
    @FXML
    private TableColumn<Patient, String> lastnameCol;
    @FXML
    private TableColumn<Patient, Integer> ageCol;
    @FXML
    private TableColumn<Patient, String> emailCol;
    @FXML
    private TableColumn<Patient, Integer> phoneCol;
    @FXML
    private TableColumn<Patient, String> genderCol;

    @FXML
    ObservableList<Patient> stdList, newList;
    @FXML
    private TextArea TextAreaShow;
    @FXML
    private Button ShowSelect;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PatientDatabase.connDB();
        stdList = FXCollections.observableArrayList();
        newList = FXCollections.observableArrayList();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        stdList = PatientDatabase.mydata();
        PatientTable.setItems(stdList);
        PatientTable.refresh();

    }

    @FXML
    private void EventAdd(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String username = usernameField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        int phone = Integer.parseInt(phoneField.getText());
        String gender = genderField.getText();

        Patient s = new Patient(id, username, firstname, lastname, age, email, phone, gender);
        stdList.add(s);
        PatientDatabase.addPatient(s);
        PatientTable.refresh();
    }

    @FXML
    private void EventUpdate(ActionEvent event) {
        if (rowPatient != -1) {
            int reqID = stdList.get(rowPatient).getId();
            int id = Integer.parseInt(idField.getText());
            String username = usernameField.getText();
            String firstname = firstnameField.getText();
            String lastname = lastnameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String email = emailField.getText();
            int phone = Integer.parseInt(phoneField.getText());
            String gender = genderField.getText();

            Patient s = new Patient(id, username, firstname, lastname, age, email, phone, gender);
            PatientDatabase.updatepatient(reqID, s);

            stdList.get(rowPatient).setId(id);
            stdList.get(rowPatient).setUsername(username);
            stdList.get(rowPatient).setFirstname(firstname);
            stdList.get(rowPatient).setLastname(lastname);
            stdList.get(rowPatient).setAge(age);
            stdList.get(rowPatient).setEmail(email);
            stdList.get(rowPatient).setPhone(phone);
            stdList.get(rowPatient).setGender(gender);
            PatientTable.refresh();
        }
    }

    @FXML
    private void EventDelete(ActionEvent event) {
        if (rowPatient != -1) {
            Patient delPatient = stdList.get(rowPatient);
            PatientDatabase. deletePatient(delPatient.getId());
            stdList.remove(rowPatient);
            PatientTable.refresh();

            if (PatientTable.getItems().isEmpty()) {
                idField.clear();
                usernameField.clear();
                firstnameField.clear();
                lastnameField.clear();
                ageField.clear();
                emailField.clear();
                phoneField.clear();
                genderField.clear();
            }
        }
    }

    @FXML
    private void EventAddAppointment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMyAppointments.fxml"));
        Scene scene = new Scene(root);
        Final3.appintmentsStage.setScene(scene);
        Final3.appintmentsStage.setTitle("Course");
        Final3.appintmentsStage.show();
        Final3.patientStage.hide();
    }

    @FXML
    private void loaddata(MouseEvent event) {

        rowPatient = PatientTable.getSelectionModel().getSelectedIndex();
    }

    @FXML
    private void EventSelect(ActionEvent event) {

        newList = PatientDatabase.myqydata(TextAreaShow.getText());
        PatientTable.setItems(newList);
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {

        Final3.patientStage.hide();

    }
}
