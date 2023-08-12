package BussinesAppDev;
import java.awt.*;

public class AWTExample {
    public static void main(String[] args) {
        Frame frame = new Frame("Contoh AWT");
        Label label = new Label("Halo, AWT!");
        frame.add(label);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
