package BussinesAppDev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableAndJTextFieldExample extends JFrame {
    private JTable table;
    private JTextField textFieldName;
    private JTextField textFieldAge;
    private DefaultTableModel tableModel;

    public JTableAndJTextFieldExample() {
        setTitle("JTable and JTextField Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Object[][] data = {
                {"AS", 30},
                {"EQ", 25},
                {"ASCCX", 28}
        };

        String[] columnNames = {"Name", "Age"};
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex >= 0) {
                    Object name = table.getValueAt(selectedRowIndex, 0);
                    Object age = table.getValueAt(selectedRowIndex, 1);
                    textFieldName.setText(name.toString());
                    textFieldAge.setText(age.toString());
                }
            }
        });
        JPanel textFieldPanel = new JPanel(new GridLayout(2, 2));
        textFieldPanel.add(new JLabel("Name:"));
        textFieldName = new JTextField();
        textFieldPanel.add(textFieldName);
        textFieldPanel.add(new JLabel("Age:"));
        textFieldAge = new JTextField();
        textFieldPanel.add(textFieldAge);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(textFieldPanel, BorderLayout.NORTH);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        add(contentPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JTableAndJTextFieldExample().setVisible(true));
    }
}
