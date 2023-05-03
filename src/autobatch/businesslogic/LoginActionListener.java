package autobatch.businesslogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autobatch.dbaccess.Datenbankabfrage;
import autobatch.gui.Main;

public class LoginActionListener implements ActionListener {
    private Main main;
    private JTextField tf_username;
    private JPasswordField tf_password;
    private JLabel lbl_error;

    public LoginActionListener(Main main, JTextField tf_username, JPasswordField tf_password, JLabel lbl_error) {
        this.main = main;
        this.tf_username = tf_username;
        this.tf_password = tf_password;
        this.lbl_error = lbl_error;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = tf_username.getText();
        char[] passwordChars = tf_password.getPassword();
        String password = new String(passwordChars);

        Datenbankabfrage datenbankabfrage = new Datenbankabfrage();
        boolean checkData = datenbankabfrage.searchStudentsByUsernameAndPassword(username, password);
        if (checkData) {
            main.switchToStudentPanel();
        } else {
            lbl_error.setVisible(true);
        }
    }
}
