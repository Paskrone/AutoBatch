package autobatch.businessobjects;

/**
 * Diese Klasse repräsentiert einen Studiendekan.
 * Sie erbt von der abstrakten Klasse Benutzer und fügt ein spezifisches Attribut (Studiengang) hinzu.
 */
public class Studiendekan extends Benutzer {

    /**
     * Der Vorname des Studiendekans.
     */
    private String vorname;

    /**
     * Der Nachname des Studiendekans.
     */
    private String nachname;

    /**
     * Das Passwort des Studiendekans.
     */
    private String passwort;

    /**
     * Der Benutzername des Studiendekans.
     */
    private String benutzername;

    /**
     * Die E-Mail-Adresse des Studiendekans.
     */
    private String email;

    /**
     * Der Studiengang, den der Studiendekan leitet.
     */
    private String studiengang;

    /**
     * Konstruktor für einen Studiendekan.
     *
     * @param vorname      Der Vorname des Studiendekans.
     * @param nachname     Der Nachname des Studiendekans.
     * @param passwort     Das Passwort des Studiendekans.
     * @param benutzername Der Benutzername des Studiendekans.
     * @param email        Die E-Mail-Adresse des Studiendekans.
     * @param studiengang  Der Studiengang, den der Studiendekan leitet.
     */
    public Studiendekan(String vorname, String nachname, String passwort, String benutzername, String email,
            String studiengang) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.benutzername = benutzername;
        this.email = email;
        this.studiengang = studiengang;
    }

    /**
     * Gibt den Vornamen des Studiendekans zurück.
     *
     * @return Der Vorname des Studiendekans.
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Studiendekans.
     *
     * @param vorname Der neue Vorname des Studiendekans.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des Studiendekans zurück.
     *
     * @return Der Nachname des Studiendekans.
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Studiendekans.
     *
     * @param nachname Der neue Nachname des Studiendekans.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt das Passwort des Studiendekans zurück.
     *
     * @return Das Passwort des Studiendekans.
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Studiendekans.
     *
     * @param passwort Das neue Passwort des Studiendekans.
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt den Benutzernamen des Studiendekans zurück.
     *
     * @return Der Benutzername des Studiendekans.
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Setzt den Benutzernamen des Studiendekans.
     *
     * @param benutzername Der neue Benutzername des Studiendekans.
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Gibt die E-Mail-Adresse des Studiendekans zurück.
     *
     * @return Die E-Mail-Adresse des Studiendekans.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Studiendekans.
     *
     * @param email Die neue E-Mail-Adresse des Studiendekans.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gibt den Studiengang des Studiendekans zurück.
     *
     * @return Der Studiengang des Studiendekans.
     */
    public String getStudiengang() {
        return studiengang;
    }

    /**
     * Setzt den Studiengang des Studiendekans.
     *
     * @param studiengang Der neue Studiengang des Studiendekans.
     */
    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

}

