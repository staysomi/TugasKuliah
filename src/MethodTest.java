import java.util.Scanner;

public class MethodTest {
    public static void main(String[] args) {
        String lagi;
        do {
            int x, resultPow, result = 0;
            String penjumlahan = "";
            System.out.print("Masukan Angka : ");
            Scanner input_angka = new Scanner(System.in);
            x = input_angka.nextInt();
            resultPow = (int) Math.pow(2, x);

            for (String angka : String.valueOf(resultPow).split("")) {
                result += Integer.parseInt(angka);
                penjumlahan += angka + "+";
            }
            penjumlahan = penjumlahan.substring(0, penjumlahan.length() - 1);
            System.out.print("2 ^" + x + " = " + resultPow + ".\nJawabannya = " + penjumlahan + " = " + result);
            System.out.print("\nMasukan Lagi ? ");
            Scanner input_lagi = new Scanner(System.in);
            lagi = input_lagi.next();
        } while (lagi.equals("y") || lagi.equals("Y"));
    }
}
