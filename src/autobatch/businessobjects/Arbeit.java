package autobatch.businessobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

import autobatch.dbaccess.Datenbankabfrage;

public class Arbeit {

	private int idArbeit;

	private String unternehmen;
	private String thema;
	private String beschreibung;

	private float noteArbeit;
	private float noteVortrag;
	private float gesamtnote;

	private byte themaAngenommen;
	private byte nda_notwendig;
	private byte ipAngenommen;

	private byte ba_Anmeldung_Student;
	private byte ba_Anmeldung_Betreuer;
	private byte ba_Anmeldung_Studiendekan;
	private byte ipBestanden;
	private byte veroeffentlichung;

	private LocalDate ipStart;
	private LocalDate baAbgabetermin;
	private LocalDate baStart;

	private int studentMNR;
	private String betreuerMail;
	private String studiendekanMail;

	public Arbeit(int idArbeit, String unternehmen, String thema, String beschreibung, float noteArbeit,
			float noteVortrag, byte angenommen, byte nda_notwendig, byte ipAngenommen, byte ba_Anmeldung_Student,
			byte ba_Anmeldung_Betreuer, byte ba_Anmeldung_Studiendekan, byte ipBestanden, byte veroeffentlichung,
			Date ipStart, Date baAbgabetermin, Date baStart, int studentMNR, String betreuerMail,
			String studiendekanMail) {
		super();
		this.idArbeit = idArbeit;
		this.unternehmen = unternehmen;
		this.thema = thema;
		this.beschreibung = beschreibung;

		this.noteArbeit = noteArbeit;
		this.noteVortrag = noteVortrag;
		setGesamtnote();

		this.themaAngenommen = angenommen;
		this.nda_notwendig = nda_notwendig;
		this.ipAngenommen = ipAngenommen;

		this.ba_Anmeldung_Betreuer = ba_Anmeldung_Betreuer;
		this.ba_Anmeldung_Student = ba_Anmeldung_Student;
		this.ba_Anmeldung_Studiendekan = ba_Anmeldung_Studiendekan;
		this.ipBestanden = ipBestanden;
		this.veroeffentlichung = veroeffentlichung;

		this.ipStart = setDate(ipStart);
		this.baAbgabetermin = setDate(baAbgabetermin);
		this.baStart = setDate(baStart);

		this.studentMNR = studentMNR;
		this.betreuerMail = betreuerMail;
		this.studiendekanMail = studiendekanMail;

	}

	public LocalDate setDate(Date d) {
		if (d != null) {
			return d.toLocalDate();
		} else {
			return null;
		}
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

	public boolean getThemaAngenommen() {
		return themaAngenommen == 1;
	}

	public void setThemaAngenommen(boolean b) {
		if (b) {
			this.themaAngenommen = 1;
		} else {
			this.themaAngenommen = 0;
		}
	}

	public boolean getNda_notwenidg() {
		return nda_notwendig == 1;
	}

	public void setNda_notwenidg(boolean b) {
		if (b) {
			this.nda_notwendig = 1;
		} else {
			this.nda_notwendig = 0;
		}
	}

	public float getNoteArbeit() {
		return noteArbeit;
	}

	public void setNoteArbeit(float note) {
		this.noteArbeit = note;
		setGesamtnote();
	}

	public float getNoteVortrag() {
		return noteVortrag;
	}

	public void setNoteVortrag(float noteVortrag) {
		this.noteVortrag = noteVortrag;
		setGesamtnote();
	}

	public float getGesamtnote() {
		return gesamtnote;
	}

	private void setGesamtnote() {
		if (this.noteArbeit != 0 && this.noteVortrag != 0) {
			this.gesamtnote = (this.noteArbeit * 12 + 3 * this.noteVortrag) / 15;
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			datenbankabfrage.updateDataArbeitFloat(this, this.gesamtnote, "gesamtnote");
		}

	}

	public String getStudiendekanMail() {
		return studiendekanMail;
	}

	public void setStudiendekanMail(String studiendekanMail) {
		this.studiendekanMail = studiendekanMail;
	}

	public boolean getIpAngenommen() {
		return ipAngenommen == 1;
	}

	public void setIpAngenommen(boolean b) {
		if (b) {
			this.ipAngenommen = 1;
		} else {
			this.ipAngenommen = 0;
		}
	}

	public boolean getBa_Anmeldung_Student() {
		return ba_Anmeldung_Student == 1;
	}

	public void setBa_Anmeldung_Student(boolean b) {
		if (b) {
			this.ba_Anmeldung_Student = 1;
		} else {
			ba_Anmeldung_Student = 0;
		}
	}

	public boolean getBa_Anmeldung_Betreuer() {
		return ba_Anmeldung_Betreuer == 1;
	}

	public void setBa_Anmeldung_Betreuer(boolean b) {
		if (b) {
			this.ba_Anmeldung_Betreuer = 1;
		} else {
			this.ba_Anmeldung_Betreuer = 0;
		}
	}

	public boolean getBa_Anmeldung_Studiendekan() {
		return ba_Anmeldung_Studiendekan == 1;
	}

	public void setBa_Anmeldung_Studiendekan(boolean b) {
		if (b) {
			this.ba_Anmeldung_Studiendekan = 1;
		} else {
			this.ba_Anmeldung_Studiendekan = 0;
		}
	}

	public boolean getIpBestanden() {
		return ipBestanden == 1;
	}

	public void setIpBestanden(boolean b) {
		if (b) {
			this.ipBestanden = 1;
		} else {
			this.ipBestanden = 0;
		}
	}

	public boolean getVeroeffentlichung() {
		return veroeffentlichung == 1;
	}

	public void setVeroeffentlichung(boolean b) {
		if (b) {
			this.veroeffentlichung = 1;
		} else {
			this.veroeffentlichung = 0;
		}
	}

	public String getIpStart() {

		try {
			return ipStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (Exception e) {
			return null;
		}

	}

	public String getIpEnde() {
		try {
			LocalDate ipEnde = this.ipStart.plusMonths(1);
			return ipEnde.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setIpStart(LocalDate datum) {
		this.ipStart = datum;
	}

	public String getBaAbgabetermin() {
		try {
			return baAbgabetermin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		} catch (Exception e) {
			return null;
		}
	}

	public void setBaAbgabetermin(LocalDate baAbgabetermin) {
		this.baAbgabetermin = baAbgabetermin;
	}

	public String getBaStart() {
		try {
			return baStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		} catch (Exception e) {
			return null;
		}
	}

	public void setBaStart(LocalDate baStart) {
		this.baStart = baStart;
	}

}
