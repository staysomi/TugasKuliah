package BussinesAppDev;
import javax.swing.*;

public class BoxLayoutExp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(),  BoxLayout.X_AXIS));
        JButton button1 = new JButton("Tombol 1");
        JButton button2 = new JButton("Tombol 2");
        JButton button3 = new JButton("Tombol 3");
        JPanel panel1 = new JPanel();

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


        panel1.add(button1);
        panel1.add(button2);

        JPanel panel2 = new JPanel();

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel2.add(button3);

        frame.add(panel1);
        frame.add(panel2);

        frame.pack();
        frame.setVisible(true);
    }
}
