package BussinesAppDev;
import javax.swing.*;

public class Underrlined {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("JTextArea with Underline Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setText("<html>This is a <u>sample text</u> with underscore.</html>");
        textArea.setEditable(false); // To prevent editing of the HTML content

        frame.add(new JScrollPane(textArea));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
