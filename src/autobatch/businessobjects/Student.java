package autobatch.businessobjects;

public class Student {

	private int mnr;
	private String vorname;
	private String nachname;
	private String passwort;
	private String benutzername;
	private String email;
	private String telefonnummer;
	private String studiengang;
	private String studiendekan;
	private String betreuer;
	
	
	public Student(int mnr, String vorname, String nachname, String passwort, String benutzername, String email,
			String telefonnummer, String studiengang, String studiendekan, String betreuer) {
		super();
		this.mnr = mnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
		this.benutzername = benutzername;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.studiengang = studiengang;
		this.studiendekan = studiendekan;
		this.betreuer = betreuer;
	}


	public int getMnr() {
		return mnr;
	}


	public String getVorname() {
		return vorname;
	}


	public String getNachname() {
		return nachname;
	}


	public String getPasswort() {
		return passwort;
	}


	public String getBenutzername() {
		return benutzername;
	}


	public String getEmail() {
		return email;
	}


	public String getTelefonnummer() {
		return telefonnummer;
	}


	public String getStudiengang() {
		return studiengang;
	}


	public String getStudiendekan() {
		return studiendekan;
	}


	public String getBetreuer() {
		return betreuer;
	}
	
	
	
}
