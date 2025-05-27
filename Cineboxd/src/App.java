import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static Akun currentAkun = null;
    
    public static void main(String[] args) {
        try {
            // Test database connection
            clearScreen();
            DatabaseConnector.getConnection();
            System.out.println("Database connection successful!");
            
            // Loop utama program, bakal jalan terus sampe user exit atau error
            while (true) {
                if (currentAkun == null) {
                    showAuthMenu();
                } else if (currentAkun instanceof Admin) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        } catch (SQLException e) {
            clearScreen();
            System.out.println("Database error: " + e.getMessage());
        } finally {
            DatabaseConnector.closeConnection();
        }
    }
    
    // Method buat nge-clear layar console, keren sih ini wkwk. gimmick aowkowkowkwo
    // Pake ini biar UI nya ga numpuk2 kalo pindah menu
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Kalo gagal clear screen ya udah spam new line aja wkwk
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }
    
    // Buat bikin header di menu, biar UI nya kece gitu. gimmick doang
    private static void displayHeader(String title) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.printf("║%s║\n", centerText(title, 42));
        System.out.println("╚══════════════════════════════════════════╝");
    }
    
    // Ngitung padding buat teks tengah, males sih sebenernya bikin ginian
    // tapi biar UI nya bagus ya kudu gini lah
    private static String centerText(String text, int width) {
        int padding = width - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        return " ".repeat(paddingLeft) + text + " ".repeat(paddingRight);
    }
    
    // Nah ini nih fungsi penting buat tampilin daftar film
    // Gw jadiin satu fungsi gini biar ga ngulangin kode mulu
    private static Map<Integer, Integer> displayFilmList(List<Film> films) {
        if (films.isEmpty()) {
            System.out.println("\nBelum ada film yang tersedia.");
            waitForEnter();
            return null;
        }
        
        // Mulai dari 1 biar user ga bingung
        // Kalo mulai dari 0 ntar dikira array wkwk
        int index = 1;
        Map<Integer, Integer> displayIndexToFilmId = new HashMap<>();
        
        for (Film film : films) {
            System.out.printf("[%d] %s (%d)\n", index, film.getTitle(), film.getYear());
            displayIndexToFilmId.put(index, film.getIdFilm());
            index++;
        }
        
        return displayIndexToFilmId;
    }
    
    // Fungsi gimmick simpel biar ga nulis kode yang sama berulang-ulang
    private static void waitForEnter() {
        System.out.println("\nTekan Enter untuk kembali...");
        scanner.nextLine();
        clearScreen();
    }
    
    // Menu sign in/register
    private static void showAuthMenu() {
        displayHeader("C I N E B O X D");
        System.out.println("           Aplikasi Review Film           ");
        System.out.println();
        System.out.println("[1] Sign In");
        System.out.println("[2] Register");
        System.out.println("[0] Exit");
        System.out.println();
        System.out.print("Pilih menu: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            // Pake arrow operator biar keren, lebih simple juga
            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleRegister();
                case 0 -> {
                    System.out.println("\nTerima kasih telah menggunakan Cineboxd!");
                    DatabaseConnector.closeConnection();
                    System.exit(0);
                }
                default -> {
                    System.out.println("\nPilihan tidak valid!");
                    waitForEnter();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInput tidak valid. Harap masukkan angka.");
            waitForEnter();
        } catch (SQLException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
            waitForEnter();
        }
    }
    
    private static void handleLogin() throws SQLException {
        clearScreen();
        displayHeader("SIGN IN");
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        currentAkun = DatabaseConnector.login(username, password);
        
        if (currentAkun != null) {
            System.out.println("\nSign in berhasil! Selamat datang, " + currentAkun.getUsername() + "!");
            waitForEnter();
        } else {
            System.out.println("\nSign in gagal. Username atau password salah.");
            waitForEnter();
        }
    }
    
    private static void handleRegister() throws SQLException {
        clearScreen();
        displayHeader("REGISTER");
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        if (username.trim().isEmpty()) {
            System.out.println("\nUsername tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (password.trim().isEmpty()) {
            System.out.println("\nPassword tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        boolean success = DatabaseConnector.register(username, password);
        
        if (success) {
            System.out.println("\nRegistrasi berhasil! Silakan Sign in.");
        } else {
            System.out.println("\nRegistrasi gagal. Username mungkin sudah digunakan.");
        }
        waitForEnter();
    }
    
    // Menu admin, beda sama menu user biasa
    // Admin bisa manage film tp ga bisa ngasih review
    private static void showAdminMenu() {
        displayHeader("ADMIN DASHBOARD");
        System.out.println();
        System.out.println("[1] Lihat Film");
        System.out.println("[2] Tambah Film");
        System.out.println("[3] Edit Film");
        System.out.println("[4] Hapus Film");
        System.out.println("[0] Sign Out");
        System.out.println();
        System.out.print("Pilih menu: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1 -> lihatFilm();
                case 2 -> tambahFilm();
                case 3 -> editFilm();
                case 4 -> hapusFilm();
                case 0 -> {
                    System.out.print("\nApakah Anda yakin ingin keluar? (y/n): ");
                    if (scanner.nextLine().toLowerCase().equals("y")) {
                        currentAkun = null;
                        System.out.println("Sign out berhasil.");
                    }
                    waitForEnter();
                }
                default -> {
                    System.out.println("\nPilihan tidak valid!");
                    waitForEnter();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInput tidak valid. Harap masukkan angka.");
            waitForEnter();
        } catch (SQLException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
            waitForEnter();
        }
    }

    // Menu user biasa, bisa review film tapi ga bisa nambah/edit/hapus film
    private static void showUserMenu() {
        displayHeader("MENU UTAMA");
        System.out.println();
        System.out.println("[1] Lihat Film");
        System.out.println("[2] Tambah Review");
        System.out.println("[3] Edit Review");
        System.out.println("[4] Hapus Review");
        System.out.println("[0] Sign Out");
        System.out.println();
        System.out.print("Pilih menu: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1 -> lihatFilm();
                case 2 -> tambahReview();
                case 3 -> editReview();
                case 4 -> hapusReview();
                case 0 -> {
                    System.out.print("\nApakah Anda yakin ingin keluar? (y/n): ");
                    if (scanner.nextLine().toLowerCase().equals("y")) {
                        currentAkun = null;
                        System.out.println("Sign out berhasil.");
                    }
                    waitForEnter();
                    
                }
                default -> {
                    System.out.println("\nPilihan tidak valid!");
                    waitForEnter();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInput tidak valid. Harap masukkan angka.");
            waitForEnter();
        } catch (SQLException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
            waitForEnter();
        }
    }
    
    // Fungsi lihat film, diakses admin & user
    private static void lihatFilm() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("DAFTAR FILM");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        // Nah ini nih bagian yang paling ribet
        // Kita harus translate dari index tampilan ke id film asli di database
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        clearScreen();
        // Tampilkan detail film
        displayHeader("DETAIL FILM");
        System.out.println("Judul     : " + film.getTitle());
        System.out.println("Tahun     : " + film.getYear());
        System.out.println("Sutradara : " + film.getDirector());
        System.out.println("Genre     : " + film.getGenre());
        System.out.println("\nSinopsis:");
        System.out.println(film.getSynopsis());
        
        // Tampilkan rating rata-rata
        double avgRating = DatabaseConnector.getAverageRating(idFilm);
        if (avgRating > 0) {
            System.out.printf("\nRating Rata-rata: %.1f/10\n", avgRating);
        } else {
            System.out.println("\nRating Rata-rata: Belum ada rating");
        }
        
        // Tampilkan review
        List<Review> reviews = DatabaseConnector.getReviewsByFilmId(idFilm);
        System.out.println("\n===== REVIEW =====");
        
        if (reviews.isEmpty()) {
            System.out.println("Belum ada review untuk film ini.");
        } else {
            for (Review review : reviews) {
                System.out.println("\nFrom: " + review.getUsername());
                System.out.println("Rating: " + review.getRating() + "/10");
                System.out.println("Komentar: " + review.getKomentar());
                System.out.println("----------------------------");
            }
        }
        
        waitForEnter();
    }
    
    // Fungsi admin buat tambah film baru ke database
    private static void tambahFilm() throws SQLException {
        clearScreen();
        displayHeader("TAMBAH FILM");
        
        System.out.print("Judul film: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("Judul tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        // Validasi tahun rilis
        // Film pertama tahun 1878, jadi batas bawahnya ya segitu
        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.print("Tahun rilis (1878-2025): ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (Film.isValidYear(year)) {
                    validYear = true;
                } else {
                    System.out.println("Tahun rilis harus antara 1878-2025!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tahun tidak valid!");
            }
        }
        
        System.out.print("Sutradara: ");
        String director = scanner.nextLine();
        if (director.trim().isEmpty()) {
            System.out.println("Sutradara tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        if (genre.trim().isEmpty()) {
            System.out.println("Genre tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        System.out.print("Sinopsis: ");
        String synopsis = scanner.nextLine();
        if (synopsis.trim().isEmpty()) {
            System.out.println("Sinopsis tidak boleh kosong!");
            waitForEnter();
            return;
        }

        System.out.print("\nApakah Anda yakin ingin menambahkan film ini? (y/n): ");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            try {
                boolean success = DatabaseConnector.tambahFilm(title, year, director, genre, synopsis);
                if (success) {
                    System.out.println("\nFilm berhasil ditambahkan!");
                } else {
                    System.out.println("\nGagal menambahkan film.");
                }
            } catch (SQLException e) {
                // Cek apakah error disebabkan oleh duplikat film
                if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("unique_film")) {
                    System.out.println("\nGagal menambahkan film. Film dengan judul dan tahun yang sama sudah ada.");
                } else {
                    // Jika bukan karena duplikat, lempar pesan error asli dari query database yakannn
                    throw e;
                }
            }
        } else {
            System.out.println("\nPenambahan film dibatalkan.");
        }
        
        waitForEnter();
    }
    
    // Fungsi admin buat edit film yang udah ada
    private static void editFilm() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("EDIT FILM");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film yang akan diedit (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        clearScreen();
        displayHeader("EDIT DETAIL FILM");
        System.out.println("Film: " + film.getTitle() + " (" + film.getYear() + ")");
        System.out.println("\n(Biarkan kosong jika tidak ingin mengubah)");
        
        System.out.print("Judul baru: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            title = film.getTitle();
        }
        
        int year = film.getYear();
        System.out.print("Tahun rilis baru (1878-2025): ");
        String yearInput = scanner.nextLine();
        if (!yearInput.trim().isEmpty()) {
            try {
                int newYear = Integer.parseInt(yearInput);
                if (Film.isValidYear(newYear)) {
                    year = newYear;
                } else {
                    System.out.println("Tahun rilis harus antara 1878-2025! Menggunakan tahun lama.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tahun tidak valid! Menggunakan tahun lama.");
            }
        }
        
        System.out.print("Sutradara baru: ");
        String director = scanner.nextLine();
        if (director.trim().isEmpty()) {
            director = film.getDirector();
        }
        
        System.out.print("Genre baru: ");
        String genre = scanner.nextLine();
        if (genre.trim().isEmpty()) {
            genre = film.getGenre();
        }
        
        System.out.print("Sinopsis baru: ");
        String synopsis = scanner.nextLine();
        if (synopsis.trim().isEmpty()) {
            synopsis = film.getSynopsis();
        }

        System.out.print("\nApakah Anda yakin ingin menyimpan perubahan?(y/n): ");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            try {
                boolean success = DatabaseConnector.editFilm(idFilm, title, year, director, genre, synopsis);
                if (success) {
                    System.out.println("\nFilm berhasil diperbarui!");
                } else {
                    System.out.println("\nGagal memperbarui film.");
                }
            } catch (SQLException e) {
                // Cek apakah error disebabkan oleh duplikat film
                if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("unique_film")) {
                    System.out.println("\nGagal memperbarui film. Film dengan judul dan tahun yang sama sudah ada.");
                } else {
                    // Jika bukan karena duplikat, lempar pesan error asli dari query database yakannn
                    throw e;
                }
            }
        } else {
            System.out.println("\nPe dibatalkan.");
        }
        
        waitForEnter();
    }
    
    // Fungsi admin buat hapus film
    // Hati-hati nih, kalo film dihapus semua reviewnya jg ikut ilang
    private static void hapusFilm() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("HAPUS FILM");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film yang akan dihapus (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        clearScreen();
        displayHeader("KONFIRMASI HAPUS");
        System.out.println("Film: " + film.getTitle() + " (" + film.getYear() + ")");
        System.out.println("Sutradara: " + film.getDirector());
        System.out.println("Genre: " + film.getGenre());
        
        // Double confirmation, supaya ga keapus gara2 mis-click
        System.out.print("\nAPAKAH ANDA YAKIN INGIN MENGHAPUS FILM INI? (y/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        
        if (confirm.equals("y")) {
            boolean success = DatabaseConnector.hapusFilm(idFilm);
            if (success) {
                System.out.println("\nFilm berhasil dihapus!");
            } else {
                System.out.println("\nGagal menghapus film.");
            }
        } else {
            System.out.println("\nPenghapusan film dibatalkan.");
        }
        
        waitForEnter();
    }
    
    // User bisa tambahin review buat film
    private static void tambahReview() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("TAMBAH REVIEW");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film untuk direview (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        // Cek dulu udah pernah review belum
        // 1 user cuma boleh kasih 1 review per film
        if (DatabaseConnector.hasUserReviewed(currentAkun.getIdAkun(), idFilm)) {
            System.out.println("\nAnda sudah memberi review untuk film ini.");
            System.out.println("Silakan edit review yang sudah ada.");
            waitForEnter();
            return;
        }
        
        clearScreen();
        displayHeader("TAMBAH REVIEW FILM");
        System.out.println("Film: " + film.getTitle() + " (" + film.getYear() + ")");
        
        // Input rating 1-10
        int rating = 0;
        boolean validRating = false;
        while (!validRating) {
            System.out.print("\nRating (1-10): ");
            try {
                rating = Integer.parseInt(scanner.nextLine());
                if (Review.isValidRating(rating)) {
                    validRating = true;
                } else {
                    System.out.println("Rating harus antara 1-10!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input rating tidak valid!");
            }
        }
        
        System.out.print("Komentar: ");
        String komentar = scanner.nextLine();
        if (komentar.trim().isEmpty()) {
            System.out.println("Komentar tidak boleh kosong!");
            waitForEnter();
            return;
        }
        
        System.out.print("\nApakah Anda yakin ingin menambahkan review ini? (y/n): ");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            boolean success = DatabaseConnector.tambahReview(idFilm, currentAkun.getIdAkun(), rating, komentar);
            if (success) {
                System.out.println("\nReview berhasil ditambahkan!");
            } else {
                System.out.println("\nGagal menambahkan review.");
            }
        } else {
            System.out.println("\nPenambahan review dibatalkan.");
        }
        
        waitForEnter();
    }
    
    // User bisa edit reviewnya sendiri
    // Tapi ga bisa edit review orang lain, itu mah namanya troll wkwk
    private static void editReview() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("EDIT REVIEW");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film yang reviewnya akan diedit (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        // Cek dulu apakah user sudah pernah review film ini
        Review review = DatabaseConnector.getReviewByFilmAndUser(idFilm, currentAkun.getIdAkun());
        if (review == null) {
            System.out.println("\nAnda belum memberi review untuk film ini.");
            waitForEnter();
            return;
        }
        
        clearScreen();
        displayHeader("EDIT REVIEW");
        System.out.println("Film: " + film.getTitle() + " (" + film.getYear() + ")");
        System.out.println("\nReview Anda saat ini:");
        System.out.println("Rating: " + review.getRating() + "/10");
        System.out.println("Komentar: " + review.getKomentar());
        System.out.println("\n(Biarkan kosong jika tidak ingin mengubah)");
        
        int rating = review.getRating();
        boolean validRating = false;
        while (!validRating) {
            System.out.print("\nRating baru (1-10): ");
            try {
                String ratingInput = scanner.nextLine();
                if (ratingInput.trim().isEmpty()) {
                    validRating = true;
                } else {
                    rating = Integer.parseInt(ratingInput);
                    if (Review.isValidRating(rating)) {
                        validRating = true;
                    } else {
                        System.out.println("Rating harus antara 1-10!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Input rating tidak valid!");
            }
        }
        
        System.out.print("Komentar baru: ");
        String komentar = scanner.nextLine();
        if (komentar.trim().isEmpty()) {
            komentar = review.getKomentar();
        }
        
        System.out.print("\nApakah Anda yakin ingin menyimpan perubahan? (y/n): ");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            boolean success = DatabaseConnector.editReview(review.getIdReview(), rating, komentar);
            if (success) {
                System.out.println("\nReview berhasil diperbarui!");
            } else {
                System.out.println("\nGagal memperbarui review.");
            }
        } else {
            System.out.println("\nPerubahan dibatalkan.");
        }
        
        waitForEnter();
    }
    
    // User bisa hapus reviewnya sendiri
    // Buat kalo mau ganti pendapat atau salah review film wkwk
    private static void hapusReview() throws SQLException {
        clearScreen();
        List<Film> films = DatabaseConnector.getAllFilms();
        
        displayHeader("HAPUS REVIEW");
        
        Map<Integer, Integer> displayIndexToFilmId = displayFilmList(films);
        if (displayIndexToFilmId == null) {
            return;
        }
        
        System.out.print("\nPilih film yang reviewnya akan dihapus (0 untuk kembali): ");
        int displayIndex;
        
        try {
            displayIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Kembali ke menu utama.");
            waitForEnter();
            return;
        }
        
        if (displayIndex == 0) {
            clearScreen();
            return;
        }
        
        if (!displayIndexToFilmId.containsKey(displayIndex)) {
            System.out.println("Film tidak ditemukan!");
            waitForEnter();
            return;
        }
        
        int idFilm = displayIndexToFilmId.get(displayIndex);
        Film film = DatabaseConnector.getFilmById(idFilm);
        
        Review review = DatabaseConnector.getReviewByFilmAndUser(idFilm, currentAkun.getIdAkun());
        if (review == null) {
            System.out.println("\nAnda belum memberi review untuk film ini.");
            waitForEnter();
            return;
        }
        
        clearScreen();
        displayHeader("KONFIRMASI HAPUS");
        System.out.println("Film: " + film.getTitle() + " (" + film.getYear() + ")");
        System.out.println("\nReview Anda yang akan dihapus:");
        System.out.println("Rating: " + review.getRating() + "/10");
        System.out.println("Komentar: " + review.getKomentar());
        
        System.out.print("\nAPAKAH ANDA YAKIN INGIN MENGHAPUS REVIEW INI? (y/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        
        if (confirm.equals("y")) {
            boolean success = DatabaseConnector.hapusReview(review.getIdReview());
            if (success) {
                System.out.println("\nReview berhasil dihapus!");
            } else {
                System.out.println("\nGagal menghapus review.");
            }
        } else {
            System.out.println("\nPenghapusan review dibatalkan.");
        }
        
        waitForEnter();
    }
}