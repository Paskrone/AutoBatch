package autobatch.dbaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Studiendekan;
import autobatch.businessobjects.Arbeit;

//Database connection for macos-terminal: source ~/.zshrc
//export PATH="/usr/local/opt/mysql-client/bin:$PATH"
//mysql -u db4 -p'!db4.hfts23?' -h 3.69.96.96 -P 3306 db4

/**
 * Datenbankabfrageklasse zur Interaktion mit der Datenbank.
 */
public class Datenbankabfrage {

	 /**
     * Die URL zur Datenbank.
     */
    private String url = "jdbc:mysql://3.69.96.96:3306/";

    /**
     * Der Name der Datenbank.
     */
    private String dbName = "db4";

    /**
     * Der JDBC-Treiber.
     */
    private String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * Der Benutzername für den Datenbankzugriff.
     */
    private String userName = "db4";

    /**
     * Das Passwort für den Datenbankzugriff.
     */
    private String pw = "!db4.hfts23?";

	
	/**
     * Gibt ein Benutzerobjekt (Student, Betreuer oder Studiendekan) zurück,
     * basierend auf dem übergebenen Benutzernamen.
     * Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie
     * anhand eines übergebenen usernames.
     *
     * @param username Der Benutzername des gesuchten Benutzers.
     * @return Ein Benutzerobjekt, das dem übergebenen Benutzernamen entspricht. Gibt null zurück, wenn kein Benutzer gefunden wird.
     */
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

	/**
     * Gibt ein Betreuerobjekt zurück, das auf den übergebenen Benutzernamen basiert.
     * Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie anhand eines übergebenen usernames.
     *
     * @param username Der Benutzername des gesuchten Betreuers.
     * @return Ein Betreuerobjekt, das dem übergebenen Benutzernamen entspricht. Gibt null zurück, wenn kein Betreuer gefunden wird.
     */
	public Betreuer getBetreuerByUsername(String username) {
		List<Betreuer> betreuerList = getAllBetreuer();
		for (Betreuer betreuer : betreuerList) {
			if (betreuer.getBenutzername().equals(username)) {
				return betreuer;
			}
		}
		return null;
	}

	/**
     * Sucht und gibt einen Betreuer zurück, basierend auf der übergebenen E-Mail.
     *
     * @param mail Die E-Mail des gesuchten Betreuers.
     * @return Ein Betreuerobjekt, das der übergebenen E-Mail entspricht. Gibt null zurück, wenn kein Betreuer gefunden wird.
     */
	public Betreuer getBetreuerByMail(String mail) {
		List<Betreuer> betreuerList = getAllBetreuer();
		for (Betreuer betreuer : betreuerList) {
			if (betreuer.getEmail().equals(mail)) {
				return betreuer;
			}
		}
		return null;
	}
	
	/**
     * Aktualisiert Daten in der Betreuer-Tabelle basierend auf den übergebenen Argumenten.
     *
     * @param betreuer Das Betreuerobjekt, das aktualisiert werden soll.
     * @param arg Das neue Argument.
     * @param spalte Die Spalte, in der das Argument aktualisiert werden soll.
     * @return true, wenn die Aktualisierung erfolgreich war, sonst false.
     */
	public boolean updateDataBetreuerString(Betreuer betreuer, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`betreuer` SET `" + spalte + "` = '" + arg + "' WHERE (`email` = ? )";

			try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
					PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, betreuer.getEmail());

				stmt.executeUpdate();
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
     * Gibt ein Studiendekanobjekt zurück, das auf den übergebenen Benutzernamen basiert.
     * Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie anhand eines übergebenen usernames.
     *
     * @param username Der Benutzername des gesuchten Studiendekans.
     * @return Ein Studiendekanobjekt, das dem übergebenen Benutzernamen entspricht. Gibt null zurück, wenn kein Studiendekan gefunden wird.
     */
	public Studiendekan getStudiendekanByUsername(String username) {
		List<Studiendekan> studiendekaneList = getAllStudiendekane();
		for (Studiendekan studiendekan : studiendekaneList) {
			if (studiendekan.getBenutzername().equals(username)) {
				return studiendekan;
			}
		}
		return null;
	}

	/**
     * Aktualisiert Daten in der Studiendekan-Tabelle basierend auf den übergebenen Argument
     * @param studiendekan Das Studiendekanobjekt, das aktualisiert werden soll.
     * @param arg Das neue Argument.
     * @param spalte Die Spalte, in der das Argument aktualisiert werden soll.
     * @return true, wenn die Aktualisierung erfolgreich war, sonst false.
     */
	public boolean updateDataStudiendekanString(Studiendekan studiendekan, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`studiendekan` SET `" + spalte + "` = '" + arg + "' WHERE (`email` = ? )";

			try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
					PreparedStatement stmt = conn.prepareStatement(query)) {

				stmt.setString(1, studiendekan.getEmail());

				stmt.executeUpdate();
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
     * Gibt ein Studentenobjekt zurück, das auf den übergebenen Benutzernamen basiert.
     * Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie anhand eines übergebenen usernames.
     *
     * @param username Der Benutzername des gesuchten Studenten.
     * @return Ein Studentenobjekt, das dem übergebenen Benutzernamen entspricht. Gibt null zurück, wenn kein Student gefunden wird.
     */
	public Student getStudentByUsername(String username) {
		List<Student> sl = this.getAllStudents();
		for (Student student : sl) {
			if (student.getBenutzername().equals(username)) {
				return student;
			}

		}
		return null;
	}

	/**
     * Gibt ein Studentenobjekt zurück, das auf die übergebene Matrikelnummer basiert.
     * Verwendet die schon erstellten ArrayListen aus der DB und durchsucht sie anhand einer übergebenen Matrikelnummer.
     *
     * @param mnr Die Matrikelnummer des gesuchten Studenten.
     * @return Ein Studentenobjekt, das der übergebenen Matrikelnummer entspricht. Gibt null zurück, wenn kein Student gefunden wird.
     */
	public Student getStudentByMNR(int mnr) {
		List<Student> sl = this.getAllStudents();
		for (Student student : sl) {
			if (student.getMnr() == mnr) {
				return student;
			}

		}
		return null;
	}

	/**
     * Gibt eine Liste aller Studenten zurück, die in der Datenbank existieren.
     *
     * @return Eine Liste aller Studenten.
     */
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT MNR, Nachname, Vorname, email, semseter, Telefonnummer, Studiengang, studiendekan, betreuer, Benutzername, Passwort, ort, postleizahl, strasse, arbeit From studenten");

			while (rs.next()) {
				int mnr = rs.getInt("MNR");
				String nachname = rs.getString("Nachname");
				String vorname = rs.getString("Vorname");
				String password = rs.getString("Passwort");
				String username = rs.getString("Benutzername");
				String email = rs.getString("email");
				long telefonnummer = rs.getLong("Telefonnummer");
				int semester = rs.getInt("semseter");
				String studiengang = rs.getString("Studiengang");
				String ort = rs.getString("ort");
				int postleizahl = rs.getInt("postleizahl");
				String strasse = rs.getString("strasse");
				String studiendekan = rs.getString("studiendekan");
				String betreuer = rs.getString("betreuer");
				int arbeit = rs.getInt("arbeit");

				Student student = new Student(mnr, vorname, nachname, password, username, email, telefonnummer,
						semester, studiengang, ort, strasse, postleizahl, studiendekan, betreuer);
				// arbeit mit Setter, da es ein int ist und sonst Probleme auftauchen da int
				// nicht null sein kann
				student.setArbeit(arbeit);
				students.add(student);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	/**
     * Gibt eine Liste aller Studiendekane zurück, die in der Datenbank existieren.
     *
     * @return Eine Liste aller Studiendekane.
     */
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
				Studiendekan studiendekan = new Studiendekan(vorname, nachname, password, username, email, studiengang);
				studiendekans.add(studiendekan);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studiendekans;
	}

	/**
     * Gibt eine Liste aller Betreuer zurück, die in der Datenbank existieren.
     *
     * @return Eine Liste aller Betreuer.
     */
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
	
	/**
     * Sucht in allen Tabellen nach einem Benutzernamen und Passwort.
     *
     * @param username Der zu suchende Benutzername.
     * @param password Das zugehörige Passwort.
     * @return true, wenn der Benutzername und das Passwort gefunden wurden, sonst false.
     */
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

	/**
     * Überprüft, ob der übergebene Benutzername bereits in der Datenbank existiert.
     *
     * @param username Der zu überprüfende Benutzername.
     * @return true, wenn der Benutzername bereits existiert, sonst false.
     */
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

	/**
     * Registriert einen neuen Studenten in der Datenbank.
     *
     * @param student Das zu registrierende Studentenobjekt.
     * @return true, wenn die Registrierung erfolgreich war, sonst false.
     */
	public boolean registerStudent(Student student) {
		if (isUsernameTaken(student.getBenutzername())) {
			return false;
		}

		String query = "INSERT INTO studenten (MNR, Nachname, Vorname, Passwort, Benutzername, email, Telefonnummer, Studiengang, ort, strasse, postleizahl, studiendekan, betreuer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

	/**
     * Aktualisiert die Daten in der Spalte eines Studenten in der Studenten Tabelle.
     * @param student Der Student, dessen Daten aktualisiert werden sollen.
     * @param arg Der neue Wert, der in die Spalte eingetragen werden soll.
     * @param spalte Der Name der Spalte, die aktualisiert werden soll.
     * @return Gibt zurück, ob die Aktualisierung erfolgreich war oder nicht.
     */
	public boolean updateDataStudentString(Student student, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`studenten` SET `" + spalte + "` = '" + arg + "' WHERE (MNR = "
					+ student.getMnr() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	/**
     * Aktualisiert die Daten in der Spalte eines Studenten in der Studenten Tabelle.
     * @param student Der Student, dessen Daten aktualisiert werden sollen.
     * @param arg Der neue Wert, der in die Spalte eingetragen werden soll.
     * @param spalte Der Name der Spalte, die aktualisiert werden soll.
     * @return Gibt zurück, ob die Aktualisierung erfolgreich war oder nicht.
     */
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

	/**
     * Aktualisiert die Daten in der Spalte eines Studenten in der Studenten Tabelle.
     * @param student Der Student, dessen Daten aktualisiert werden sollen.
     * @param arg Der neue Wert, der in die Spalte eingetragen werden soll.
     * @param spalte Der Name der Spalte, die aktualisiert werden soll.
     * @return Gibt zurück, ob die Aktualisierung erfolgreich war oder nicht.
     */
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

	/**
	 * Fügt einen neuen Arbeitseintrag in die Datenbank ein.
	 * 
	 * @param student Der Student, der die Arbeit durchführt.
	 * @param betreuer Der Betreuer der Arbeit.
	 * @param thema Das Thema der Arbeit.
	 * @param unternehmen Das Unternehmen, das die Arbeit anbietet.
	 * @param beschreibung Die Beschreibung der Arbeit.
	 * @return Gibt true zurück, wenn das Hinzufügen erfolgreich war, sonst false.
	 */
	public boolean setDataArbeit(Student student, Betreuer betreuer, String thema, String unternehmen,
			String beschreibung) {
		if (student != null && betreuer != null && thema != null && unternehmen != null && beschreibung != null) {
			int idArbeit = getViableIdArbeit();

			String query = "INSERT INTO `db4`.`arbeit` (`idArbeit`, `thema`, `unternehmen`, `beschreibung`, `angenommen`, `student`, `betreuer`, `studiendekan`) VALUES ('"
					+ idArbeit + "', '" + thema + "', '" + unternehmen + "', '" + beschreibung + "', b'0', '"
					+ student.getMnr() + "', '" + betreuer.getEmail() + "', '" + student.getStudiendekan() + "')";

			if (update(query)) {
				System.out.println("insert");

				return true;

			}
		}
		return false;
	}
	
	/**
	 * Ermittelt eine geeignete ID für eine neue Arbeit.
	 * 
	 * @return Die ermittelte ID.
	 */
	public int getViableIdArbeit() {

		List<Integer> ids = new ArrayList<>();

		int id = 1;

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT idArbeit FROM arbeit");

			while (rs.next()) {
				int idArbeit = rs.getInt("idArbeit");
				ids.add(idArbeit);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Integer integer : ids) {
			if (id == integer) {
				id++;
			}
		}

		return id;

	}

	/**
	 * Aktualisiert eine bestimmte Spalte eines Arbeitseintrags mit einem boolean-Wert in der Datenbank.
	 * 
	 * @param arbeit Die Arbeit, deren Daten aktualisiert werden sollen.
	 * @param arg Der boolean-Wert, mit dem die Spalte aktualisiert werden soll.
	 * @param spalte Der Name der Spalte, die aktualisiert werden soll.
	 * @return Gibt true zurück, wenn die Aktualisierung erfolgreich war, sonst false.
	 */
	public boolean updateDataArbeitBoolean(Arbeit arbeit, boolean arg, String spalte) {
		if (arg) {
			String query = "UPDATE `db4`.`arbeit` SET `" + spalte + "` = b'1' WHERE (idArbeit = " + arbeit.getIdArbeit()
					+ ")";
			if (update(query)) {
				return true;
			}
		} else if (!arg) {
			String query = "UPDATE `db4`.`arbeit` SET `" + spalte + "` = b'0' WHERE (idArbeit = " + arbeit.getIdArbeit()
					+ ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Aktualisiert eine bestimmte Spalte eines Arbeitseintrags mit einem float-Wert in der Datenbank.
	 * 
	 * @param arbeit Die Arbeit, deren
	 * * Daten aktualisiert werden sollen.
	 * @param arg Der float-Wert, mit dem die Spalte aktualisiert werden soll.
	 * @param spalte Der Name der Spalte, die aktualisiert werden soll.
	 * @return Gibt true zurück, wenn die Aktualisierung erfolgreich war, sonst false.
	 */
	public boolean updateDataArbeitFloat(Arbeit arbeit, float arg, String spalte) {
		if (arg != 0) {
			String query = "UPDATE `db4`.`arbeit` SET `" + spalte + "` = '" + arg + "' WHERE (idArbeit = "
					+ arbeit.getIdArbeit() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Aktualisiert eine bestimmte Spalte eines Arbeitseintrags mit einem int-Wert in der Datenbank.
	 * 
	 * @param arbeit Die Arbeit, deren Daten aktualisiert werden sollen.
	 * @param arg Der int-Wert, mit dem die Spalte aktualisiert werden soll.
	 * @param spalte Der Name der Spalte, die aktualisiert werden soll.
	 * @return Gibt true zurück, wenn die Aktualisierung erfolgreich war, sonst false.
	 */
	public boolean updateDataArbeitDate(Arbeit arbeit, LocalDate arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`arbeit` SET `" + spalte + "` = '" + arg + "' WHERE (idArbeit = "
					+ arbeit.getIdArbeit() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Aktualisiert eine bestimmte Spalte eines Arbeitseintrags mit einem String-Wert in der Datenbank.
	 * 
	 * @param arbeit Das Arbeit-Objekt, dessen Daten aktualisiert werden sollen.
	 * @param arg Der String-Wert, mit dem die Spalte aktualisiert werden soll.
	 * @param spalte Der Name der Spalte, die aktualisiert werden soll.
	 * @return Gibt true zurück, wenn die Aktualisierung erfolgreich war, sonst false.
	 */
	public boolean updateDataArbeitString(Arbeit arbeit, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`arbeit` SET `" + spalte + "` = '" + arg + "' WHERE (idArbeit = "
					+ arbeit.getIdArbeit() + ")";
			if (update(query)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Löscht eine Arbeitseintrag in der Datenbank.
	 * 
	 * @param studentMNR Die Matrikelnummer des Studenten, der an der Arbeit beteiligt ist.
	 * @param betreuerEmail Die E-Mail-Adresse des Betreuers der Arbeit.
	 * @return Gibt true zurück, wenn das Löschen erfolgreich war, sonst false.
	 */
	public boolean deleteDataArbeit(int studentMNR, String betreuerEmail) {

		String query = "DELETE FROM `db4`.`arbeit` WHERE (`student` = '" + studentMNR + "' && `betreuer` = '"
				+ betreuerEmail + "')";

		if (update(query)) {
			return true;
		}
		return false;
	}

	/**
	 * Ruft alle Arbeitseinträge aus der Datenbank ab.
	 * 
	 * @return Eine Liste aller Arbeitseinträge.
	 */
	public List<Arbeit> getAllArbeiten() {
		List<Arbeit> arbeiten = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT idArbeit, unternehmen, thema, beschreibung, noteArbeit, noteVortrag, gesamtnote, angenommen, nda_notwendig, ipAngenommen, ipStart, baAbgabetermin, baStart, ba_Anmeldung_Student, ba_Anmeldung_Betreuer, ba_Anmeldung_Studiendekan, ipBestanden, veroeffentlichung, student, betreuer, studiendekan FROM arbeit");

			while (rs.next()) {
				int idArbeit = rs.getInt("idArbeit");
				String unternehmen = rs.getString("unternehmen");
				String thema = rs.getString("thema");
				String beschreibung = rs.getString("beschreibung");
				float noteArbeit = rs.getFloat("noteArbeit");
				float noteVortrag = rs.getFloat("noteVortrag");
				byte angenommen = rs.getByte("angenommen");
				byte nda_notwenig = rs.getByte("nda_notwendig");
				byte ipAngeneommen = rs.getByte("ipAngenommen");

				byte ba_Anmeldung_Student = rs.getByte("ba_Anmeldung_Student");
				byte ba_Anmeldung_Betreuer = rs.getByte("ba_Anmeldung_Betreuer");
				byte ba_Anmeldung_Studiendekan = rs.getByte("ba_Anmeldung_Studiendekan");
				byte ipBestanden = rs.getByte("ipBestanden");
				byte veroeffentlichung = rs.getByte("veroeffentlichung");

				Date ipStart = rs.getDate("ipStart");
				Date baAbgabetermin = rs.getDate("baAbgabetermin");
				Date baStart = rs.getDate("baStart");

				int studentMNR = rs.getInt("student");
				String betreuerMail = rs.getString("betreuer");
				String studiendekanMail = rs.getString("studiendekan");
				Arbeit t = new Arbeit(idArbeit, unternehmen, thema, beschreibung, noteArbeit, noteVortrag, angenommen,
						nda_notwenig, ipAngeneommen, ba_Anmeldung_Student, ba_Anmeldung_Betreuer,
						ba_Anmeldung_Studiendekan, ipBestanden, veroeffentlichung, ipStart, baAbgabetermin, baStart,
						studentMNR, betreuerMail, studiendekanMail);
				arbeiten.add(t);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arbeiten;
	}

	/**
	 * Ruft einen Arbeitseintrag anhand seiner ID aus der Datenbank ab.
	 * 
	 * @param idArbeit Die ID des Arbeitseintrags, der abgerufen werden soll.
	 * @return Den Arbeitseintrag mit der gegebenen ID. Gibt null zurück, wenn kein Arbeitseintrag mit der gegebenen ID gefunden wurde.
	 */
	public Arbeit getArbeitByID(int idArbeit) {
		List<Arbeit> a = getAllArbeiten();
		for (Arbeit arbeit : a) {
			if (arbeit.getIdArbeit() == idArbeit) {
				return arbeit;
			}
		}
		return null;
	}

	/**
	 * Führt ein Update-Statement in der Datenbank aus.
	 * 
	 * @param query Das SQL-Update-Statement, das ausgeführt werden soll.
	 * @return Gibt true zurück, wenn das Update erfolgreich war, sonst false.
	 */
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

	/**
	 * Speichert eine Datei in der Datenbank.
	 * 
	 * @param selectedFile Die zu speichernde Datei.
	 * @param username Der Benutzername des Benutzers, der die Datei speichert.
	 */
	public void saveFileToDatabase(File selectedFile, String username) {
		try {
			FileInputStream input = new FileInputStream(selectedFile);
			Connection con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO documents (username, file, filename) VALUES (?, ?, ?)");
			statement.setString(1, username);
			statement.setBinaryStream(2, input);
			statement.setString(3, selectedFile.getName());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert eine Datei in der Datenbank und verknüpft sie mit einem bestimmten Studenten.
	 * 
	 * @param selectedFile Die zu speichernde Datei.
	 * @param username Der Benutzername des Benutzers, der die Datei speichert.
	 * @param studentUsername Der Benutzername des Studenten, mit dem die Datei verknüpft werden soll.
	 */
	public void saveFileToDatabase(File selectedFile, String username, String studentUsername) {
		try {
			FileInputStream input = new FileInputStream(selectedFile);
			Connection con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO documents (username, file, filename, student) VALUES (?, ?, ?, ?)");
			statement.setString(1, username);
			statement.setBinaryStream(2, input);
			statement.setString(3, selectedFile.getName());
			statement.setString(4, studentUsername);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ruft eine Datei anhand ihrer ID aus der Datenbank ab und speichert sie an einem gegebenen Ort.
	 * 
	 * @param id Die ID der abzurufenden Datei.
	 * @param outputFilePath Der Pfad, an dem die abgerufene Datei gespeichert werden soll.
	 */
	public void saveFileFromDatabaseById(int id, String outputFilePath) {
		try {
			Connection con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con.prepareStatement("SELECT file FROM documents WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				byte[] fileBytes = rs.getBytes("file");
				OutputStream targetFile = new FileOutputStream(outputFilePath);
				targetFile.write(fileBytes);
				targetFile.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lädt eine Datei aus der Datenbank als InputStream.
	 * @param filename der Name der Datei, die aus der Datenbank geladen werden soll.
	 * @return Ein InputStream, der die Daten der Datei enthält, oder null, wenn die Datei nicht gefunden wurde.
	 */
	public InputStream getFileFromDatabase(String filename) {
		InputStream input = null;

		try {
			Connection con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con.prepareStatement("SELECT file FROM documents WHERE filename = ?");
			statement.setString(1, filename);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				input = result.getBinaryStream("file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}

	/**
	 * Liefert eine Liste der Einreichungen eines Benutzers.
	 * @param username der Benutzername, für den die Einreichungen gefunden werden sollen.
	 * @return Eine Liste der Dateinamen der Einreichungen des Benutzers.
	 */
	public List<String> getSubmissions(String username) {
		System.out.println("Abfragen der Studentenabgaben für Benutzer: " + username);
		List<String> filenames = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con.prepareStatement("SELECT filename FROM documents WHERE username = ?");
			statement.setString(1, username);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				filenames.add(result.getString("filename"));
			}
			System.out.println("Gefundene Dateinamen: " + filenames);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filenames;
	}
	
	/**
	 * Liefert eine Liste der Einreichungen eines bestimmten Studenten für einen bestimmten Betreuer.
	 * @param betreuerUsername der Benutzername des Betreuers, für den die Einreichungen gefunden werden sollen.
	 * @param studentUsername der Benutzername des Studenten, dessen Einreichungen gefunden werden sollen.
	 * @return Eine Liste der Dateinamen der Einreichungen des Studenten für den Betreuer.
	 */
	public List<String> getSubmissionsFromBetreuerWithStudent(String betreuerUsername, String studentUsername) {
		System.out.println("Abfragen der Studentenabgaben für Benutzer: " + betreuerUsername);
		List<String> filenames = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(url + dbName, userName, pw);
			PreparedStatement statement = con.prepareStatement("SELECT filename FROM documents WHERE username = ? AND student = ?");
			statement.setString(1, betreuerUsername);
			statement.setString(2, studentUsername);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				filenames.add(result.getString("filename"));
			}
			System.out.println("Gefundene Dateinamen: " + filenames);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filenames;
	}
	
	/**
	 * Liefert eine Liste der Einreichungen eines bestimmten Studenten für einen bestimmten Betreuer.
	 * @param betreuerUsername der Benutzername des Betreuers, für den die Einreichungen gefunden werden sollen.
	 * @param studentUsername der Benutzername des Studenten, dessen Einreichungen gefunden werden sollen.
	 * @return Eine Liste der Dateinamen der Einreichungen des Studenten für den Betreuer.
	 */
	public List<String> getSubmissionsForBetreuerFromStudent(String betreuerUsername, String studentUsername) {
		List<String> filenames = new ArrayList<>();
		Connection con = null;

		try {
			con = DriverManager.getConnection(url + dbName, userName, pw);
			System.out.println("StudentenUsername: " + studentUsername);
			PreparedStatement statement = con.prepareStatement("SELECT filename FROM documents WHERE username = ? AND student = ?");
			statement.setString(1, betreuerUsername);
			statement.setString(2, studentUsername);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				filenames.add(result.getString("filename"));
			}
			System.out.println("Gefundene Dateinamen: " + filenames);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filenames;
	}

}
