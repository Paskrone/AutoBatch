package autobatch.businessobjects;

/**
 * Diese Klasse stellt einen Betreuer dar.
 * Sie erbt von der Klasse Benutzer und fügt spezifische Attribute für einen Betreuer hinzu.
 */
public class Betreuer extends Benutzer {
	
    private String vorname;
    private String nachname;
    private String passwort;
    private String benutzername;
    private String email;
	
    /**
     * Erzeugt ein neues Betreuer-Objekt.
     *
     * @param vorname Der Vorname des Betreuers.
     * @param nachname Der Nachname des Betreuers.
     * @param passwort Das Passwort des Betreuers.
     * @param benutzername Der Benutzername des Betreuers.
     * @param email Die E-Mail-Adresse des Betreuers.
     */
    public Betreuer(String vorname, String nachname, String passwort, String benutzername, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.benutzername = benutzername;
        this.email = email;
    }

    /**
     * Gibt den Vornamen des Betreuers zurück.
     *
     * @return Der Vorname des Betreuers.
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Betreuers.
     *
     * @param vorname Der neue Vorname des Betreuers.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des Betreuers zurück.
     *
     * @return Der Nachname des Betreuers.
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Betreuers.
     *
     * @param nachname Der neue Nachname des Betreuers.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt das Passwort des Betreuers zurück.
     *
     * @return Das Passwort des Betreuers.
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Betreuers.
     *
     * @param passwort Das neue Passwort des Betreuers.
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt den Benutzernamen des Betreuers zurück.
     *
     * @return Der Benutzername des Betreuers.
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Setzt den Benutzernamen des Betreuers.
     *
     * @param benutzername Der neue Benutzername des Betreuers.
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Gibt die E-Mail-Adresse des Betreuers zurück.
     *
     * @return Die E-Mail-Adresse des Betreuers.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Betreuers.
     *
     * @param email Die neue E-Mail-Adresse des
    /**
     * Setzt die E-Mail-Adresse des Betreuers.
     *
     * @param email Die neue E-Mail-Adresse des Betreuers.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
