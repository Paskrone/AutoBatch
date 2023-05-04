package autobatch.businessobjects;

public class Studiendekan {

	private String vorname;
	private String nachname;
	private String passwort;
	private String benutzername;
	private String email;
	private String studiengang;
	
	public Studiendekan(String vorname, String nachname, String passwort, String benutzername, String email,
			String studiengang) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
		this.benutzername = benutzername;
		this.email = email;
		this.studiengang = studiengang;
	}
	
	
}
