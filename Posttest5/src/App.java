import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static ArrayList<Film> daftarFilm = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            cetakGaris();
            System.out.println("\tCineboxd: Movie Catalog");
            cetakGaris();
            System.out.println("[1] Tambah Film");
            System.out.println("[2] Lihat Daftar Film");
            System.out.println("[3] Edit Film");
            System.out.println("[4] Hapus Film");
            System.out.println("[0] Keluar");
            cetakGaris();
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1 -> tambahFilm();
                case 2 -> lihatFilm();
                case 3 -> editFilm();
                case 4 -> hapusFilm();
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan Cineboxd!");
                    return;
                }
                default -> {
                    System.out.println("Pilihan tidak valid, coba lagi!");
                    tunggu(2000);
                }
            }
        }
    }

    private static void clearScreen() {
        try {
            String operatingSystem = System.getProperty("os.name");
            
            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static void cetakGaris() {
        System.out.println("========================================");
    }

    private static void tunggu(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void tambahFilm() {
        clearScreen();
        cetakGaris();
        System.out.println("\tTAMBAH FILM");
        cetakGaris();
        
        System.out.println("Pilih jenis film:");
        System.out.println("[1] Film Komersial");
        System.out.println("[2] Film Festival");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan judul film: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan sutradara: ");
        String sutradara = scanner.nextLine();
        System.out.print("Masukkan tahun rilis: ");
        int tahun = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan genre: ");
        String genreNama = scanner.nextLine();
        Genre genre = new Genre(genreNama);

        if (jenis == 1) {
            System.out.print("Masukkan studio produksi: ");
            String studio = scanner.nextLine();
            System.out.print("Masukkan budget produksi ($): ");
            long budget = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Masukkan pendapatan ($): ");
            long pendapatan = scanner.nextLong();
            scanner.nextLine();
            daftarFilm.add(new FilmKomersial(judul, sutradara, tahun, genre, budget, pendapatan, studio));
        } else if (jenis == 2) {
            FilmFestival film = new FilmFestival(judul, sutradara, tahun, genre);
            
            System.out.println("Tambahkan festival (ketik 'selesai' untuk berhenti): ");
            while (true) {
                System.out.print("Nama Festival: ");
                String festival = scanner.nextLine();
                if (festival.equalsIgnoreCase("selesai")) break;
                film.tambahFestival(festival);
            }
            
            System.out.println("Tambahkan penghargaan (ketik 'selesai' untuk berhenti): ");
            while (true) {
                System.out.print("Nama Penghargaan: ");
                String penghargaan = scanner.nextLine();
                if (penghargaan.equalsIgnoreCase("selesai")) break;
                film.tambahPenghargaan(penghargaan);
            }
            
            daftarFilm.add(film);
        } else {
            System.out.println("Jenis film tidak valid!");
            tunggu(2000);
            return;
        }
        System.out.println("Film berhasil ditambahkan!");
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }

    private static void lihatFilm() {
        clearScreen();
        cetakGaris();
        System.out.println("\tDaftar Film");
        cetakGaris();
        
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang terdaftar.");
            System.out.println("Tekan Enter untuk kembali ke menu...");
            scanner.nextLine();
            return;
        }
        
        for (int i = 0; i < daftarFilm.size(); i++) {
            Film film = daftarFilm.get(i);
            System.out.println((i + 1) + ". " + (film instanceof FilmKomersial ? 
                               ((FilmKomersial)film).getInfoLengkap() : 
                               ((FilmFestival)film).getInfoLengkap()));
            cetakGaris();
        }
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }

    private static void editFilm() {
        clearScreen();
        cetakGaris();
        System.out.println("\tEDIT FILM");
        cetakGaris();
        
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang terdaftar.");
            System.out.println("Tekan Enter untuk kembali ke menu...");
            scanner.nextLine();
            return;
        }
        
        for (int i = 0; i < daftarFilm.size(); i++) {
            Film film = daftarFilm.get(i);
            System.out.println((i + 1) + ". " + film.getJudul());
        }
        
        cetakGaris();
        System.out.print("Pilih nomor film yang ingin diedit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index < 0 || index >= daftarFilm.size()) {
            System.out.println("Nomor film tidak valid!");
            tunggu(2000);
            return;
        }

        Film film = daftarFilm.get(index);
        System.out.print("Masukkan judul baru: ");
        film.setJudul(scanner.nextLine());
        System.out.print("Masukkan sutradara baru: ");
        film.setSutradara(scanner.nextLine());
        System.out.print("Masukkan tahun rilis baru: ");
        film.setTahunRilis(scanner.nextInt());
        scanner.nextLine();
        
        if (film instanceof FilmKomersial) {
            FilmKomersial fk = (FilmKomersial) film;
            System.out.print("Masukkan studio produksi baru: ");
            fk.setStudioProduksi(scanner.nextLine());
            System.out.print("Masukkan budget baru ($): ");
            fk.setBudget(scanner.nextLong());
            scanner.nextLine();
            System.out.print("Masukkan pendapatan baru ($): ");
            fk.setPendapatan(scanner.nextLong());
            scanner.nextLine();
        } else if (film instanceof FilmFestival) {
            FilmFestival ff = (FilmFestival) film;
            
            System.out.println("Edit festival (hapus semua dan tambahkan yang baru)");
            ff.getDaftarFestival().clear();
            System.out.println("Tambahkan festival (ketik 'selesai' untuk berhenti): ");
            while (true) {
                System.out.print("Nama Festival: ");
                String festival = scanner.nextLine();
                if (festival.equalsIgnoreCase("selesai")) break;
                ff.tambahFestival(festival);
            }
            
            System.out.println("Edit penghargaan (hapus semua dan tambahkan yang baru)");
            ff.getPenghargaan().clear();
            System.out.println("Tambahkan penghargaan (ketik 'selesai' untuk berhenti): ");
            while (true) {
                System.out.print("Nama Penghargaan: ");
                String penghargaan = scanner.nextLine();
                if (penghargaan.equalsIgnoreCase("selesai")) break;
                ff.tambahPenghargaan(penghargaan);
            }
        }
        
        System.out.println("Film berhasil diperbarui!");
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }

    private static void hapusFilm() {
        clearScreen();
        cetakGaris();
        System.out.println("\tHAPUS FILM");
        cetakGaris();
        
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang terdaftar.");
            System.out.println("Tekan Enter untuk kembali ke menu...");
            scanner.nextLine();
            return;
        }
        
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul());
        }
        
        cetakGaris();
        System.out.print("Pilih nomor film yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index < 0 || index >= daftarFilm.size()) {
            System.out.println("Nomor film tidak valid!");
            tunggu(2000);
            return;
        }
        
        String judulFilm = daftarFilm.get(index).getJudul();
        daftarFilm.remove(index);
        System.out.println("Film \"" + judulFilm + "\" berhasil dihapus!");
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }
}