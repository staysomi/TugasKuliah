import java.math.BigInteger;
import java.util.*;

public class SortingMahasiswa {
    public static void main(String[] args) {
        ArrayList<BigInteger> nim = new ArrayList<>();
        ArrayList<String> nama = new ArrayList<String>();
        ArrayList<Float> ipk = new ArrayList<Float>();

        int no = 1;

        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Jumlah Mahasiswa : ");
        int jumlah_mahasiswa = input.nextInt();
        String[][] absen = new String[jumlah_mahasiswa][3];

        do {
            // INPUT NIM
            System.out.print(no + ">\nMasukan NIM Mahasiswa : ");
            BigInteger v_nim;
            boolean exist;
            do {
                exist = false;
                Scanner input_nim = new Scanner(System.in);
                while (!input_nim.hasNext("[0-9]+")) {
                    System.out.print("Tidak Valid!! Masukan NIM Angka : ");
                    input_nim.nextLine();
                }
                v_nim = input_nim.nextBigInteger();
                if (String.valueOf(v_nim).length() != 10) {
                    System.out.print("Harus 10 : ");
                } else if (nim.contains(v_nim)) {
                    System.out.print("NIM Sudah ada : ");
                    exist = true;
                } else {
                    nim.add(v_nim);
                    absen[no - 1][0] = String.valueOf(v_nim);
                }
            } while (String.valueOf(v_nim).length() != 10 || exist);
            // INPUT NAMA
            System.out.print("Masukan Nama Mahasiswa : ");
            Scanner input_nama = new Scanner(System.in);
            while (input_nama.hasNextInt()) {
                System.out.print("Tidak Valid!! Masukan Nama : ");
                input_nama.nextLine();
            }
            String v_nama = input_nama.next();
            nama.add(v_nama);
            absen[no - 1][1] = v_nama;

            // INPUT IPK
            System.out.print("Masukan IPK Mahasiswa : ");
            float v_ipk;
            do {
                Scanner input_ipk = new Scanner(System.in);
                while (input_ipk.hasNext("[A-Za-z]+")) {
                    System.out.print("Tidak Valid!! Masukan IPK Angka : ");
                    input_ipk.nextLine();
                }
                v_ipk = input_ipk.nextFloat();
                if (v_ipk > 4.0) {
                    System.out.print("IPK Tidak Valid : ");
                } else {
                    ipk.add(v_ipk);
                    absen[no - 1][2] = String.valueOf(v_ipk);
                }
            } while (v_ipk > 4.0);
            no++;
        } while (no <= jumlah_mahasiswa);

        System.out.println("\nAbsen :");

        Arrays.sort(absen, new Comparator<String[]>(){
            @Override
            public int compare(String[] first, String[] second){
                // compare the first element
                int comparedTo = first[1].compareTo(second[1]);
                // if the first element is same (result is 0), compare the second element
                if (comparedTo == 0) return first[0].compareTo(second[0]);
                else return comparedTo;
            }
        });
        formatArray(absen);
    }

    public static void formatArray(String[][] data) {
        System.out.println(Arrays.deepToString(data)
                .replace("[[", "\n- ")
                .replace("],", "\n")
                .replace(" [", "- ")
                .replace("]]", "")
        );
    }
}

