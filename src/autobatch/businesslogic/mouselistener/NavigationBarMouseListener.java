package autobatch.businesslogic.mouselistener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * Diese abstrakte Klasse erweitert MouseAdapter und definiert einige Standardverhaltensweisen
 * für ein JLabel, das als Navigationselement dient.
 */
public abstract class NavigationBarMouseListener extends MouseAdapter {

    /**
     * Diese Methode wird aufgerufen, wenn auf das JLabel geklickt wird. Sie ändert die Schriftfarbe
     * des JLabels zu Blau und ruft die abstrakte Methode labelClicked() auf.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        clickedLabel.setForeground(Color.BLUE);
        labelClicked();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Mauszeiger über das JLabel bewegt wird. Sie ändert
     * die Schriftfarbe des JLabels zu Blau und ändert den Mauszeiger zu einem Hand-Cursor.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel hoveredLabel = (JLabel) e.getSource();
        hoveredLabel.setForeground(Color.BLUE);
        hoveredLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Mauszeiger das JLabel verlässt. Sie ändert die
     * Schriftfarbe des JLabels zu Schwarz und ändert den Mauszeiger zurück zum Standard-Cursor.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        JLabel exitedLabel = (JLabel) e.getSource();
        exitedLabel.setForeground(Color.BLACK);
        exitedLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Diese abstrakte Methode wird aufgerufen, wenn auf das JLabel geklickt wird. Sie muss von
     * jeder Klasse, die diese abstrakte Klasse erweitert, implementiert werden.
     */
    public abstract void labelClicked();
}
