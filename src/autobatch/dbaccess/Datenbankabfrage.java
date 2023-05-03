package autobatch.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Datenbankabfrage {

    public void getStudent() {
        Connection con = null;

        String url = "jdbc:mysql://3.69.96.96:3306/";
        String dbName = "db4";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "db4";
        String pw = "!db4.hfts23?";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + dbName, userName, pw);
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT MNR, Nachname, Vorname, Passwort From studenten");

            while (rs.next()) {
                int mnr = rs.getInt("MNR");
                String nachname = rs.getString("Nachname");
                String vorname = rs.getString("Vorname");
                String password = rs.getString("Passwort");
                System.out.println("Matrikelnummer: " + mnr + ", Nachname: " + nachname + ", Vorname: " + vorname + ", Passwort: " + password);
            }

            con.close();
            System.out.println("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean searchStudentsByUsernameAndPassword(String username, String password) {
        try {
            String url = "jdbc:mysql://3.69.96.96:3306/";
            String dbName = "db4";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "db4";
            String pw = "!db4.hfts23?";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url + dbName, userName, pw);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM studenten WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
