package BussinesAppDev;
import javax.swing.*;

public class SwingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Contoh Swing");
        JLabel label = new JLabel("Halo, Swing!");
        frame.add(label);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

