package autobatch.gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	
    private JFrame frame;
    private LoginPanel loginPanel;
    private StudentenPanel studentenPanel;
    private JPanel cards;

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
        loginPanel = new LoginPanel(this);
        studentenPanel = new StudentenPanel(this);

        cards.add(loginPanel, "Login");
        cards.add(studentenPanel, "Studenten");

        frame.setContentPane(cards);
        frame.pack();
    }

    public void switchToStudentPanel() {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Studenten");
    }
}