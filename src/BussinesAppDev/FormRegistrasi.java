package BussinesAppDev;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegistrasi extends JFrame {

    private JComboBox<String> memberLevelComboBox;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;

    public FormRegistrasi() {
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Form Registrasi");
        northPanel.add(titleLabel);

        mainPanel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // Increased rows for the new field

        JLabel nameLabel = new JLabel("Name:");
        JPanel textpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        textpanel.add(nameLabel);
        nameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPanel passpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        passpanel.add(passwordLabel);
        passwordField = new JPasswordField(15);

        JLabel confirmPasswordLabel = new JLabel("Password Confirmation:");
        JPanel cpasspanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cpasspanel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField(15);

        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        genderpanel.add(genderLabel);
        JPanel radiopanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setSelected(true);
        femaleRadioButton = new JRadioButton("Female");
        radiopanel.add(maleRadioButton);
        radiopanel.add(femaleRadioButton);

        JLabel memberLevelLabel = new JLabel("Member Level:");
        JPanel levelpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        levelpanel.add(memberLevelLabel);
        memberLevelComboBox = new JComboBox<>(new String[]{"Basic", "Gold", "Platinum"});

        centerPanel.add(textpanel);
        centerPanel.add(nameField);
        centerPanel.add(passpanel);
        centerPanel.add(passwordField);
        centerPanel.add(cpasspanel);
        centerPanel.add(confirmPasswordField);
        centerPanel.add(genderpanel);
        centerPanel.add(radiopanel);
        centerPanel.add(levelpanel);
        centerPanel.add(memberLevelComboBox);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerMember();
            }
        });
        southPanel.add(submitButton);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void registerMember() {
        String memberLevel = (String) memberLevelComboBox.getSelectedItem();
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
        } else {
            JOptionPane.showMessageDialog(this, "Registration successful for " + memberLevel + " member\nName: " + name +
                    "\nGender: " + gender);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormRegistrasi registrationGUI = new FormRegistrasi();
            registrationGUI.setVisible(true);
        });
    }
}

