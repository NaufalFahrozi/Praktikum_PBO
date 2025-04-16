public class ShortFilm extends Film {
    private String festival; // Nama festival jika pernah diputar

    public ShortFilm(String judul, String sutradara, int tahunRilis, Genre genre, String festival) {
        super(judul, sutradara, tahunRilis, genre);
        this.festival = festival;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    // Polymorphism: Override
    @Override
    public String toString() {
        return "[Short Film] " + super.toString() + " | Festival: " + festival;
    }
}