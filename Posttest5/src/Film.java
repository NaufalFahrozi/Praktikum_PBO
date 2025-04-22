public abstract class Film {
    private String judul;
    private String sutradara;
    private int tahunRilis;
    private final Genre genre;

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

    public abstract String getJenisPendistribusian();

    public final String toString() {
        return "Judul: " + judul + 
               "\nSutradara: " + sutradara + 
               "\nTahun: " + tahunRilis + 
               "\nGenre: " + genre.getNama();
    }
}