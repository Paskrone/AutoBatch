package autobatch.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.businessobjects.Thema;

//Database connection for macos-terminal: source ~/.zshrc
//export PATH="/usr/local/opt/mysql-client/bin:$PATH"
//mysql -u db4 -p'!db4.hfts23?' -h 3.69.96.96 -P 3306 db4

public class Datenbankabfrage {

	private String url = "jdbc:mysql://3.69.96.96:3306/";
	private String dbName = "db4";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String userName = "db4";
	private String pw = "!db4.hfts23?";

	// Gibt ein Benutzerobjekt (Student, Betreuer oder Studiendekan) zurück,
	// basierend auf dem übergebenen Benutzernamen.
	// Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie
	// anhand eines übergebenen usernames.

	public Benutzer getBenutzer(String username) {
		Student student = getStudentByUsername(username);
		if (student != null) {
			return student;
		}

		Betreuer betreuer = getBetreuerByUsername(username);
		if (betreuer != null) {
			return betreuer;
		}

		Studiendekan studiendekan = getStudiendekanByUsername(username);
		if (studiendekan != null) {
			return studiendekan;
		}

		return null;
	}

	// Gibt ein Betreuerobjekt zurück, das auf den übergebenen Benutzernamen
	// basiert.
	// Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie
	// anhand eines übergebenen usernames.

	public Betreuer getBetreuerByUsername(String username) {
		List<Betreuer> betreuerList = getAllBetreuer();
		for (Betreuer betreuer : betreuerList) {
			if (betreuer.getBenutzername().equals(username)) {
				return betreuer;
			}
		}
		return null;
	}

	public Betreuer getBetreuerByMail(String mail) {
		List<Betreuer> betreuerList = getAllBetreuer();
		for (Betreuer betreuer : betreuerList) {
			if (betreuer.getEmail().equals(mail)) {
				return betreuer;
			}
		}
		return null;
	}

	// Gibt ein Studiendekanobjekt zurück, das auf den übergebenen Benutzernamen
	// basiert.
	// Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie
	// anhand eines übergebenen usernames.

	public Studiendekan getStudiendekanByUsername(String username) {
		List<Studiendekan> studiendekaneList = getAllStudiendekane();
		for (Studiendekan studiendekan : studiendekaneList) {
			if (studiendekan.getBenutzername().equals(username)) {
				return studiendekan;
			}
		}
		return null;
	}

	public Student getStudentByUsername(String username) {
		List<Student> sl = this.getAllStudents();
		for (Student student : sl) {
			if (student.getBenutzername().equals(username)) {
				return student;
			}

		}
		return null;
	}

	public Student getStudentByMNR(int mnr) {
		List<Student> sl = this.getAllStudents();
		for (Student student : sl) {
			if (student.getMnr() == mnr) {
				return student;
			}

		}
		return null;
	}

	// Gibt ALLE Studenten zurück, die in der DB existieren.

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT MNR, Nachname, Vorname, email, Telefonnummer, Studiengang, studiendekan, betreuer, Benutzername, Passwort, ort, postleizahl, strasse From studenten");

			while (rs.next()) {
				int mnr = rs.getInt("MNR");
				String nachname = rs.getString("Nachname");
				String vorname = rs.getString("Vorname");
				String password = rs.getString("Passwort");
				String username = rs.getString("Benutzername");
				String email = rs.getString("email");
				long telefonnummer = rs.getLong("Telefonnummer");
				String studiengang = rs.getString("Studiengang");
				String ort = rs.getString("ort");
				int postleizahl = rs.getInt("postleizahl");
				String strasse = rs.getString("strasse");
				String studiendekan = rs.getString("studiendekan");
				String betreuer = rs.getString("betreuer");
				Student student = new Student(mnr, vorname, nachname, password, username, email, telefonnummer,
						studiengang, ort, strasse, postleizahl, studiendekan, betreuer);
				students.add(student);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	// Gibt ALLE Studiendekane zurück, die in der DB existieren.

	public List<Studiendekan> getAllStudiendekane() {
		List<Studiendekan> studiendekans = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT email, Nachname, Vorname, Benutzername, Passwort, Studiengang FROM studiendekan");

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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studiendekans;
	}

	// Gibt ALLE Betreuer zurück, die in der DB existieren.

	public List<Betreuer> getAllBetreuer() {
		List<Betreuer> betreuer = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT email, Nachname, Vorname, Benutzername, Passwort FROM betreuer");

			while (rs.next()) {
				String email = rs.getString("email");
				String nachname = rs.getString("Nachname");
				String vorname = rs.getString("Vorname");
				String username = rs.getString("Benutzername");
				String password = rs.getString("Passwort");
				Betreuer betreuerobj = new Betreuer(vorname, nachname, password, username, email);
				betreuer.add(betreuerobj);
			}

			con.close();

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
			String sqlStudenten = "SELECT * FROM studenten WHERE Benutzername = '" + username + "' AND Passwort = '"
					+ password + "'";
			try (Statement stmtStudenten = conn.createStatement();
					ResultSet rsStudenten = stmtStudenten.executeQuery(sqlStudenten)) {

				if (rsStudenten.next()) {
					conn.close();
					return true;
				}
			}

			// Suche in der Tabelle "Betreuer"
			String sqlBetreuer = "SELECT * FROM betreuer WHERE Benutzername = '" + username + "' AND Passwort = '"
					+ password + "'";
			try (Statement stmtBetreuer = conn.createStatement();
					ResultSet rsBetreuer = stmtBetreuer.executeQuery(sqlBetreuer)) {

				if (rsBetreuer.next()) {
					conn.close();
					return true;
				}
			}

			// Suche in der Tabelle "Studiendekan"
			String sqlStudiendekan = "SELECT * FROM studiendekan WHERE Benutzername = '" + username
					+ "' AND Passwort = '" + password + "'";
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

	// Sucht in der DB nach einen username und gibt true zurück, falls einer
	// existiert
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

	// Student in der Datenbank anlegen
	public boolean registerStudent(Student student) {
		if (isUsernameTaken(student.getBenutzername())) {
			return false;
		}

		String query = "INSERT INTO studenten (MNR, Nachname, Vorname, Passwort, Benutzername, email, Telefonnummer, Studiengang, ort, strasse, postleizahl, studiendekan, betreuer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
		System.out.println(query);
		try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setLong(1, student.getMnr());
			stmt.setString(2, student.getNachname());
			stmt.setString(3, student.getVorname());
			stmt.setString(4, student.getPasswort());
			stmt.setString(5, student.getBenutzername());
			stmt.setString(6, student.getEmail());
			stmt.setLong(7, student.getTelefonnummer());
			stmt.setString(8, student.getStudiengang());
			stmt.setString(9, student.getOrt());
			stmt.setString(10, student.getStrasse());
			stmt.setLong(11, student.getPostleizahl());
			stmt.setString(12, student.getStudiendekan());
			stmt.setString(13, student.getBetreuer());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update Studenten Tabelle
	public boolean updateDataStudentString(Student student, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`studenten` SET `" + spalte + "` = '" + arg + "' WHERE (MNR = "
					+ student.getMnr() + ")";
			if (update(query)) {
				student.setOrt(arg);
				return true;
			}
		}
		return false;
	}

	public boolean updateDataStudentInt(Student student, int arg, String spalte) {
		if (arg != 0) {
			String query = "UPDATE `db4`.`studenten` SET `" + spalte + "` = '" + arg + "' WHERE (MNR = "
					+ student.getMnr() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	public boolean updateDataStudentLong(Student student, long arg, String spalte) {
		if (arg != 0) {
			String query = "UPDATE `db4`.`studenten` SET `" + spalte + "` = '" + arg + "' WHERE (MNR = "
					+ student.getMnr() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	// befülle Thema Tabelle

	public boolean setDataThema(Student student, Betreuer betreuer, String thema, String unternehmen,
			String beschreibung) {
		if (student != null && betreuer != null && thema != null && unternehmen != null && beschreibung != null) {
			int idThema = getAllThemen().size();

			String query = "INSERT INTO `db4`.`thema` (`idThema`, `thema`, `unternehmen`, `beschreibung`, `angenommen`, `student`, `betreuer`) VALUES ('"
					+ idThema + "', '" + thema + "', '" + unternehmen + "', '" + beschreibung + "', '0', '"
					+ student.getMnr() + "', '" + betreuer.getEmail() + "')";

			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	public boolean updateDataThemaInt(Thema thema, int arg, String spalte) {
		if (arg != 0) {
			String query = "UPDATE `db4`.`thema` SET `" + spalte + "` = '" + arg + "' WHERE (idThema = "
					+ thema.getIdThema() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	public boolean deleteDataThema(int studentMNR) {

		String query = "DELETE FROM `db4`.`thema` WHERE (`student` = '" + studentMNR + "')";

		if (update(query)) {
			return true;
		}
		return false;
	}

	public List<Thema> getAllThemen() {
		List<Thema> themen = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT idThema, unternehmen, thema, beschreibung, angenommen, student, betreuer FROM thema");

			while (rs.next()) {
				int idThema = rs.getInt("idThema");
				String unternehmen = rs.getString("unternehmen");
				String thema = rs.getString("thema");
				String beschreibung = rs.getString("beschreibung");
				int angenommen = rs.getInt("angenommen");
				int studentMNR = rs.getInt("student");
				String betreuerMail = rs.getString("betreuer");
				Thema t = new Thema(idThema, unternehmen, thema, beschreibung, angenommen, studentMNR, betreuerMail);
				themen.add(t);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return themen;
	}

	public Thema getThemaByID(int idThema) {
		List<Thema> t = getAllThemen();
		for (Thema thema : t) {
			if (thema.getIdThema() == idThema) {
				return thema;
			}
		}
		return null;
	}

	private boolean update(String query) {
		try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
