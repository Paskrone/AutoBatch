package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import autobatch.businessobjects.Arbeit;
import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Betreuer;
import autobatch.businessobjects.Student;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.session.SessionManager;

public class IPAnfragenActionListener implements ActionListener {
	private Arbeit themaa;
	private Student student;
	private Betreuer betreuer;

	public IPAnfragenActionListener(Arbeit themaa, Student student, Betreuer betreuer) {
		super();
		this.themaa = themaa;
		this.student = student;
		this.betreuer = betreuer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Benutzer currentUser = SessionManager.getInstance().getAktuellerBenutzer();
		Datenbankabfrage dbaccess = new Datenbankabfrage();	
		boolean pruefe=true;
		
		for (int i = 0; i < dbaccess.getAllArbeiten().size(); i++) {
			if (dbaccess.getAllArbeiten().get(i).getStudentMNR()==((Student) currentUser).getMnr()) {
				pruefe=false;
			}
		}
		
		
		if (pruefe) {
			if (betreuer==null) {
				JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Sie müssen einen Betreuer finden",
			               "Infomation", JOptionPane.ERROR_MESSAGE);
			}
			else {
				System.out.println(dbaccess.setDataArbeit((Student)currentUser, betreuer, themaa.getThema(),themaa.getUnternehmen() ,themaa.getBeschreibung()));
				
				JFrame frame = new JFrame();
				 JOptionPane.showMessageDialog(frame, "Anfrage geschickt",
			               "Infomation", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			 JFrame frame = new JFrame();
			 JOptionPane.showMessageDialog(frame, "Anfrage schon geschickt",
		               "Infomation", JOptionPane.ERROR_MESSAGE);
		}
	}
}




//String url = "jdbc:mysql://3.69.96.96:3306/";
//String dbName = "db4";
//String driver = "com.mysql.cj.jdbc.Driver";
//String userName = "db4";
//String pw = "!db4.hfts23?";
//try(Connection con = DriverManager.getConnection(url + dbName, userName, pw))
//{
//Statement stmt = con.createStatement();
//ResultSet rs;
//
//rs = stmt.executeQuery("SELECT * From thema");
//
//while (rs.next()) {
//	if (rs.getLong(6)==((Student) currentUser).getMnr()) {
//		pruefe=false;
//	}
//	else {
//		i=rs.getLong(1);
//		i++;
//	}
//}
//
//con.close();
//
//} catch (Exception e2) {
//e2.printStackTrace();
//}
//
//for (int i = 0; i < dbaccess.getAllArbeiten().size(); i++) {
//	if ((this.thema==dbaccess.getAllArbeiten().get(i))) {
//		pruefe=false;
//	}
//}


//String query = "INSERT INTO `db4`.`thema` (`idThema`, `thema`, `unternehmen`, `beschreibung`, `angenommen`, `student`, `betreuer`) VALUES (?, ?, ?, ?, ?, ?, ?)";
//System.out.println(query);
//try (Connection conn = DriverManager.getConnection(url + dbName, userName, pw);
//		PreparedStatement stmt = conn.prepareStatement(query)) {
//	stmt.setLong(1, i);
//	stmt.setString(2, thema.getThema());
//	stmt.setString(3, thema.getUnternehmen());
//	stmt.setString(4, thema.getBeschreibung());
//	stmt.setBoolean(5, thema.getAngenommen());
//	stmt.setLong(6, ((Student) currentUser).getMnr());
//	stmt.setString(7,((Student) currentUser).getBetreuer());
//	
//	int rowsAffected = stmt.executeUpdate();
//	 JFrame frame = new JFrame();
//	 JOptionPane.showMessageDialog(frame, "Anfrage geschickt",
//               "Infomation", JOptionPane.INFORMATION_MESSAGE);
//}catch(Exception e1) {
//	e1.printStackTrace();
//}