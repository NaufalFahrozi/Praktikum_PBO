import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static ArrayList<Film> daftarFilm = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Cineboxd: Movie Catalog ===");
            System.out.println("[1]. Tambah Film");
            System.out.println("[2]. Lihat Daftar Film");
            System.out.println("[3]. Edit Film");
            System.out.println("[4]. Hapus Film");
            System.out.println("[0] Keluar");
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
                default -> System.out.println("Pilihan tidak valid, coba lagi!");
            }
        }
    }

    private static void tambahFilm() {
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
        
        daftarFilm.add(new Film(judul, sutradara, tahun, genre));
        System.out.println("Film berhasil ditambahkan!");
    }

    private static void lihatFilm() {
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang terdaftar.");
            return;
        }
        System.out.println("\n=== Daftar Film ===");
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.println((i + 1) + ". " + daftarFilm.get(i));
        }
    }

    private static void editFilm() {
        lihatFilm();
        if (daftarFilm.isEmpty()) return;
        
        System.out.print("Pilih nomor film yang ingin diedit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index < 0 || index >= daftarFilm.size()) {
            System.out.println("Nomor film tidak valid!");
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
        System.out.print("Masukkan genre baru: ");
        film.setGenre(new Genre(scanner.nextLine()));
        
        System.out.println("Film berhasil diperbarui!");
    }

    private static void hapusFilm() {
        lihatFilm();
        if (daftarFilm.isEmpty()) return;
        
        System.out.print("Pilih nomor film yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (index < 0 || index >= daftarFilm.size()) {
            System.out.println("Nomor film tidak valid!");
            return;
        }
        
        daftarFilm.remove(index);
        System.out.println("Film berhasil dihapus!");
    }
}