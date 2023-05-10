package autobatch.businessobjects;

public class Student extends Benutzer{

	private int mnr;
	private String vorname;
	private String nachname;
	private String passwort;
	private String benutzername;
	private String email;
	private int telefonnummer;
	private String studiengang;
	private String ort;
	private String strasse;
	private int postleizahl;
	private String studiendekan;
	private String betreuer;
	
	
	public Student(int mnr, String vorname, String nachname, String passwort, String benutzername, String email,
			int telefonnummer, String studiengang, String ort, String strasse, int postleizahl, String studiendekan, String betreuer) {
		super();
		this.mnr = mnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
		this.benutzername = benutzername;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.studiengang = studiengang;
		this.ort = ort;
		this.strasse = strasse;
		this.postleizahl = postleizahl;
		this.studiendekan = studiendekan;
		this.betreuer = betreuer;
	}
	


	public String getOrt() {
		return ort;
	}



	public void setOrt(String ort) {
		this.ort = ort;
	}



	public String getStrasse() {
		return strasse;
	}



	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}



	public int getPostleizahl() {
		return postleizahl;
	}



	public void setPostleizahl(int postleizahl) {
		this.postleizahl = postleizahl;
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


	public int getTelefonnummer() {
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
