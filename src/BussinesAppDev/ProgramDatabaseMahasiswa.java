package BussinesAppDev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;

public class ProgramDatabaseMahasiswa extends JFrame {
    private JTextField textFieldNIM, textFieldNama, textFieldNilaiTugas, textFieldNilaiKuis, textFieldNilaiUTS, textFieldNilaiUAS;
    private JLabel labelNIM, labelNama, labelNilaiTugas, labelNilaiKuis, labelNilaiUTS, labelNilaiUAS;
    private JLabel labelResultNIM, labelResultNama, labelResultNilai, labelResultGrade, labelResultKet;
    private JButton buttonSimpan, buttonTampilkan, buttonReset;
    private JTextArea textAreaHasil;
    private String nama, nim;
    double nilaiTugas, nilaiKuis, nilaiUts, nilaiUas, nilaiRerata;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProgramDatabaseMahasiswa() {
        initGUI();
        initDatabase();
    }

    private void initGUI() {
        setTitle("Program Database Mahasiswa");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        labelNama = new JLabel("Nama:");
        textFieldNama = new JTextField(10);
        validationType(textFieldNama, "alpha");

        labelNIM = new JLabel("NIM:");
        textFieldNIM = new JTextField(10);
        validationType(textFieldNIM, "number");

        labelNilaiTugas = new JLabel("Nilai Tugas:");
        textFieldNilaiTugas = new JTextField(10);
        validationType(textFieldNilaiTugas, "number");

        labelNilaiKuis = new JLabel("Nilai Kuis:");
        textFieldNilaiKuis = new JTextField(10);
        validationType(textFieldNilaiKuis, "number");

        labelNilaiUTS = new JLabel("Nilai UTS:");
        textFieldNilaiUTS = new JTextField(10);
        validationType(textFieldNilaiUTS, "number");

        labelNilaiUAS = new JLabel("Nilai UAS:");
        textFieldNilaiUAS = new JTextField(10);
        validationType(textFieldNilaiUAS, "number");

        labelResultNama = new JLabel();
        labelResultNIM = new JLabel();
        labelResultNilai = new JLabel();
        labelResultGrade = new JLabel();
        labelResultKet = new JLabel();

        buttonSimpan = new JButton("Simpan");
        buttonSimpan.addActionListener(e -> simpanDataMahasiswa());

        buttonTampilkan = new JButton("Tampilkan Data");
        buttonTampilkan.addActionListener(e -> tampilkanDataMahasiswa());

        buttonReset = new JButton("Reset Field");
        buttonReset.addActionListener(e -> resetField());

        textAreaHasil = new JTextArea(5, 10);
        textAreaHasil.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaHasil);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelNama)
                        .addComponent(labelNIM)
                        .addComponent(labelNilaiTugas)
                        .addComponent(labelNilaiKuis)
                        .addComponent(labelNilaiUTS)
                        .addComponent(labelNilaiUAS))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldNama)
                        .addComponent(textFieldNIM)
                        .addComponent(textFieldNilaiTugas)
                        .addComponent(textFieldNilaiKuis)
                        .addComponent(textFieldNilaiUTS)
                        .addComponent(textFieldNilaiUAS)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonSimpan)
                                .addComponent(buttonTampilkan)
                                .addComponent(buttonReset))
                        .addComponent(scrollPane)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelResultNama)
                        .addComponent(labelResultNIM)
                        .addComponent(labelResultNilai)
                        .addComponent(labelResultGrade)
                        .addComponent(labelResultKet)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNama)
                        .addComponent(textFieldNama)
                        .addComponent(labelResultNama))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNIM)
                        .addComponent(textFieldNIM)
                        .addComponent(labelResultNIM))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNilaiTugas)
                        .addComponent(textFieldNilaiTugas)
                        .addComponent(labelResultNilai))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNilaiKuis)
                        .addComponent(textFieldNilaiKuis)
                        .addComponent(labelResultGrade))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNilaiUTS)
                        .addComponent(textFieldNilaiUTS)
                        .addComponent(labelResultKet))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNilaiUAS)
                        .addComponent(textFieldNilaiUAS))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSimpan)
                        .addComponent(buttonTampilkan)
                        .addComponent(buttonReset))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(scrollPane))
        );

        add(panel);
        pack();
        setVisible(true);
    }

    private void initDatabase() {
        String url = "jdbc:mysql://localhost:3306/universitas";
        String user = "username";
        String password = "password";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghubungkan ke database!");
            System.exit(1);
        }
    }

    private void validationType(JTextField textField, String type) {
        textField.addKeyListener(
                new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (type.equals("number")){
                            if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                                e.consume();
                            }
                        } else {
                            if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                                e.consume();
                            }
                        }
                    }
                }
        );
    }

    private void resetField() {
        textFieldNama.setText("");
        textFieldNIM.setText("");
        textFieldNilaiTugas.setText("");
        textFieldNilaiKuis.setText("");
        textFieldNilaiUTS.setText("");
        textFieldNilaiUAS.setText("");
        labelResultNama.setText("");
        labelResultNIM.setText("");
        labelResultNilai.setText("");
        labelResultGrade.setText("");
        labelResultKet.setText("");
        pack();
    }

    private void simpanDataMahasiswa() {
        nama = textFieldNama.getText();
        nim = textFieldNIM.getText();
        nilaiTugas = Double.parseDouble(textFieldNilaiTugas.getText());
        nilaiKuis = Double.parseDouble(textFieldNilaiKuis.getText());
        nilaiUts = Double.parseDouble(textFieldNilaiUTS.getText());
        nilaiUas = Double.parseDouble(textFieldNilaiUAS.getText());
        nilaiRerata = (nilaiTugas + nilaiKuis + nilaiUts + nilaiUas) / 4;
        String grade = "", keterangan = "";
        if (nilaiRerata < 50) {
            grade = "E";
        } else if (nilaiRerata >= 50 && nilaiRerata < 70) {
            grade = "D";
        } else if (nilaiRerata >= 70 && nilaiRerata < 80) {
            grade = "C";
        } else if (nilaiRerata >= 80 && nilaiRerata < 90) {
            grade = "B";
        } else {
            grade = "A";
        }
        keterangan = nilaiRerata < 70 ? "Tidak Lulus" : "Lulus";

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO mahasiswa (nama, nim, nilai, grade, keterangan) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, nim);
            preparedStatement.setDouble(3, nilaiRerata);
            preparedStatement.setString(4, grade);
            preparedStatement.setString(5, keterangan);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil disimpan!");

            labelResultNama.setText("Nama : " + nama);
            labelResultNIM.setText("NIM : " + nim);
            labelResultNilai.setText("Rerata : " + nilaiRerata);
            labelResultGrade.setText("Grade : " + grade);
            labelResultKet.setText("Keterangan : " + keterangan);

            pack();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data mahasiswa!");
        }
    }

    private void tampilkanDataMahasiswa() {

        String url = "jdbc:mysql://localhost:3306/universitas";
        String user = "username";
        String password = "password";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgramDatabaseMahasiswa().setVisible(true));
    }
}
