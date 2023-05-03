package autobatch.gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import autobatch.dbaccess.Datenbankabfrage;

public class Main {

    private JFrame frame;
    private LoginPanel loginFrame;
    private StudentenPanel studentenFrame;
    private JPanel cards;

    public static void main(String[] args) {
        Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
        datenbankabfrage.getStudent();

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
        loginFrame = new LoginPanel(this);
        studentenFrame = new StudentenPanel();

        cards.add(loginFrame, "Login");
        cards.add(studentenFrame, "Studenten");

        frame.setContentPane(cards);
        frame.pack();
    }

    public void switchToStudentPanel() {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "Studenten");
    }
}