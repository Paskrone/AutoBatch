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

    // Instanzvariablen
    private Arbeit arbeit; // Arbeitsobjekt, das aktualisiert wird
    private JCheckBox checkbox; // Die Checkbox, die den NDA-Zustand repräsentiert
    private boolean notwenidg; // Flag, das anzeigt, ob eine NDA erforderlich ist oder nicht

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
     * Diese Methode wird ausgeführt, wenn der Zustand der Checkbox geändert wird.
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
