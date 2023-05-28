/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author FUAD
 */
public class FXMLRegsterController implements Initializable {

    @FXML
    private TableView<regs> semeTable;
    @FXML
    private TableColumn<regs, Integer> patient;
    @FXML
    private TableColumn<regs, Integer> apointmet;
    @FXML
    private TableColumn<regs, String> status;
    @FXML
    private TextField patientid;
    @FXML
    private TextField appoitmentid;
    @FXML
    private TextField statusid;
     @FXML
private Button backButton;
    private int selectedIndex;

    ObservableList<regs> stdcLits;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
 @Override
public void initialize(URL url, ResourceBundle rb) {
    stdcLits = FXCollections.observableArrayList();
    stdcLits.addAll(AppointmentsDatabase.getdatastd());

    semeTable.setItems(stdcLits);
    patient.setCellValueFactory(new PropertyValueFactory<>("stdid"));
    apointmet.setCellValueFactory(new PropertyValueFactory<>("coid"));
    status.setCellValueFactory(new PropertyValueFactory<>("seme"));
}


    @FXML
    private void addseme(ActionEvent event) {
        int stId = Integer.parseInt(patientid.getText());
        int coId = Integer.parseInt(appoitmentid.getText());
        String semes = statusid.getText();

        regs reg1 = new regs(stId, coId, semes);

        stdcLits.add(reg1);
        AppointmentsDatabase.addseme(reg1);
        semeTable.refresh();
    }

    @FXML
    private void deleteseme(ActionEvent event) {
        selectedIndex = semeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            regs datasel = stdcLits.get(selectedIndex);
            AppointmentsDatabase.delseme(datasel.getStdid());
            stdcLits.remove(selectedIndex);
            semeTable.refresh();
        }
    }

    @FXML
    private void loadData(MouseEvent event) {
        selectedIndex = semeTable.getSelectionModel().getSelectedIndex();
    }
    @FXML
private void handleBackButton(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLMyAppointments.fxml"));
    Scene scene = new Scene(root);
    Final3.patientStage.setScene(scene);
    Final3.patientStage.setTitle("Patient");
    Final3.patientStage.show();
    Final3.regstage.hide();
    
}
}
