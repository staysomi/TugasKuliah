package BussinesAppDev;

import java.util.Scanner;

public class BilanganPrima {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Bilangan Prima");

        int n, i, pembagi = 0;
        boolean prima = true;

        System.out.print("Input Angka : ");
        n = input.nextInt();

        if (n == 0 || n == 1) {
            prima = false;
        } else {
            for (i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    pembagi = i;
                    prima = false;
                    break;
                }
            }
        }

        if (prima) System.out.println(n + " adalah angka prima");
        else System.out.println(n + " bukan angka prima karena bisa dibagi " + pembagi);
    }
}
