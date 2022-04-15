public class ChangeText {
    public static void main(String[] args) {
        String value = "abc";
        changeValue(value);
        System.out.print(value);
    }

    public static void changeValue(String a) {
        a = "xyz";
    }
}
