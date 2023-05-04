package autobatch.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Database connection for macos-terminal: source ~/.zshrc
//export PATH="/usr/local/opt/mysql-client/bin:$PATH"
//mysql -u db4 -p'!db4.hfts23?' -h 3.69.96.96 -P 3306 db4

public class Datenbankabfrage {
	
	private String url = "jdbc:mysql://3.69.96.96:3306/";
    private String dbName = "db4";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "db4";
    private String pw = "!db4.hfts23?";

    public void getStudent() {
        Connection con = null;

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
    
    
    public boolean searchAllTablesByUsernameAndPassword(String username, String password) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url + dbName, userName, pw);

            // Suche in der Tabelle "studenten"
            String sqlStudenten = "SELECT * FROM studenten WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            try (Statement stmtStudenten = conn.createStatement();
                 ResultSet rsStudenten = stmtStudenten.executeQuery(sqlStudenten)) {

                if (rsStudenten.next()) {
                    conn.close();
                    return true;
                }
            }

            // Suche in der Tabelle "betreuer"
            String sqlBetreuer = "SELECT * FROM betreuer WHERE Benutzername = '" + username + "' AND Passwort = '" + password + "'";
            try (Statement stmtBetreuer = conn.createStatement();
                 ResultSet rsBetreuer = stmtBetreuer.executeQuery(sqlBetreuer)) {

                if (rsBetreuer.next()) {
                    conn.close();
                    return true;
                }
            }

            // Suche in der Tabelle "studiendekan"
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


    
}
