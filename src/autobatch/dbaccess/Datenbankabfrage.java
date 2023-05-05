package autobatch.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;

//Database connection for macos-terminal: source ~/.zshrc
//export PATH="/usr/local/opt/mysql-client/bin:$PATH"
//mysql -u db4 -p'!db4.hfts23?' -h 3.69.96.96 -P 3306 db4

public class Datenbankabfrage {
	
	private String url = "jdbc:mysql://3.69.96.96:3306/";
    private String dbName = "db4";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "db4";
    private String pw = "!db4.hfts23?";

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + dbName, userName, pw);
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT MNR, Nachname, Vorname, email, Telefonnumer, Studiengang, studiendekan, betreuer, Benutzername, Passwort From studenten");

            while (rs.next()) {
                int mnr = rs.getInt("MNR");
                String nachname = rs.getString("Nachname");
                String vorname = rs.getString("Vorname");
                String password = rs.getString("Passwort");
                String username = rs.getString("Benutzername");
                String email = rs.getString("email");
                String phonenumber = rs.getString("Telefonnummer");
                String studiengang = rs.getString("Studiengang");
                String studiendekan = rs.getString("studiendekan");
                String betreuer = rs.getString("betreuer");
                Student student = new Student(mnr, vorname, nachname, password, username, email, phonenumber, studiengang, studiendekan, betreuer);
                students.add(student);
            }

            con.close();
            System.out.println("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    
    public List<Studiendekan> getStudiendekane() {
        List<Studiendekan> studiendekans = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + dbName, userName, pw);
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT email, Nachname, Vorname, Benutzername, Passwort, Studiengang FROM studiendekan");

            while (rs.next()) {
                String email = rs.getString("email");
                String nachname = rs.getString("Nachname");
                String vorname = rs.getString("Vorname");
                String username = rs.getString("Benutzername");
                String password = rs.getString("Passwort");
                String studiengang = rs.getString("Studiengang");
                Studiendekan studiendekan = new Studiendekan(email, nachname, vorname, username, password, studiengang);
                studiendekans.add(studiendekan);
            }

            con.close();
            System.out.println("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studiendekans;
    }

    public List<Betreuer> getBetreuer() {
        List<Betreuer> betreuer = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + dbName, userName, pw);
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT email, Nachname, Vorname, Benutzername, Passwort FROM betreuer");

            while (rs.next()) {
                String email = rs.getString("email");
                String nachname = rs.getString("Nachname");
                String vorname = rs.getString("Vorname");
                String username = rs.getString("Benutzername");
                String password = rs.getString("Passwort");
                Betreuer betreuerobj = new Betreuer(email, nachname, vorname, username, password);
                betreuer.add(betreuerobj);
            }

            con.close();
            System.out.println("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return betreuer;
    }

    
    
    public boolean searchAllTablesByUsernameAndPassword(String username, String password) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url + dbName, userName, pw);

            // Suche in der Tabelle "Studenten"
            String sqlStudenten = "SELECT * FROM studenten WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            try (Statement stmtStudenten = conn.createStatement();
                 ResultSet rsStudenten = stmtStudenten.executeQuery(sqlStudenten)) {

                if (rsStudenten.next()) {
                    conn.close();
                    return true;
                }
            }

            // Suche in der Tabelle "Betreuer"
            String sqlBetreuer = "SELECT * FROM betreuer WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            try (Statement stmtBetreuer = conn.createStatement();
                 ResultSet rsBetreuer = stmtBetreuer.executeQuery(sqlBetreuer)) {

                if (rsBetreuer.next()) {
                    conn.close();
                    return true;
                }
            }

            // Suche in der Tabelle "Studiendekan"
            String sqlStudiendekan = "SELECT * FROM studiendekan WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            try (Statement stmtStudiendekan = conn.createStatement();
                 ResultSet rsStudiendekan = stmtStudiendekan.executeQuery(sqlStudiendekan)) {

                if (rsStudiendekan.next()) {
                    conn.close();
                    return true;
                }
            }

            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Sucht in der DB nach einen username und gibt true zurück, falls einer existiert
    public boolean isUsernameTaken(String username) {
        String query = "SELECT 1 FROM (SELECT Benutzername FROM studenten UNION SELECT Benutzername FROM betreuer UNION SELECT Benutzername FROM studiendekan) AS all_users WHERE Benutzername = ?";
        try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
}
