import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
//        String[] kota = {"Serang", "Tangerang", "Palembang", "Lombok", "Halmahera", "Surabaya"};
//        Arrays.stream(kota).sorted((s1,s2) -> s2.compareTo(s1))
//                .forEach(System.out::println);
        ArrayList<String> coba = new ArrayList<>();
        coba.add("satu");
        coba.add("dua");
        System.out.println(coba.contains(new String("satu")));
        System.out.println(coba.indexOf("dua"));
        coba.clear();
        System.out.println(coba);
        System.out.println(coba.get(1));
    }
}
