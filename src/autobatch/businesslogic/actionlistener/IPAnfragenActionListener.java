package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;
import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

public class IPAnfragenActionListener implements ActionListener {
	
	private Arbeit arbeit;
	private Student student;
	private Betreuer betreuer;
	private Long i;
	
	public IPAnfragenActionListener(Arbeit arbeit, Student student, Betreuer betreuer) {
		super();
		this.arbeit = arbeit;
		this.student = student;
		this.betreuer = betreuer;
		this.i=(long) arbeit.getIdArbeit();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String url = "jdbc:mysql://3.69.96.96:3306/";
		String dbName = "db4";
		String driver = "com.mysql.cj.jdbc.Driver";
		String userName = "db4";
		String pw = "!db4.hfts23?";
		Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
		Datenbankabfrage dbaccess = new Datenbankabfrage();	
		boolean pruefe=true;
		try(Connection con = DriverManager.getConnection(url + dbName, userName, pw))
		{
		Statement stmt = con.createStatement();
		ResultSet rs;

		rs = stmt.executeQuery(
				"SELECT * From arbeit");

		while (rs.next()) {
			if (rs.getLong(6)==((Student) currentUser).getMnr()) {
				pruefe=false;
			}
			else {
				i=rs.getLong(1);
				i++;
			}
		}
		
		con.close();
		
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	

	
		if (pruefe) {
			
		String query = "INSERT INTO `db4`.`arbeit` (`idThema`, `arbeit`, `unternehmen`, `beschreibung`, `angenommen`, `student`, `betreuer`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		System.out.println(query);
		try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setLong(1, i);
			stmt.setString(2, arbeit.getThema());
			stmt.setString(3, arbeit.getUnternehmen());
			stmt.setString(4, arbeit.getBeschreibung());
			stmt.setBoolean(5, arbeit.getAngenommen());
			stmt.setLong(6, ((Student) currentUser).getMnr());
			stmt.setString(7,((Student) currentUser).getBetreuer());
			
			int rowsAffected = stmt.executeUpdate();
			 JFrame frame = new JFrame();
			 JOptionPane.showMessageDialog(frame, "Anfrage geschickt",
		               "Infomation", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	 }
		else {
			 JFrame frame = new JFrame();
			 JOptionPane.showMessageDialog(frame, "Anfrage schon geschickt",
		               "Infomation", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	
}