package Pokemon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarteFactory {
    private static final List<Carte> ALL_CARDS = new ArrayList<>();

    static {
        // Pour chaque carte, on précise maintenant les dégâts
        ALL_CARDS.add(new Carte("Pikachu",    "Électrik", 60, 20));
        ALL_CARDS.add(new Carte("Salamèche",  "Feu",      50, 15));
        ALL_CARDS.add(new Carte("Carapuce",   "Eau",      50, 10));
        ALL_CARDS.add(new Carte("Bulbizarre", "Plante",   60, 12));
        ALL_CARDS.add(new Carte("Rondoudou",  "Fée",      70,  8));
        ALL_CARDS.add(new Carte("Miaouss",    "Normal",   40, 10));
        ALL_CARDS.add(new Carte("Roucool",    "Vol",      45, 14));
        ALL_CARDS.add(new Carte("Évoli",      "Normal",   60, 18));
    }

    public static List<Carte> genererBooster(int nombre) {
        Collections.shuffle(ALL_CARDS);
        return new ArrayList<>(ALL_CARDS.subList(0, nombre));
    }
}

