package Pokemon.model;

public class Carte {
    private String nom;
    private String type;
    private int pv;
    private int degats;   // ← nouveau

    public Carte(String nom, String type, int pv, int degats) {
        this.nom    = nom;
        this.type   = type;
        this.pv     = pv;
        this.degats = degats;
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

    public int getDegats() {    // ← nouveau getter
        return degats;
    }

    @Override
    public String toString() {
        return nom;
    }
}


