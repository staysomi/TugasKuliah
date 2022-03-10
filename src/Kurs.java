import java.util.Scanner;

public class Kurs {

    public static void main(String[] args) {
        double uang, kurs;
        int mata_uang, type_konversi;
        String mata_uang_str = "";

        System.out.println("Masukan Type Konversi\n 1. IDR to Other \n 2. Other to IDR \n Ketik (1/2)");
        Scanner type = new Scanner(System.in);
        type_konversi = type.nextInt();

        if (type_konversi == 1) {
            System.out.println("""
                    IDR to Other\s
                     Pilih Tujuan Konversi\s
                     1. YEN ( 1 IDR = 0.0081 Yen )\s
                     2. Ringgit ( 1 IDR = 0.00029 Ringgit )\s
                     3. SGD ( 1 IDR = 0.000095 SGD )\s
                     4. Yuan ( 1 IDR = 0.00044 Yuan )
                     Ketik Angka\s""");
            Scanner curr = new Scanner(System.in);
            mata_uang = curr.nextInt();

            System.out.println("Masukan Jumlah Uang");
            Scanner jumlah = new Scanner(System.in);
            uang = jumlah.nextInt();

            if (mata_uang == 1) {
                kurs = 0.0081;
                double hasil_konversi = uang * kurs;
                mata_uang_str = hasil_konversi + " YEN";
            } else if (mata_uang == 2) {
                kurs = 0.00029;
                double hasil_konversi = uang * kurs;
                mata_uang_str = hasil_konversi + " Ringgit";
            } else if (mata_uang == 3) {
                kurs = 0.000095;
                double hasil_konversi = uang * kurs;
                mata_uang_str = hasil_konversi + " SGD";
            } else if (mata_uang == 4) {
                kurs = 0.00044;
                double hasil_konversi = uang * kurs;
                mata_uang_str = hasil_konversi + " YUAN";
            }
        } else {
            System.out.println("""
                    Other to IDR\s
                     Pilih Asal Mata Uang\s
                     1. YEN ( 1 YEN = IDR 123.23 )\s
                     2. Ringgit ( 1 Ringgit = IDR 3411.95 )\s
                     3. SGD ( 1 SGD = IDR 10511.70 )\s
                     4. Yuan ( 1 YUAN = IDR 2259.42 )
                     Ketik Angka\s""");
            Scanner curr = new Scanner(System.in);
            mata_uang = curr.nextInt();

            System.out.println("Masukan Jumlah Uang");
            Scanner jumlah = new Scanner(System.in);
            uang = jumlah.nextInt();

            double hasil_konversi = 0;
            if (mata_uang == 1) {
                kurs = 123.23;
                hasil_konversi = uang * kurs;
            } else if (mata_uang == 2) {
                kurs = 3411.95;
                hasil_konversi = uang * kurs;
            } else if (mata_uang == 3) {
                kurs = 10511.70;
                hasil_konversi = uang * kurs;
            } else if (mata_uang == 4) {
                kurs = 2259.42;
                hasil_konversi = uang * kurs;
            }
            mata_uang_str = "IDR " + hasil_konversi;
        }

        System.out.println("Hasil Konversi Adalah " + mata_uang_str);
    }
}
