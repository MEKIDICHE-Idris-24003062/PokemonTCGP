package Pokemon.model;

import java.util.ArrayList;
import java.util.List;

public class CollectionJoueur {
    private static final List<Carte> cartesPossedees = new ArrayList<>();

    public static void ajouterCarte(Carte carte) {
        cartesPossedees.add(carte);
    }

    public static void ajouterCartes(List<Carte> cartes) {
        cartesPossedees.addAll(cartes);
    }

    public static List<Carte> getCartesPossedees() {
        return new ArrayList<>(cartesPossedees);
    }

    public static void vider() {
        cartesPossedees.clear();
    }
}

