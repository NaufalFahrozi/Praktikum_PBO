public abstract class Akun {
    private final int idAkun;
    private String username;
    private String password;
    private final String role;
    
    // Constructor buat inisialisasi akun
    public Akun(int idAkun, String username, String password, String role) {
        this.idAkun = idAkun;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getters dan setters
    public int getIdAkun() {
        return idAkun;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    // Method abstract yg harus diimplementasikan di child class
    // Ini contoh polymorphism sih, tapi sebenernya kodenya sama aja wkwkwk
    public abstract boolean authenticate(String username, String password);
    
    // Method lainnya
    @Override
    public String toString() {
        return "Akun [id=" + idAkun + ", username=" + username + ", role=" + role + "]";
    }
}