package autobatch.businessobjects;

public class Studiendekan extends Benutzer{

	private String vorname;
	private String nachname;
	private String passwort;
	private String benutzername;
	private String email;
	private String studiengang;
	
	public Studiendekan(String vorname, String nachname, String passwort, String benutzername, String email,
			String studiengang) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
		this.benutzername = benutzername;
		this.email = email;
		this.studiengang = studiengang;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	
	
}
