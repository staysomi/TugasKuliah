package BussinesAppDev;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame{

    private JLabel oke;
    private JTextField textfieldnama;
    private JPanel MainPanel;
    private JButton button1;

    public Test() {
        setContentPane(MainPanel);

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Test.this,  textfieldnama.getText());
            }
        });
    }

    public static void main(String[] args) {
        new Test();
    }
}
