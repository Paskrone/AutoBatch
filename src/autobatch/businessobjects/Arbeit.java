package autobatch.businessobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

import autobatch.dbaccess.Datenbankabfrage;

/**
 * Die Klasse Arbeit repräsentiert eine Arbeit im Autobatch-System.
 * Sie enthält Informationen wie die Arbeit ID, das Unternehmen, das Thema, die Beschreibung,
 * die Noten für die Arbeit und den Vortrag, die Gesamtnote, verschiedene Status-Flags,
 * Termine und Informationen zu den beteiligten Personen.
 */
public class Arbeit {

	/**
	 * Die ID der Arbeit.
	 */
	private int idArbeit;

	/**
	 * Das Unternehmen, in dem die Arbeit ausgeführt wird.
	 */
	private String unternehmen;

	/**
	 * Das Thema der Arbeit.
	 */
	private String thema;

	/**
	 * Eine Beschreibung der Arbeit.
	 */
	private String beschreibung;

	/**
	 * Die Note für die Arbeit.
	 */
	private float noteArbeit;

	/**
	 * Die Note für den Vortrag.
	 */
	private float noteVortrag;

	/**
	 * Die Gesamtnote für die Arbeit und den Vortrag.
	 */
	private float gesamtnote;

	/**
	 * Status, ob das Thema der Arbeit angenommen wurde.
	 */
	private byte themaAngenommen;

	/**
	 * Status, ob eine Non-Disclosure Agreement (NDA) notwendig ist.
	 */
	private byte nda_notwendig;

	/**
	 * Status, ob die IP angenommen wurde.
	 */
	private byte ipAngenommen;

	/**
	 * Status der Anmeldung der Arbeit durch den Studenten.
	 */
	private byte ba_Anmeldung_Student;

	/**
	 * Status der Anmeldung der Arbeit durch den Betreuer.
	 */
	private byte ba_Anmeldung_Betreuer;

	/**
	 * Status der Anmeldung der Arbeit durch den Studiendekan.
	 */
	private byte ba_Anmeldung_Studiendekan;

	/**
	 * Status, ob die IP bestanden wurde.
	 */
	private byte ipBestanden;

	/**
	 * Status der Veröffentlichung der Arbeit.
	 */
	private byte veroeffentlichung;

	/**
	 * Das Startdatum der IP.
	 */
	private LocalDate ipStart;

	/**
	 * Der Abgabetermin der Arbeit.
	 */
	private LocalDate baAbgabetermin;

	/**
	 * Das Startdatum der Arbeit.
	 */
	private LocalDate baStart;

	/**
	 * Die Matrikelnummer des Studenten, der die Arbeit ausführt.
	 */
	private int studentMNR;

	/**
	 * Die E-Mail-Adresse des Betreuers.
	 */
	private String betreuerMail;

	/**
	 * Die E-Mail-Adresse des Studiendekans.
	 */
	private String studiendekanMail;


	/**
	 * Konstruktor für die Klasse Arbeit.
	 *
	 * @param idArbeit Die ID der Arbeit.
	 * @param unternehmen Das Unternehmen der Arbeit.
	 * @param thema Das Thema der Arbeit.
	 * @param beschreibung Die Beschreibung der Arbeit.
	 * @param noteArbeit Die Note für die Arbeit.
	 * @param noteVortrag Die Note für den Vortrag.
	 * @param angenommen Der Status der Annahme des Themas.
	 * @param nda_notwendig Der Status, ob ein NDA notwendig ist.
	 * @param ipAngenommen Der Status der Annahme der IP.
	 * @param ba_Anmeldung_Student Der Status der Anmeldung der Arbeit durch den Studenten.
	 * @param ba_Anmeldung_Betreuer Der Status der Anmeldung der Arbeit durch den Betreuer.
	 * @param ba_Anmeldung_Studiendekan Der Status der Anmeldung der Arbeit durch den Studiendekan.
	 * @param ipBestanden Der Status der erfolgreichen Absolvierung der IP.
	 * @param veroeffentlichung Der Status der Veröffentlichung der Arbeit.
	 * @param ipStart Das Startdatum der IP.
	 * @param baAbgabetermin Der Abgabetermin der Arbeit.
	 * @param baStart Der Starttermin der Arbeit.
	 * @param studentMNR Die Matrikelnummer des Studenten.
	 * @param betreuerMail Die E-Mail-Adresse des Betreuers.
	 * @param studiendekanMail Die E-Mail-Adresse des Studiendekans.
	 */
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

	/**
	 * Konvertiert das gegebene Date-Objekt in ein LocalDate.
	 *
	 * @param d das Date-Objekt, das konvertiert werden soll.
	 * @return das konvertierte LocalDate oder null, wenn das Eingabe-Datum null ist.
	 */
	public LocalDate setDate(Date d) {
		if (d != null) {
			return d.toLocalDate();
		} else {
			return null;
		}
	}

	/**
	 * Gibt die ID der Arbeit zurück.
	 *
	 * @return die ID der Arbeit.
	 */
	public int getIdArbeit() {
		return idArbeit;
	}
	/**
	 * Setzt die ID der Arbeit.
	 *
	 * @param idArbeit die neue ID der Arbeit.
	 */
	public void setIdArbeit(int idArbeit) {
		this.idArbeit = idArbeit;
	}
	/**
	 * Gibt den Namen des Unternehmens zurück.
	 *
	 * @return den Namen des Unternehmens.
	 */
	public String getUnternehmen() {
		return unternehmen;
	}
	/**
	 * Setzt den Namen des Unternehmens.
	 *
	 * @param unternehmen der neue Name des Unternehmens.
	 */
	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}

	/**
	 * Gibt das Thema der Arbeit zurück.
	 *
	 * @return Das Thema der Arbeit.
	 */
	public String getThema() {
		return thema;
	}

	/**
	 * Setzt das Thema der Arbeit.
	 *
	 * @param thema Das neue Thema der Arbeit.
	 */
	public void setThema(String thema) {
		this.thema = thema;
	}
	/**
	 * Gibt die Beschreibung zurück.
	 *
	 * @return die Beschreibung.
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	/**
	 * Setzt die Beschreibung.
	 *
	 * @param beschreibung die neue Beschreibung.
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	/**
	 * Gibt die Matrikelnummer des Studenten zurück.
	 *
	 * @return die Matrikelnummer des Studenten.
	 */
	public int getStudentMNR() {
		return studentMNR;
	}


	/**
	 * Setzt die Matrikelnummer des Studenten.
	 *
	 * @param studentMNR die neue Matrikelnummer des Studenten.
	 */
	public void setStudentMNR(int studentMNR) {
		this.studentMNR = studentMNR;
	}
	/**
	 * Gibt die E-Mail-Adresse des Betreuers zurück.
	 *
	 * @return die E-Mail-Adresse des Betreuers.
	 */
	public String getBetreuerMail() {
		return betreuerMail;
	}
	/**
	 * Setzt die E-Mail-Adresse des Betreuers.
	 *
	 * @param betreuerMail die neue E-Mail-Adresse des Betreuers.
	 */
	public void setBetreuerMail(String betreuerMail) {
		this.betreuerMail = betreuerMail;
	}
	/**
	 * Überprüft, ob das Thema der Arbeit angenommen wurde.
	 *
	 * @return true, wenn das Thema der Arbeit angenommen wurde, sonst false.
	 */
	public boolean getThemaAngenommen() {
		return themaAngenommen == 1;
	}
	/**
	 * Setzt den Status der Themenannahme.
	 *
	 * @param b der neue Status der Themenannahme.
	 */
	public void setThemaAngenommen(boolean b) {
		if (b) {
			this.themaAngenommen = 1;
		} else {
			this.themaAngenommen = 0;
		}
	}
	/**
	 * Überprüft, ob eine Geheimhaltungsvereinbarung (NDA) notwendig ist.
	 *
	 * @return true, wenn eine NDA notwendig ist, sonst false.
	 */
	public boolean getNda_notwenidg() {
		return nda_notwendig == 1;
	}
	/**
	 * Setzt den Status der Notwendigkeit einer NDA.
	 *
	 * @param b der neue Status der Notwendigkeit einer NDA.
	 */
	public void setNda_notwenidg(boolean b) {
		if (b) {
			this.nda_notwendig = 1;
		} else {
			this.nda_notwendig = 0;
		}
	}
	/**
	 * Gibt die Note der Arbeit zurück.
	 *
	 * @return die Note der Arbeit.
	 */
	public float getNoteArbeit() {
		return noteArbeit;
	}
	/**
	 * Setzt die Note der Arbeit und aktualisiert die Gesamtnote.
	 *
	 * @param note die neue Note der Arbeit.
	 */
	public void setNoteArbeit(float note) {
		this.noteArbeit = note;
		setGesamtnote();
	}

	/**
	 * Gibt die Note des Vortrags zurück.
	 *
	 * @return die Note des Vortrags.
	 */
	public float getNoteVortrag() {
		return noteVortrag;
	}

	/**
	 * Setzt die Note des Vortrags und aktualisiert die Gesamtnote.
	 *
	 * @param noteVortrag die neue Note des Vortrags.
	 */
	public void setNoteVortrag(float noteVortrag) {
		this.noteVortrag = noteVortrag;
		setGesamtnote();
	}

	/**
	 * Gibt die Gesamtnote zurück.
	 *
	 * @return die Gesamtnote.
	 */
	public float getGesamtnote() {
		return gesamtnote;
	}

	/**
	 * Aktualisiert die Gesamtnote basierend auf der Note der Arbeit und der Note des Vortrags.
	 * Die Gesamtnote wird aktualisiert, wenn beide Noten nicht null sind.
	 * Anschließend wird die aktualisierte Gesamtnote in der Datenbank gespeichert.
	 */
	private void setGesamtnote() {
		if (this.noteArbeit != 0 && this.noteVortrag != 0) {
			this.gesamtnote = (this.noteArbeit * 12 + 3 * this.noteVortrag) / 15;
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
			datenbankabfrage.updateDataArbeitFloat(this, this.gesamtnote, "gesamtnote");
		}

	}

	/**
	 * Gibt die E-Mail-Adresse des Studiendekan zurück.
	 *
	 * @return die E-Mail-Adresse des Studiendekan.
	 */
	public String getStudiendekanMail() {
		return studiendekanMail;
	}

	/**
	 * Setzt die E-Mail-Adresse des Studiendekan.
	 *
	 * @param studiendekanMail die neue E-Mail-Adresse des Studiendekan.
	 */
	public void setStudiendekanMail(String studiendekanMail) {
		this.studiendekanMail = studiendekanMail;
	}

	/**
	 * Überprüft, ob die IP angenommen wurde.
	 *
	 * @return true, wenn die IP angenommen wurde, sonst false.
	 */
	public boolean getIpAngenommen() {
		return ipAngenommen == 1;
	}

	/**
	 * Setzt den Status der IP Annahme.
	 *
	 * @param b der neue Status der IP Annahme.
	 */
	public void setIpAngenommen(boolean b) {
		if (b) {
			this.ipAngenommen = 1;
		} else {
			this.ipAngenommen = 0;
		}
	}

	/**
	 * Überprüft, ob die BA-Anmeldung vom Studenten erfolgt ist.
	 *
	 * @return true, wenn die BA-Anmeldung vom Studenten erfolgt ist, sonst false.
	 */
	public boolean getBa_Anmeldung_Student() {
		return ba_Anmeldung_Student == 1;
	}

	/**
	 * Setzt den Status der BA-Anmeldung vom Studenten.
	 *
	 * @param b der neue Status der BA-Anmeldung vom Studenten.
	 */
	public void setBa_Anmeldung_Student(boolean b) {
		if (b) {
			this.ba_Anmeldung_Student = 1;
		} else {
			ba_Anmeldung_Student = 0;
		}
	}

	/**
	 * Überprüft, ob die BA-Anmeldung vom Betreuer erfolgt ist.
	 *
	 * @return true, wenn die BA-Anmeldung vom Betreuer erfolgt ist, sonst false.
	 */
	public boolean getBa_Anmeldung_Betreuer() {
		return ba_Anmeldung_Betreuer == 1;
	}

	/**
	 * Setzt den Status der BA-Anmeldung vom Betreuer.
	 *
	 * @param b der neue Status der BA-Anmeldung vom Betreuer.
	 */
	public void setBa_Anmeldung_Betreuer(boolean b) {
		if (b) {
			this.ba_Anmeldung_Betreuer = 1;
		} else {
			this.ba_Anmeldung_Betreuer = 0;
		}
	}

	/**
	 * Überprüft, ob die BA-Anmeldung vom Studiendekan erfolgt ist.
	 *
	 * @return true, wenn die BA-Anmeldung vom Studiendekan erfolgt ist, sonst false.
	 */
	public boolean getBa_Anmeldung_Studiendekan() {
		return ba_Anmeldung_Studiendekan == 1;
	}

	/**
	 * Setzt den Status der BA-Anmeldung vom Studiendekan.
	 *
	 * @param b der neue Status der BA-Anmeldung vom Studiendekan.
	 */
	public void setBa_Anmeldung_Studiendekan(boolean b) {
		if (b) {
			this.ba_Anmeldung_Studiendekan = 1;
		} else {
			this.ba_Anmeldung_Studiendekan = 0;
		}
	}

	/**
	 * Überprüft, ob die IP bestanden wurde.
	 *
	 * @return true, wenn die IP bestanden wurde, sonst false.
	 */
	public boolean getIpBestanden() {
		return ipBestanden == 1;
	}

	/**
	 * Setzt den Status des IP Bestehens.
	 *
	 * @param b der neue Status des IP Bestehens.
	 */
	public void setIpBestanden(boolean b) {
		if (b) {
			this.ipBestanden = 1;
		} else {
			this.ipBestanden = 0;
		}
	}


	/**
	 * Überprüft, ob eine Veröffentlichung vorliegt.
	 *
	 * @return true, wenn eine Veröffentlichung vorliegt, sonst false.
	 */
	public boolean getVeroeffentlichung() {
		return veroeffentlichung == 1;
	}

	/**
	 * Setzt den Status der Veröffentlichung.
	 *
	 * @param b der neue Status der Veröffentlichung.
	 */
	public void setVeroeffentlichung(boolean b) {
		if (b) {
			this.veroeffentlichung = 1;
		} else {
			this.veroeffentlichung = 0;
		}
	}

	/**
	 * Gibt das Startdatum der IP zurück.
	 *
	 * @return das Startdatum der IP im Format "dd.MM.yyyy".
	 */
	public String getIpStart() {

		try {
			return ipStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Gibt das Enddatum der IP zurück.
	 *
	 * @return das Enddatum der IP im Format "dd.MM.yyyy".
	 */
	public String getIpEnde() {
		try {
			LocalDate ipEnde = this.ipStart.plusMonths(1);
			return ipEnde.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Setzt das Startdatum der IP. Das Datum muss im Format 'dd.MM.yyyy' vorliegen.
	 *
	 * @param datum Das neue Startdatum der IP.
	 */
	public void setIpStart(LocalDate datum) {
		this.ipStart = datum;
	}

	/**
	 * Gibt das Abgabedatum der BA zurück.
	 *
	 * @return das Abgabedatum der BA im Format "dd.MM.yyyy".
	 */
	public String getBaAbgabetermin() {
		try {
			return baAbgabetermin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Setzt das Abgabedatum der BA.
	 *
	 * @param baAbgabetermin das neue Abgabedatum der BA.
	 */
	public void setBaAbgabetermin(LocalDate baAbgabetermin) {
		this.baAbgabetermin = baAbgabetermin;
	}

	/**
	 * Gibt das Startdatum der BA zurück.
	 *
	 * @return das Startdatum der BA im Format "dd.MM.yyyy".
	 */
	public String getBaStart() {
		try {
			return baStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Setzt das Startdatum der BA.
	 *
	 * @param baStart das neue Startdatum der BA.
	 */
	public void setBaStart(LocalDate baStart) {
		this.baStart = baStart;
	}

}
