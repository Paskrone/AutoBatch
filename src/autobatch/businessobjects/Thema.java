package autobatch.businessobjects;

public class Thema {

	private int idThema;

	private String unternehmen;
	private String thema;
	private String beschreibung;

	private int studentMNR;
	private String betreuerMail;

	public Thema(int idThema, String unternehmen, String thema, String beschreibung, int studentMNR,
			String betreuerMail) {
		super();
		this.idThema = idThema;
		this.unternehmen = unternehmen;
		this.thema = thema;
		this.beschreibung = beschreibung;
		this.studentMNR = studentMNR;
		this.betreuerMail = betreuerMail;

	}

	public int getIdThema() {
		return idThema;
	}

	public void setIdThema(int idThema) {
		this.idThema = idThema;
	}

	public String getUnternehmen() {
		return unternehmen;
	}

	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getStudentMNR() {
		return studentMNR;
	}

	public void setStudentMNR(int studentMNR) {
		this.studentMNR = studentMNR;
	}

	public String getBetreuerMail() {
		return betreuerMail;
	}

	public void setBetreuerMail(String betreuerMail) {
		this.betreuerMail = betreuerMail;
	}

}
