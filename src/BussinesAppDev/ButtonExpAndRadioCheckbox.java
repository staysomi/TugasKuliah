package BussinesAppDev;
import javax.swing.*;

public class ButtonExpAndRadioCheckbox {
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Contoh Tombol");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//
//        JPanel panel = new JPanel();
//        frame.add(panel);
//
//        JTextField textField = new JTextField(20);
//        panel.add(textField);
//
//        JButton button = new JButton("Klik Saya");
//        panel.add(button);
//
//        button.addActionListener(e -> {
//            String teks = textField.getText();
//
//            JOptionPane.showMessageDialog(frame, teks);
//        });
//
//        frame.setVisible(true);
        JFrame frame = new JFrame("Form Pilihan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel labelGender = new JLabel("Jenis Kelamin:");
        panel.add(labelGender);

        JRadioButton radioButtonMale = new JRadioButton("Laki-Laki");
        JRadioButton radioButtonFemale = new JRadioButton("Perempuan");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonMale);
        genderGroup.add(radioButtonFemale);

        panel.add(radioButtonMale);
        panel.add(radioButtonFemale);

        JLabel labelEducation = new JLabel("Pendidikan Terakhir:");
        panel.add(labelEducation);

        JRadioButton radioButtonHighSchool = new JRadioButton("SMA");
        JRadioButton radioButtonBachelor = new JRadioButton("Sarjana");
        JRadioButton radioButtonMaster = new JRadioButton("Magister");

        ButtonGroup educationGroup = new ButtonGroup();
        educationGroup.add(radioButtonHighSchool);
        educationGroup.add(radioButtonBachelor);
        educationGroup.add(radioButtonMaster);

        panel.add(radioButtonHighSchool);
        panel.add(radioButtonBachelor);
        panel.add(radioButtonMaster);
        frame.setVisible(true);
    }
}
