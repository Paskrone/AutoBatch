package autobatch.session;

import autobatch.businessobjects.Benutzer;
import autobatch.businessobjects.Student;

public class SessionManager {
	
    private static SessionManager instance;
    private Benutzer aktuellerBenutzer;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setAktuellerBenutzer(Benutzer aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }
}

