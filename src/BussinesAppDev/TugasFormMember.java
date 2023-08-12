package BussinesAppDev;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class TugasFormMember extends JFrame {

    private Connection connection;
    private JTextField textFieldName;
    private JComboBox levelComboBox, userComboBox;
    private JButton showTransPage;
    private JTextField textFieldAmount;
    private JTextArea addMemberResult, addTransResult;
    JFrame transFrame, memberListFrame, transListFrame;
    JScrollPane scrollMember, scrollTrans;
    JTable tableMember, tableTrans;
    String selectedLevel;
    String url = "jdbc:mysql://localhost:3306/member";
    String user = "username";
    String password = "password";
    ArrayList<MemberModel> memberObject;
    ArrayList<TransactionModel> transObject;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TugasFormMember().setVisible(true));
    }

    public TugasFormMember() {
        initDB();
        initInputMember();
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

    private void initInputMember() {
        memberObject = new ArrayList<>();
        transObject = new ArrayList<>();
        transFrame = new JFrame();
        transFrame.setVisible(false);
        transListFrame = new JFrame();
        transListFrame.setVisible(false);
        memberListFrame = new JFrame();
        memberListFrame.setVisible(false);
        setTitle("Input Member");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width / 2) - 300,
                (dim.height / 2) - 300);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //init member panel
        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));

        //name panel
        JPanel namePanel = new JPanel();
        namePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        textFieldName = new JTextField(20);
        validationType(textFieldName, "alpha");
        setPlaceholder(textFieldName, "Enter Text Here");
        namePanel.add(nameLabel);
        namePanel.add(textFieldName);

        //level panel
        JPanel levelPanel = new JPanel();
        levelPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.X_AXIS));
        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        String[] level = {"Basic", "Gold", "Platinum"};
        levelComboBox = new JComboBox<>(level);
        levelPanel.add(levelLabel);
        levelPanel.add(levelComboBox);

        //textarea result
        addMemberResult = new JTextArea("Output Add Member");
        addMemberResult.setPreferredSize(new Dimension(300, 50));
        addMemberResult.setEditable(false);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(200, 100));
        JButton addMember = new JButton("Add Member");
        addMember.addActionListener(e -> addMember());
        JButton showMember = new JButton("Show Member");
        showMember.addActionListener(e -> showMember());
        buttonPanel.add(addMember);
        buttonPanel.add(showMember);

        //openTrans
        JPanel otherButtonPanel = new JPanel();
        otherButtonPanel.setLayout(new BoxLayout(otherButtonPanel, BoxLayout.X_AXIS));
        otherButtonPanel.setPreferredSize(new Dimension(200, 100));
        showTransPage = new JButton("Go to Transaction Page");
        otherButtonPanel.add(showTransPage);
        showTransPage.addActionListener(e -> {
            showTransPage();
            showTransPage.setVisible(false);
        });

        memberPanel.add(namePanel);
        memberPanel.add(levelPanel);
        memberPanel.add(addMemberResult);
        memberPanel.add(buttonPanel);
        memberPanel.add(otherButtonPanel);
        add(memberPanel);
        pack();
    }


    private DefaultTableModel initMemberData() {
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
                memberObject.add(new MemberModel(id, nama, level, point));
                tableModel.addRow(new Object[]{id, nama, level, point});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    private DefaultTableModel initTransData() {
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
                transObject.add(new TransactionModel(id, nama, level, amount, point));
                tableModel.addRow(new Object[]{id, nama, level, amount, point});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }

    private void addMember() {
        selectedLevel = (String) levelComboBox.getSelectedItem();
        try {
            PreparedStatement addMemberStatement = connection.prepareStatement("INSERT INTO member (nama, level, point) VALUES (?, ?, ?)");
            addMemberStatement.setString(1, textFieldName.getText());
            addMemberStatement.setString(2, selectedLevel);
            addMemberStatement.setInt(3, 0);
            addMemberStatement.executeUpdate();
            addMemberResult.setText("Name : " + textFieldName.getText() + "\nLevel : " + selectedLevel + "\nPoint : " + 0);
            JOptionPane.showMessageDialog(this, "Success Add Member!");
            if (transFrame.isVisible()) transFrame.dispose();
            if (memberListFrame.isVisible()) memberListFrame.dispose();
            pack();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to Add Member!");
        }
        textFieldName.setText("");
    }

    private void showMember() {
        memberListFrame.dispose();
        memberListFrame.setTitle("Member List");
        memberListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        memberListFrame.setLocation((dim.width / 2) + 100,
                (dim.height / 2) - 500);
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        scrollMember = new JScrollPane();
        tableMember = new JTable();
        JLabel idlabel = new JLabel();
        JTextField editName = new JTextField();
        JComboBox editLevel = new JComboBox<>(new String[]{"Basic", "Gold", "Platinum"});
        JButton update = new JButton("Update");
        newPanel.add(idlabel);
        newPanel.add(editName);
        newPanel.add(editLevel);
        newPanel.add(update);
        newPanel.add(scrollMember);
        newPanel.add(tableMember);
        tableMember.setModel(initMemberData());
        scrollMember.setViewportView(tableMember);
        tableMember.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRowIndex = tableMember.getSelectedRow();
                if (selectedRowIndex >= 0) {
                    Object a1 = tableMember.getValueAt(selectedRowIndex, 0);
                    Object a2 = tableMember.getValueAt(selectedRowIndex, 1);
                    Object a3 = tableMember.getValueAt(selectedRowIndex, 2);
                    idlabel.setText(String.valueOf(a1));
                    editName.setText(a2.toString());
                    editLevel.setSelectedItem(a3.toString());
                }
            }
        });
        update.addActionListener(e -> {
            try {
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE member set nama = ?, level = ? where id = ?");
                updateStatement.setString(1, editName.getText());
                updateStatement.setString(2, editLevel.getSelectedItem().toString());
                updateStatement.setInt(3, Integer.parseInt(idlabel.getText()));
                updateStatement.executeUpdate();
                updateStatement.close();

                JOptionPane.showMessageDialog(this, "Success Update Member");
                if (transListFrame.isVisible()) transListFrame.dispose();
                //textarea result
                pack();
            } catch (SQLException er) {
                er.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to Create Transaction!");
            }
            idlabel.setText("");
            editName.setText("");
            memberListFrame.dispose();
            showMember();
        });
        memberListFrame.add(newPanel);
        memberListFrame.setVisible(true);
        memberListFrame.pack();
    }

    private void showTransPage() {
        transFrame.dispose();
        transFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showTransPage.setVisible(true);
            }
        });
        transFrame.setTitle("Input Transaction");
        transFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        transFrame.setLocation((dim.width / 2) - 300,
                (dim.height / 2) + 100);
        transFrame.setVisible(true);
        transFrame.setLayout(new BoxLayout(transFrame.getContentPane(), BoxLayout.Y_AXIS));
        //init trans panel
        JPanel transPanel = new JPanel();
        transPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        transPanel.setLayout(new BoxLayout(transPanel, BoxLayout.Y_AXIS));

        //member panel
        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.X_AXIS));
        JLabel memberLabel = new JLabel("Member: ");
        memberLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM member";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String level = resultSet.getString("level");
                int point = resultSet.getInt("point");
                memberObject.add(new MemberModel(id, nama, level, point));
            }
            statement.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
        userComboBox = new JComboBox<>(memberObject.toArray());
        memberPanel.add(memberLabel);
        memberPanel.add(userComboBox);

        //amount panel
        JPanel amountPanel = new JPanel();
        amountPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.X_AXIS));
        JLabel amountLabel = new JLabel("Amount: ");
        amountLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        textFieldAmount = new JTextField(20);
        validationType(textFieldAmount, "number");
        setPlaceholder(textFieldAmount, "Enter Amount Here");
        amountPanel.add(amountLabel);
        amountPanel.add(textFieldAmount);

        //textarea result
        addTransResult = new JTextArea("Output Add Trans");
        addTransResult.setPreferredSize(new Dimension(300, 80));
        addTransResult.setEditable(false);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(200, 100));
        JButton addTrans = new JButton("Add Trans");
        addTrans.addActionListener(e -> addTrans());
        JButton showTrans = new JButton("Show Trans");
        showTrans.addActionListener(e -> showTransList());
        buttonPanel.add(addTrans);
        buttonPanel.add(showTrans);

        transPanel.add(memberPanel);
        transPanel.add(amountPanel);
        transPanel.add(addTransResult);
        transPanel.add(buttonPanel);
        transFrame.add(transPanel);
        transFrame.pack();
    }

    private void addTrans() {
        MemberModel selectedUser = memberObject.get(userComboBox.getSelectedIndex());
        int amount = Integer.parseInt(textFieldAmount.getText());
        LevelFunction level = new LevelFunction();
        int point = 0, addedPoint = 0;
        try {
            String sql = "SELECT id, level, point FROM member WHERE (id) = (?)";
            PreparedStatement selectUserStatement = connection.prepareStatement(sql);
            selectUserStatement.setInt(1, selectedUser.id);
            ResultSet resultSet = selectUserStatement.executeQuery();

            while (resultSet.next()) {
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

            addTransResult.setText("Name : " + selectedUser.nama + "\nLevel : " + selectedUser.level + "\nPoint Added : " + addedPoint + "\nCurrent Point : " + point);
            JOptionPane.showMessageDialog(this, "Success Add Transaction");
            if (transListFrame.isVisible()) transListFrame.dispose();
            //textarea result
            pack();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to Create Transaction!");
        }
        textFieldAmount.setText("");
    }

    private void showTransList() {
        transListFrame.dispose();
        transListFrame.setTitle("Transaction  List");
        transListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        transListFrame.setLocation((dim.width / 2) + 100,
                (dim.height / 2));
        JPanel newPanel = new JPanel(new FlowLayout());
        scrollTrans = new JScrollPane();
        tableTrans = new JTable();
        newPanel.add(scrollTrans);
        newPanel.add(tableTrans);
        tableTrans.setModel(initTransData());
        scrollTrans.setViewportView(tableTrans);
        transListFrame.add(newPanel);
        transListFrame.setVisible(true);
        transListFrame.pack();
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

class LevelFunction {
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

class MemberModel {
    int id;
    String nama, level;
    int point;

    public String toString() {
        return nama;
    }

    public MemberModel(int id, String nama, String level, int point) {
        this.id = id;
        this.nama = nama;
        this.level = level;
        this.point = point;
    }
}

class TransactionModel {
    int id;
    String nama, level;
    int amount;
    int point;

    public String toString() {
        return nama;
    }

    public TransactionModel(int id, String nama, String level, int amount, int point) {
        this.id = id;
        this.nama = nama;
        this.level = level;
        this.amount = amount;
        this.point = point;
    }
}
