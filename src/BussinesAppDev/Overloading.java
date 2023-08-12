package BussinesAppDev;

public class Overloading {
    public static void main(String[] args) {
        Matematika matematika = new Matematika();
        int hasilInt = matematika.tambah(1, 2);
        double hasilDouble = matematika.tambah(5.5, 3.1);

        System.out.println("Hasil int: " + hasilInt);
        System.out.println("Hasil double: " + hasilDouble);
    }
}

class Matematika {
    public int tambah(int a, int b) {
        return a + b;
    }

    public double tambah(double a, double b) {
        return a + b;
    }
}