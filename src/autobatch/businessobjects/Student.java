package autobatch.businessobjects;

/**
 * Die Klasse Student erweitert die Klasse Benutzer und
 * beinhaltet spezifische Informationen zu einem Studenten.
 */
public class Student extends Benutzer {

	private int mnr;
	private String vorname;
	private String nachname;
	private String passwort;
	private String benutzername;
	private String email;

	private long telefonnummer;
	private int semester;

	private String studiengang;
	private String ort;
	private String strasse;
	private int postleizahl;

	private String studiendekan;
	private String betreuer;
	private int arbeit;
	
	/**
	 * Konstruktor für die Klasse Student.
	 * 
	 * @param mnr Die Matrikelnummer des Studenten.
	 * @param vorname Der Vorname des Studenten.
	 * @param nachname Der Nachname des Studenten.
	 * @param passwort Das Passwort des Studenten.
	 * @param benutzername Der Benutzername des Studenten.
	 * @param email Die E-Mail-Adresse des Studenten.
	 * @param telefonnummer Die Telefonnummer des Studenten.
	 * @param semester Das aktuelle Semester des Studenten.
	 * @param studiengang Der Studiengang des Studenten.
	 * @param ort Der Wohnort des Studenten.
	 * @param strasse Die Straße, in der der Student wohnt.
	 * @param postleizahl Die Postleitzahl des Studenten.
	 * @param studiendekan Der Studiendekan des Studenten.
	 * @param betreuer Der Betreuer des Studenten.
	 */

	public Student(int mnr, String vorname, String nachname, String passwort, String benutzername, String email,
			long telefonnummer, int semester, String studiengang, String ort, String strasse, int postleizahl,
			String studiendekan, String betreuer) {
		super();
		this.mnr = mnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
		this.benutzername = benutzername;
		this.email = email;
		this.telefonnummer = telefonnummer;
		this.semester = semester;
		this.studiengang = studiengang;
		this.ort = ort;
		this.strasse = strasse;
		this.postleizahl = postleizahl;
		this.studiendekan = studiendekan;
		this.betreuer = betreuer;
	}
	
	/**
	 * Gibt die Matrikelnummer des Studenten zurück.
	 * 
	 * @return Die Matrikelnummer des Studenten.
	 */

	public int getMnr() {
		return mnr;
	}
	
	/**
	 * Setzt die Matrikelnummer des Studenten.
	 * 
	 * @param mnr Die neue Matrikelnummer des Studenten.
	 */

	public void setMnr(int mnr) {
		this.mnr = mnr;
	}

	/**
	 * Gibt den Vornamen des Studenten zurück.
	 * 
	 * @return Der Vorname des Studenten.
	 */
	public String getVorname() {
		return vorname;
	}

	
    /**
     * Setzt den Vornamen des Studenten.
     *
     * @param vorname Der neue Vorname des Studenten.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des Studenten zurück.
     *
     * @return Der Nachname des Studenten.
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Studenten.
     *
     * @param nachname Der neue Nachname des Studenten.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt das Passwort des Studenten zurück.
     *
     * @return Das Passwort des Studenten.
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Studenten.
     *
     * @param passwort Das neue Passwort des Studenten.
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt den Benutzernamen des Studenten zurück.
     *
     * @return Der Benutzername des Studenten.
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Setzt den Benutzernamen des Studenten.
     *
     * @param benutzername Der neue Benutzername des Studenten.
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Gibt die E-Mail-Adresse des Studenten zurück.
     *
     * @return Die E-Mail-Adresse des Studenten.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Studenten.
     *
     * @param email Die neue E-Mail-Adresse des Studenten.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gibt die Telefonnummer des Studenten zurück.
     *
     * @return Die Telefonnummer des Studenten.
     */
    public long getTelefonnummer() {
        return telefonnummer;
    }

    /**
     * Setzt die Telefonnummer des Studenten.
     *
     * @param telefonnummer Die neue Telefonnummer des Studenten.
     */
    public void setTelefonnummer(long telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    /**
     * Gibt den Studiengang des Studenten zurück.
     *
     * @return Der Studiengang des Studenten.
     */
    public String getStudiengang() {
        return studiengang;
    }

    /**
     * Setzt den Studiengang des Studenten.
     *
     * @param studiengang Der neue Studiengang des Studenten.
     */
    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    /**
     * Gibt den Wohnort des Studenten zurück.
     *
     * @return Der Wohnort des Studenten.
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Setzt den Wohnort des Studenten.
     *
     * @param ort Der neue Wohnort des Studenten.
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Gibt die Straße des Studenten zurück.
     *
     * @return Die Straße des Studenten.
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Setzt die Straße des Studenten.
     *
     * @param strasse Die neue Straße des Studenten.
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Gibt die Postleitzahl des Studenten zurück.
     *
     * @return Die Postleitzahl des Studenten.
     */
    public int getPostleizahl() {
        return postleizahl;
    }

    /**
     * Setzt die Postleitzahl des Studenten.
     *
     * @param postleizahl Die neue Postleitzahl des Studenten.
     */
    public void setPostleizahl(int postleizahl) {
        this.postleizahl = postleizahl;
    }

    /**
     * Gibt den Studiendekan des Studenten zurück.
     *
     * @return Der Studiendekan des Studenten.
     */
    public String getStudiendekan() {
        return studiendekan;
    }

    /**
     * Setzt den Studiendekan des Studenten.
     *
     * @param studiendekan Der neue Studiendekan des Studenten.
     */
    public void setStudiendekan(String studiendekan) {
        this.studiendekan = studiendekan;
    }

    /**
     * Gibt den Betreuer des Studenten zurück.
     *
     * @return Der Betreuer des Studenten.
     */
    public String getBetreuer() {
        return betreuer;
    }

    /**
     * Setzt den Betreuer des Studenten.
     *
     * @param betreuer Der neue Betreuer des Studenten.
     */
    public void setBetreuer(String betreuer) {
        this.betreuer = betreuer;
    }

    /**
     * Gibt die Arbeitslast des Studenten zurück.
     *
     * @return Die Arbeitslast des Studenten.
     */
    public int getArbeit() {
        return arbeit;
    }

    /**
     * Setzt die Arbeitslast des Studenten.
     *
     * @param arbeit Die neue Arbeitslast des Studenten.
     */
    public void setArbeit(int arbeit) {
        this.arbeit = arbeit;
    }

    /**
     * Gibt das Semester des Studenten zurück.
     *
     * @return Das Semester des Studenten.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Setzt das Semester des Studenten.
     *
     * @param semester Das neue Semester des Studenten.
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }


}
