import java.text.DecimalFormat;
import java.util.Scanner;

public class RestoranBungar {
    public static void main(String[] args) {
        int jml_orang, menu1 = 0, menu2 = 0, menu3 = 0, menu4 = 0, menu5 = 0;
        double total1, total2, total3, total4, total5, total, discount, afterdisc, per_org;
        String pemesan;

        System.out.print("Masukan Jumlah Orang : ");
        Scanner sc_jumlah = new Scanner(System.in);

        //VALIDASI SUPAYA TIDAK BISA MEMASUKAN HURUF DI JUMLAH ORANG
        while (sc_jumlah.hasNext("[A-Za-z]+")) {
            System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan Jumlah Orang : ");
            sc_jumlah.nextLine();
        }
        jml_orang = sc_jumlah.nextInt();

        System.out.print("Pemesan : ");
        Scanner sc_pemesan = new Scanner(System.in);
        pemesan = sc_pemesan.nextLine();

        System.out.println("Menu :");
        System.out.println("""
                1.\tNasi Goreng Spesial dengan harga Rp. 9999,99
                2.\tAyam Bakar Spesial dengan harga Rp. 12345,67
                3.\tSteak Sirloin Spesial dengan harga Rp. 21108,40
                4.\tKwetiaw Siram Spesial dengan harga Rp. 13579,13
                5.\tKambing Guling Spesial dengan harga Rp. 98765,43
                """);


        System.out.println("Pesanan Anda (batas pesanan 0-10) : ");
        //VALIDASI SUPAYA TIDAK BISA PESAN LEBIH DARI 10
        do {
            Scanner inp_menu1 = new Scanner(System.in);
            if (menu1 > 10) {
                System.out.println("Tidak Bisa Pesan Lebih Dari 10");
            }
            System.out.print("1.\tNasi Goreng Spesial : ");
            //VALIDASI SUPAYA TIDAK BISA MEMASUKAN HURUF DI JUMLAH PESANAN
            while (inp_menu1.hasNext("[A-Za-z]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!! \n1.\tNasi Goreng Spesial : ");
                inp_menu1.nextLine();
            }
            menu1 = inp_menu1.nextInt();
        } while (menu1 > 10);
        do {
            Scanner inp_menu2 = new Scanner(System.in);
            if (menu2 > 10) {
                System.out.println("Tidak Bisa Pesan Lebih Dari 10");
            }
            System.out.print("2.\tAyam Bakar Spesial : ");
            while (inp_menu2.hasNext("[A-Za-z]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!! \n2.\tAyam Bakar Spesial : ");
                inp_menu2.nextLine();
            }
            menu2 = inp_menu2.nextInt();
        } while (menu2 > 10);
        do {
            Scanner inp_menu3 = new Scanner(System.in);
            if (menu3 > 10) {
                System.out.println("Tidak Bisa Pesan Lebih Dari 10");
            }
            System.out.print("3.\tSteak Sirloin Spesial : ");
            while (inp_menu3.hasNext("[A-Za-z]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!! \n3.\tSteak Sirloin Spesial : ");
                inp_menu3.nextLine();
            }
            menu3 = inp_menu3.nextInt();
        } while (menu3 > 10);
        do {
            Scanner inp_menu4 = new Scanner(System.in);
            if (menu4 > 10) {
                System.out.println("Tidak Bisa Pesan Lebih Dari 10");
            }
            System.out.print("4.\tKwetiaw Siram Spesial : ");
            while (inp_menu4.hasNext("[A-Za-z]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!! \n4.\tKwetiaw Siram Spesial : ");
                inp_menu4.nextLine();
            }
            menu4 = inp_menu4.nextInt();
        } while (menu4 > 10);
        do {
            Scanner inp_menu5 = new Scanner(System.in);
            if (menu5 > 10){
                System.out.println("Tidak Bisa Pesan Lebih Dari 10");
            }
            System.out.print("5.\tKambing Guling Spesial : ");
            while (inp_menu5.hasNext("[A-Za-z]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!! \n5.\tKambing Guling Spesial : ");
                inp_menu5.nextLine();
            }
            menu5 = inp_menu5.nextInt();
        } while (menu5 > 10);

        // HITUNG HARGA
        total1 = menu1 * 9999.99;
        total2 = menu2 * 12345.67;
        total3 = menu3 * 21109.40;
        total4 = menu4 * 13579.13;
        total5 = menu5 * 98765.43;
        total = total1 + total2 + total3 + total4 + total5;
        discount = total / 10;
        afterdisc = total - discount;
        per_org = afterdisc / jml_orang;

        // NOTA
        System.out.println("Selamat Menikmati Makanan Anda!!!\n\nPembelian Atas Nama " + pemesan + " : \n" +
                "1.\tNasi Goreng Spesial      " + menu1 + "porsi X Rp. 9999,99  = Rp." + decimal(total1) + "\n" +
                "2.\tAyam Bakar Spesial       " + menu2 + "porsi X Rp. 12345,67 = Rp." + decimal(total2) + "\n" +
                "3.\tSteak Sirloin Spesial    " + menu3 + "porsi X Rp. 21108,40 = Rp." + decimal(total3) + "\n" +
                "4.\tKwetiaw Siram Spesial    " + menu4 + "porsi X Rp. 13579,13 = Rp." + decimal(total4) + "\n" +
                "5.\tKambing Guling Spesial   " + menu5 + "porsi X Rp. 98765,43 = Rp." + decimal(total5) + "\n" +
                "================================================================+\n" +
                "Total Pembelian \t\t\t\t\t\t\t\t   = Rp. " + decimal(total) + "\n" +
                "Discount 10 % \t\t\t\t\t\t\t\t\t   = Rp. " + decimal(discount) + "\n" +
                "================================================================-\n" +
                "Total Pembelian \t\t\t\t\t\t\t\t   = Rp. " + decimal(afterdisc) + "\n" +
                "Pembelian Per Orang (untuk " + jml_orang + " orang) \t\t\t   = Rp. " + decimal(per_org) +
                "\nTerimakasih"
        );
    }

    static String decimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);
    }
}
