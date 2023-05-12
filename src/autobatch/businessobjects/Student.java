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


	public int getMnr() {
		return mnr;
	}


	public void setMnr(int mnr) {
		this.mnr = mnr;
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


	public int getTelefonnummer() {
		return telefonnummer;
	}


	public void setTelefonnummer(int telefonnummer) {
		this.telefonnummer = telefonnummer;
	}


	public String getStudiengang() {
		return studiengang;
	}


	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
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


	public String getStudiendekan() {
		return studiendekan;
	}


	public void setStudiendekan(String studiendekan) {
		this.studiendekan = studiendekan;
	}


	public String getBetreuer() {
		return betreuer;
	}


	public void setBetreuer(String betreuer) {
		this.betreuer = betreuer;
	}

	
}
