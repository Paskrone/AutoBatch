package autobatch.businessobjects;

/**
 * Diese abstrakte Klasse stellt einen Benutzer dar.
 * Sie definiert die gemeinsamen Attribute und Methoden für alle Benutzertypen.
 */
public abstract class Benutzer {
	
    protected String nachname;
    protected String vorname;
    protected String benutzername;
    protected String passwort;
    protected String email;
    
    /**
     * Gibt den Nachnamen des Benutzers zurück.
     *
     * @return Der Nachname des Benutzers.
     */
    public String getNachname() {
        return nachname;
    }
    
    /**
     * Setzt den Nachnamen des Benutzers.
     *
     * @param nachname Der neue Nachname des Benutzers.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    
    /**
     * Gibt den Vornamen des Benutzers zurück.
     *
     * @return Der Vorname des Benutzers.
     */
    public String getVorname() {
        return vorname;
    }
    
    /**
     * Setzt den Vornamen des Benutzers.
     *
     * @param vorname Der neue Vorname des Benutzers.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    /**
     * Gibt den Benutzernamen des Benutzers zurück.
     *
     * @return Der Benutzername des Benutzers.
     */
    public String getBenutzername() {
        return benutzername;
    }
    
    /**
     * Setzt den Benutzernamen des Benutzers.
     *
     * @param benutzername Der neue Benutzername des Benutzers.
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
    
    /**
     * Gibt das Passwort des Benutzers zurück.
     *
     * @return Das Passwort des Benutzers.
     */
    public String getPasswort() {
        return passwort;
    }
    
    /**
     * Setzt das Passwort des Benutzers.
     *
     * @param passwort Das neue Passwort des Benutzers.
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    
    /**
     * Gibt die E-Mail-Adresse des Benutzers zurück.
     *
     * @return Die E-Mail-Adresse des Benutzers.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Setzt die E-Mail-Adresse des Benutzers.
     *
     * @param email Die neue E-Mail-Adresse des Benutzers.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
