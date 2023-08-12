package BussinesAppDev;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class ItemEventExp extends JFrame {

    public ItemEventExp() {
        setTitle("CheckBox Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCheckBox checkBox = new JCheckBox("Enable Feature");
        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Feature enabled!");
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                System.out.println("Feature disabled!");
            }
        });
        add(checkBox);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ItemEventExp().setVisible(true));
    }
}
