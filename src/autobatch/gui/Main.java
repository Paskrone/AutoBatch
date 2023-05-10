package autobatch.gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

public class Main {
	
    private JFrame frame;
    private JPanel cards;
    private PanelSwitcher panelSwitcher;
    public static PanelManager panelManager;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main main = new Main();
                    main.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cards = new JPanel(new CardLayout());
        panelSwitcher = new PanelSwitcher(cards);
        panelManager = new PanelManager(panelSwitcher, cards);

        frame.setContentPane(cards);
        frame.pack();
    }

}
