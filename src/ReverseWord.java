import java.util.Scanner;

public class ReverseWord {
    public static void main(String[] args) {
        String kalimat, lagi;
        do {
            System.out.print("\nMasukan Kalimat : ");
            Scanner scanner = new Scanner(System.in);
            kalimat = scanner.nextLine();

            if (reverseWord(kalimat)) {
                System.out.println("COOL");
            } else {
                System.out.println("BAD");
            }
            System.out.println("\nLagi? Y/T ");
            Scanner next = new Scanner(System.in);
            lagi = next.next();
        } while (lagi.equals("Y") || lagi.equals("y"));
    }


    public static boolean reverseWord(String string) {
        boolean isSame = false;
        char[] ch = string.toCharArray();
        String rev = "";
        for (int i = ch.length - 1; i >= 0; i--) {
            rev += ch[i];
        }

        if (rev.trim().equals(string.trim())) {
            isSame = true;
        }
        return isSame;
    }
}
