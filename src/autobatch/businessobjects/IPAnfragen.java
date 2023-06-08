package autobatch.businessobjects;
import java.sql.Date;

public class IPAnfragen {



	private int idArbeit;

	private String unternehmen;
	private String thema;
	private String beschreibung;
	
	private int angenommen;

	private int studentMNR;
	private String betreuerMail;
	private Date iptermin;
	
	public IPAnfragen( String unternehmen, String thema, String beschreibung, int angenommen, int studentMNR,
			String betreuerMail, Date termin, int idArbeit) {
		super();
		this.idArbeit = idArbeit;
		this.unternehmen = unternehmen;
		this.thema = thema;
		this.beschreibung = beschreibung;
		this.angenommen = angenommen;
		this.studentMNR = studentMNR;
		this.betreuerMail = betreuerMail;
		this.iptermin=termin;

	}

	public Date getIptermin() {
		return iptermin;
	}

	public void setIptermin(Date iptermin) {
		this.iptermin = iptermin;
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

	public void setAngenommen(int angenommen) {
		this.angenommen = angenommen;
	}

}
