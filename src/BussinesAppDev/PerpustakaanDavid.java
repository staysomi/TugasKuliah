package BussinesAppDev;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PerpustakaanDavid {
    private static final int BATAS_WAKTU_PINJAM = 10;
    private static final int DENDA_PELAJARAN = 2000;
    private static final int DENDA_NOVEL = 5000;
    private static final int DENDA_SKRIPSI = 10000;

    private Map<String, String> buku;

    public PerpustakaanDavid() {
        buku = new HashMap<>();
    }

    public void tambahBuku(String judul, String jenis) {
        buku.put(judul, jenis);
    }

    public int hitungDenda(String judul, int hariTerlambat) {
        String jenis = buku.get(judul);
        int denda = 0;

        if (jenis != null) {
            if (hariTerlambat > BATAS_WAKTU_PINJAM) {
                switch (jenis) {
                    case "pelajaran":
                        denda = DENDA_PELAJARAN * (hariTerlambat - BATAS_WAKTU_PINJAM);
                        break;
                    case "novel":
                        denda = DENDA_NOVEL * (hariTerlambat - BATAS_WAKTU_PINJAM);
                        break;
                    case "skripsi":
                        denda = DENDA_SKRIPSI * (hariTerlambat - BATAS_WAKTU_PINJAM);
                        break;
                    default:
                        System.out.println("Jenis buku tidak valid!");
                }
            }
        } else {
            System.out.println("Buku tidak ditemukan!");
        }

        return denda;
    }

    public static void main(String[] args) {
        PerpustakaanDavid perpustakaan = new PerpustakaanDavid();

        perpustakaan.tambahBuku("Saints", "pelajaran");
        perpustakaan.tambahBuku("Cerita Lama", "novel");
        perpustakaan.tambahBuku("Abstract", "skripsi");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan judul buku: ");
        String judulBuku = scanner.nextLine();

        System.out.print("Masukkan jumlah hari pinjam: ");
        int hariTerlambat = scanner.nextInt();

        int denda = perpustakaan.hitungDenda(judulBuku, hariTerlambat);
        int hari = hariTerlambat - 10;
        if (denda > 0) {
            System.out.println("Hari Terlambat : " + hari);
            System.out.println("Denda yang harus dibayarkan: Rp" + denda);
        } else {
            System.out.println("Tidak ada denda.");
        }
    }
}
