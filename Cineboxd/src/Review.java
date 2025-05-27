public class Review {
    private int idReview;
    private int idFilm;
    private int idAkun;
    private int rating;
    private String komentar;
    private String username; // Field tambahan untuk tampilan
    
    // Constructor dasar
    public Review(int idReview, int idFilm, int idAkun, int rating, String komentar) {
        this.idReview = idReview;
        this.idFilm = idFilm;
        this.idAkun = idAkun;
        this.rating = rating;
        this.komentar = komentar;
    }
    
    // Overload constructor dengan tambahan username
    // Ini biar gampang nampilinya, ga perlu query lagi
    public Review(int idReview, int idFilm, int idAkun, int rating, String komentar, String username) {
        this(idReview, idFilm, idAkun, rating, komentar);
        this.username = username;
    }
    
    // Getters dan setters buat mengakses private fields
    public int getIdReview() {
        return idReview;
    }
    
    public int getIdFilm() {
        return idFilm;
    }
    
    public int getIdAkun() {
        return idAkun;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getKomentar() {
        return komentar;
    }
    
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Validasi rating 1-10
    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 10;
    }
    
    // Override toString buat debugging atau display
    @Override
    public String toString() {
        return "Review by " + username + ": " + rating + "/10 - " + komentar;
    }
}