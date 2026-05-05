//Final Tubes struktur data 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class TubesStrukdat {

    static Scanner sc = new Scanner(System.in);
    static int indikator = 0;

    // =========================================================//
    // classes //
    static class user {

        String nama;
        String alamat;
        String password;
        String role;
        int poin;
        int lv; // 1 = bronze; 2 = silver; 3 = Gold (paling tinggi)
        /*
         * Algo:
         * point < 500 = bronze
         * point > 500 && < 1000 = silver
         * point > 1000 = Gold
         */
        double nominal; // nambah nominal
        int[] kupon = new int[5];
        int head;
        int tail;
        int[] shoppingCart = new int[20];

        // history
        LinkedList<History> historyTransaksi = new LinkedList<>();
        LinkedList<History> historyTopUp = new LinkedList<>();
    }

    static class tempatkios {
        String alamat; // untuk ditunjukkan di reciept nanti
        double revenue;
        static itemlist[] item = new itemlist[20];
        LinkedList<reviewNode> daftarReview = new LinkedList<>();
    }

    static class itemlist {
        String nama;
        int harga;
        int qty;

        public String toString() {
            return "Nama: " + nama + ", Harga: " + harga + ", Qty: " + qty;
        }
    }

    static class reviewNode {
        String[] info;
        int bintang;

        public reviewNode(String[] info, int bintang) {
            this.info = info;
            this.bintang = bintang;
        }
    }

    static class History {
        ArrayList<String> detail;
        String tipe;
        String kategori;
        String nmKios;
        double total;

        public History(String tipe, String kategori, String nmKios, double total) {
            this.tipe = tipe;
            this.kategori = kategori;
            this.nmKios = nmKios;
            this.total = total;
            this.detail = new ArrayList<>();
        }

        // tambah item detail
        public void addDetail(String item) {
            detail.add(item);
        }

        public void tampil() {
            int nomor = 1;

            System.out.println("=== " + kategori + " ===");
            System.out.println("Kios: " + nmKios);

            for (int i = 0; i < detail.size(); i++) {
                String data = detail.get(i);

                if (data.contains("8001")) {
                    data = data.replace("8001", "BCA ");
                } 
                else if (data.contains("9002")) {
                    data = data.replace("9002", "Mandiri ");
                } 
                else if (data.contains("7003")) {
                    data = data.replace("7003", "BNI ");
                }

                System.out.println(nomor + ".) " + data);
                nomor++;
            }

            System.out.println("Total: " + total);
            System.out.println();
        }
    }

    static class Pengumuman {

        String isi;

        public Pengumuman(String isi) {
            this.isi = isi;
        }
    }

    static LinkedList<Pengumuman> listPengumuman = new LinkedList<>();

    static user[] U = new user[100];
    static tempatkios[] kios = new tempatkios[5];

    static void inisialisasi() {
        for (int i = 0; i < 100; i++) {
            U[i] = new user();
            U[i].poin = 0;
            U[i].nominal = 100000;
            U[i].lv = 1;
            U[i].head = -1;
            U[i].tail = -1;
        }

        for (int i = 0; i < 5; i++) {
            kios[i] = new tempatkios();
            kios[i].revenue = 0;
        }

        for (int i = 0; i < 20; i++) {
            tempatkios.item[i] = new itemlist();
        }

        // Data dummy pengguna
        U[0].nama = "udin";
        U[0].alamat = "Jl. Mawar 1";
        U[0].password = "password123";
        U[0].role = "pengguna";
        U[0].poin = 0;

        // Data dummy admin
        U[1].nama = "budi";
        U[1].alamat = "Jl. Melati 2";
        U[1].password = "adminpass";
        U[1].role = "admin";
        U[1].poin = 1000000; // khusus employee

        // hardcode untuk semua kios dan itemlistnya
        kios[0].alamat = "Jl. Anggrek No. 1";
        kios[1].alamat = "Jl. Kenanga No. 2";
        kios[2].alamat = "Jl. Dahlia No. 3";
        kios[3].alamat = "Jl. Mawar No. 4";
        kios[4].alamat = "Jl. Melati No. 5";

        tempatkios.item[0].nama = "Caramel Latte";
        tempatkios.item[0].harga = 25000;
        tempatkios.item[0].qty = 20;
        tempatkios.item[1].nama = "Espresso";
        tempatkios.item[1].harga = 18000;
        tempatkios.item[1].qty = 15;
        tempatkios.item[2].nama = "Cappuccino";
        tempatkios.item[2].harga = 22000;
        tempatkios.item[2].qty = 12;
        tempatkios.item[3].nama = "Americano";
        tempatkios.item[3].harga = 20000;
        tempatkios.item[3].qty = 8;
        tempatkios.item[4].nama = "Vanilla Latte";
        tempatkios.item[4].harga = 26000;
        tempatkios.item[4].qty = 9;
        tempatkios.item[5].nama = "Mocha";
        tempatkios.item[5].harga = 27000;
        tempatkios.item[5].qty = 11;
        tempatkios.item[6].nama = "Hazelnut Latte";
        tempatkios.item[6].harga = 28000;
        tempatkios.item[6].qty = 7;
        tempatkios.item[7].nama = "Matcha Latte";
        tempatkios.item[7].harga = 29000;
        tempatkios.item[7].qty = 13;
        tempatkios.item[8].nama = "Affogato";
        tempatkios.item[8].harga = 30000;
        tempatkios.item[8].qty = 6;
        tempatkios.item[9].nama = "Macchiato";
        tempatkios.item[9].harga = 24000;
        tempatkios.item[9].qty = 14;
    }

    // =========================================================//
    // admin Menu //
    static void MenuA() {
        System.out.println();
        System.out.println("=== Wellcome min mau apa hari ini? ===");
        System.out.println("1. Create (Menambah data pada itemlist)");
        System.out.println("2. Read (Membaca Ulasan dari Review)");
        System.out.println("3. Update (update data pada itemlist)");
        System.out.println("4. Delete (Mengurangi data pada itemlist)");
        System.out.println("5. Search (Mencari Data)");
        System.out.println("6. Kelola Pengumuman");
        System.out.println("7. Kelola History");
        System.out.println("8. Log Out");

        System.out.print("Silahkan Pilih min (1 - 8) :");
        int inputM = sc.nextInt();

        switch (inputM) {
            case 1:
                Create();
                break;

            case 2:
                Read();
                break;

            case 3:
                Update();
                break;

            case 4:
                Delete();
                break;

            case 5:
                Search();
                break;

            // Wili (tambahan menu)
            case 6:
                KelolaPengumuman();
                break;

            // ALDI (tambahan menu)
            case 7:
                kelolaHistoryAdmin();
                break;

            case 8:
                Login();
                break;

            default:
                System.out.println("salah input? coba ulang lagi");
                MenuA();
                break;
        }
    }

    static void Create() {
        sc.nextLine();
        System.out.println();
        System.out.println("Apa nama makanan/minuman ini? : ");
        String namaMM = sc.nextLine();

        System.out.println("Berapa harga makanan/minuman ini? : ");
        int hargaMM = sc.nextInt();

        System.out.println("Berapa qty makanan/minuman ini? : ");
        int qty = sc.nextInt();

        int indexkosong = -1;

        for (int i = 0; i < 20; i++) {
            if (tempatkios.item[i].nama == null) {
                indexkosong = i;
                break;
            }
        }

        if (indexkosong != -1) {
            tempatkios.item[indexkosong].nama = namaMM;
            tempatkios.item[indexkosong].harga = hargaMM;
            tempatkios.item[indexkosong].qty = qty;
        } else {
            System.out.println("Database makanan full, delete terlebih dahulu atau lakukan edit saja");
        }
        MenuA();
    }

    static void Read() {
        System.out.println();
        System.out.println("Mau liat Review kios yang mana ? ");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + kios[i].alamat);
        }
        System.out.println("Pilih (1 - 5) || 0 to print all");
        int pilihan = sc.nextInt();

        if (pilihan == 0) {
            for (int i = 0; i < 5; i++) {
                if (kios[i] != null) {
                    System.out.println("--- Review untuk Kios: " + kios[i].alamat + " ---");

                    Iterator<reviewNode> it = kios[i].daftarReview.iterator();
                    if (!it.hasNext()) {
                        System.out.println("Belum ada review di kios ini.");
                    } else {
                        while (it.hasNext()) {
                            reviewNode current = it.next();
                            System.out.println("Bintang: " + current.bintang + " | Review: "
                                    + java.util.Arrays.toString(current.info));
                        }
                        System.out.println();
                    }
                }
            }
        } else {
            int idx = pilihan - 1;
            Iterator<reviewNode> it = kios[idx].daftarReview.iterator();
            if (!it.hasNext()) {
                System.out.println("Belum ada review di kios ini.");
            } else {
                while (it.hasNext()) {
                    reviewNode current = it.next();
                    System.out.print("Bintang: " + current.bintang + " | Review: ");

                    boolean adaReviewText = false;
                    if (current.info != null) {
                        for (int i = 0; i < current.info.length; i++) {
                            if (current.info[i] != null) {
                                System.out.print(current.info[i] + " ");
                                adaReviewText = true;
                            }
                        }
                    }

                    if (!adaReviewText) {
                        System.out.print("(Tidak ada teks review)");
                    }

                    System.out.println();
                }
            }
        }
        MenuA();
    }

    static void Update() {
        sc.nextLine();
        int jdata = 0;
        System.out.println();
        System.out.println("Mau update data yang mana ?");
        for (int i = 0; i < 20; i++) {
            if (tempatkios.item[i].nama != null) {
                System.out.println((i + 1) + ". " + tempatkios.item[i].nama);
                jdata++;
            } else {
                break;
            }
        }
        System.out.println("Pilih data yang ingin di update");
        int inputU = sc.nextInt();

        inputU--;
        if (inputU <= jdata) {
            System.out.println("Mau update nama atau harga? (0. Nama, 1. Harga, 2. qty, 3.All)");
            int pilih = sc.nextInt();
            sc.nextLine();

            if (pilih == 0) {
                System.out.println("input nama baru untuk data : " + tempatkios.item[inputU].nama);
                String namaP = sc.nextLine();

                tempatkios.item[inputU].nama = namaP;
            } else {
                if (pilih == 1) {
                    System.out.println("input harga baru untuk data : " + tempatkios.item[inputU].harga);
                    int harga = sc.nextInt();

                    tempatkios.item[inputU].harga = harga;
                } else {
                    if (pilih == 2) {
                        System.out.println("input qty baru untuk data : " + tempatkios.item[inputU].qty);
                        int qty = sc.nextInt();

                        tempatkios.item[inputU].qty = qty;
                    } else {
                        if (pilih == 3) {
                            System.out.println("input nama baru untuk data : " + tempatkios.item[inputU].nama);
                            String namaP = sc.nextLine();
                            System.out.println("input harga baru untuk data : " + tempatkios.item[inputU].harga);
                            int harga = sc.nextInt();
                            System.out.println("input qty baru untuk data : " + tempatkios.item[inputU].qty);
                            int qty = sc.nextInt();

                            tempatkios.item[inputU].nama = namaP;
                            tempatkios.item[inputU].harga = harga;
                            tempatkios.item[inputU].qty = qty;
                        }
                    }
                }
            }

            System.out.println("Data TerUpdate!");
        } else {
            System.out.println("data tidak ada");
        }
        MenuA();
    }

    static void Delete() {
        sc.nextLine();
        int jdata = 0;
        System.out.println();
        System.out.println("Mau delete data yang mana ?");
        for (int i = 0; i < 20; i++) {
            if (tempatkios.item[i].nama != null) {
                System.out.println((i + 1) + ". " + tempatkios.item[i].nama);
                jdata++;
            } else {
                break;
            }
        }
        System.out.println();
        System.out.println("0. Cancel");

        System.out.println("Pilih data yang ingin dihapus");
        int inputD = sc.nextInt();

        if (inputD == 0) {
            MenuA();
            return;
        }

        inputD--;
        if (inputD >= 0 && inputD < jdata) {
            for (int i = inputD; i < jdata - 1; i++) {
                tempatkios.item[i].nama = tempatkios.item[i + 1].nama;
                tempatkios.item[i].harga = tempatkios.item[i + 1].harga;
                tempatkios.item[i].qty = tempatkios.item[i + 1].qty;
            }

            tempatkios.item[jdata - 1].nama = null;
            tempatkios.item[jdata - 1].harga = 0;
            tempatkios.item[jdata - 1].qty = 0;

            System.out.println("Data Terdelete!");
        } else {
            System.out.println("data tidak ada");
        }
        MenuA();
    }

    static void Search() {
        sc.nextLine();
        System.out.println();
        System.out.println("Data apa yang ingin dicari?");
        String input = sc.nextLine();

        for (int i = 0; i < 20; i++) {
            if (tempatkios.item[i] != null && tempatkios.item[i].nama != null) {
                if (tempatkios.item[i].nama.equalsIgnoreCase(input)) {
                    System.out.println("Nama : " + tempatkios.item[i].nama
                            + " Harga : " + tempatkios.item[i].harga
                            + " QTY : " + tempatkios.item[i].qty);
                }
            } else {
                break;
            }
        }
        MenuA();
    }

    // ALDI (menu 7)
    static void kelolaHistoryAdmin() {
        System.out.println("\n=== KELOLA HISTORY ADMIN ===");

        for (int i = 0; i < 100; i++) {
            if (U[i].nama != null) {
                System.out.println(i + ". " + U[i].nama);
            }
        }

        System.out.print("Pilih user: ");
        int pilihUser = sc.nextInt();

        if (pilihUser < 0 || pilihUser >= 100 || U[pilihUser].nama == null) {
            System.out.println("User tidak valid!");
            MenuA();
            return;
        }

        System.out.println("\n=== HISTORY TRANSAKSI ===");
        int nomor = 1;

        for (int i = 0; i < U[pilihUser].historyTransaksi.size(); i++) {
            System.out.println((nomor++) + ". TRANSAKSI");
            U[pilihUser].historyTransaksi.get(i).tampil();
        }

        System.out.println("\n=== HISTORY TOP UP ===");
        for (int i = 0; i < U[pilihUser].historyTopUp.size(); i++) {
            System.out.println((nomor++) + ". TOP UP");
            U[pilihUser].historyTopUp.get(i).tampil();
        }

        System.out.println("\n1. Delete Transaksi");
        System.out.println("2. Delete TopUp");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        int pilih = sc.nextInt();

        if (pilih == 1) {
            System.out.print("Index transaksi: ");
            int idx = sc.nextInt();

            if (idx >= 0 && idx < U[pilihUser].historyTransaksi.size()) {
                U[pilihUser].historyTransaksi.remove(idx);
                System.out.println("Transaksi dihapus!");
            }
        } else if (pilih == 2) {
            System.out.print("Index topup: ");
            int idx = sc.nextInt();

            if (idx >= 0 && idx < U[pilihUser].historyTopUp.size()) {
                U[pilihUser].historyTopUp.remove(idx);
                System.out.println("TopUp dihapus!");
            }
        }

        MenuA();
    }

    // Wili
    static void KelolaPengumuman() {
        System.out.println("\n===Kelola Pengumuman ===");
        System.out.println("1. Tambah Pengumuman");
        System.out.println("2. Hapus Pengumuman");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        int p = sc.nextInt();

        if (p == 1) {
            System.out.print("Masukkan isi pengumuman: ");
            sc.nextLine();
            String info = sc.nextLine();
            listPengumuman.add(new Pengumuman(info));
            System.out.println("Pengumuman ditambahkan!");
        } else if (p == 2) {
            if (listPengumuman.isEmpty()) {
                System.out.println("Tidak ada pengumuman");
            } else {
                for (int i = 0; i < listPengumuman.size(); i++) {
                    System.out.println((i + 1) + ". " + listPengumuman.get(i).isi);
                }
                System.out.print("Pilih nomor yang dihapus: ");
                int hapus = sc.nextInt();
                if (hapus > 0 && hapus <= listPengumuman.size()) {
                    listPengumuman.remove(hapus - 1);
                    System.out.println("Pengumuman berhasil dihapus");
                } else {
                    System.out.println("Nomor tidak ada");
                }
            }
        }
        MenuA();
    }

    // =========================================================//
    // user menu //
    static void MenuP() {
        System.out.println("\nWelcome " + U[indikator].nama);
        System.out.println("=== Main Menu ===");
        System.out.println("1. Belanja"); // ---> Lihat Shopping Cart ada didalam sini
        System.out.println("2. History");
        System.out.println("3. cek poin akun");
        System.out.println("4. Top up");
        System.out.println("5. Pengumuman");
        System.out.println("6. Log Out");
        System.out.print("Silahkan Pilih (1 - 6) :");
        int inputM = sc.nextInt();

        switch (inputM) {
            case 1:
                // pemilihan kios terlebih dahulu baru ada keluar main
                // menunya
                listkios();
                System.out.println("Tolong pilih Kios mana");
                int pilihkios = sc.nextInt() - 1;

                if (pilihkios > 5) {
                    System.out.println("Kios Tidak ada");
                    MenuP();
                }
                System.out.println("Welcome to Kopi Kenangan " + kios[pilihkios].alamat);
                System.out.println("=== Pilihan Menu ===");

                int in = 0;
                while (in < 20 && kios[pilihkios].item[in] != null && kios[pilihkios].item[in].nama != null) {
                    System.out.println("Menu " + (in + 1) + " " + kios[pilihkios].item[in]);
                    in++;
                }

                System.out.println("=== Pilih Menu ===");

                boolean cekipt = true;
                int j = 0;

                while (cekipt && j < 20) {
                    System.out.print("Masukkan menu: ");
                    int x = sc.nextInt() - 1;

                    if (x < 0 || x >= in) {
                        System.out.println("Menu tidak tersedia");
                    } else {
                        System.out.print("Masukkan Jumlah: ");
                        int jumlah_data = sc.nextInt();

                        U[indikator].shoppingCart[x] = U[indikator].shoppingCart[x] + jumlah_data;

                        System.out.println("Tambah menu lagi? (Y/N)");
                        char cek = sc.next().charAt(0);

                        if (Character.toUpperCase(cek) == 'Y') {
                            cekipt = true;
                        } else {
                            cekipt = false;
                        }
                    }

                    j++;
                }
                // =======================
                // HITUNG TOTAL
                // =======================
                int[] penambl = belanja(pilihkios);
                double total = total(penambl);

                // =======================
                // BUILD DETAIL TRANSAKSI BARU (PENTING)
                // =======================
                String[] multitxt = new String[20];
                int idx = 0;

                int ic = 0;
                while (ic < 20) {
                    if (U[indikator].shoppingCart[ic] != 0) {
                        multitxt[idx] = "Menu " + (ic + 1) + " " + kios[pilihkios].item[ic].nama + " | Jumlah: "
                                + U[indikator].shoppingCart[ic] + " | Total: " + penambl[ic];
                        idx++;
                    }
                    ic++;
                }
                System.out.print("Delivery/Dine In? (1 = delivery; 2 = Dine In): ");
                int mkn = sc.nextInt();
                int ongkir = 10000;
                String ktgr;

                System.out.println("Total Pembelian: " + total);
                System.out.println("Biaya Ongkir: " + ongkir);
                if (mkn == 1) {
                    ktgr = "DELIVERY";
                    System.out.println("Alamat Anda: " + U[indikator].alamat);
                    total = total + 10000; // Tarif flat, jauh dekat
                } else
                    ktgr = "DINE IN";
                System.out.println("Total Akhir: " + total);
                double cekvch = voucher();
                if (cekvch != 0) {
                    System.out.println("Kamu Punya Voucher, Mau Pakai? (1 = Ya; 2 = tidak): ");

                    int ck = sc.nextInt();

                    if (ck == 1) {
                        double tm = (total * cekvch);
                        System.out.println("Total Voucher: " + tm);
                        total = total - tm;

                        System.out.println("Total Akhir: " + total);
                    }
                }
                double temper = bayar(total);

                if (temper < 0) {
                    System.out.println("Maaf Saldo Tidak Mencukupi");
                } else {

                    System.out.println("=== Receipt ===");

                    int z = 0;
                    while (z < 20) {
                        if (multitxt[z] != null) {
                            System.out.println(multitxt[z]);
                        }
                        z++;
                    }

                    System.out.println("Pembayaran Berhasil, sisa saldo: " + U[indikator].nominal);

                    // POINT
                    U[indikator].poin = point(total);
                    System.out.println("Anda mendapat " + point(total) + " point");

                    // RATING
                    System.out.print("Masukkan Rating (1-5): ");
                    int rt = sc.nextInt();

                    kios[pilihkios].daftarReview.add(new reviewNode(multitxt, rt));

                    System.out.println("Gacha Voucher Otomatis");
                    int random_voucher = randomvoucher();

                    boolean checked = add(random_voucher);
                    if (!checked) {
                        System.out.println("Maaf, batas maksimal Voucher Anda sudah Penuh");
                    } else {
                        System.out.println("Voucher sebesar " + random_voucher + "% Berhasil ditambah");
                    }

                    // =======================
                    // HISTORY TRANSAKSI (FIX UTAMA)
                    // =======================
                    String[] copy = new String[20];
                    int c = 0;
                    while (c < 20) {
                        copy[c] = multitxt[c];
                        c++;
                    }

                    History h = new History("TRANSAKSI", ktgr, kios[pilihkios].alamat, total);
                    int y = 0;
                    while (y < 20) {
                        if (multitxt[y] != null) {
                            h.addDetail(multitxt[y]);
                        }
                        y++;
                    }

                    U[indikator].historyTransaksi.addFirst(h);

                    // REVENUE
                    kios[pilihkios].revenue += total;

                    // RESET CART (BIAR GA KEBAWA TRANSAKSI SELANJUTNYA)
                    int r = 0;
                    while (r < 20) {
                        U[indikator].shoppingCart[r] = 0;
                        r++;
                    }

                    try {
                        System.out.print("Otomatis kembali dalam ");
                        for (int i = 3; i > 0; i--) {
                            Thread.sleep(1000);
                            System.out.print(i + "...");
                        }
                    } catch (Exception e) {
                    }
                }

                System.out.println();
                MenuP();
                break;

            case 2:
                menuHistoryUser(); // yang ini void pasti karna cuman nampilin doang
                break;

            case 3:
                System.out.println("Poin anda sekarang ada : " + U[indikator].poin);
                System.out.println("Kembali ke menu? (1) = yes: ");
                int bk = sc.nextInt();
                if (bk == 1) {
                    MenuP();
                }
                break;

            case 4:
                topUp();
                break;

            case 5:
                LihatPengumuman();
                break;

            case 6:
                Login();
                break;

            default:
                System.out.println("salah input? coba ulang lagi");
                MenuP();
                break;
        }
    }

    static void ceklevel() {
        int pt = U[indikator].poin;

        if (pt < 500) {
            U[indikator].lv = 1;
        } else if (pt < 1000) {
            U[indikator].lv = 2;
        } else {
            U[indikator].lv = 3;
        }

    }

    static int[] belanja(int kd) {
        int[] tempatsementara = new int[20];

        for (int i = 0; i < 20; i++) {
            tempatsementara[i] = 0;
        }
        for (int ct = 0; ct < 20; ct++) {
            if (U[indikator].shoppingCart[ct] != 0) {
                tempatsementara[ct] = U[indikator].shoppingCart[ct] * kios[kd].item[ct].harga;
            }
        }
        return tempatsementara;
    }

    static int randomvoucher() {
        ceklevel();
        int vch;
        if (U[indikator].lv < 500) {
            vch = (int) (Math.random() * 60) + 10;
        } else if (U[indikator].lv < 1000) {
            vch = (int) (Math.random() * 40) + 10;
        } else {
            vch = (int) (Math.random() * 20) + 10;
        }

        return vch;
    }

    static boolean isFull() {
        return (U[indikator].tail == 4);
    }

    static boolean add(int vh) {
        if (isFull()) {
            return false;
        } else {
            if (U[indikator].head == -1) {
                U[indikator].head++;
            }
            U[indikator].tail++;
            U[indikator].kupon[U[indikator].tail] = vh;
            return true;
        }
    }

    static int point(double tl) {
        int pn = (int) (tl / 1000);
        return pn;
    }

    static int total(int[] cb) {
        int total = 0;
        for (int i = 0; i < 20; i++) {
            if (cb[i] != 0) {
                total = total + cb[i];
            }
        }

        return total;
    }

    static double voucher() {
        double tempor = U[indikator].kupon[0];

        if (tempor == 0) {
            return 0;
        } else {
            double kp = tempor / 100;

            for (int i = 0; i < 4; i++) {
                U[indikator].kupon[i] = U[indikator].kupon[i + 1];
            }
            return kp;
        }
    }

    static double bayar(double nm) {
        if (U[indikator].nominal < nm) {
            return -1;
        } else {
            U[indikator].nominal = U[indikator].nominal - nm;
            return U[indikator].nominal;
        }
    }

    static void menuHistoryUser() {
        System.out.println("\n=== MENU HISTORY ===");
        System.out.println("1. History Transaksi");
        System.out.println("2. History Top Up");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        int pilih = sc.nextInt();

        switch (pilih) {
            case 1:
                historyTransaksi();
                break;

            case 2:
                historyTopUp();
                break;

            case 3:
                MenuP(); // ✔ hanya di sini
                return;
        }

        menuHistoryUser(); // biar balik lagi ke menu history
    }

    // ALDI
    static void historyTransaksi() {
        System.out.println("\n=== HISTORY TRANSAKSI ===");

        if (U[indikator].historyTransaksi.isEmpty()) {
            System.out.println("Belum ada history.");
            return;
        }

        int nomor = 1;

        for (int i = 0; i < U[indikator].historyTransaksi.size(); i++) {
            History h = U[indikator].historyTransaksi.get(i);

            System.out.println(nomor + ".");
            h.tampil();
            nomor++;
        }

        MenuP();
    }

    static void historyTopUp() {
        System.out.println("\n=== HISTORY TOP UP ===");

        if (U[indikator].historyTopUp.isEmpty()) {
            System.out.println("Belum ada history.");
            return;
        }

        int nomor = 1;

        for (int i = 0; i < U[indikator].historyTopUp.size(); i++) {
            History h = U[indikator].historyTopUp.get(i);

            System.out.println(nomor + ".");
            h.tampil();
            nomor++;
        }
    }

    static void topUp() {
        System.out.println("\n=== Top Up Menu ===");
        System.out.println("Pilih Bank: ");
        System.out.println("1. BCA");
        System.out.println("2. Mandiri");
        System.out.println("3. BNI");
        System.out.print("Pilih (1-3): ");
        int pilihBank = sc.nextInt();

        String kodeBank = "";
        if (pilihBank == 1) {
            kodeBank = "8001";
        } else if (pilihBank == 2) {
            kodeBank = "9002";
        } else {
            kodeBank = "7003";
        }

        System.out.println("\n=== Top Up Menu ===");
        System.out.println("Saldo anda saat ini: Rp" + U[indikator].nominal);
        System.out.println("1. Rp10.000");
        System.out.println("2. Rp50.000");
        System.out.println("3. Rp100.000");
        System.out.println("4. Input Manual");
        System.out.print("Silahkan Pilih (1 - 4) : ");
        int pilih = sc.nextInt();
        int tambah = 0;
        switch (pilih) {
            case 1:
                tambah = 10000;
                break;
            case 2:
                tambah = 50000;
                break;
            case 3:
                tambah = 100000;
                break;
            case 4:
                System.out.println("\nMasukan Nominal: Rp");
                tambah = sc.nextInt();
                break;

            default:
                System.out.println("Pilihan tidak tersedia");
                topUp();
                return;
        }
        int acak1 = (int) (Math.random() * 9000) + 1000;
        int acak2 = (int) (Math.random() * 9000) + 1000;
        String virtualAccount = kodeBank + acak1 + acak2;

        System.out.println("\nNomor Virtual Account Anda: " + virtualAccount);

        U[indikator].nominal += tambah;

        // ALDI
        String[] topupDetail = new String[1];
        topupDetail[0] = "Top Up via kode bank " + kodeBank + " sebesar: Rp" + tambah;

        History h = new History("TOPUP", "SALDO", "SYSTEM", tambah);

        h.addDetail("Top Up via bank " + kodeBank);
        h.addDetail("Virtual Account: " + virtualAccount);
        h.addDetail("Nominal: Rp" + tambah);

        U[indikator].historyTopUp.addFirst(h);

        System.out.println("\nPEMBAYARAN BERHASIL!");
        System.out.println("Saldo bertambah: Rp" + tambah);
        System.out.println("Total Saldo " + U[indikator].nama + ": Rp" + U[indikator].nominal);

        MenuP();
    }

    static void LihatPengumuman() {
        System.out.println("\n=== Pengumuman ===");
        if (listPengumuman.isEmpty()) {
            System.out.println("Tidak ada pengumuman!");
            System.out.println();
        } else {
            int count = 0;
            for (int i = listPengumuman.size() - 1; i >= 0; i--) {
                count++;
                System.out.println(count + ". " + listPengumuman.get(i).isi);
            }
            System.out.println();
        }
        MenuP();
    }

    static void listkios() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Kios ke: " + (i + 1) + " " + kios[i].alamat);
        }
    }

    // =========================================================//
    // user and admin menu //
    static void Login() {
        System.out.println("Login || Register (L/R) || Close App (C)");
        char log = sc.next().charAt(0);
        sc.nextLine();

        if (log == 'L' || log == 'l') {
            System.out.println("Masukan nama");
            String inputN = sc.nextLine();

            boolean ketemu = false;
            for (int i = 0; i < 100; i++) {
                if (U[i] != null && U[i].nama != null) {
                    if (U[i].nama.equals(inputN)) {
                        indikator = i; // gunakan indikator ini untuk ngasih tau user mana yang lagi log in yak
                        ketemu = true;

                        System.out.println("Masukan Password");
                        String inputP = sc.nextLine();

                        if (U[i].password.equals(inputP)) {
                            if (U[i].role.equals("pengguna")) {
                                System.out.println("Login berhasil");
                                MenuP();
                            } else {
                                if (U[i].role.equals("admin")) {
                                    System.out.println("Login berhasil, welcome admin");
                                    MenuA();
                                }
                            }
                        } else {
                            System.out.println("Password salah tolong coba lagi");
                            System.out.println();
                            Login();
                        }
                        break;
                    }
                }
            }
            if (!ketemu) {
                System.out.println("Username tidak ditemukan");
                System.out.println();
                Login();
            }
        } else {
            if (log == 'R' || log == 'r') {
                System.out.println("Tolong masukan nama");
                String inputN = sc.nextLine();

                System.out.println("Masukan password");
                String inputP = sc.nextLine();

                System.out.println("Masukan Address");
                String add = sc.nextLine();

                int indexkosong = 0;
                for (int i = 0; i < 100; i++) {
                    if (U[i].nama == "") {
                        indexkosong = i;
                        break;
                    }
                }

                if (indexkosong != -1) {
                    U[indexkosong].nama = inputN;
                    U[indexkosong].password = inputP;
                    U[indexkosong].alamat = add;
                    U[indexkosong].role = "pengguna";
                    System.out.println("Anda sudah Terdaftar!");
                    System.out.println();
                } else {
                    System.out.println("database penuh, tolong upgrade");
                    System.out.println();
                }

                Login();
            } else {
                if (log == 'C' || log == 'c'){
                    System.out.println("Good Bye, See you later U_U ");

                } else {
                    System.out.println("Salah Input Tolong ulang lagi!");
                    System.out.println();
                    Login();
                }
            }
        }
    }

    public static void main(String[] args) {
        inisialisasi();
        Login();
        // MenuP();
    }
}
