package Pokemon.model;

import java.util.List;

public class Carte {
    private String nom;
    private String type;
    private int pv;
    private int degats;
    private List<String> faiblesses;  // ← nouveau

    /**
     * Nouveau constructeur prenant en compte les faiblesses.
     */
    public Carte(String nom,
                 String type,
                 int pv,
                 int degats,
                 List<String> faiblesses) {
        this.nom = nom;
        this.type = type;
        this.pv = pv;
        this.degats = degats;
        this.faiblesses = faiblesses;
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

    public int getDegats() {
        return degats;
    }

    public List<String> getFaiblesses() {
        return faiblesses;
    }

    @Override
    public String toString() {
        return nom + " (" + type + ")";
    }
}



