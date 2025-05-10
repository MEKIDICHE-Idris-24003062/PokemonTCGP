package model;

public class Carte {
    private String nom;
    private String type;
    private int pv;

    public Carte(String nom, String type, int pv) {
        this.nom = nom;
        this.type = type;
        this.pv = pv;
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
}
