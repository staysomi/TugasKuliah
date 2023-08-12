package BussinesAppDev;

public class PersegiPanjang {
    static int panjang, lebar;

    public static void main(String[] args) {
        panjang = 1;
        lebar = 2;
        System.out.println("Luas  = " + luas(panjang, lebar));
        System.out.println("Keliling  = " + keliling(panjang, lebar));
    }

    private static int luas(int p, int l) {
        return p * l;
    }

    private static int keliling(int p, int l) {
        return 2 * (p + l);
    }
}
