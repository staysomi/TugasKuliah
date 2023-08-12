package BussinesAppDev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class TugasMember extends JFrame {

    private Connection connection;

    private JPanel panel;
    private JLabel nameLabel;
    private JTextField textFieldName;
    private JLabel levelLabel;
    private JComboBox levelComboBox;
    private JButton addButton;
    private JComboBox userComboBox;
    private JTextField textFieldAmount;
    private JButton processButton;
    private JTextArea addResult;
    private JTextArea processResult;
    private JButton showMember;
    private JButton showTrans;
    JScrollPane scrollMember;
    JTable tableMember;
    private JTable tableTrans;
    private JScrollPane scrollTrans;
    String selectedLevel;
    String url = "jdbc:mysql://localhost:3306/member";
    String user = "username";
    String password = "password";
    ArrayList<Member> memberObject;
    ArrayList<Transaction> transObject;
    boolean afterAdd;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TugasMember().setVisible(true));
    }

    public TugasMember() {
        initDB();
        setContentPane(panel);
        setTitle("Input Member");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 300,
                dim.height / 2 - 300);
        setVisible(true);
        validationType(textFieldName, "alpha");
        setPlaceholder(textFieldName, "Enter Text Here");
        validationType(textFieldAmount, "number");
        setPlaceholder(textFieldAmount, "Enter Amount Here");
        addButton.addActionListener(e -> addMember());
        processButton.addActionListener(e -> processTransaction());
        showMember.addActionListener(e -> showMemberData());
        showTrans.addActionListener(e -> showTransData());
        pack();
    }

    private void initDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghubungkan ke database!");
            System.exit(1);
        }
    }

    private void createUIComponents() {
        memberObject = new ArrayList<>();
        transObject = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM member";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String level = resultSet.getString("level");
                int point = resultSet.getInt("point");
                memberObject.add(new Member(id, nama, level, point));
            }
            statement.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
        userComboBox = new JComboBox<>(memberObject.toArray());
        String[] level = {"Basic", "Gold", "Platinum"};
        levelComboBox = new JComboBox<>(level);
        tableMember = new JTable();
        scrollMember = new JScrollPane();
    }

    private DefaultTableModel initMemberData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM member";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            tableModel.addColumn("id");
            tableModel.addColumn("nama");
            tableModel.addColumn("level");
            tableModel.addColumn("point");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String level = resultSet.getString("level");
                int point = resultSet.getInt("point");
                memberObject.add(new Member(id, nama, level, point));
                tableModel.addRow(new Object[]{id, nama, level, point});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    private DefaultTableModel initTransData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT transaction.id_trans, member.nama, member.level, transaction.amount, transaction.point FROM transaction INNER JOIN member ON transaction.id_member = member.id;";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            tableModel.addColumn("id_trans");
            tableModel.addColumn("Member");
            tableModel.addColumn("Level");
            tableModel.addColumn("Amount");
            tableModel.addColumn("Point");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_trans");
                String nama = resultSet.getString("nama");
                String level = resultSet.getString("level");
                int point = resultSet.getInt("point");
                int amount = resultSet.getInt("amount");
                transObject.add(new Transaction(id, nama, level, amount, point));
                tableModel.addRow(new Object[]{id, nama, level,amount, point});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    private void showMemberData() {
        scrollMember.setVisible(true);
        tableMember.setModel(initMemberData());
        scrollMember.setViewportView(tableMember);
        pack();
    }

    private void showTransData() {
        scrollTrans.setVisible(true);
        tableTrans.setModel(initTransData());
        scrollTrans.setViewportView(tableTrans);
        pack();
    }

    private void addMember() {
        afterAdd = true;
        selectedLevel = (String) levelComboBox.getSelectedItem();
        try {
            PreparedStatement addMemberStatement = connection.prepareStatement("INSERT INTO member (nama, level, point) VALUES (?, ?, ?)");
            addMemberStatement.setString(1, textFieldName.getText());
            addMemberStatement.setString(2, selectedLevel);
            addMemberStatement.setInt(3, 0);
            addMemberStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Member berhasil disimpan!");
            addResult.setText("Name : " + textFieldName.getText() + "\nLevel : " + selectedLevel + "\nPoint : " + 0);
            pack();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data member!");
        }
        textFieldName.setText("");
        if (scrollMember.isVisible()){
            showMemberData();
        }
    }

    private void processTransaction() {
        Member selectedUser = memberObject.get(userComboBox.getSelectedIndex());
        int amount = Integer.parseInt(textFieldAmount.getText());
        Level level = new Level();
        int point = 0, addedPoint = 0;
        try {
            String sql = "SELECT id, level, point FROM member WHERE (id) = (?)";
            PreparedStatement selectUserStatement = connection.prepareStatement(sql);
            selectUserStatement.setInt(1, selectedUser.id);
            ResultSet resultSet = selectUserStatement.executeQuery();

            while (resultSet.next()){
                if (resultSet.getString("level").equals("Basic")) {
                    addedPoint = level.basic(amount);
                    point = resultSet.getInt("point") + addedPoint;
                } else if (resultSet.getString("level").equals("Gold")) {
                    addedPoint = level.gold(amount);
                    point = resultSet.getInt("point") + addedPoint;
                } else {
                    addedPoint = level.platinum(amount);
                    point = resultSet.getInt("point") + addedPoint;
                }
            }
            selectUserStatement.close();
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE member set point = ? where id = ?");
            updateStatement.setInt(1, point);
            updateStatement.setInt(2, selectedUser.id);
            updateStatement.executeUpdate();
            updateStatement.close();

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO transaction (amount, id_member, point) VALUES (?, ?, ?)");
            insertStatement.setInt(1, amount);
            insertStatement.setInt(2, selectedUser.id);
            insertStatement.setInt(3, addedPoint);
            insertStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Success Add Transaction");
            processResult.setText("Name : " + selectedUser.nama + "\nLevel : " + selectedUser.level + "\nPoint Added : " + addedPoint + "\nCurrent Point : " + point);
            pack();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to Create Transaction!");
        }
        textFieldAmount.setText("");
        if (scrollMember.isVisible()){
            showMemberData();
        }
        if (scrollTrans.isVisible()){
            showTransData();
        }
    }

    private void validationType(JTextField textField, String type) {
        textField.addKeyListener(
                new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (type.equals("number")) {
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

    public static void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
}

class Level {
    public int basic(int a) {
        return (a / 10000) * 2;
    }

    public int gold(int a) {
        return a / 1000;
    }

    public int platinum(int a) {
        int gold = a / 1000;
        int basic = (a / 5000) * 2;
        return gold + basic;
    }
}

class Member {
    int id;
    String nama, level;
    int point;

    public String toString(){
        return nama;
    }

    public Member(int id, String nama, String level, int point){
        this.id = id;
        this.nama = nama;
        this.level = level;
        this.point = point;
    }
}

class Transaction {
    int id;
    String nama, level;
    int amount;
    int point;

    public String toString(){
        return nama;
    }

    public Transaction(int id, String nama, String level, int amount, int point){
        this.id = id;
        this.nama = nama;
        this.level = level;
        this.amount = amount;
        this.point = point;
    }
}