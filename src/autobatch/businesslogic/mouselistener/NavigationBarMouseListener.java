package autobatch.businesslogic.mouselistener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public abstract class NavigationBarMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        clickedLabel.setForeground(Color.BLUE);
        labelClicked();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel hoveredLabel = (JLabel) e.getSource();
        hoveredLabel.setForeground(Color.BLUE);
        hoveredLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel exitedLabel = (JLabel) e.getSource();
        exitedLabel.setForeground(Color.BLACK);
        exitedLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public abstract void labelClicked();
}
