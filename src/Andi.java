import java.util.Scanner;

public class Andi {
    public static void main(String[] args) {
        String lagi = "";
        do {
            int total_deret, beda;
            System.out.print("\nBelajar Aritmatika\nMasukan Angka yang mau  di cetak [2-10] : ");
            do {
                Scanner sc_angka1 = new Scanner(System.in);
                while (!sc_angka1.hasNext("[0-9]+")) {
                    System.out.print("Tidak Valid!! Masukan Angka [2-10] : ");
                    sc_angka1.next();
                }
                total_deret = sc_angka1.nextInt();
                if (total_deret < 2 || total_deret > 10) {
                    System.out.print("Harus [2-10] : ");
                }
            } while (total_deret < 2 || total_deret > 10);
            System.out.print("Masukan Angka beda masing masing [2-9] : ");
            do {
                Scanner sc_angka2 = new Scanner(System.in);
                while (!sc_angka2.hasNext("[0-9]+")) {
                    System.out.print("Tidak Valid!! Masukan Angka [2-9] : ");
                    sc_angka2.next();
                }
                beda = sc_angka2.nextInt();
                if (beda < 2 || beda > 9) {
                    System.out.print("Harus [2-9] : ");
                }
            } while (beda < 2 || beda > 9);

            // DERET ARITMATIKA
            System.out.print("\nDeret Aritmatika\n");
            int una = 1;
            for (int i = 1; i <= total_deret; i++) {
                System.out.print(una + " ");
                una = una + beda;
            }

            // DERET GEOMETRI
            System.out.print("\nDeret Geometri\n");
            int ung = 1;
            for (int i = 1; i <= total_deret; i++) {
                System.out.print(ung + " ");
                ung = ung * beda;
            }

            // FAKTORIAL
            System.out.print("\nFaktorial \n");
            int unf = total_deret;
            for (int i = 0; i < total_deret; i++) {
                if (unf == 1) {
                    System.out.print(unf + " = ");
                } else {
                    System.out.print(unf + " * ");
                }
                unf = unf - 1;
            }
            System.out.println(Faktorial(total_deret));
            System.out.print("\nTerimakasih!!!! \nLagi?? ");
            Scanner lagii = new Scanner(System.in);
            lagi = lagii.next();
        } while (lagi.equals("Y") || lagi.equals("y"));
    }

    static int Faktorial(int angka) {
        if (angka == 1 || angka == 0)
            return 1;
        else
            return angka * Faktorial(angka - 1);
    }
}
