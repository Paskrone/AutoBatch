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
	
	public boolean updateDataStudiendekanString(Studiendekan studiendekan, String arg, String spalte) {
		if (arg != null) {
			String query = "UPDATE `db4`.`betreuer` SET `" + spalte + "` = '" + arg + "' WHERE (`email` = ? )";
			
			
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
				Studiendekan studiendekan = new Studiendekan(vorname, nachname, password, username, email, studiengang);
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

	// Update Studenten Tabelle
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

	// befülle Arbeit Tabelle

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

	public int getViableIdArbeit() {

		List<Integer> ids = new ArrayList<>();

		int id = 0;

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

	// befülle Ip_Anfragen tabelle:)

	public boolean setDataIpAnfragen(Student student, Betreuer betreuer, String thema, String unternehmen,
			String beschreibung, Date termin) {
		if (student != null && betreuer != null && thema != null && unternehmen != null && beschreibung != null) {
			int idArbeit = getViableIdIp();

			String query = "INSERT INTO `db4`.`ip_anfragen` (`thema`, `unternehmen`, `beschreibung`, `angenommen`, `student`, `betreuer`,`termin`,`idArbeit`) VALUES ('"
					+ thema + "', '" + unternehmen + "', '" + beschreibung + "', '0', '" + student.getMnr() + "', '"
					+ betreuer.getEmail() + "', '" + termin + "', '" + idArbeit + "')";

			if (update(query)) {
				System.out.println("insert");

				return true;

			}
		}
		return false;
	}

	public int getViableIdIp() {

		List<Integer> ids = new ArrayList<>();

		int id = 1;

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT idArbeit FROM ip_anfragen");

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

	public boolean deleteDataArbeit(int studentMNR, String betreuerEmail) {

		String query = "DELETE FROM `db4`.`arbeit` WHERE (`student` = '" + studentMNR + "' && `betreuer` = '"
				+ betreuerEmail + "')";

		if (update(query)) {
			return true;
		}
		return false;
	}

	public List<Arbeit> getAllArbeiten() {
		List<Arbeit> arbeiten = new ArrayList<>();
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, userName, pw);

			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(
					"SELECT idArbeit, unternehmen, thema, beschreibung, noteArbeit, noteVortrag, gesamtnote, angenommen, nda_notwendig, ipAngefragt, ipAngenommen, ipStart, baAbgabetermin, ausgabetermin, ba_Anmeldung_Student, ba_Anmeldung_Betreuer, ba_Anmeldung_Studiendekan, ipBestanden, veroeffentlichung, student, betreuer, studiendekan FROM arbeit");

			while (rs.next()) {
				int idArbeit = rs.getInt("idArbeit");
				String unternehmen = rs.getString("unternehmen");
				String thema = rs.getString("thema");
				String beschreibung = rs.getString("beschreibung");
				float noteArbeit = rs.getFloat("noteArbeit");
				float noteVortrag = rs.getFloat("noteVortrag");
				byte angenommen = rs.getByte("angenommen");
				byte nda_notwenig = rs.getByte("nda_notwendig");
				byte ipAngefragt = rs.getByte("ipAngefragt");
				byte ipAngeneommen = rs.getByte("ipAngenommen");

				byte ba_Anmeldung_Student = rs.getByte("ba_Anmeldung_Student");
				byte ba_Anmeldung_Betreuer = rs.getByte("ba_Anmeldung_Betreuer");
				byte ba_Anmeldung_Studiendekan = rs.getByte("ba_Anmeldung_Studiendekan");
				byte ipBestanden = rs.getByte("ipBestanden");
				byte veroeffentlichung = rs.getByte("veroeffentlichung");

				Date ipStart = rs.getDate("ipStart");
				Date baAbgabetermin = rs.getDate("baAbgabetermin");
				Date ausgabetermin = rs.getDate("ausgabetermin");

				int studentMNR = rs.getInt("student");
				String betreuerMail = rs.getString("betreuer");
				String studiendekanMail = rs.getString("studiendekan");
				Arbeit t = new Arbeit(idArbeit, unternehmen, thema, beschreibung, noteArbeit, noteVortrag, angenommen,
						nda_notwenig, ipAngefragt, ipAngeneommen, ba_Anmeldung_Student, ba_Anmeldung_Betreuer,
						ba_Anmeldung_Studiendekan, ipBestanden, veroeffentlichung, ipStart, baAbgabetermin,
						ausgabetermin, studentMNR, betreuerMail, studiendekanMail);
				arbeiten.add(t);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arbeiten;
	}

	public Arbeit getArbeitByID(int idArbeit) {
		List<Arbeit> a = getAllArbeiten();
		for (Arbeit arbeit : a) {
			if (arbeit.getIdArbeit() == idArbeit) {
				return arbeit;
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

}
