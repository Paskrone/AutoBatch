package autobatch.businesslogic.listselectionlistener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import autobatch.navigation.PanelSwitcher;

public class AuswahlSelectionListener implements ListSelectionListener {
	
	private PanelSwitcher panelSwitcher;
	
	public AuswahlSelectionListener(PanelSwitcher panelSwitcher, JTable table) {
		super();
		this.panelSwitcher = panelSwitcher;
		this.table = table;
	}


	private JTable table;
	

	 @Override
     public void valueChanged(ListSelectionEvent e) {
         if (!e.getValueIsAdjusting()) { // Überprüfen Sie, ob die Selektion vollständig ist
             int selectedRow = table.getSelectedRow();
             if (selectedRow != -1) { // Überprüfen Sie, ob eine Zeile ausgewählt wurde
                 // Holen Sie sich die Daten aus der ausgewählten Zeile
                 Object lastName = table.getValueAt(selectedRow, 0);
                 Object mail = table.getValueAt(selectedRow, 1);
                 // Machen Sie hier Ihre Aktion mit den ausgewählten Daten
                 System.out.println("Selected: " + lastName + ", mail " + mail);
                 
                 panelSwitcher.switchToPanel("Studenten_Betreuer_Anfrage");

             }
         }
     }
	
}
