package autobatch.navigation;

import java.awt.CardLayout;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * PanelSwitcher ist eine Klasse, die zum Wechseln zwischen verschiedenen Panels in einer CardLayout-Umgebung verwendet wird.
 * Es kann auch Daten speichern und abrufen, die zwischen verschiedenen Panels geteilt werden sollen.
 */
public class PanelSwitcher {
    private JPanel cards;
    private HashMap<String, Object> sharedData;

    /**
     * Konstruktor zum Initialisieren von PanelSwitcher.
     * @param cards Das JPanel, das als Container für die Karten dient.
     */
    public PanelSwitcher(JPanel cards) {
        this.cards = cards;
        this.sharedData = new HashMap<>();

    }

    /**
     * Wechselt zu einem bestimmten Panel, das durch seinen Namen angegeben wird.
     * @param panelName Der Name des Panels, zu dem gewechselt werden soll.
     */
    public void switchToPanel(String panelName) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, panelName);
    }
    
    /**
     * Speichert ein Datenobjekt mit einem bestimmten Schlüssel.
     * Dies kann verwendet werden, um Daten zwischen verschiedenen Panels zu teilen.
     * @param key Der Schlüssel, unter dem die Daten gespeichert werden.
     * @param data Das zu speichernde Datenobjekt.
     */
    public void storeData(String key, Object data) {
        this.sharedData.put(key, data);
    }

    /**
     * Ruft ein Datenobjekt mit einem bestimmten Schlüssel ab.
     * Dies kann verwendet werden, um Daten, die zwischen verschiedenen Panels geteilt wurden, abzurufen.
     * @param key Der Schlüssel, unter dem die Daten gespeichert wurden.
     * @return Das gespeicherte Datenobjekt.
     */
    public Object getData(String key) {
        return this.sharedData.get(key);
    }

}
