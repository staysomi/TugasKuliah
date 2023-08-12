package BussinesAppDev;
import javax.swing.*;
import java.awt.*;

public class GroupLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GroupLayout Outside Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the content pane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Create components for GroupLayout
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        // Create a GroupLayout and set it as the layout for the content pane
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);

        // Horizontal Group
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(button1)
                .addGap(10) // Add gap between buttons
                .addComponent(button2)
        );

        // Vertical Group
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(button1)
                .addComponent(button2)
        );

        // Add the GroupLayout container to the content pane
        frame.setContentPane(contentPane);

        // Add other components outside of GroupLayout using BorderLayout
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new FlowLayout());
        otherPanel.add(button3);
        otherPanel.add(button4);

        // Add the otherPanel to the NORTH position of the content pane
        contentPane.add(otherPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
