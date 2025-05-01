import java.util.ArrayList;

public interface KatalogManager {
    ArrayList<Film> cariFilm(String keyword);

    void urutkanFilm(String kriteria);
}