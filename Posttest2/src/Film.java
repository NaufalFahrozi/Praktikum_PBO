public class Film {
    private String judul;
    private String sutradara;
    private int tahunRilis;
    private Genre genre;

    public Film(String judul, String sutradara, int tahunRilis, Genre genre) {
        this.judul = judul;
        this.sutradara = sutradara;
        this.tahunRilis = tahunRilis;
        this.genre = genre;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getSutradara() {
        return sutradara;
    }
    
    public void setSutradara(String sutradara) {
        this.sutradara = sutradara;
    }
    
    public int getTahunRilis() {
        return tahunRilis;
    }

    public void setTahunRilis(int tahunRilis) {
        this.tahunRilis = tahunRilis;
    }
    
    protected Genre getGenre() {
        return genre;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "Judul: " + judul + " | Sutradara: " + sutradara + " | Tahun: " + tahunRilis + " | Genre: " + genre.getNama();
    }
}