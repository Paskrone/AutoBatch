package autobatch.navigation;

import java.awt.CardLayout;
import java.util.HashMap;

import javax.swing.JPanel;

public class PanelSwitcher {
    private JPanel cards;
    private HashMap<String, Object> sharedData;

    public PanelSwitcher(JPanel cards) {
        this.cards = cards;
        this.sharedData = new HashMap<>();

    }

    public void switchToPanel(String panelName) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, panelName);
    }
    
    public void storeData(String key, Object data) {
        this.sharedData.put(key, data);
    }

    public Object getData(String key) {
        return this.sharedData.get(key);
    }

}

