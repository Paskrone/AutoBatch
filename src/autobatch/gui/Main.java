package autobatch.gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.navigation.PanelManager;
import autobatch.navigation.PanelSwitcher;

/**
 * Die Main-Klasse ist der Hauptpunkt des Programms und startet die grafische Benutzeroberfläche.
 */
public class Main {
	
    private JFrame frame;
    private JPanel cards;
    private PanelSwitcher panelSwitcher;
    public static PanelManager panelManager;

    /**
     * Der Hauptprogrammeinstiegspunkt.
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {

        // Erstellt und zeigt das Hauptfenster im Event-Dispatch-Thread.
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

    /**
     * Konstruktor für die Main-Klasse.
     * Initialisiert das Hauptfenster und legt das Layout und das Verhalten fest.
     */
    public Main() {
        // Initialisierung des Hauptfensters
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialisierung des Karten-Layouts und des Panel-Schalters
        cards = new JPanel(new CardLayout());
        panelSwitcher = new PanelSwitcher(cards);
        
        // Initialisierung des Panel-Managers
        panelManager = new PanelManager(panelSwitcher, cards);

        // Setzen des Inhaltsbereichs des Hauptfensters und Packen des Fensters
        frame.setContentPane(cards);
        frame.pack();
    }

}
