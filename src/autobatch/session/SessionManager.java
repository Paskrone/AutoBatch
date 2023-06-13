package autobatch.session;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Student;

/**
 * Verwaltet die aktuelle Benutzersitzung.
 * Diese Klasse folgt dem Singleton-Entwurfsmuster, sodass immer nur eine Instanz existieren kann.
 */
public class SessionManager {
	
	/**
     * Singleton-Instanz des SessionManagers.
     */
    private static SessionManager instance;

    /**
     * Aktuell angemeldeter Benutzer.
     */
    private Benutzer aktuellerBenutzer;

    /**
     * Privater Konstruktor für Singleton-Entwurfsmuster.
     */
    private SessionManager() {
    }

    /**
     * Gibt die Singleton-Instanz des SessionManagers zurück.
     *
     * @return Singleton-Instanz des SessionManagers.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Setzt den aktuell angemeldeten Benutzer.
     *
     * @param aktuellerBenutzer Der Benutzer, der gerade angemeldet ist.
     */
    public void setAktuellerBenutzer(Benutzer aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
    }

    /**
     * Gibt den aktuell angemeldeten Benutzer zurück.
     *
     * @return Der aktuell angemeldete Benutzer.
     */
    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }
}
