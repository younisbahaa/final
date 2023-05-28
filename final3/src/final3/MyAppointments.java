/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final3;

/**
 *
 * @author Yuns
 */
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class MyAppointments implements Initializable {

    @FXML
    private TableView<Appointments> AppointmentsTable;
    @FXML
    private TableColumn<Appointments,Integer> idCol;
    @FXML
    private TableColumn<Appointments,String> dayCol;
    @FXML
    private TableColumn<Appointments,String> doctorCol;
    @FXML
    private TextField IDTextField;
    @FXML
    private TextField dayTextField;
    @FXML
    private TextField doctorTextField;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button ViewStudentsButton;
    @FXML
private Button backButton;
    
    ObservableList<Appointments> courseList;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            PatientDatabase.connDB();
        courseList = FXCollections.observableArrayList();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("appointment_day"));
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
     
        courseList = AppointmentsDatabase.getallAppointments();
        AppointmentsTable.setItems(courseList);
        AppointmentsTable.refresh();
    }    



    @FXML
    private void EventaddAppointments(ActionEvent event) {
         int id = Integer.parseInt(IDTextField.getText());
        String appointment_day = dayTextField.getText();
        String doctor = doctorTextField.getText();
        Appointments s = new Appointments(id, appointment_day, doctor);
        courseList.add(s);
        AppointmentsDatabase.addAppointment(s);
        AppointmentsTable.refresh();
        
    }

    @FXML
    private void EventviewAppointments(ActionEvent event) throws IOException {
        
        Parent p = FXMLLoader.load(getClass().getResource("FXMLRegster.fxml"));
        Scene s = new Scene(p);
        Final3.regstage.setScene(s);
        Final3.regstage.setTitle("regster ");
        Final3.regstage.show();
        Final3.appintmentsStage.hide();
        
    }
   @FXML
private void handleBackButton(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLPatient.fxml"));
    Scene scene = new Scene(root);
    Final3.patientStage.setScene(scene);
    Final3.patientStage.setTitle("Patient");
    Final3.patientStage.show();
    Final3.appintmentsStage.hide();
}
    
}
