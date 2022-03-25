import java.util.Scanner;

public class Ajaib {
    public static void main(String[] args) {
        int angka = 0;
        String lagi;
        do {
            do {
                Scanner scanner = new Scanner(System.in);
                if (angka > 100) {
                    System.out.println("Tidak Bisa Lebih Dari 100");
                }
                System.out.print("1 - 100 : ");
                while (scanner.hasNext("[A-Za-z]+")) {
                    System.out.print("Tidak Valid!! Masukan Angka!! \n1 - 100 : ");
                    scanner.nextLine();
                }
                angka = scanner.nextInt();
            } while (angka > 100);

            int modulus = angka % 2;

            if (modulus == 1) {
                System.out.println("AJAIB");
            } else if (modulus == 0) {
                if (angka >= 2 && angka <= 5) {
                    System.out.println("TIDAK AJAIB");
                } else if (angka >= 6 && angka <= 20) {
                    System.out.println("AJAIB");
                } else {
                    System.out.println("TIDAK AJAIB");
                }
            }
            System.out.print("\nLagi ?? Y/T = ");
            Scanner input_lagi = new Scanner(System.in);
            lagi = input_lagi.next();
        }while (lagi.equals("Y") || lagi.equals("y"));
    }
}
