import java.text.DecimalFormat;
import java.util.Scanner;

public class GudangOke {
    public static void main(String[] args) {
        String nama, nama_barang;
        int jumlah_barang;
        double harga_beli, harga_jual;

        //RUMUS DESIMAL
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        System.out.println("Masukan Nama Anda ");
        Scanner input_nama = new Scanner(System.in);
        nama = input_nama.nextLine();

        System.out.println("Stock Gudang Input Barang \nSelamat Datang " + nama + "\n========================");

        System.out.println("Masukan Nama Barang ");
        Scanner input_nama_barang = new Scanner(System.in);
        nama_barang = input_nama_barang.nextLine();

        System.out.println("Masukan Jumlah Barang ");
        Scanner input_jumlah_barang = new Scanner(System.in);
        jumlah_barang = input_jumlah_barang.nextInt();

        System.out.println("Masukan Harga Beli Barang ");
        Scanner input_harga_beli_barang = new Scanner(System.in);
        harga_beli = input_harga_beli_barang.nextDouble();

        System.out.println("Masukan Harga Jual Barang ");
        Scanner input_harga_jual_barang = new Scanner(System.in);
        harga_jual = input_harga_jual_barang.nextDouble();

        System.out.println("Stock Gudang Rincian Barang " + "\n============================" +
                "\nNama Barang : " + nama_barang +
                "\nJumlah Barang : " + jumlah_barang +
                "\nHarga Beli : " + decimalFormat.format(harga_beli) +
                "\nHarga Jual : " + decimalFormat.format(harga_jual));
    }
}
