package autobatch.businesslogic.itemlistener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import autobatch.businessobjects.Arbeit;
import autobatch.dbaccess.Datenbankabfrage;

public class NdaItemListener implements ItemListener {

	private Arbeit arbeit;
	private JCheckBox checkbox;
	private boolean notwenidg;

	public NdaItemListener(Arbeit arbeit, JCheckBox checkbox, boolean notwenidg) {
		super();
		this.arbeit = arbeit;
		this.checkbox = checkbox;
		this.notwenidg = notwenidg;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (checkbox.isSelected()) {
			System.out.println("Checkbox aktiviert");
			Datenbankabfrage datenbankabfrage = new Datenbankabfrage();

			datenbankabfrage.updateDataArbeitBoolean(arbeit, notwenidg, "nda_notwendig");

		} else {
			System.out.println("Checkbox deaktiviert");
		}

	}

}
