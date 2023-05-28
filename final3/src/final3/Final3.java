/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yuns
 */
public class Final3 extends Application {
    static Stage patientStage = new Stage();
    static Stage appintmentsStage = new Stage();
    static Stage regstage = new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPatient.fxml"));
        Scene scene = new Scene(root);
        patientStage.setScene(scene);
        patientStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
