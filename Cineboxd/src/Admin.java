public final class Admin extends Akun {
    
    // Constructor buat bikin object admin
    // parameter "admin" ini nanti disimpen di field role dari parent class
    public Admin(int idAkun, String username, String password) {
        super(idAkun, username, password, "admin");
    }
    
    // Override method dari parent class
    @Override
    public boolean authenticate(String username, String password) {
        return getUsername().equals(username) && getPassword().equals(password);
    }
    
    // Method khusus admin, sebenernya ga kepake sih karena logic nya di DatabaseConnector
    // Cuman buat nunjukin konsep OOP aja aowkowkokwwo
    public void tambahFilm(String title, int year, String director, String genre, String synopsis) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
    
    public void editFilm(int idFilm, String title, int year, String director, String genre, String synopsis) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
    
    public void hapusFilm(int idFilm) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
}