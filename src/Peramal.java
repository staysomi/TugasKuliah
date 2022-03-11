import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Peramal {
    public static void main(String[] args) {
        int age, age2;
        String name, name2;
        double kecocokan;

        System.out.println("Selamat Datang Di Ramalan Paten\n=========================");
        System.out.println("Data Anda\n<3<3<3<3<3<3");

        System.out.println("Nama Anda : ");
        Scanner input_name = new Scanner(System.in);
        name = input_name.nextLine();

        System.out.println("Umur Anda : ");
        Scanner input_age = new Scanner(System.in);
        age = input_age.nextInt();

        System.out.println("Data Pasangan Anda\n<3<3<3<3<3<3");

        System.out.println("Nama Pasangan Anda : ");
        Scanner input_name2 = new Scanner(System.in);
        name2 = input_name2.nextLine();

        System.out.println("Umur Pasangan Anda : ");
        Scanner input_age2 = new Scanner(System.in);
        age2 = input_age2.nextInt();

        System.out.println(name + "[" + age + "] tahun" + "   <3   " + name2 + "[" + age2 + "] tahun");
        System.out.println("Tekan Enter Untuk Hasil Ramalan...");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();

        Random random = new Random();
        int x = 50 + random.nextInt(50);
        kecocokan = x / 1.1;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        System.out.println("Kecocokan anda dengan pasangan adalah : " + decimalFormat.format(kecocokan) +"%");

    }
}
