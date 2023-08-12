package BussinesAppDev;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDatabaseGUI extends JFrame {

    private JTextField memberIdField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection connection;
    private String jdbcUrl = "jdbc:mysql://localhost:3306/member";
    private String username = "username";
    private String password = "password";

    public MemberDatabaseGUI() {
        setTitle("Member Database GUI");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Member ID", "Name", "Phone", "Address"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel editPanel = new JPanel(new FlowLayout());

        JPanel paneleditmember = new JPanel();
        paneleditmember.setLayout(new BoxLayout(paneleditmember, BoxLayout.Y_AXIS));
        memberIdField = new JTextField(10);
        memberIdField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                int memberId = 0;
                try {
                    memberId = Integer.parseInt(memberIdField.getText());
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                loadMemberData(memberId);
            }
        });
        paneleditmember.add(new JLabel("Member ID:"));
        paneleditmember.add(memberIdField);

        JPanel paneledithp = new JPanel();
        paneledithp.setLayout(new BoxLayout(paneledithp, BoxLayout.Y_AXIS));
        phoneField = new JTextField(10);
        paneledithp.add(new JLabel("Handphone:"));
        paneledithp.add(phoneField);

        JPanel paneleditalamat = new JPanel();
        paneleditalamat.setLayout(new BoxLayout(paneleditalamat, BoxLayout.Y_AXIS));
        addressField = new JTextField(20);
        paneleditalamat.add(new JLabel("Alamat:"));
        paneleditalamat.add(addressField);

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> saveData());

        editPanel.add(paneleditmember);
        editPanel.add(paneledithp);
        editPanel.add(paneleditalamat);

        editPanel.add(saveButton);

        mainPanel.add(editPanel, BorderLayout.SOUTH);

        add(mainPanel);

        connectToDatabase();
        loadData();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
        }
    }

    private void loadData() {
        try {
            String query = "SELECT * FROM member2";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int memberId = resultSet.getInt("member_id");
                String name = resultSet.getString("member_name");
                String phone = resultSet.getString("handphone");
                String address = resultSet.getString("address");

                tableModel.addRow(new Object[]{memberId, name, phone, address});
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load data from the database.");
        }
    }

    private void loadMemberData(int memberId) {
        try {
            String query = "SELECT * FROM member2 WHERE member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String phone = resultSet.getString("handphone");
                String address = resultSet.getString("address");

                memberIdField.setText(Integer.toString(memberId));
                phoneField.setText(phone);
                addressField.setText(address);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load member data from the database.");
        }
    }

    private void saveData() {
        String memberIdText = memberIdField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if (memberIdText.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
            return;
        }
        try {
            long testphone = Long.parseLong(phone);
            try {
                String query = "UPDATE member2 SET handphone = ?, address = ? WHERE member_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, phone);
                statement.setString(2, address);
                statement.setInt(3, Integer.parseInt(memberIdText));

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    tableModel.setRowCount(0);
                    loadData();
                    JOptionPane.showMessageDialog(this, "Data updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Member ID not found.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update data in the database.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Phone must be a valid number.");
            return;
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MemberDatabaseGUI gui = new MemberDatabaseGUI();
            gui.setVisible(true);
        });
    }
}

