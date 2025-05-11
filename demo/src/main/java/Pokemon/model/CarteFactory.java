package Pokemon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarteFactory {
    private static final List<Carte> ALL_CARDS = new ArrayList<>();

    static {
        ALL_CARDS.add(new Carte("Pikachu", "Électrik", 60, "/images/pikachu.png"));
        ALL_CARDS.add(new Carte("Salamèche", "Feu", 50, "/images/salameche.png"));
        ALL_CARDS.add(new Carte("Carapuce", "Eau", 50, "/images/carapuce.png"));
        ALL_CARDS.add(new Carte("Bulbizarre", "Plante", 60, "/images/bulbizarre.png"));
        ALL_CARDS.add(new Carte("Rondoudou", "Fée", 70, "/images/rondoudou.png"));
        ALL_CARDS.add(new Carte("Miaouss", "Normal", 40, "/images/miaouss.png"));
        ALL_CARDS.add(new Carte("Roucool", "Vol", 45, "/images/roucool.png"));
        ALL_CARDS.add(new Carte("Évoli", "Normal", 60, "/images/evoli.png"));
    }

    public static List<Carte> genererBooster(int nombre) {
        Collections.shuffle(ALL_CARDS);
        return new ArrayList<>(ALL_CARDS.subList(0, nombre));
    }
}
