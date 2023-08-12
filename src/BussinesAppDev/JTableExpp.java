package BussinesAppDev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableExpp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/universitas";
        String username = "username";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM mahasiswa";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("nim");
            tableModel.addColumn("nama");
            tableModel.addColumn("nilai");

            while (resultSet.next()) {
                int id = resultSet.getInt("nim");
                String nama = resultSet.getString("nama");
                int umur = resultSet.getInt("nilai");

                tableModel.addRow(new Object[]{id, nama, umur});
            }

            JTable table = new JTable(tableModel);

            JFrame frame = new JFrame("Data from Database to JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
