package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Datenbankabfrage {

    public void holeStudenten() {
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

            rs = stmt.executeQuery("SELECT MNR, Nachname, Vorname From studenten");

            while (rs.next()) {
                int mnr = rs.getInt("MNR");
                String nachname = rs.getString("Nachname");
                String vorname = rs.getString("Vorname");
                System.out.println("Matrikelnummer: " + mnr + ", Nachname: " + nachname + ", Vorname: " + vorname);
            }

            con.close();
            System.out.println("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
