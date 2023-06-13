package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import autobatch.navigation.PanelSwitcher;

/**
 * ActionListener zur Verarbeitung des Übergangs zur Registrierungsseite.
 */
public class RegistrationActionListener implements ActionListener{

	/**
     * PanelSwitcher zur Verwaltung des Wechsels zwischen verschiedenen Panels (Ansichten) in der Anwendung.
     */
    private PanelSwitcher panelSwitcher; // Hilfsklasse zum Wechseln zwischen verschiedenen Panels

    /**
     * Konstruktor
     * @param panelSwitcher Hilfsklasse zum Wechseln zwischen verschiedenen Panels
     */
    public RegistrationActionListener(PanelSwitcher panelSwitcher) {
        this.panelSwitcher = panelSwitcher;
    }

    /**
     * Diese Methode wird ausgeführt, wenn der Benutzer auf den Button zum Übergang zur Registrierungsseite klickt.
     * In der Methode wird die Funktion switchToPanel des panelSwitcher Objekts aufgerufen, um zur Registrierungsseite zu wechseln.
     * Der String "Registrieren" ist dabei der Schlüsselname des entsprechenden Panels in der Panel-Verwaltung der Anwendung.
     * @param e Das ausgelöste ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Wechseln zum Registrierungs-Panel
        panelSwitcher.switchToPanel("Registrieren");

    }

}
