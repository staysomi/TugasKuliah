import java.util.Scanner;

public class TebakAngka {

    public static void main(String[] args) {
        int random, tebak, jumlah;

        random = (int) (Math.random() * 100);
        System.out.println("Tebaklah Angka Antara 1-100");

        Scanner masukan = new Scanner(System.in);
        jumlah = 0;

        do {
            jumlah++;
            System.out.println("Masukan Tebakan Anda : ");
            tebak = masukan.nextInt();

            if (jumlah != 5) {
                if (tebak > random) {
                    System.out.println("Tebakan terlalu Besar");
                } else if (tebak < random) {
                    System.out.println("Tebakan Terlalu Kecil");
                } else {
                    System.out.println("Tebakan Benar setelah " + jumlah + " Kali Menebak");
                }
            } else {
                System.out.println("Anda Telah Salah Menebak Sebanyak 5 kali.\n Jawabannya adalah " + random);
            }

        } while (tebak != random && jumlah < 5);
    }
}
