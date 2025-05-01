import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.InputMismatchException;

public class App implements KatalogManager {
    private static ArrayList<Film> daftarFilm = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    // Static counters untuk statistik
    private static int jumlahFilmKomersial = 0;
    private static int jumlahFilmFestival = 0;
    
    // Static constants untuk validasi
    private static final int TAHUN_MINIMUM = 1900;
    private static final int TAHUN_MAKSIMUM = Calendar.getInstance().get(Calendar.YEAR);

    public static void main(String[] args) {
        App app = new App(); // Membuat instance untuk menggunakan method dari interface

        // Menambahkan data film statis
        tambahDataFilmStatis();
        
        while (true) {
            clearScreen();
            cetakGaris();
            System.out.println("\tCineboxd: Movie Catalog");
            cetakGaris();
            System.out.println("[1] Tambah Film");
            System.out.println("[2] Lihat Daftar Film");
            System.out.println("[3] Edit Film");
            System.out.println("[4] Hapus Film");
            System.out.println("[5] Cari Film");
            System.out.println("[6] Urutkan Film");
            System.out.println("[7] Lihat Statistik");
            System.out.println("[0] Keluar");
            cetakGaris();
            System.out.print("Pilih menu: ");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();
                
                switch (pilihan) {
                    case 1 -> tambahFilm();
                    case 2 -> lihatFilm();
                    case 3 -> editFilm();
                    case 4 -> hapusFilm();
                    case 5 -> {
                        clearScreen();
                        cetakGaris();
                        System.out.println("\tCARI FILM");
                        cetakGaris();
                        System.out.print("Masukkan keyword pencarian: ");
                        String keyword = scanner.nextLine();
                        ArrayList<Film> hasilPencarian = app.cariFilm(keyword);
                        tampilkanHasilPencarian(hasilPencarian, keyword);
                    }
                    case 6 -> {
                        clearScreen();
                        cetakGaris();
                        System.out.println("\tURUTKAN FILM");
                        cetakGaris();
                        System.out.println("Pilih kriteria pengurutan:");
                        System.out.println("[1] Judul");
                        System.out.println("[2] Tahun");
                        System.out.println("[3] Sutradara");
                        System.out.print("Pilihan: ");
                        
                        int kriteria = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (kriteria) {
                            case 1 -> app.urutkanFilm("judul");
                            case 2 -> app.urutkanFilm("tahun");
                            case 3 -> app.urutkanFilm("sutradara");
                            default -> System.out.println("Kriteria tidak valid!");
                        }
                        System.out.println("Film berhasil diurutkan!");
                        lihatFilm();
                    }
                    case 7 -> tampilkanStatistik();
                    case 0 -> {
                        System.out.println("Terima kasih telah menggunakan Cineboxd!");
                        return;
                    }
                    default -> {
                        System.out.println("Pilihan tidak valid, coba lagi!");
                        tunggu(2000);
                    }
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Error: Input harus berupa angka!");
                tunggu(2000);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                tunggu(2000);
            }
        }
    }

    // Method untuk menambahkan data film statis
    private static void tambahDataFilmStatis() {
        // Film Komersial
        FilmKomersial film1 = new FilmKomersial("The Avengers", "Joss Whedon", 2012, 
                              new Genre("Action"), 220000000, 1518812988, "Marvel Studios");
        daftarFilm.add(film1);
        jumlahFilmKomersial++;
        
        FilmKomersial film2 = new FilmKomersial("Inception", "Christopher Nolan", 2010, 
                              new Genre("Sci-Fi"), 160000000, 836800000, "Warner Bros.");
        daftarFilm.add(film2);
        jumlahFilmKomersial++;
        
        FilmKomersial film3 = new FilmKomersial("Parasite", "Bong Joon-ho", 2019, 
                              new Genre("Drama"), 11400000, 263100000, "CJ Entertainment");
        daftarFilm.add(film3);
        jumlahFilmKomersial++;
        
        FilmKomersial film4 = new FilmKomersial("The Dark Knight", "Christopher Nolan", 2008, 
                              new Genre("Action"), 185000000, 1003000000, "Warner Bros.");
        daftarFilm.add(film4);
        jumlahFilmKomersial++;
        
        // Film Festival
        FilmFestival film5 = new FilmFestival("Nomadland", "Chloé Zhao", 2020, 
                             new Genre("Drama"));
        film5.tambahFestival("Venice Film Festival");
        film5.tambahFestival("Toronto International Film Festival");
        film5.tambahPenghargaan("Oscar - Best Picture");
        film5.tambahPenghargaan("Oscar - Best Director");
        daftarFilm.add(film5);
        jumlahFilmFestival++;
        
        FilmFestival film6 = new FilmFestival("Moonlight", "Barry Jenkins", 2016, 
                             new Genre("Drama"));
        film6.tambahFestival("Telluride Film Festival");
        film6.tambahFestival("Toronto International Film Festival");
        film6.tambahPenghargaan("Oscar - Best Picture");
        film6.tambahPenghargaan("Golden Globe - Best Motion Picture");
        daftarFilm.add(film6);
        jumlahFilmFestival++;
        
        FilmFestival film7 = new FilmFestival("The Lighthouse", "Robert Eggers", 2019, 
                             new Genre("Horror"));
        film7.tambahFestival("Cannes Film Festival");
        film7.tambahFestival("Toronto International Film Festival");
        film7.tambahPenghargaan("Critics' Choice - Best Cinematography");
        daftarFilm.add(film7);
        jumlahFilmFestival++;
        
        // Film Indonesia wkwkwk
        FilmKomersial film8 = new FilmKomersial("KKN di Desa Penari", "Awi Suryadi", 2022, 
                              new Genre("Horror"), 10000000, 30000000, "MD Pictures");
        daftarFilm.add(film8);
        jumlahFilmKomersial++;
        
        FilmFestival film9 = new FilmFestival("Yuni", "Kamila Andini", 2021, 
                             new Genre("Drama"));
        film9.tambahFestival("Toronto International Film Festival");
        film9.tambahFestival("Busan International Film Festival");
        film9.tambahPenghargaan("TIFF Platform Prize");
        daftarFilm.add(film9);
        jumlahFilmFestival++;
        
        System.out.println("Berhasil menambahkan " + daftarFilm.size() + " film ke katalog.");
        tunggu(1000);
    }

    // Implementasi method dari interface KatalogManager
    @Override
    public ArrayList<Film> cariFilm(String keyword) {
        ArrayList<Film> hasilPencarian = new ArrayList<>();
        keyword = keyword.toLowerCase();
        
        for (Film film : daftarFilm) {
            if (film.getJudul().toLowerCase().contains(keyword) || 
                film.getSutradara().toLowerCase().contains(keyword) ||
                film.getGenre().getNama().toLowerCase().contains(keyword)) {
                hasilPencarian.add(film);
            }
        }
        
        return hasilPencarian;
    }
    
    @Override
    public void urutkanFilm(String kriteria) {
        switch (kriteria.toLowerCase()) {
            case "judul" -> daftarFilm.sort(Comparator.comparing(Film::getJudul));
            case "tahun" -> daftarFilm.sort(Comparator.comparing(Film::getTahunRilis));
            case "sutradara" -> daftarFilm.sort(Comparator.comparing(Film::getSutradara));
            default -> throw new IllegalArgumentException("Kriteria pengurutan tidak valid");
        }
    }
    
    // Static method untuk validasi input
    public static boolean isValidTahun(int tahun) {
        return tahun >= TAHUN_MINIMUM && tahun <= TAHUN_MAKSIMUM;
    }
    
    public static boolean isValidJudul(String judul) {
        return judul != null && !judul.trim().isEmpty();
    }
    
    // Static method untuk menampilkan statistik
    private static void tampilkanStatistik() {
        clearScreen();
        cetakGaris();
        System.out.println("\tSTATISTIK FILM");
        cetakGaris();
        System.out.println("Total Film: " + daftarFilm.size());
        System.out.println("Film Komersial: " + jumlahFilmKomersial);
        System.out.println("Film Festival: " + jumlahFilmFestival);
        
        if (!daftarFilm.isEmpty()) {
            // Mencari tahun tertua dan terbaru
            int tahunTertua = TAHUN_MAKSIMUM;
            int tahunTerbaru = TAHUN_MINIMUM;
            String judulTertua = "";
            String judulTerbaru = "";
            
            for (Film film : daftarFilm) {
                if (film.getTahunRilis() < tahunTertua) {
                    tahunTertua = film.getTahunRilis();
                    judulTertua = film.getJudul();
                }
                if (film.getTahunRilis() > tahunTerbaru) {
                    tahunTerbaru = film.getTahunRilis();
                    judulTerbaru = film.getJudul();
                }
            }
            
            System.out.println("Film Tertua: " + judulTertua + " (" + tahunTertua + ")");
            System.out.println("Film Terbaru: " + judulTerbaru + " (" + tahunTerbaru + ")");
        }
        
        cetakGaris();
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }
    
    private static void tampilkanHasilPencarian(ArrayList<Film> hasil, String keyword) {
        clearScreen();
        cetakGaris();
        System.out.println("\tHASIL PENCARIAN: \"" + keyword + "\"");
        cetakGaris();
        
        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan film yang sesuai dengan keyword \"" + keyword + "\"");
        } else {
            System.out.println("Ditemukan " + hasil.size() + " film:");
            for (int i = 0; i < hasil.size(); i++) {
                Film film = hasil.get(i);
                System.out.println((i + 1) + ". " + (film instanceof FilmKomersial ? 
                                   ((FilmKomersial)film).getInfoLengkap() : 
                                   ((FilmFestival)film).getInfoLengkap()));
                cetakGaris();
            }
        }
        
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
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
        
        try {
            int jenis = scanner.nextInt();
            scanner.nextLine();
            
            // Validasi jenis film
            if (jenis != 1 && jenis != 2) {
                throw new IllegalArgumentException("Jenis film tidak valid");
            }
            
            System.out.print("Masukkan judul film: ");
            String judul = scanner.nextLine();
            
            // Validasi judul
            if (!isValidJudul(judul)) {
                throw new IllegalArgumentException("Judul film tidak boleh kosong");
            }
            
            System.out.print("Masukkan sutradara: ");
            String sutradara = scanner.nextLine();
            
            // Validasi sutradara
            if (!isValidJudul(sutradara)) {
                throw new IllegalArgumentException("Nama sutradara tidak boleh kosong");
            }
            
            System.out.print("Masukkan tahun rilis: ");
            int tahun = scanner.nextInt();
            scanner.nextLine();
            
            // Validasi tahun
            if (!isValidTahun(tahun)) {
                throw new IllegalArgumentException("Tahun harus antara " + TAHUN_MINIMUM + 
                                                   " dan " + TAHUN_MAKSIMUM);
            }
            
            System.out.print("Masukkan genre: ");
            String genreNama = scanner.nextLine();
            
            // Validasi genre
            if (!isValidJudul(genreNama)) {
                throw new IllegalArgumentException("Genre tidak boleh kosong");
            }
            
            Genre genre = new Genre(genreNama);
    
            if (jenis == 1) {
                System.out.print("Masukkan studio produksi: ");
                String studio = scanner.nextLine();
                
                // Validasi studio
                if (!isValidJudul(studio)) {
                    throw new IllegalArgumentException("Studio produksi tidak boleh kosong");
                }
                
                System.out.print("Masukkan budget produksi ($): ");
                long budget = scanner.nextLong();
                scanner.nextLine();
                
                System.out.print("Masukkan pendapatan ($): ");
                long pendapatan = scanner.nextLong();
                scanner.nextLine();
                
                daftarFilm.add(new FilmKomersial(judul, sutradara, tahun, genre, budget, pendapatan, studio));
                jumlahFilmKomersial++;
            } else {
                FilmFestival film = new FilmFestival(judul, sutradara, tahun, genre);
                
                System.out.println("Tambahkan festival (ketik 'selesai' untuk berhenti): ");
                while (true) {
                    System.out.print("Nama Festival: ");
                    String festival = scanner.nextLine();
                    if (festival.equalsIgnoreCase("selesai")) break;
                    
                    // Validasi nama festival
                    if (!isValidJudul(festival)) {
                        System.out.println("Nama festival tidak boleh kosong. Coba lagi.");
                        continue;
                    }
                    
                    film.tambahFestival(festival);
                }
                
                System.out.println("Tambahkan penghargaan (ketik 'selesai' untuk berhenti): ");
                while (true) {
                    System.out.print("Nama Penghargaan: ");
                    String penghargaan = scanner.nextLine();
                    if (penghargaan.equalsIgnoreCase("selesai")) break;
                    
                    // Validasi nama penghargaan
                    if (!isValidJudul(penghargaan)) {
                        System.out.println("Nama penghargaan tidak boleh kosong. Coba lagi.");
                        continue;
                    }
                    
                    film.tambahPenghargaan(penghargaan);
                }
                
                daftarFilm.add(film);
                jumlahFilmFestival++;
            }
            
            System.out.println("Film berhasil ditambahkan!");
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Error: Input tidak valid, harus berupa angka untuk tahun/budget/pendapatan");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error tidak terduga: " + e.getMessage());
        }
        
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }

    private static void lihatFilm() {
        while (true) {
            clearScreen();
            cetakGaris();
            System.out.println("\tLIHAT DAFTAR FILM");
            cetakGaris();
            
            if (daftarFilm.isEmpty()) {
                System.out.println("Belum ada film yang terdaftar.");
                System.out.println("Tekan Enter untuk kembali ke menu...");
                scanner.nextLine();
                return;
            }
            
            System.out.println("[1] Film Komersial");
            System.out.println("[2] Film Festival");
            System.out.println("[3] Semua Film");
            System.out.println("[0] Kembali ke Menu Utama");
            cetakGaris();
            System.out.print("Pilihan: ");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();
                
                if (pilihan == 0) {
                    return;
                }
                
                ArrayList<Film> filmYangDitampilkan = new ArrayList<>();
                String jenisFilm = "";
                
                switch (pilihan) {
                    case 1 -> {
                        // Menampilkan hanya film komersial
                        for (Film film : daftarFilm) {
                            if (film instanceof FilmKomersial) {
                                filmYangDitampilkan.add(film);
                            }
                        }
                        jenisFilm = "Komersial";
                    }
                    case 2 -> {
                        // Menampilkan hanya film festival
                        for (Film film : daftarFilm) {
                            if (film instanceof FilmFestival) {
                                filmYangDitampilkan.add(film);
                            }
                        }
                        jenisFilm = "Festival";
                    }
                    case 3 -> {
                        // Menampilkan semua film
                        filmYangDitampilkan.addAll(daftarFilm);
                        jenisFilm = "Semua";
                    }
                    default -> {
                        System.out.println("Pilihan tidak valid!");
                        tunggu(1500);
                        continue;
                    }
                }
                
                if (filmYangDitampilkan.isEmpty()) {
                    clearScreen();
                    cetakGaris();
                    System.out.println("\tDaftar Film " + jenisFilm);
                    cetakGaris();
                    System.out.println("Tidak ada film " + jenisFilm.toLowerCase() + " yang terdaftar.");
                    System.out.println("Tekan Enter untuk kembali...");
                    scanner.nextLine();
                    continue;
                }
                
                // Menampilkan daftar judul film dengan tahun
                while (true) {
                    clearScreen();
                    cetakGaris();
                    System.out.println("\tDaftar Film " + jenisFilm);
                    cetakGaris();
                    
                    for (int i = 0; i < filmYangDitampilkan.size(); i++) {
                        Film film = filmYangDitampilkan.get(i);
                        System.out.println((i + 1) + ". " + film.getJudul() + " (" + film.getTahunRilis() + ")");
                    }
                    
                    cetakGaris();
                    System.out.print("Pilih nomor film untuk melihat detail (0 untuk kembali): ");
                    
                    try {
                        int indexFilm = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (indexFilm == 0) {
                            break;
                        }
                        
                        if (indexFilm < 1 || indexFilm > filmYangDitampilkan.size()) {
                            System.out.println("Nomor film tidak valid!");
                            tunggu(1500);
                            continue;
                        }
                        
                        // Menampilkan detail film yang dipilih
                        Film filmDipilih = filmYangDitampilkan.get(indexFilm - 1);
                        clearScreen();
                        cetakGaris();
                        System.out.println("\tDETAIL FILM");
                        cetakGaris();
                        
                        if (filmDipilih instanceof FilmKomersial) {
                            System.out.println(((FilmKomersial) filmDipilih).getInfoLengkap());
                        } else if (filmDipilih instanceof FilmFestival) {
                            System.out.println(((FilmFestival) filmDipilih).getInfoLengkap());
                        }
                        
                        cetakGaris();
                        System.out.println("Tekan Enter untuk kembali ke daftar film...");
                        scanner.nextLine();
                        
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Error: Input harus berupa angka!");
                        tunggu(1500);
                    }
                }
                
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Error: Input harus berupa angka!");
                tunggu(1500);
            }
        }
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
            System.out.println((i + 1) + ". " + film.getJudul() + " (" + film.getTahunRilis() + ")");
        }
        
        cetakGaris();
        System.out.print("Pilih nomor film yang ingin diedit (0 untuk kembali): ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (index == -1) {
                return;
            }
            
            if (index < 0 || index >= daftarFilm.size()) {
                throw new IllegalArgumentException("Nomor film tidak valid!");
            }
    
            Film film = daftarFilm.get(index);
            
            System.out.print("Masukkan judul baru: ");
            String judul = scanner.nextLine();
            if (isValidJudul(judul)) {
                film.setJudul(judul);
            } else {
                throw new IllegalArgumentException("Judul tidak boleh kosong");
            }
            
            System.out.print("Masukkan sutradara baru: ");
            String sutradara = scanner.nextLine();
            if (isValidJudul(sutradara)) {
                film.setSutradara(sutradara);
            } else {
                throw new IllegalArgumentException("Nama sutradara tidak boleh kosong");
            }
            
            System.out.print("Masukkan tahun rilis baru: ");
            int tahun = scanner.nextInt();
            scanner.nextLine();
            
            if (isValidTahun(tahun)) {
                film.setTahunRilis(tahun);
            } else {
                throw new IllegalArgumentException("Tahun harus antara " + TAHUN_MINIMUM + 
                                                 " dan " + TAHUN_MAKSIMUM);
            }
            
            if (film instanceof FilmKomersial) {
                FilmKomersial fk = (FilmKomersial) film;
                System.out.print("Masukkan studio produksi baru: ");
                String studio = scanner.nextLine();
                
                if (isValidJudul(studio)) {
                    fk.setStudioProduksi(studio);
                } else {
                    throw new IllegalArgumentException("Studio produksi tidak boleh kosong");
                }
                
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
                    
                    if (!isValidJudul(festival)) {
                        System.out.println("Nama festival tidak boleh kosong. Coba lagi.");
                        continue;
                    }
                    
                    ff.tambahFestival(festival);
                }
                
                System.out.println("Edit penghargaan (hapus semua dan tambahkan yang baru)");
                ff.getPenghargaan().clear();
                System.out.println("Tambahkan penghargaan (ketik 'selesai' untuk berhenti): ");
                while (true) {
                    System.out.print("Nama Penghargaan: ");
                    String penghargaan = scanner.nextLine();
                    if (penghargaan.equalsIgnoreCase("selesai")) break;
                    
                    if (!isValidJudul(penghargaan)) {
                        System.out.println("Nama penghargaan tidak boleh kosong. Coba lagi.");
                        continue;
                    }
                    
                    ff.tambahPenghargaan(penghargaan);
                }
            }
            
            System.out.println("Film berhasil diperbarui!");
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Error: Input tidak valid, harus berupa angka untuk tahun/budget/pendapatan");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error tidak terduga: " + e.getMessage());
        }
        
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
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul() + " (" + daftarFilm.get(i).getTahunRilis() + ")");
        }
        
        cetakGaris();
        System.out.print("Pilih nomor film yang ingin dihapus (0 untuk kembali): ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (index == -1) {
                return;
            }
            
            if (index < 0 || index >= daftarFilm.size()) {
                throw new IllegalArgumentException("Nomor film tidak valid!");
            }
            
            String judulFilm = daftarFilm.get(index).getJudul();
            
            // Konfirmasi penghapusan
            System.out.print("Anda yakin ingin menghapus film \"" + judulFilm + "\"? (y/n): ");
            String konfirmasi = scanner.nextLine();
            
            if (konfirmasi.equalsIgnoreCase("y")) {
                Film filmDihapus = daftarFilm.remove(index);

                if (filmDihapus instanceof FilmKomersial) {
                    jumlahFilmKomersial--;
                } else if (filmDihapus instanceof FilmFestival) {
                    jumlahFilmFestival--;
                }
                
                System.out.println("Film \"" + judulFilm + "\" berhasil dihapus!");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Error: Input tidak valid, harus berupa angka");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error tidak terduga: " + e.getMessage());
        }
        
        System.out.println("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }
}