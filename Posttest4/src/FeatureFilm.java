public class FeatureFilm extends Film {
    private int durasi; // dalam menit

    public FeatureFilm(String judul, String sutradara, int tahunRilis, Genre genre, int durasi) {
        super(judul, sutradara, tahunRilis, genre);
        this.durasi = durasi;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    // Polymorphism: Override
    @Override
    public String toString() {
        return "[Feature Film] " + super.toString() + " | Durasi: " + durasi + " menit";
    }
}