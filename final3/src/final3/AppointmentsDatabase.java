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


public class AppointmentsDatabase {

    static Connection Appconn = null;

    public static void appconnDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Appconn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_appointments", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentsDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppointmentsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Appointments> getallAppointments() {
              ObservableList<Appointments> AppointmentsList = FXCollections.observableArrayList();
        try {
        
            if (Appconn == null) {
                appconnDB();
            }
          
                
                
            PreparedStatement getcouse = Appconn.prepareStatement("SELECT * FROM Appointments");
            ResultSet AppointmentData = getcouse.executeQuery();
            
            while (AppointmentData.next()) {
                int id = AppointmentData.getInt("id");
                String appointment_day = AppointmentData.getString("appointment_day");
                String doctor = AppointmentData.getString("doctor");
                
                Appointments Appointment=new Appointments(id, appointment_day, doctor);
                AppointmentsList.add(Appointment);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
          return AppointmentsList ;
    }
    
        public static void addAppointment(Appointments app) {

          if (Appconn == null) {
                appconnDB();
            }
          

        try {
            PreparedStatement insertStatment = Appconn.prepareStatement("INSERT INTO Appointments VALUES (?,?,?)");
            insertStatment.setInt(1, app.getId());
            insertStatment.setString(2, app.getAppointment_day());
            insertStatment.setString(3, app.getDoctor());
            insertStatment.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Not Insert , Try Agin");
        }

    }

    
     public static void addseme(regs re) {

          if (Appconn == null) {
                appconnDB();
            }
          

        try {
            PreparedStatement insertStatment = Appconn.prepareStatement("INSERT INTO booked_appointments VALUES (?,?,?)");
            insertStatment.setInt(1, re.getStdid());
            insertStatment.setInt(2, re.getCoid());
            insertStatment.setString(3, re.getSeme());
            insertStatment.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Not Insert , Try Agin");
        }

    }
    
    
    public static void delseme(int  ptid) {

          if (Appconn == null) {
                appconnDB();
            }
          

        try {
            PreparedStatement insertStatment = Appconn.prepareStatement("DELETE FROM booked_appointments WHERE user_id  = ?");
            insertStatment.setInt(1,  ptid);
            
            insertStatment.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Not Insert , Try Agin");
        }

    }

    
    public static ObservableList<regs> getdatastd() {
        ObservableList <regs> regList = FXCollections.observableArrayList();
          if (Appconn == null) {
                appconnDB();
            }
          

        try {
            PreparedStatement selectStatment = Appconn.prepareStatement("select * from booked_appointments");
                
            ResultSet s = selectStatment.executeQuery();
            while(s.next()){
            
            int ptd = s.getInt("appointments_id");
            int apd = s.getInt("user_id");
            String dd = s.getString("doctor_name");
            
            regs regs = new regs(ptd, apd, dd);
            regList.add(regs);
            }
            
        } catch (SQLException ex) {
            System.err.println("Not Insert , Try Agin");
        }
            return regList;
    }
    
}
