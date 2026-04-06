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
        int nominal; // nambah nominal
        int[] uang = new int[5];
        int[] kupon = new int[5];
        int[] shoppingCart = new int[20];
        LinkedList<hsus> hst = new LinkedList<>();
    }

    static class hsus{
        String[] ifo;
        int ttl;
        public hsus(String[] io, int tl) {
            this.ifo = io;
            this.ttl = tl;
        }
    }

    static class tempatkios {
        String alamat; // untuk ditunjukan di reciept nanti
        int revenue;
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

    static user[] U = new user[100];
    static tempatkios[] kios = new tempatkios[5];

    static void inisialisasi() {
        for (int i = 0; i < 100; i++) {
            U[i] = new user();
            U[i].poin = 0;
            U[i].nominal = 100000;
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
        System.out.println("6. Log Out");
        System.out.print("Silahkan Pilih min (1 - 5) :");
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

            case 6:
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

        int indexkosong = 0;
        for (int i = 0; i < 20; i++) {
            if (tempatkios.item[i] == null) {
                indexkosong = i;
                break;
            }
        }

        if (indexkosong != -1) {
            tempatkios.item[indexkosong].nama = namaMM;
            tempatkios.item[indexkosong].harga = hargaMM;
            tempatkios.item[indexkosong].qty = qty;
        } else {
            System.out.println("Database makanan full, delete terlebioh dahulu atau lakukan edit saja");
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
                    System.out.println(
                            "Bintang: " + current.bintang + " | Review: " + java.util.Arrays.toString(current.info));
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
        System.out.println("Pilih data yang ingin dihapus");
        int inputD = sc.nextInt();

        inputD--;
        if (inputD <= jdata) {
            for (int i = inputD; i < jdata; i++) {
                tempatkios.item[i].nama = tempatkios.item[i + 1].nama;
                tempatkios.item[i].harga = tempatkios.item[i + 1].harga;
                tempatkios.item[i].qty = tempatkios.item[i + 1].qty;
            }
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
            if (tempatkios.item[i].nama != null) {
                if (tempatkios.item[i].nama.equalsIgnoreCase(input)) {
                    System.out.println("Nama : " + tempatkios.item[i].nama +
                            "Harga : " + tempatkios.item[i].harga +
                            "QTY : " + tempatkios.item[i].qty);
                }
            } else {
                break;
            }
        }
        MenuA();
    }

    // =========================================================//
    // user menu //

    static void MenuP() {
        System.out.println("Tolong pilih Kios mana"); // pemilihan kios terlebih dahulu baru ada keluar main menunya
        int pilihkios = 1; // sc.nextInt() pakai hard code dulu
        if (pilihkios < 5) {
            System.out.println();
            System.out.println("Welcome to Kopi kenangan, Jl." + kios[pilihkios].alamat);
            System.out.println("=== Main Menu ===");
            System.out.println("1. Belanja"); // ---> Lihat Shopping Cart ada didalam sini
            System.out.println("2. History Transaksi");
            System.out.println("3. cek poin akun");
            System.out.println("4. Top up");
            System.out.println("5. Log Out");
            System.out.print("Silahkan Pilih (1 - 5) :");
            int inputM = sc.nextInt();

            switch (inputM) {
                case 1:
                    System.out.println("=== Pilihan Menu ===");
                    int in = 0;

                    while (kios[pilihkios].item[in].nama != null) {
                        System.out.println("Menu " + (in + 1) + " " + kios[pilihkios].item[in]);
                        in++;
                    }

                    System.out.println("=== Pilih Menu (input angka)===");
                    boolean cekipt = true;
                    int j = 0;
                    while (cekipt && j < 20) {
                        System.out.print("Masukkan menu: ");
                        int x = sc.nextInt() - 1;
                        if (x > (in + 1) || x <= -1) {
                            System.out.println("Menu tidak tersedia");
                        } else {
                            U[indikator].shoppingCart[x]++;
                            System.out.println("Tambah menu (Y/N)");
                            char cek = sc.next().charAt(0);

                            if (Character.toUpperCase(cek) == 'Y') {
                                cekipt = true;
                            } else {
                                cekipt = false;
                            }
                        }

                    }

                    int[] penambl = belanja(pilihkios); // Total per Item
                    int total = total(penambl); // total akhir
                    String[] multitxt = new String[20];
                    for (int ic = 0; ic < 20; ic++) {
                        if (U[indikator].shoppingCart[ic] != 0) {
                            String txt = "Menu " + (ic + 1) + " " + kios[pilihkios].item[ic].nama + "  Jumlah: "
                                    + U[indikator].shoppingCart[ic] + " Total: " + penambl[ic];
                            multitxt[ic] = txt;
                        }
                    }
                    System.out.println("Total akhir: " + total);
                    int temper = bayar(total); // cek saldo user
                    if (temper == -1) {
                        System.out.println("Maaf Saldo Tidak Mencukupi");
                    } else {
                        System.out.println("=== Receipt ===");
                        for (int jk = 0; jk < 20; jk++) {
                            if (multitxt[jk] != null)
                                System.out.println(multitxt[jk]);
                        }
                        System.out.println("Pembayaran Berhasi, sisa saldo: " + U[indikator].nominal);

                        // Rating
                        System.out.print("Masukkan Rating: ");
                        int rt = sc.nextInt();
                        kios[pilihkios].daftarReview.add(new reviewNode(multitxt, rt)); //Kirim ke Toko, Menu yang dibeli + rating transaksi

                        //Masuk ke History

                        U[indikator].hst.add(new hsus(multitxt, total));

                        // Masukkan revenue ke kios
                        kios[pilihkios].revenue = kios[pilihkios].revenue + total;
                    }
                    break;

                case 2:
                    historyTransaksi(); // yang ini void pasti karna cuman nampilin doang
                    break;

                case 3:
                    System.out.println("Poin anda sekarang ada : " + U[indikator].poin);
                    MenuP();
                    break;

                case 4:
                    topUp();
                    break;

                case 5:
                    Login();
                    break;

                default:
                    System.out.println("salah input? coba ulang lagi");
                    MenuP();
                    break;
            }
        } else {
            System.out.println("Kios tidak ada, Tolong pilih lagi");
            MenuP();
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

    static int total(int[] cb) {
        int total = 0;
        for (int i = 0; i < 20; i++) {
            if (cb[i] != 0) {
                total = total + cb[i];
            }
        }
        return total;
    }

    static int bayar(int nm) {
        if (U[indikator].nominal < nm) {
            return -1;
        } else {
            U[indikator].nominal = U[indikator].nominal - nm;
            return U[indikator].nominal;
        }
    }

    static void historyTransaksi() {

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
        try {
            System.out.print("Memproses");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            System.out.println();
        } catch (Exception e) {
        }

        U[indikator].nominal += tambah;

        System.out.println("\nPEMBAYARAN BERHASIL!");
        System.out.println("Saldo bertambah: Rp" + tambah);
        System.out.println("Total Saldo " + U[indikator].nama + ": Rp" + U[indikator].nominal);

        MenuP();
    }

    // =========================================================//
    // user and admin menu //

    static void Login() {
        System.out.println("Login || Register (L/R)");
        char log = sc.next().charAt(0);
        sc.nextLine();

        if (log == 'L' || log == 'l') {
            System.out.println("Masukan nama");
            String inputN = sc.nextLine();

            boolean ketemu = false;
            for (int i = 0; i < 100; i++) {
                if (U[i] != null) {
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
                            Login();
                        }
                        break;
                    }
                }
            }
            if (!ketemu) {
                System.out.println("Username tidak ditemukan");
            }
        } else {
            if (log == 'R' || log == 'r') {
                System.out.println("Tolong masukan nama");
                String inputN = sc.nextLine();

                System.out.println("Masukan password");
                String inputP = sc.nextLine();

                System.out.println("Masukan Address");
                String add = sc.nextLine();
                sc.nextLine();

                int indexkosong = 0;
                for (int i = 0; i < 100; i++) {
                    if (U[i].nama == null) {
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
                } else {
                    System.out.println("database penuh, tolong upgrade");
                }

                Login();
            } else {
                System.out.println("Salah Input Tolong ulang lagi!");
                Login();
            }
        }
    }

    public static void main(String[] args) {
        inisialisasi();
        // Login();
        MenuP();
    }
}
