public final class FilmKomersial extends Film {
    private long budget;
    private long pendapatan;
    private String studioProduksi;

    public FilmKomersial(String judul, String sutradara, int tahunRilis, Genre genre, 
                        long budget, long pendapatan, String studioProduksi) {
        super(judul, sutradara, tahunRilis, genre);
        this.budget = budget;
        this.pendapatan = pendapatan;
        this.studioProduksi = studioProduksi;
    }

    public FilmKomersial(String judul, String sutradara, int tahunRilis, Genre genre, 
                        String studioProduksi) {
        this(judul, sutradara, tahunRilis, genre, 0, 0, studioProduksi);
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(long pendapatan) {
        this.pendapatan = pendapatan;
    }

    public String getStudioProduksi() {
        return studioProduksi;
    }

    public void setStudioProduksi(String studioProduksi) {
        this.studioProduksi = studioProduksi;
    }

    public long hitungKeuntungan() {
        return pendapatan - budget;
    }

    @Override
    public String getJenisPendistribusian() {
        return "Bioskop/Streaming";
    }
    
    public String getInfoLengkap() {
        return "[Film Komersial]\n" + toString() + 
               "\nStudio: " + studioProduksi + 
               "\nBudget: $" + budget + 
               "\nPendapatan: $" + pendapatan;
    }
}