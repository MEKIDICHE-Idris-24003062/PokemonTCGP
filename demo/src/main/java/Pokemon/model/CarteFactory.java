package Pokemon.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CarteFactory {

    private static final List<Carte> ALL_CARDS = new ArrayList<>();

    static {
        try (InputStream is = CarteFactory.class.getResourceAsStream("/gen1.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                // format attendu : nom,type,pv,degats,faiblesse1;faiblesse2;…
                String[] parts = line.split(",");
                String nom    = parts[0].trim();
                String type   = parts[1].trim();
                int pv        = Integer.parseInt(parts[2].trim());
                int degats    = Integer.parseInt(parts[3].trim());
                List<String> faiblesses = Collections.emptyList();
                if (parts.length >= 5 && !parts[4].isBlank()) {
                    faiblesses = Arrays.stream(parts[4].split(";"))
                            .map(String::trim)
                            .collect(Collectors.toList());
                }
                ALL_CARDS.add(new Carte(nom, type, pv, degats, faiblesses));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible de charger gen1.csv", e);
        }
    }

    /** Génère un booster aléatoire de 'nombre' cartes */
    public static List<Carte> genererBooster(int nombre) {
        Collections.shuffle(ALL_CARDS);
        return new ArrayList<>(ALL_CARDS.subList(0, nombre));
    }
}
