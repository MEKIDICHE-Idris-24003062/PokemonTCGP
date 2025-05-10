package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarteFactory {
    private static final List<Carte> ALL_CARDS = new ArrayList<>();

    static {
        ALL_CARDS.add(new Carte("Pikachu", "Électrik", 60));
        ALL_CARDS.add(new Carte("Salamèche", "Feu", 50));
        ALL_CARDS.add(new Carte("Carapuce", "Eau", 50));
        ALL_CARDS.add(new Carte("Bulbizarre", "Plante", 60));
        ALL_CARDS.add(new Carte("Rondoudou", "Fée", 70));
        ALL_CARDS.add(new Carte("Miaouss", "Normal", 40));
        ALL_CARDS.add(new Carte("Roucool", "Vol", 45));
        ALL_CARDS.add(new Carte("Évoli", "Normal", 60));
    }

    public static List<Carte> genererBooster(int nombre) {
        Collections.shuffle(ALL_CARDS);
        return new ArrayList<>(ALL_CARDS.subList(0, nombre));
    }
}
