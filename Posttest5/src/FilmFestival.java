import java.util.ArrayList;

public class FilmFestival extends Film {
    private ArrayList<String> daftarFestival;
    private ArrayList<String> penghargaan;

    public FilmFestival(String judul, String sutradara, int tahunRilis, Genre genre) {
        super(judul, sutradara, tahunRilis, genre);
        this.daftarFestival = new ArrayList<>();
        this.penghargaan = new ArrayList<>();
    }

    public ArrayList<String> getDaftarFestival() {
        return daftarFestival;
    }

    public void tambahFestival(String festival) {
        daftarFestival.add(festival);
    }
    
    public void hapusFestival(String festival) {
        daftarFestival.remove(festival);
    }

    public ArrayList<String> getPenghargaan() {
        return penghargaan;
    }

    public void tambahPenghargaan(String penghargaan) {
        this.penghargaan.add(penghargaan);
    }
    
    public void hapusPenghargaan(String penghargaan) {
        this.penghargaan.remove(penghargaan);
    }

    public String getDaftarFestivalString() {
        if (daftarFestival.isEmpty()) {
            return "Belum ada";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String festival : daftarFestival) {
            sb.append("\n- ").append(festival);
        }
        return sb.toString();
    }
    
    public String getPenghargaanString() {
        if (penghargaan.isEmpty()) {
            return "Belum ada";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String award : penghargaan) {
            sb.append("\n- ").append(award);
        }
        return sb.toString();
    }

    @Override
    public String getJenisPendistribusian() {
        return "Festival Film";
    }
    
    public String getInfoLengkap() {
        return "[Film Festival]\n" + toString() + 
               "\nFestival: " + getDaftarFestivalString() + 
               "\nPenghargaan: " + getPenghargaanString();
    }
}