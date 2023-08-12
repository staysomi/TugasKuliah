package BussinesAppDev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginExample extends JFrame implements ActionListener {
    private JLabel label1, label2, label3;
    private JTextField user;
    private JPasswordField pass;
    private JButton masuk, keluar;
    private JCheckBox tampilpass;

    public LoginExample() {
        setTitle("Login App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        label1 = new JLabel("Username:");
        label2 = new JLabel("Password:");
        label3 = new JLabel("");
        user = new JTextField(15);
        pass = new JPasswordField(15);
        masuk = new JButton("Masuk");
        keluar = new JButton("Keluar");
        tampilpass = new JCheckBox("Tampilkan password");

        masuk.addActionListener(this);
        keluar.addActionListener(this);
        tampilpass.addActionListener(this);

        add(label1);
        add(user);
        add(label2);
        add(pass);
        add(masuk);
        add(keluar);
        add(label3);
        add(tampilpass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == masuk) {
            String username = user.getText();
            String password = new String(pass.getPassword());
            label3.setText("Proses login...");
        } else if (e.getSource() == keluar) {
            System.exit(0);
        } else if (e.getSource() == tampilpass) {
            if (tampilpass.isSelected()) {
                pass.setEchoChar((char) 0);
            } else {
                pass.setEchoChar('\u2022');
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginExample().setVisible(true));
    }
}