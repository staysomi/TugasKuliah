package BussinesAppDev;

public class Overriding {
    public static void main(String[] args) {
        Hewan hewan = new Hewan();
        hewan.suara();

        Sapi sapi = new Sapi();
        sapi.suara();
    }
}

class Hewan {
    public void suara() {
        System.out.println("Suara sapi...");
    }
}

class Sapi extends Hewan {
    @Override
    public void suara() {
        System.out.println("Moo Moo!");
    }
}

