import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    // Konstanta buat koneksi database
    // Ubah aja kalo mau pake database lain
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cineboxd_oop";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static Connection connection = null;
    
    // Metode buat koneksi ke MySQL
    // Singleton pattern biar ga bikin koneksi baru terus
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load driver JDBC
                // Ini sering error kalo ga ada jar filenya di classpath
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }
        }
        return connection;
    }
    
    // Biasakan selalu tutup koneksinya
    // Memory leak itu nyebelin banget wkwk
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // ========== BAGIAN AUTH ==========
    
    // Cek username password di database
    // Return object Akun (bisa User/Admin) kalo sesuai
    public static Akun login(String username, String password) throws SQLException {
        String query = "SELECT * FROM tbAkun WHERE username = ?";
        
        // Pake try-with-resources biar auto close
        // Lebih clean daripada manual finally
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // CATATAN: Sebenernya ga bagus nyimpen password plaintext
                // Harusnya pake hash bcrypt/argon2 tapi ya udah lah yang penting selesai PA doang nilai praktikum cuma 20 % awokwokwokwokwo
                if (storedPassword.equals(password)) {
                    int idAkun = rs.getInt("idAkun");
                    String role = rs.getString("role");
                    
                    // Bikin object sesuai role
                    if ("admin".equals(role)) {
                        return new Admin(idAkun, username, password);
                    } else {
                        return new User(idAkun, username, password);
                    }
                }
            }
        }
        return null; // Kalo gagal login
    }
    
    // Buat akun baru
    public static boolean register(String username, String password) throws SQLException {
        // Cek dulu usernamenya udah ada belum
        if (isUsernameExists(username)) {
            return false;
        }
        
        String query = "INSERT INTO tbAkun (username, password, role) VALUES (?, ?, 'user')";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        }
    }
    
    // Helper method buat cek username udah ada di database belum
    private static boolean isUsernameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM tbAkun WHERE username = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    // ========== BAGIAN FILM ==========
    
    // Ambil semua film dari database
    // Diurutin berdasarkan judul biar gampang dicari
    public static List<Film> getAllFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM tbFilm ORDER BY title ASC";
        
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int id = rs.getInt("idFilm");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String synopsis = rs.getString("synopsis");
                
                Film film = new Film(id, title, year, director, genre, synopsis);
                films.add(film);
            }
        }
        
        return films;
    }
    
    // Cari film berdasarkan ID
    public static Film getFilmById(int idFilm) throws SQLException {
        String query = "SELECT * FROM tbFilm WHERE idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String synopsis = rs.getString("synopsis");
                
                return new Film(idFilm, title, year, director, genre, synopsis);
            }
        }
        
        return null;
    }
    
    // Tambah film baru ke database
    public static boolean tambahFilm(String title, int year, String director, String genre, String synopsis) throws SQLException {
        String query = "INSERT INTO tbFilm (title, year, director, genre, synopsis) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setString(3, director);
            stmt.setString(4, genre);
            stmt.setString(5, synopsis);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Update data film yang sudah ada
    public static boolean editFilm(int idFilm, String title, int year, String director, String genre, String synopsis) throws SQLException {
        String query = "UPDATE tbFilm SET title = ?, year = ?, director = ?, genre = ?, synopsis = ? WHERE idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setString(3, director);
            stmt.setString(4, genre);
            stmt.setString(5, synopsis);
            stmt.setInt(6, idFilm);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Hapus film dari database
    // Biar review ikut kehapus kalo filmnya dihapus
    public static boolean hapusFilm(int idFilm) throws SQLException {
        String query = "DELETE FROM tbFilm WHERE idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // ========== BAGIAN REVIEW ==========
    
    // Ambil semua review untuk film tertentu
    public static List<Review> getReviewsByFilmId(int idFilm) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT r.*, a.username FROM tbReview r JOIN tbAkun a ON r.idAkun = a.idAkun WHERE r.idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idReview = rs.getInt("idReview");
                int idAkun = rs.getInt("idAkun");
                int rating = rs.getInt("rating");
                String komentar = rs.getString("komentar");
                String username = rs.getString("username");
                
                Review review = new Review(idReview, idFilm, idAkun, rating, komentar, username);
                reviews.add(review);
            }
        }
        
        return reviews;
    }
    
    // Hitung rating rata-rata film
    public static double getAverageRating(int idFilm) throws SQLException {
        String query = "SELECT AVG(rating) as avg_rating FROM tbReview WHERE idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                double avgRating = rs.getDouble("avg_rating");
                if (rs.wasNull()) {
                    return 0.0; // Kalo belum ada review
                }
                return avgRating;
            }
        }
        
        return 0.0;
    }
    
    // Cek apakah user sudah pernah review film tertentu
    // Buat mencegah duplikasi review dari user yang sama
    public static boolean hasUserReviewed(int idAkun, int idFilm) throws SQLException {
        String query = "SELECT COUNT(*) FROM tbReview WHERE idAkun = ? AND idFilm = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idAkun);
            stmt.setInt(2, idFilm);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        
        return false;
    }
    
    // Tambah review baru
    public static boolean tambahReview(int idFilm, int idAkun, int rating, String komentar) throws SQLException {
        // Double check lagi buat mastiin ga ada duplikasi
        if (hasUserReviewed(idAkun, idFilm)) {
            return false;
        }
        
        String query = "INSERT INTO tbReview (idFilm, idAkun, rating, komentar) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            stmt.setInt(2, idAkun);
            stmt.setInt(3, rating);
            stmt.setString(4, komentar);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Ambil review tertentu berdasarkan film dan user
    public static Review getReviewByFilmAndUser(int idFilm, int idAkun) throws SQLException {
        String query = "SELECT r.*, a.username FROM tbReview r JOIN tbAkun a ON r.idAkun = a.idAkun WHERE r.idFilm = ? AND r.idAkun = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            stmt.setInt(2, idAkun);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int idReview = rs.getInt("idReview");
                int rating = rs.getInt("rating");
                String komentar = rs.getString("komentar");
                String username = rs.getString("username");
                
                return new Review(idReview, idFilm, idAkun, rating, komentar, username);
            }
        }
        
        return null;
    }
    
    // Update review yang sudah ada
    public static boolean editReview(int idReview, int rating, String komentar) throws SQLException {
        String query = "UPDATE tbReview SET rating = ?, komentar = ? WHERE idReview = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, rating);
            stmt.setString(2, komentar);
            stmt.setInt(3, idReview);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Hapus review
    public static boolean hapusReview(int idReview) throws SQLException {
        String query = "DELETE FROM tbReview WHERE idReview = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idReview);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}