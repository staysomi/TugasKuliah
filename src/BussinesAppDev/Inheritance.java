package BussinesAppDev;

class Kendaraan {
    String merek, warna;

    public Kendaraan(String merek, String warna) {
        this.merek = merek;
        this.warna = warna;
    }

    public void info() {
        System.out.println("Kendaraan " + merek + ", warna: " + warna);
    }
}

class Mobil extends Kendaraan {
    private final int jumlahPintu;

    public Mobil(String merek, String warna, int jumlahPintu) {
        super(merek, warna);
        this.jumlahPintu = jumlahPintu;
    }

    public void infoMobil() {
        System.out.println("Mobil " + super.merek + ", warna: " + super.warna + ", jumlah pintu: " + jumlahPintu);
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Mobil mobil = new Mobil("Toyota", "Merah", 4);
        mobil.info();
        mobil.infoMobil();
    }
}
