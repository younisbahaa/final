package final3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientDatabase {

    static Connection ptconn = null;

    public static void connDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ptconn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_appointments", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Patient> mydata() {

        if (ptconn == null) {
            connDB();
        }

        ObservableList<Patient> ptdData = FXCollections.observableArrayList();

        try {
            PreparedStatement getdata = ptconn.prepareStatement("SELECT * FROM USERS");
            ResultSet data = getdata.executeQuery();
            while (data.next()) {
                int x1 = data.getInt("id");
                String x2 = data.getString("username");
                String x3 = data.getString("firstname");
                String x4 = data.getString("lastname");
                int x5 = data.getInt("age");
                String x6 = data.getString("email");
                int x7 = data.getInt("phone");
                String x8 = data.getString("gender");
                
                Patient Std = new Patient(x1, x2, x3, x4,x5,x6,x7,x8);

                ptdData.add(Std);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ptdData;
    }

    public static void addPatient(Patient ptd) {

        if (ptconn == null) {
            connDB();
        }

        try {
            PreparedStatement insertStatment = ptconn.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?)");
            insertStatment.setInt(1, ptd.getId());
            insertStatment.setString(2, ptd.getUsername());
             insertStatment.setString(3, ptd.getFirstname());
             insertStatment.setString(4, ptd.getLastname());
             insertStatment.setInt(5, ptd.getAge());
            insertStatment.setString(6, ptd.getEmail());
            insertStatment.setInt(7, ptd.getPhone());
            insertStatment.setString(8, ptd.getGender());
            insertStatment.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Not Insert , Try Agin");
        }

    }

public static void updatepatient(int id, Patient ptd) {
    if (ptconn == null) {
        connDB();
    }

    try {
        PreparedStatement updateStatement = ptconn.prepareStatement("UPDATE USERS SET id=?, username=? , firstname=?,lastname=?,age=?,email=?,phone=?,gender=? WHERE id=?");
        updateStatement.setInt(1, ptd.getId());
        updateStatement.setString(2, ptd.getUsername());
        updateStatement.setString(3, ptd.getFirstname());
        updateStatement.setString(4, ptd.getLastname());
        updateStatement.setInt(5, ptd.getAge());
        updateStatement.setString(6, ptd.getEmail());
        updateStatement.setInt(7, ptd.getPhone());
        updateStatement.setString(8, ptd.getGender());
        updateStatement.setInt(9, id);  
        updateStatement.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Not Updated, Try Again");
    }
}

    public static void deletePatient(int id) {
        if (ptconn == null) {
            connDB();
        }

        try {
            PreparedStatement deleteStatment = ptconn.prepareStatement("DELETE FROM USERS WHERE id = ?");
            deleteStatment.setInt(1, id);
            int rowdel = deleteStatment.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Not Delete , Try Agin");
        }

    }
    public static ObservableList<Patient> myqydata(String qu) {

        if (ptconn == null) {
            connDB();
        }

        ObservableList<Patient> ptdData = FXCollections.observableArrayList();

        try {
            PreparedStatement getdata = ptconn.prepareStatement(qu);
            ResultSet data = getdata.executeQuery();
            while (data.next()) {
                int x1 = data.getInt("id");
                String x2 = data.getString("username");
                String x3 = data.getString("firstname");
                String x4 = data.getString("lastname");
                
                int x5 = data.getInt("age");
                 String x6 = data.getString("email");
                 int x7 = data.getInt("phone");
                  String x8 = data.getString("gender");
                Patient Std = new Patient(x1, x2, x3, x4,x5,x6,x7,x8);
                ptdData.add(Std);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ptdData;
    }


}
