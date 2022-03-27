import java.util.Scanner;

public class DeretBilangan {
    public static void main(String[] args) {
        String nama = "", lagi;
        int nim = 0, angka = 0;

        do {
            System.out.print("\nMasukan Nama Anda [1..25] : ");
            do {
                Scanner sc_nama = new Scanner(System.in);
                while (!sc_nama.hasNext("[A-Za-z]+")) {
                    System.out.print("Tidak Valid!! Masukan Nama Anda [1..25] : ");
                    sc_nama.nextLine();
                }
                nama = sc_nama.nextLine();
                if (!Function.validasiChar(nama, 1, 25)) {
                    System.out.print("Tidak Bisa Pesan Lebih Dari 25 karakter : ");
                }
            } while (!Function.validasiChar(nama, 1, 25));
            System.out.print("Masukan NIM Anda [harus 10 karakter]: ");
            do {
                Scanner sc_nim = new Scanner(System.in);
                while (!sc_nim.hasNext("[0-9]+")) {
                    System.out.print("Tidak Valid!! Masukan Angka : ");
                    sc_nim.nextLine();
                }
                nim = sc_nim.nextInt();
                if (!Function.validasiChar(String.valueOf(nim), 10, 10)) {
                    System.out.print("Harus 10 : ");
                }
            } while (!Function.validasiChar(String.valueOf(nim), 10, 10));
            System.out.println("\nRegistrasi Sukses.. \nSelamat datang " + nama + " [NIM : " + nim + "]..\nMari Belajar Macam-macam Deret Bilangan..");
            System.out.print("\nMasukan Bebas Angka Deretan [5..20] : ");
            do {
                Scanner sc_angka = new Scanner(System.in);
                while (!sc_angka.hasNext("[0-9]+")) {
                    System.out.print("Tidak Valid!! Masukan Angka : ");
                    sc_angka.nextLine();
                }
                angka = sc_angka.nextInt();
                if (angka < 5 || angka > 20) {
                    System.out.print("Minimal 5 Maksimal 20 : ");
                }
            } while (angka < 5 || angka > 20);

            System.out.print("\nDeret Bilangan : ");

            // BILANGAN GANJIL
            int ganjil = 1, jumlah_ganjil = 0;
            System.out.print("\n\n" + angka + " Deret Bilangan Ganjil : ");
            for (int i = 1; i <= angka; i++) {
                System.out.print(ganjil + ", ");
                jumlah_ganjil += ganjil;
                ganjil = ganjil + 2;
            }
            System.out.print("\nJumlah Deret Bilangan Ganjil : " + jumlah_ganjil);
            // BILANGAN GANJIL

            // BILANGAN GENAP
            int genap = 2, jumlah_genap = 0;
            System.out.print("\n\n" + angka + " Deret Bilangan Genap : ");
            for (int i = 1; i <= angka; i++) {
                System.out.print(genap + ", ");
                jumlah_genap += genap;
                genap = genap + 2;
            }
            System.out.print("\nJumlah Deret Bilangan Genap : " + jumlah_genap);
            // BILANGAN GENAP

            // HITUNG FIBONACI
            int past, current, fibonacci, jumlah_fibo = 0;
            past = 0;
            current = 1;
            fibonacci = 1;
            System.out.print("\n\n" + angka + " Deret Bilangan Fibonaci : ");
            for (int i = 1; i <= angka; i++) {
                System.out.print(current + ", ");
                fibonacci = past + current;
                past = current;
                jumlah_fibo += current;
                current = fibonacci;
            }
            System.out.print("\nJumlah Deret Bilangan Fibonaci : " + jumlah_fibo);
            // HITUNG FIBONACI

            System.out.print("\n\nLagi ?? Y/T = ");
            Scanner input_lagi = new Scanner(System.in);
            lagi = input_lagi.next();
        } while (lagi.equals("Y") || lagi.equals("y"));
    }

    // UNTUK MEMVALIDASI CHARACTER
    public static class Function {
        public static boolean validasiChar(String value, int minLength, int maxLength) {
            if (value == null || value.equals("")) {
                return false;
            }

            int length = value.length();
            return length >= minLength && length <= maxLength;
        }
    }
}
