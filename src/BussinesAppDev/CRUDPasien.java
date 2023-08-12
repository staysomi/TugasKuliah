package BussinesAppDev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CRUDPasien extends JFrame {
    private List<Patient> patients;
    private int currentIndex;

    private JTextField txtName;
    private JTextField txtNIK;
    private JTextField txtDOB;
    private JTextField txtAddress;
    private JTable table;

    public CRUDPasien() {
        patients = new ArrayList<>();
        currentIndex = -1;

        // Set up the UI
        setTitle("CRUD Pasien");
        setSize(960, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Input Form
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nama Pasien:"));
        txtName = new JTextField(20);
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("NIK:"));
        txtNIK = new JTextField(15);
        inputPanel.add(txtNIK);

        inputPanel.add(new JLabel("Tanggal Lahir (YYYY-MM-DD):"));
        txtDOB = new JTextField(10);
        inputPanel.add(txtDOB);

        inputPanel.add(new JLabel("Alamat:"));
        txtAddress = new JTextField(50);
        inputPanel.add(txtAddress);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Tambah");
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Perbarui");
        buttonPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        buttonPanel.add(btnDelete);

        JButton btnPrevious = new JButton("<< Sebelumnya");
        buttonPanel.add(btnPrevious);

        JButton btnNext = new JButton("Berikutnya >>");
        buttonPanel.add(btnNext);

        JButton btnList = new JButton("Daftar Pasien");
        buttonPanel.add(btnList);

        JButton btnExit = new JButton("Keluar");
        buttonPanel.add(btnExit);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Table
        String[] columnNames = {"No", "Nama Pasien", "NIK", "Tanggal Lahir", "Alamat"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Button Actions
        btnAdd.addActionListener(e -> addPatient());

        btnUpdate.addActionListener(e -> updatePatient());

        btnDelete.addActionListener(e -> deletePatient());

        btnPrevious.addActionListener(e -> previousPatient());

        btnNext.addActionListener(e -> nextPatient());

        btnList.addActionListener(e -> showPatientList());

        btnExit.addActionListener(e -> System.exit(0));

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }

    private void addPatient() {
        String name = txtName.getText();
        String nik = txtNIK.getText();
        String dob = txtDOB.getText();
        String address = txtAddress.getText();

        if (name.isEmpty() || nik.isEmpty() || dob.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan isi semua field.");
            return;
        }

        if (isNIKExists(nik)) {
            JOptionPane.showMessageDialog(this, "NIK sudah ada dalam data pasien.");
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dob);
            Patient patient = new Patient(name, nik, date, address);
            patients.add(patient);
            updateTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Data pasien berhasil ditambahkan.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal lahir tidak valid (YYYY-MM-DD).");
        }
    }

    private void updatePatient() {
        if (currentIndex == -1) {
            JOptionPane.showMessageDialog(this, "Tidak ada data pasien yang dipilih.");
            return;
        }

        String name = txtName.getText();
        String address = txtAddress.getText();

        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan isi semua field.");
            return;
        }

        Patient patient = patients.get(currentIndex);
        patient.setName(name);
        patient.setAddress(address);

        updateTable();
        JOptionPane.showMessageDialog(this, "Data pasien berhasil diperbarui.");
    }

    private void deletePatient() {
        if (currentIndex == -1) {
            JOptionPane.showMessageDialog(this, "Tidak ada data pasien yang dipilih.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data pasien ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            patients.remove(currentIndex);
            currentIndex = -1;
            updateTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Data pasien berhasil dihapus.");
        }
    }

    private void previousPatient() {
        if (currentIndex > 0) {
            currentIndex--;
            displayPatient();
        } else {
            JOptionPane.showMessageDialog(this, "Ini adalah pasien pertama.");
        }
    }

    private void nextPatient() {
        if (currentIndex < patients.size() - 1) {
            currentIndex++;
            displayPatient();
        } else {
            JOptionPane.showMessageDialog(this, "Ini adalah pasien terakhir.");
        }
    }

    private void showPatientList() {
        JFrame patientListFrame = new JFrame("Daftar Pasien");
        patientListFrame.setSize(500, 300);
        patientListFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"No", "Nama Pasien", "NIK", "Tanggal Lahir", "Alamat"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable patientTable = new JTable(tableModel);

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            Object[] rowData = {i + 1, patient.getName(), patient.getNIK(), formatDate(patient.getDOB()), patient.getAddress()};
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(patientTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        patientListFrame.add(panel);
        patientListFrame.setVisible(true);
    }

    private void updateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            Object[] rowData = {i + 1, patient.getName(), patient.getNIK(), formatDate(patient.getDOB()), patient.getAddress()};
            tableModel.addRow(rowData);
        }
    }

    private void displayPatient() {
        if (currentIndex != -1) {
            Patient patient = patients.get(currentIndex);
            txtName.setText(patient.getName());
            txtNIK.setText(patient.getNIK());
            txtDOB.setText(formatDate(patient.getDOB()));
            txtAddress.setText(patient.getAddress());
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtNIK.setText("");
        txtDOB.setText("");
        txtAddress.setText("");
    }

    private boolean isNIKExists(String nik) {
        for (Patient patient : patients) {
            if (patient.getNIK().equals(nik)) {
                return true;
            }
        }
        return false;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRUDPasien::new);
    }
}
class Patient {
    private String name;
    private String NIK;
    private Date DOB;
    private String address;

    public Patient(String name, String NIK, Date DOB, String address) {
        this.name = name;
        this.NIK = NIK;
        this.DOB = DOB;
        this.address = address;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}