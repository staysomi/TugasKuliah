package BussinesAppDev;

import javax.swing.*;

public class JOptionPaneExample {
    public static void main(String[] args) {
        //Message Dialog
        JOptionPane.showMessageDialog(null, "Message Dialog");

        //Confirm Dialog
        int confirm_result = JOptionPane.showConfirmDialog(null, "Confirm Dialog\nApakah Anda yakin ingin melanjutkan?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm_result == JOptionPane.YES_OPTION) {
            System.out.println("Anda memilih Yes.");
        } else if (confirm_result == JOptionPane.NO_OPTION) {
            System.out.println("Anda memilih No.");
        } else if (confirm_result == JOptionPane.CANCEL_OPTION) {
            System.out.println("Anda memilih Cancel.");
        }

        //Input Dialog
        String input = JOptionPane.showInputDialog(null, "Masukkan nama Anda:", "Input Nama", JOptionPane.QUESTION_MESSAGE);
        if (input != null && !input.isEmpty()) {
            System.out.println("Halo, " + input + "!");
        } else {
            System.out.println("Anda belum memasukkan nama.");
        }

        //Option Dialog
        String[] options = {"Mobil", "Motor", "Sepeda"};
        int option_result = JOptionPane.showOptionDialog(null, "Pilih kendaraan favorit Anda:", "Pilihan",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (option_result >= 0) {
            System.out.println("Anda memilih: " + options[option_result]);
        } else {
            System.out.println("Anda belum memilih kendaraan.");
        }
    }
}
