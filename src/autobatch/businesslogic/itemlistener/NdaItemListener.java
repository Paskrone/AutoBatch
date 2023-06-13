package autobatch.businesslogic.itemlistener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

/**
 * ItemListener zur Verarbeitung des Zustands einer Checkbox, die repräsentiert, ob eine NDA erforderlich ist.
 */
public class NdaItemListener implements ItemListener {

    /**
     * Arbeit ist ein Geschäftsobjekt, das die Daten und Operationen einer Arbeit repräsentiert. 
     * In diesem Kontext wird es verwendet, um die Zustandsänderung einer NDA-Anforderung an eine Arbeit zu repräsentieren.
     */
    private Arbeit arbeit;

    /**
     * Checkbox ist ein Swing-Komponente, die einen Schalter darstellt, der ein- oder ausgeschaltet sein kann.
     * In diesem Kontext repräsentiert es die Zustandsänderung, ob eine NDA-Anforderung für eine Arbeit notwendig ist oder nicht.
     */
    private JCheckBox checkbox;

    /**
     * Notwendig ist ein boolean Flag, das anzeigt, ob eine NDA-Anforderung für eine Arbeit notwendig ist oder nicht.
     * Wenn es auf true gesetzt ist, ist eine NDA-Anforderung notwendig. Wenn es auf false gesetzt ist, ist sie nicht notwendig.
     */
    private boolean notwenidg;


    /**
     * Konstruktor
     * @param arbeit Arbeitsobjekt, das aktualisiert wird
     * @param checkbox Die Checkbox, die den NDA-Zustand repräsentiert
     * @param notwenidg Flag, das anzeigt, ob eine NDA erforderlich ist oder nicht
     */
    public NdaItemListener(Arbeit arbeit, JCheckBox checkbox, boolean notwenidg) {
        super();
        this.arbeit = arbeit;
        this.checkbox = checkbox;
        this.notwenidg = notwenidg;
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Zustand der Checkbox geändert wird.
     * Wenn die Checkbox aktiviert ist, wird eine neue Datenbankabfrage Instanz erstellt und die updateDataArbeitBoolean Methode aufgerufen,
     * um den NDA-Zustand des Arbeitsobjekts in der Datenbank zu aktualisieren.
     * Wenn die Checkbox deaktiviert ist, wird eine Statusmeldung auf der Konsole ausgegeben.
     * @param e Das ItemEvent, das die Zustandsänderung der Checkbox darstellt.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        // Wenn die Checkbox ausgewählt ist
        if (checkbox.isSelected()) {
            System.out.println("Checkbox aktiviert");

            // Erstelle eine Instanz von Datenbankabfrage
            Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

            // Aktualisiere den NDA-Zustand im Datenbank-Arbeitsobjekt
            datenbankabfrage.updateDataArbeitBoolean(arbeit, notwenidg, "nda_notwendig");

        } else {
            // Wenn die Checkbox nicht ausgewählt ist
            System.out.println("Checkbox deaktiviert");
        }
    }
}
