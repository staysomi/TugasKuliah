import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CoepoeWord {
    public static void main(String[] args) {
        String lagi;
        do {
            int score = 0, level = 1;

            // untuk check apakah permainan selesai
            boolean retry = false;

            rules();

            ArrayList<String> existingListWord = new ArrayList<>();
            do {
                // score per level
                int score1 = 0, score2 = 0, score3 = 0;

                System.out.println("\nLevel " + level);
                System.out.println("Letter : " + Function.showLetter(level));

                ArrayList<String> correctWordList = new ArrayList<>(
                        Arrays.asList(Function.showListCorrectWord(level))
                );

                int nomor = 1;
                do {
                    String word;
                    System.out.print(nomor + "> Your Answer : ");
                    Scanner input_word = new Scanner(System.in);
                    word = input_word.next();

                    if (word.length() < 3 || word.length() > 6) {
                        System.out.print("You Must Type 3-6 Word\n");
                    } else {
                        if (existingListWord.contains(word)) {
                            System.out.print("Already Type this word before\n");
                        } else {
                            if (correctWordList.contains(word)) {
                                // score each level condition
                                if (level == 1) {
                                    score1 = score1 + 10;
                                    System.out.print("#Right. Score : " + score1 + "\n");
                                } else if (level == 2) {
                                    score2 = score2 + 10;
                                    System.out.print("#Right. Score : " + score2 + "\n");
                                } else if (level == 3) {
                                    score3 = score3 + 10;
                                    System.out.print("#Right. Score : " + score3 + "\n");
                                }
                                existingListWord.add(word);
                            } else {
                                System.out.print("No Word Matched\n");
                            }
                        }
                    }
                    nomor = nomor + 1;
                } while (nomor <= 10);

                System.out.println("\nList Correct Word : \n" +
                        Arrays.toString(Function.showListCorrectWord(level))
                                .replace("[", "")
                                .replace("]", "") + "\n");

                // score and level condition
                if (score1 >= 70) {
                    int pass = score1 / 10;
                    System.out.println("You had answered 10 times with " + pass + " right answers..");
                    level = level + 1;
                    score = score1;
                    System.out.print("Current Score : " + score + "\n");
                } else if (score2 >= 70) {
                    int pass = score2 / 10;
                    System.out.println("You had answered 10 times with " + pass + " right answers..");
                    level = level + 1;
                    score = score + score2;
                    System.out.print("Current Score : " + score + "\n");
                } else if (score3 >= 70) {
                    int pass = score3 / 10;
                    System.out.println("You had answered 10 times with " + pass + " right answers..");
                    score = score + score3;
                    System.out.println("Overall Score : " + score + "\nYou Win!!");
                    retry = true;
                } else {
                    System.out.println("You Lose");
                    retry = true;
                }
            } while (!retry);

            System.out.print("Do you wanna retry [y/t] ?");
            Scanner lagii = new Scanner(System.in);
            lagi = lagii.next();
        } while (lagi.equals("Y") || lagi.equals("y"));
    }

    static void rules() {
        System.out.println("\nCoepoe Word Puzzle");
        System.out.println("Rules :");
        System.out.println("1.\tSusun sebuah kata dalam bahasa Inggris dengan menggunakan huruf - huruf yang diberikan, setiap kata yang disusun harus memiliki banyak karakter minimal 3 karakter dan maksimal 6 karakter.");
        System.out.println("2.\tSetiap levelnya, user diberi 10 kesempatan untuk menyusun kata dengan benar.");
        System.out.println("3.\tSetiap jawaban yang benar akan diberikan skor 10.");
        System.out.println("4.\tUntuk dapat melanjutkan ke level berikutnya, user harus mencapai skor minimal 70 setiap levelnya.");
        System.out.println("5.\tUser tidak dapat menginput kata yang sama.\n");
    }

    public static class Function {
        public static String showLetter(int value) {
            String letter = "";

            if (value == 1) {
                letter = "\td\te\tt\tt\tl\ti";
            } else if (value == 2) {
                letter = "\ts\te\tc\ta\te\tn";
            } else if (value == 3) {
                letter = "\th\tk\tr\tn\te\to";
            }

            return letter;
        }

        public static String[] showListCorrectWord(int value) {
            String[] wordlist = null;

            if (value == 1) {
                wordlist = new String[]{"die", "led", "lei", "let", "lid", "lie", "lit", "tie",
                        "deli", "diet", "edit", "idle", "lied", "tide", "tied", "tile", "tilt",
                        "tilde", "tiled", "title", "tilted", "titled"
                };
            } else if (value == 2) {
                wordlist = new String[]{"sec", "can", "cane", "scan", "scene", "case", "cease",
                        "encase", "seance", "seneca", "acnes", "canes", "cense", "scena", "aces", "acne",
                        "cans", "cees", "anes", "anes", "ease", "ace"
                };
            } else if (value == 3) {
                wordlist = new String[]{"honk", "honker", "roe", "ore", "her", "hen", "one", "horn",
                        "krone", "heron", "honer", "hoke", "hork", "okeh", "keno", "kern", "kore",
                        "hern", "hero", "hoer", "ken", "hoe"
                };
            }

            return wordlist;
        }
    }
}
