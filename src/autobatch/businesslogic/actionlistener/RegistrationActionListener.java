package autobatch.businesslogic.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import autobatch.navigation.PanelSwitcher;

/**
 * ActionListener zur Verarbeitung des Übergangs zur Registrierungsseite.
 */
public class RegistrationActionListener implements ActionListener{

    // Instanzvariable
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
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Wechseln zum Registrierungs-Panel
        panelSwitcher.switchToPanel("Registrieren");

    }

}
