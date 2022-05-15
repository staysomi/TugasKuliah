import java.util.Arrays;
import java.util.Scanner;

public class RandomSort {
    public static void main(String[] args) {
        int batas_bawah, batas_atas, pilihan;
        int[] random_data = new int[5];

        pilihan();

        System.out.print("Masukan Pilihan Anda : ");
        do {
            Scanner input_pilihan = new Scanner(System.in);
            while (!input_pilihan.hasNext("[0-9]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan Pilihan : ");
                input_pilihan.nextLine();
            }
            pilihan = input_pilihan.nextInt();
            if (pilihan > 6 || pilihan < 1) {
                System.out.print("Tidak Ada Pilihan : ");
            }
        } while (pilihan > 6 || pilihan < 1);

        if (pilihan == 6) System.exit(0);

        System.out.print("Masukan Batas Bawah : ");
        Scanner input = new Scanner(System.in);
        while (!input.hasNext("[0-9]+")) {
            System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan Batas Bawah : ");
            input.nextLine();
        }
        batas_bawah = input.nextInt();
        System.out.print("Masukan Batas Atas [Harus " + (batas_bawah + 4) + " atau Lebih] : ");
        do {
            Scanner input_batas_atas = new Scanner(System.in);
            while (!input_batas_atas.hasNext("[0-9]+")) {
                System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan Batas Atas : ");
                input_batas_atas.nextLine();
            }
            batas_atas = input_batas_atas.nextInt();
            if (batas_atas < batas_bawah + 4) {
                System.out.print("Harus " + (batas_bawah + 4) + " Atau Lebih : ");
            }
        } while (batas_atas < batas_bawah + 4);

        // RANDOM SAMPAI TIDAK ADA ANGKA YG SAMA
        for (int i = 0; i < 5; i++) {
            int random = (int) Math.floor(Math.random() * (batas_atas - batas_bawah + 1) + batas_bawah);
            while (Arrays.toString(random_data).contains(String.valueOf(random))) {
                random = (int) Math.floor(Math.random() * (batas_atas - batas_bawah + 1) + batas_bawah);
            }
            random_data[i] = random;
        }

        if (pilihan == 1) System.out.println("\nRandom Number :");
        formatArray(random_data);
        if (pilihan == 2) {
            System.out.println("Bubble Asc : ");
            // jika true ascending jika false descending
            bubbleSort(random_data, true);
            System.out.println("\nSorted Bubble Asc : ");
            for (int random_datum : random_data) {
                System.out.print(random_datum + " ");
            }
        } else if (pilihan == 3) {
            System.out.println("Selection Asc : ");
            // jika true ascending jika false descending
            selectionSort(random_data, true);
            System.out.println("\nSorted Selection Asc : ");
            for (int random_datum : random_data) {
                System.out.print(random_datum + " ");
            }
        } else if (pilihan == 4) {
            System.out.println("Bubble Desc : ");
            // jika true ascending jika false descending
            bubbleSort(random_data, false);
            System.out.println("\nSorted Bubble Desc : ");
            for (int random_datum : random_data) {
                System.out.print(random_datum + " ");
            }
        } else if (pilihan == 5) {
            System.out.println("Selection Desc : ");
            // jika true ascending jika false descending
            selectionSort(random_data, false);
            System.out.println("\nSorted Selection Desc : ");
            for (int random_datum : random_data) {
                System.out.print(random_datum + " ");
            }
        }
    }

    // MENU
    public static void pilihan() {
        System.out.println("Selamat Datang di Program Simulasi");
        System.out.println("Menu");
        System.out.println("1. Random Data");
        System.out.println("2. Simulasi Bubble Sort    - Ascending");
        System.out.println("3. Simulasi Selection Sort - Ascending");
        System.out.println("4. Simulasi Bubble Sort    - Descending");
        System.out.println("5. Simulasi Selection Sort - Descending");
        System.out.println("6. Keluar");
    }

    // FUNCTION BUBBLE SORT
    public static void bubbleSort(int[] data, boolean ascending) {
        boolean next = true;
        int[] dk1 = new int[0], dk2 = new int[0], dk3 = new int[0], dk4 = new int[0];
        for (int k = 1; k < data.length && next; k++) {
            next = false;
            if (k == 1) {
                System.out.println("Pass 1");
                formatArray(data);
            } else if (k == 2) {
                System.out.println("\nPass 2");
                formatArray(dk1);
            } else if (k == 3) {
                System.out.println("\nPass 3");
                formatArray(dk2);
            } else if (k == 4) {
                System.out.println("\nPass 4");
                formatArray(dk3);
            }
            for (int i = 0; i < data.length - k; i++) {
                if (ascending) {
                    if (data[i] > data[i + 1]) {
                        int temp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = temp;

                        next = true;
                    }
                } else {
                    if (data[i] < data[i + 1]) {
                        int temp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = temp;

                        next = true;
                    }
                }
                if (k == 1 && i == 3) dk1 = data;
                else if (k == 2 && i == 2) dk2 = data;
                else if (k == 3 && i == 1) dk3 = data;
                else if (k == 4 && i == 0) dk4 = data;

                formatArray(data);
            }
            if (k == 1) {
                System.out.println("Result Pass 1");
                formatArray(dk1);
            } else if (k == 2) {
                System.out.println("Result Pass 2");
                formatArray(dk2);
            } else if (k == 3) {
                System.out.println("Result Pass 3");
                formatArray(dk3);
            } else if (k == 4) {
                System.out.println("Result Pass 4");
                formatArray(dk4);
            }
        }
    }

    // FUNCTION SELECTION SORT
    public static void selectionSort(int[] num_data, boolean ascending) {
        int n = num_data.length;
        int[] di1 = new int[0], di2 = new int[0], di3 = new int[0], di4 = new int[0];

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (num_data[j] < num_data[min_idx]) min_idx = j;
                } else if (num_data[j] > num_data[min_idx]) min_idx = j;
                if (j == 4) {
                    if (i == 0) di1 = num_data;
                    else if (i == 1) di2 = num_data;
                    else if (i == 2) di3 = num_data;
                    else if (i == 3) di4 = num_data;
                }
            }
            int temp = num_data[min_idx];
            num_data[min_idx] = num_data[i];
            num_data[i] = temp;
            if (i == 0) {
                System.out.println("Result 1");
                formatArray(di1);
            } else if (i == 1) {
                System.out.println("Result 2");
                formatArray(di2);
            } else if (i == 2) {
                System.out.println("Result 3");
                formatArray(di3);
            } else if (i == 3) {
                System.out.println("Result 4");
                formatArray(di4);
            }
        }
    }

    // FORMAT ARRAY
    public static void formatArray(int[] data) {
        System.out.println(Arrays.toString(data)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "\t\t\t")
        );
    }
}
