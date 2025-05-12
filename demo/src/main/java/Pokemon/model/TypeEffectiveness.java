package Pokemon.model;
import java.util.*;

/**
 * Classe gérant les faiblesses de chaque type.
 */
public class TypeEffectiveness {
    private static final Map<String, List<String>> WEAKNESSES = new HashMap<>();

    static {
        WEAKNESSES.put("Feu",       Arrays.asList("Eau", "Sol", "Roche"));
        WEAKNESSES.put("Eau",       Arrays.asList("Électrik", "Plante"));
        WEAKNESSES.put("Plante",    Arrays.asList("Feu", "Glace", "Poison", "Vol", "Insecte"));
        WEAKNESSES.put("Électrik",  Arrays.asList("Sol"));
        WEAKNESSES.put("Roche",     Arrays.asList("Eau", "Plante", "Sol", "Combat", "Acier"));
        WEAKNESSES.put("Sol",       Arrays.asList("Eau", "Plante", "Glace"));
        // ... ajouter les autres types et leurs faiblesses si besoin
    }

    /**
     * Retourne true si le type du défenseur est faible face au type de l'attaquant.
     */
    public static boolean isWeakAgainst(String defenderType, String attackerType) {
        return WEAKNESSES.getOrDefault(defenderType, Collections.emptyList())
                .contains(attackerType);
    }

    /**
     * Obtient la liste des faiblesses pour un type donné.
     */
    public static List<String> getWeaknessesFor(String type) {
        return WEAKNESSES.getOrDefault(type, Collections.emptyList());
    }
}