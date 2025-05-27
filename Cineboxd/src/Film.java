public class Film {
    private final int idFilm;
    private String title;
    private int year;
    private String director;
    private String genre;
    private String synopsis;
    
    // Constructor utama buat bikin object film
    public Film(int idFilm, String title, int year, String director, String genre, String synopsis) {
        this.idFilm = idFilm;
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
    }
    
    // Getters dan setters buat akses private fields
    // Ini contoh encapsulation, fields nya private tapi bisa diakses lewat method
    public int getIdFilm() {
        return idFilm;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getSynopsis() {
        return synopsis;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    // Override toString biar enak kalo mau print
    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
    
    // Validasi tahun film, film pertama tahun 1878
    // Ini ceritanya "Sallie Gardner at a Gallop" by Eadweard Muybridge
    public static boolean isValidYear(int year) {
        return year >= 1878 && year <= 2025;
    }
}