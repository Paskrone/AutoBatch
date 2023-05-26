package autobatch.businessobjects;

public class Arbeit {

	private int idArbeit;

	private String unternehmen;
	private String thema;
	private String beschreibung;
	
	private byte angenommen;
	private byte nda_notwendig;

	private int studentMNR;
	private String betreuerMail;

	public Arbeit(int idArbeit, String unternehmen, String thema, String beschreibung, byte angenommen, byte nda_notwendig, int studentMNR,
			String betreuerMail) {
		super();
		this.idArbeit = idArbeit;
		this.unternehmen = unternehmen;
		this.thema = thema;
		this.beschreibung = beschreibung;
		this.angenommen = angenommen;
		this.nda_notwendig = nda_notwendig;
		this.studentMNR = studentMNR;
		this.betreuerMail = betreuerMail;

	}

	public int getIdArbeit() {
		return idArbeit;
	}

	public void setIdArbeit(int idArbeit) {
		this.idArbeit = idArbeit;
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

	public boolean getAngenommen() {
		return angenommen == 1;
	}

	public void setAngenommen(byte angenommen) {
		this.angenommen = angenommen;
	}

	public boolean getNda_notwenidg() {
		return nda_notwendig == 1;
	}

	public void setNda_notwenidg(byte nda_notwenidg) {
		this.nda_notwendig = nda_notwenidg;
	}

}
