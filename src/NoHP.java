import java.util.Scanner;

public class NoHP {
    public static void main(String[] args){
        String penjumlahan = "";
        int jumlah = 0;
        System.out.print("Masukan No HP : ");
        Scanner input_nomor = new Scanner(System.in);
        while (input_nomor.hasNext("[A-Za-z]+")) {
            System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan No HP : ");
            input_nomor.nextLine();
        }
        String nomor = input_nomor.next();
        for (String nomorHP : nomor.split("") ){
            jumlah+=Integer.parseInt(nomorHP);
            penjumlahan+=nomorHP+"+";
        }
        penjumlahan= penjumlahan.substring(0,penjumlahan.length()-1);
        System.out.print("Hasil Dari " + penjumlahan + " = " + jumlah);
    }
}
