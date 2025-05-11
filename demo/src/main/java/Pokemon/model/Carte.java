package Pokemon.model;

public class Carte {
    private String nom;
    private String type;
    private int pv;
    private String imagePath; // <-- ajout

    public Carte(String nom, String type, int pv, String imagePath) {
        this.nom = nom;
        this.type = type;
        this.pv = pv;
        this.imagePath = imagePath;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public int getPv() {
        return pv;
    }

    public String getImagePath() {
        return imagePath;
    }
}

