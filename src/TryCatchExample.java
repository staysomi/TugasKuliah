import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        String lagi;
        do {
            Scanner input = new Scanner(System.in);
            try {
                System.out.print("Masukan X = ");
                int x = input.nextInt();
                System.out.print("Masukan Y = ");
                int y = input.nextInt();
                int hasil = x / y;
                System.out.print("Hasilnya = " + hasil);
            } catch (Exception e) {
                System.out.print("Hasilnya = " + e);
            }
            System.out.print("\nLagi = ");
            Scanner next = new Scanner(System.in);
            lagi = next.next();
        } while (lagi.equals("Y") || lagi.equals("y"));
    }
}