public class User extends Akun {
    
    // Constructor buat bikin object user
    // parameter "user" ini nanti disimpen di field role dari parent class
    public User(int idAkun, String username, String password) {
        super(idAkun, username, password, "user");
    }
    
    // Override method dari parent class, sama persis kyk di Admin
    // Ini contoh polymorphism, tapi ya isinya sama wkwk
    @Override
    public boolean authenticate(String username, String password) {
        return getUsername().equals(username) && getPassword().equals(password);
    }
    
    // Method khusus user, sama kyk admin sebenernya ga kepake
    // Logic nya ada di DatabaseConnector
    public void tambahReview(int idFilm, int rating, String komentar) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
    
    public void editReview(int idReview, int rating, String komentar) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
    
    public void hapusReview(int idReview) {
        // Implementation akan dilakukan dengan DatabaseConnector
    }
}