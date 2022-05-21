import java.util.Arrays;
import java.util.Scanner;

public class TokoSepatu {
    public static void main(String[] args) {
        // variable boolean untuk membantu pengecekan jika user ingin kembali ke menu sebelumnya
        boolean back_type, back_shoes, back_size, back_color = false, back_metode = false, back_payment, back_bank;
        int pick_type, pick_shoes = 0, pick_size = 0, pick_color = 0, pick_payment_method = 0, pick_bank = 0;
        String name, address, phone, next_metode, next;
        String[] shoes_type = {"Running", "Futsal", "Football", "Sneakers"};
        String[] payment_methods = {"Bank Transfer", "Virtual Account", "Cash On Delivery"};

        System.out.println("\nSelamat Datang Di Toko Sepatu");
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Nama : ");
        name = input.next();

        // perulangan untuk jika user memasukan huruf di input nomor handphone maka akan diulangi
        System.out.print("Masukan No Handphone : ");
        Scanner input_phone = new Scanner(System.in);
        while (!input_phone.hasNext("[0-9]+")) {
            System.out.print("Tidak Valid!! Masukan Angka!!\nMasukan No Handphone : ");
            input_phone.nextLine();
        }
        phone = input_phone.next();
        System.out.print("Masukan Alamat : ");
        Scanner input_address = new Scanner(System.in);
        address = input_address.next();
        do {
            System.out.println("\nType :");
            // perulangan untuk menampilkan list type sepatu
            for (int counter = 0; counter < shoes_type.length; counter++) {
                System.out.println((counter + 1) + " " + Arrays.asList(shoes_type).get(counter));
            }
            System.out.print("Pilih Type Sepatu (Pilih Nomor) : ");
            Scanner input_type = new Scanner(System.in);
            pick_type = input_type.nextInt();
            // mengecek jika input pilihan lebih dari jumlah array atau inputnya 0 maka tidak ada pilihannya
            if (pick_type > shoes_type.length || pick_type == 0) {
                System.out.print("Tidak ada pilihan\n");
                back_type = true;
            } else {
                do {
                    back_type = false;
                    System.out.println("\nList Sepatu :");
                    // perulangan untuk menampilkan list sepatu dengan type yg dipilih
                    for (int counter = 0; counter < showListShoes(pick_type).length; counter++) {
                        System.out.println((counter + 1) + " " + Arrays.asList(showListShoes(pick_type)).get(counter));
                    }
                    System.out.print("Masukan 0 untuk kembali\nPilih Sepatu (Pilih Nomor) : ");
                    Scanner input_shoes = new Scanner(System.in);
                    pick_shoes = input_shoes.nextInt();
                    // mengecek jika input 0 maka akan kembali ke menu memilih type sepatu dan jika memilih lebih dari jumlah array maka tidak ada pilihan
                    if (pick_shoes == 0) {
                        back_shoes = false;
                        back_type = true;
                    } else if (pick_shoes > showListShoes(pick_type).length) {
                        System.out.print("Tidak ada pilihan\n");
                        back_shoes = true;
                    } else {
                        back_shoes = false;
                        do {
                            System.out.println("\nList Ukuran Sepatu Tersedia :");
                            // perulangan untuk menampilkan list ukuran sepatu dengan type dan sepatu yg dipilih
                            for (int counter = 0; counter < showListReadySize(pick_type, pick_shoes).length; counter++) {
                                System.out.println((counter + 1) + " " + Arrays.asList(showListReadySize(pick_type, pick_shoes)).get(counter));
                            }
                            System.out.print("Masukan 0 untuk kembali\nPilih Ukuran Sepatu (Pilih Nomor) : ");
                            Scanner input_size = new Scanner(System.in);
                            pick_size = input_size.nextInt();
                            // mengecek jika input 0 maka akan kembali ke menu memilih type sepatu dan jika memilih lebih dari jumlah array maka tidak ada pilihan
                            if (pick_size == 0) {
                                back_size = false;
                                back_shoes = true;
                            } else if (pick_size > showListReadySize(pick_type, pick_shoes).length) {
                                System.out.print("Tidak ada pilihan\n");
                                back_size = true;
                            } else {
                                back_size = false;
                                do {
                                    System.out.println("\nList Warna Sepatu Tersedia :");
                                    for (int counter = 0; counter < showListReadyColor(pick_type, pick_shoes, pick_size).length; counter++) {
                                        System.out.println((counter + 1) + " " + Arrays.asList(showListReadyColor(pick_type, pick_shoes, pick_size)).get(counter));
                                    }
                                    System.out.print("Masukan 0 untuk kembali\nPilih Warna Sepatu (Pilih Nomor) : ");
                                    Scanner input_color = new Scanner(System.in);
                                    pick_color = input_color.nextInt();
                                    if (pick_color == 0) {
                                        back_size = true;
                                    } else if (pick_color > showListReadyColor(pick_type, pick_shoes, pick_size).length) {
                                        System.out.print("Tidak ada pilihan\n");
                                        back_color = true;
                                    } else {
                                        back_color = false;
                                        do {
                                            System.out.print("\n- Ya\n- Tidak\n- Masukan 0 Untuk Kembali\nLanjut Ke Metode Pembayaran [Y/T] : ");
                                            Scanner input_next_metode = new Scanner(System.in);
                                            next_metode = input_next_metode.next();
                                            switch (next_metode) {
                                                case "0" -> {
                                                    back_metode = false;
                                                    back_color = true;
                                                }
                                                case "Y", "y" -> {
                                                    back_metode = false;
                                                    do {
                                                        System.out.println("\nDetail Sepatu : " + "\nType Sepatu : " + shoes_type[pick_type - 1] + "\nSepatu : " + showListShoes(pick_type)[pick_shoes - 1]
                                                                + "\nUkuran dan Warna Sepatu : " + showListReadySize(pick_type, pick_shoes)[pick_size - 1] + " ( " + showListReadyColor(pick_type, pick_shoes, pick_size)[pick_color - 1] + " )");
                                                        System.out.println("\nMetode Pembayaran : ");
                                                        for (int counter = 0; counter < payment_methods.length; counter++) {
                                                            System.out.println((counter + 1) + " " + Arrays.asList(payment_methods).get(counter));
                                                        }
                                                        System.out.print("Masukan 0 untuk kembali Pilih Sepatu\nPilih Metode Pembayaran : ");
                                                        Scanner input_payment = new Scanner(System.in);
                                                        pick_payment_method = input_payment.nextInt();
                                                        if (pick_payment_method == 0) {
                                                            back_payment = false;
                                                            back_type = true;
                                                        } else if (pick_payment_method == 3) {
                                                            System.out.println("Pembayaran Cash On Delivery (COD)");
                                                            back_payment = false;
                                                        } else if (pick_payment_method > payment_methods.length) {
                                                            System.out.print("Tidak ada pilihan ");
                                                            back_payment = true;
                                                        } else {
                                                            back_payment = false;
                                                            do {
                                                                System.out.println("List Bank Pembayaran : ");
                                                                if (pick_payment_method == 1) {
                                                                    for (int counter = 0; counter < showListBank(pick_payment_method).length; counter++) {
                                                                        System.out.println((counter + 1) + " " + Arrays.asList(showListBank(pick_payment_method)).get(counter));
                                                                    }
                                                                    System.out.print("Pilih Bank Pembayaran : ");
                                                                    Scanner input_bank = new Scanner(System.in);
                                                                    pick_bank = input_bank.nextInt();
                                                                    System.out.println("Rekening Pembayaran : " + showNomorBank(pick_payment_method, pick_bank));

                                                                } else if (pick_payment_method == 2) {
                                                                    for (int counter = 0; counter < showListBank(pick_payment_method).length; counter++) {
                                                                        System.out.println((counter + 1) + " " + Arrays.asList(showListBank(pick_payment_method)).get(counter));
                                                                    }
                                                                    System.out.print("Pilih Bank Pembayaran : ");
                                                                    Scanner input_bank = new Scanner(System.in);
                                                                    pick_bank = input_bank.nextInt();
                                                                    System.out.println("Rekening Pembayaran : " + showNomorBank(pick_payment_method, pick_bank) + phone);
                                                                }
                                                                // pilihan melanjutkan transaksi atau tidak, jika iya maka akan muncul invoice
                                                                System.out.print("Masukan 0 untuk kembali\nLanjutkan Transaksi ?? ( Y/T )");
                                                                Scanner input_next = new Scanner(System.in);
                                                                next = input_next.next();
                                                                switch (next) {
                                                                    case "0" -> {
                                                                        back_payment = true;
                                                                        back_bank = false;
                                                                    }
                                                                    case "Y", "y" -> {
                                                                        back_type = false;
                                                                        back_bank = false;
                                                                    }
                                                                    case "T", "t" -> {
                                                                        System.out.println("\nTransaksi Gagal\nPilih Sepatu Lagi!!!");
                                                                        back_type = true;
                                                                        back_bank = false;
                                                                    }
                                                                    default -> {
                                                                        System.out.println("Tidak Valid!! Masukan Pilihan");
                                                                        back_bank = true;
                                                                    }
                                                                }
                                                            } while (back_bank);
                                                        }
                                                    } while (back_payment);
                                                }
                                                case "T", "t" -> {
                                                    System.out.println("\nSilahkan Memilih Type Sepatu Kembali");
                                                    back_type = true;
                                                    back_metode = false;
                                                }
                                                default -> {
                                                    System.out.println("Tidak Valid!! Masukan Pilihan");
                                                    back_metode = true;
                                                }
                                            }
                                        } while (back_metode);
                                    }
                                } while (pick_color > showListReadyColor(pick_type, pick_shoes, pick_size).length || back_color);
                            }
                        } while (pick_size > showListReadySize(pick_type, pick_shoes).length || back_size);
                    }
                } while (pick_shoes > showListShoes(pick_type).length || back_shoes);
            }
        } while (back_type);

        System.out.println("\n\nINVOICE\nNama : " + name + "\nNo Handphone : " + phone + "\nAlamat : " + address);
        // -1 karena di semua variable pick memilih item berdasarkan nomor urut +1 , bukan langsung dari urutan index array
        System.out.println("Type Sepatu : " + shoes_type[pick_type - 1] + "\nSepatu : " + showListShoes(pick_type)[pick_shoes - 1]
                + "\nUkuran dan Warna Sepatu : " + showListReadySize(pick_type, pick_shoes)[pick_size - 1] + " ( " + showListReadyColor(pick_type, pick_shoes, pick_size)[pick_color - 1]
                + " )\nMetode Pembayaran : " + payment_methods[pick_payment_method - 1]);

        // membedakan virtual acc dengan no rekening, 1 = norek biasa, 2 = virtual acc
        if (pick_payment_method == 1)
            System.out.println("Nomor Pembayaran : " + showNomorBank(pick_payment_method, pick_bank));
        else if (pick_payment_method == 2)
            System.out.println("Nomor Pembayaran : " + showNomorBank(pick_payment_method, pick_bank) + phone);

        System.out.println("Terimakasih!!!");
    }

    public static String[] showListShoes(int type) {
        String[] shoes_list = null;

        if (type == 1) {
            shoes_list = new String[]{"Adidas Rp200.000", "Fila Rp500.000", "Nike Rp400.000", "New Balance Rp300.000"};
        } else if (type == 2) {
            shoes_list = new String[]{"Specs Rp500.000", "Diadora Rp200.000", "Lotto Rp400.000", "Ortuseight Rp400.000"};
        } else if (type == 3) {
            shoes_list = new String[]{"Puma Rp300.000", "Adidas Rp500.000", "Nike Rp300.000", "Specs Rp200.000"};
        } else if (type == 4) {
            shoes_list = new String[]{"Exodos Rp300.000", "Vans Rp200.000", "Converse Rp400.000", "Nike Rp500.000"};
        }

        return shoes_list;
    }

    public static String[] showListReadySize(int type, int shoes) {
        String[] shoes_size = null;

        if (type == 1 || type == 3) {
            if (shoes == 1) {
                shoes_size = new String[]{"39-40", "41-42", "43-44", "45-46"};
            } else if (shoes == 2) {
                shoes_size = new String[]{"35-36", "40-41", "42-43", "44-45"};
            } else if (shoes == 3) {
                shoes_size = new String[]{"37-38", "39-40", "41-42", "43-44"};
            } else if (shoes == 4) {
                shoes_size = new String[]{"38-39", "41-42", "44-45", "46-47"};
            }
        } else {
            if (shoes == 1) {
                shoes_size = new String[]{"35-36", "40-41", "42-43", "44-45"};
            } else if (shoes == 2) {
                shoes_size = new String[]{"37-38", "39-40", "41-42", "43-44"};
            } else if (shoes == 3) {
                shoes_size = new String[]{"38-39", "41-42", "44-45", "46-47"};
            } else if (shoes == 4) {
                shoes_size = new String[]{"39-40", "41-42", "43-44", "45-46"};
            }
        }

        return shoes_size;
    }

    public static String[] showListReadyColor(int type, int shoes, int size) {
        String[] shoes_color = null;

        if (type == 1 || type == 3) {
            if (shoes == 1) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 4) {
                    shoes_color = new String[]{"Black", "Gray"};
                }
            } else if (shoes == 2) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 2) {
                    shoes_color = new String[]{"Black", "Gray"};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                }
            } else if (shoes == 3) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 3) {
                    shoes_color = new String[]{"Black", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                }
            } else if (shoes == 4) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 3) {
                    shoes_color = new String[]{"Black", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                }
            }
        } else {
            if (shoes == 1) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Black", "Gray"};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 4) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                }
            } else if (shoes == 2) {
                if (size == 1) {
                    shoes_color = new String[]{"Black", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                }
            } else if (shoes == 3) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Black", "Gray"};
                }
            } else if (shoes == 4) {
                if (size == 1) {
                    shoes_color = new String[]{"Red", "Blue", "Black",};
                } else if (size == 2) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "White", "Gray"};
                } else if (size == 3) {
                    shoes_color = new String[]{"Red", "Blue", "Black", "Gray"};
                } else if (size == 4) {
                    shoes_color = new String[]{"Black", "Gray"};
                }
            }
        }


        return shoes_color;
    }

    public static String[] showListBank(int payment_method) {
        String[] bank = null;

        if (payment_method == 1) {
            bank = new String[]{"BCA", "BNI", "BRI", "Mandiri"};
        } else if (payment_method == 2) {
            bank = new String[]{"BCA", "BNI"};
        }

        return bank;
    }

    public static String showNomorBank(int payment_method, int bank) {
        String nomor = null;

        if (payment_method == 1) {
            if (bank == 1) {
                nomor = "BCA 8983902889";
            } else if (bank == 2) {
                nomor = "BNI 923290398398";
            } else if (bank == 3) {
                nomor = "BRI 83718372103981";
            } else if (bank == 4) {
                nomor = "Mandiri 12638621493824";
            }
        } else if (payment_method == 2) {
            if (bank == 1) {
                nomor = "Virtual Account BCA 87290";
            } else if (bank == 2) {
                nomor = "Virtual Account BNI 12123";
            }
        }

        return nomor;
    }
}
