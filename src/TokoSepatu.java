import java.util.Arrays;
import java.util.Scanner;

public class TokoSepatu {
    public static void main(String[] args) {
        String name, address, phone, next;
        int pick_type, pick_shoes = 0, pick_size = 0, pick_color = 0, pick_payment_method, pick_bank = 0;
        // variable boolean untuk membantu pengecekan jika user ingin kembali ke menu sebelumnya
        boolean back_type = false, back_payment = false, back_shoes = false, back_size = false;
        String[] shoes_type = {"Running", "Futsal", "Football", "Sneakers"};
        String[] payment_methods = {"Bank Transfer", "Virtual Account", "Cash On Delivery"};

        do {
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
            address = input.next();
            do {
                System.out.println("\nType :");
                // perulangan untuk menampilkan list type sepatu
                for (int counter = 0; counter < shoes_type.length; counter++) {
                    System.out.println((counter + 1) + " " + Arrays.asList(shoes_type).get(counter));
                }
                System.out.print("Pilih Type Sepatu (Pilih Nomor) : ");
                pick_type = input.nextInt();
                // mengecek jika input pilihan lebih dari jumlah array atau inputnya 0 maka tidak ada pilihannya
                if (pick_type > shoes_type.length || pick_type == 0) {
                    System.out.print("Tidak ada pilihan\n");
                    back_type = true;
                } else {
                    do {
                        System.out.println("\nList Sepatu :");
                        // perulangan untuk menampilkan list sepatu dengan type yg dipilih
                        for (int counter = 0; counter < showListShoes(pick_type).length; counter++) {
                            System.out.println((counter + 1) + " " + Arrays.asList(showListShoes(pick_type)).get(counter));
                        }
                        System.out.print("Masukan 0 untuk kembali\nPilih Sepatu (Pilih Nomor) : ");
                        pick_shoes = input.nextInt();
                        // mengecek jika input 0 maka akan kembali ke menu memilih type sepatu dan jika memilih lebih dari jumlah array maka tidak ada pilihan
                        if (pick_shoes == 0) {
                            back_shoes = false;
                            back_type = true;
                        } else if (pick_shoes > showListShoes(pick_type).length) {
                            System.out.print("Tidak ada pilihan\n");
                        } else {
                            do {
                                System.out.println("\nList Ukuran Sepatu Tersedia :");
                                // perulangan untuk menampilkan list ukuran sepatu dengan type dan sepatu yg dipilih
                                for (int counter = 0; counter < showListReadySize(pick_type, pick_shoes).length; counter++) {
                                    System.out.println((counter + 1) + " " + Arrays.asList(showListReadySize(pick_type, pick_shoes)).get(counter));
                                }
                                System.out.print("Masukan 0 untuk kembali\nPilih Ukuran Sepatu (Pilih Nomor) : ");
                                pick_size = input.nextInt();
                                // mengecek jika input 0 maka akan kembali ke menu memilih type sepatu dan jika memilih lebih dari jumlah array maka tidak ada pilihan
                                if (pick_size == 0) {
                                    back_size = false;
                                    back_shoes = true;
                                } else if (pick_size > showListReadySize(pick_type, pick_shoes).length) {
                                    System.out.print("Tidak ada pilihan\n");
                                } else {
                                    do {
                                        System.out.println("\nList Warna Sepatu Tersedia :");
                                        for (int counter = 0; counter < showListReadyColor(pick_type, pick_shoes, pick_size).length; counter++) {
                                            System.out.println((counter + 1) + " " + Arrays.asList(showListReadyColor(pick_type, pick_shoes, pick_size)).get(counter));
                                        }
                                        System.out.print("Masukan 0 untuk kembali\nPilih Warna Sepatu (Pilih Nomor) : ");
                                        pick_color = input.nextInt();
                                        if (pick_color == 0) {
                                            back_size = true;
                                        } else if (pick_color > showListReadyColor(pick_type, pick_shoes, pick_size).length) {
                                            System.out.print("Tidak ada pilihan\n");
                                        }
                                    } while (pick_color > showListReadyColor(pick_type, pick_shoes, pick_size).length);
                                }
                            } while (pick_size > showListReadySize(pick_type, pick_shoes).length || back_size);
                        }
                    } while (pick_shoes > showListShoes(pick_type).length || back_shoes);
                }
            } while (back_type);

            do {
                System.out.println("\nMetode Pembayaran : ");
                for (int counter = 0; counter < payment_methods.length; counter++) {
                    System.out.println((counter + 1) + " " + Arrays.asList(payment_methods).get(counter));
                }
                System.out.print("Masukan 0 untuk kembali\nPilih Metode Pembayaran : ");
                pick_payment_method = input.nextInt();
                if (pick_payment_method == 0) {
                    back_payment = true;
                } else if (pick_payment_method == 1) {
                    System.out.println("List Bank Pembayaran : ");
                    for (int counter = 0; counter < showListBank(pick_payment_method).length; counter++) {
                        System.out.println((counter + 1) + " " + Arrays.asList(showListBank(pick_payment_method)).get(counter));
                    }
                    System.out.print("Pilih Bank Pembayaran : ");
                    pick_bank = input.nextInt();
                    System.out.println("Rekening Pembayaran : " + showNomor(pick_payment_method, pick_bank));

                } else if (pick_payment_method == 2) {
                    System.out.println("List Bank Pembayaran : ");
                    for (int counter = 0; counter < showListBank(pick_payment_method).length; counter++) {
                        System.out.println((counter + 1) + " " + Arrays.asList(showListBank(pick_payment_method)).get(counter));
                    }
                    System.out.print("Pilih Bank Pembayaran : ");
                    pick_bank = input.nextInt();
                    System.out.println("Rekening Pembayaran : " + showNomor(pick_payment_method, pick_bank) + phone);
                } else if (pick_payment_method == 3) {
                    System.out.println("Pembayaran Cash On Delivery (COD)");
                } else {
                    System.out.print("Tidak ada pilihan ");
                }
            } while (pick_payment_method > payment_methods.length || back_payment);
            // pilihan melanjutkan transaksi atau tidak, jika iya maka akan muncul invoice
            System.out.print("Lanjutkan Transaksi ?? ( Y/T )");
            next = input.next();
        } while (!next.equals("Y") && !next.equals("y"));

        System.out.println("\n\nINVOICE\nNama : " + name + "\nNo Handphone : " + phone + "\nAlamat : " + address);
        // -1 karena di semua variable pick memilih item berdasarkan nomor urut +1 , bukan langsung dari urutan index array
        System.out.println("Type Sepatu : " + shoes_type[pick_type - 1] + "\nSepatu : " + showListShoes(pick_type)[pick_shoes - 1]
                + "\nUkuran dan Warna Sepatu : " + showListReadySize(pick_type, pick_shoes)[pick_size - 1] + " ( " + showListReadyColor(pick_type, pick_shoes, pick_size)[pick_color - 1]
                + " )\nMetode Pembayaran : " + payment_methods[pick_payment_method - 1]);

        // membedakan virtual acc dengan no rekening, 1 = norek biasa, 2 = virtual acc
        if (pick_payment_method == 1) {
            System.out.println("Nomor Pembayaran : " + showNomor(pick_payment_method, pick_bank));
        } else if (pick_payment_method == 2) {
            System.out.println("Nomor Pembayaran : " + showNomor(pick_payment_method, pick_bank) + phone);
        }

        System.out.println("Terimakasih!!!");
    }

    public static String[] showListShoes(int type) {
        String[] shoes_list = null;

        if (type == 1) {
            shoes_list = new String[]{"Adidas", "Fila", "Nike", "New Balance"};
        } else if (type == 2) {
            shoes_list = new String[]{"Specs", "Futsal", "Football", "Ortuseight"};
        } else if (type == 3) {
            shoes_list = new String[]{"Puma", "Adidas", "Nike", "Specs"};
        } else if (type == 4) {
            shoes_list = new String[]{"Exodos", "Vans", "Converse", "Nike"};
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

    public static String showNomor(int payment_method, int bank) {
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
