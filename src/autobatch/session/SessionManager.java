package autobatch.session;

import autobatch.businessobjects.Student;

public class SessionManager {
	
    private static SessionManager instance;
    private Student aktuellerStudent;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setAktuellerStudent(Student student) {
        this.aktuellerStudent = student;
    }

    public Student getAktuellerStudent() {
        return aktuellerStudent;
    }
}

