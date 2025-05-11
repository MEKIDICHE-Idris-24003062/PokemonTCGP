package Pokemon.view;

import Pokemon.model.Carte;
import Pokemon.model.CarteFactory;
import Pokemon.model.CollectionJoueur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CombatView {

    private int pvJoueur;
    private int pvIA;
    private int degatsJoueur;
    private int degatsIA;
    private Label pvJoueurLabel;
    private Label pvIALabel;

    public void afficher(Stage stage) {
        // 1) Sélection du Pokémon dans ta collection
        List<Carte> possedees = CollectionJoueur.getCartesPossedees();
        if (possedees.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Tu n'as aucune carte en collection.\nOuvre un booster d'abord !",
                    ButtonType.OK);
            alert.showAndWait();
            new AccueilView().afficher(stage);
            return;
        }

        // Récupérer une liste unique (une carte par nom)
        Map<String, Carte> uniques = new LinkedHashMap<>();
        for (Carte c : possedees) {
            uniques.putIfAbsent(c.getNom(), c);
        }

        ComboBox<Carte> combo = new ComboBox<>();
        combo.getItems().addAll(uniques.values());
        combo.setPromptText("Choisis ton Pokémon");

        Button startBtn = new Button("Commencer le combat");
        startBtn.setOnAction(e -> {
            Carte joueur = combo.getValue();
            if (joueur == null) return;
            // Initialise PV et dégâts
            pvJoueur   = joueur.getPv();
            degatsJoueur = joueur.getDegats();
            // Prépare l'IA
            Carte adversaire = CarteFactory.genererBooster(1).get(0);
            pvIA     = adversaire.getPv();
            degatsIA = adversaire.getDegats();
            afficherCombat(stage, joueur, adversaire);
        });

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        VBox selectionLayout = new VBox(15,
                new Label("Sélectionne ton Pokémon pour le combat :"),
                combo,
                startBtn,
                retourBtn
        );
        selectionLayout.setAlignment(Pos.CENTER);
        selectionLayout.setStyle("-fx-padding: 30;");

        stage.setScene(new Scene(selectionLayout, 800, 600));
    }

    private void afficherCombat(Stage stage, Carte joueur, Carte ia) {
        Label titre = new Label("Combat Pokémon !");
        titre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nomJoueur = new Label("Ton Pokémon : " + joueur.getNom());
        Label nomIA     = new Label("Adversaire   : " + ia.getNom());

        pvJoueurLabel = new Label("PV : " + pvJoueur);
        pvIALabel     = new Label("PV : " + pvIA);

        Button attaquerBtn = new Button(
                "Attaquer (" + degatsJoueur + " dégâts)"
        );
        attaquerBtn.setOnAction(e -> {
            // Joueur attaque
            pvIA = Math.max(0, pvIA - degatsJoueur);
            pvIALabel.setText("PV : " + pvIA + (pvIA == 0 ? " – Gagné !" : ""));

            if (pvIA == 0) {
                attaquerBtn.setDisable(true);
                return;
            }

            // IA contre-attaque
            pvJoueur = Math.max(0, pvJoueur - degatsIA);
            pvJoueurLabel.setText("PV : " + pvJoueur + (pvJoueur == 0 ? " – Perdu !" : ""));

            if (pvJoueur == 0) {
                attaquerBtn.setDisable(true);
            }
        });

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        VBox combatLayout = new VBox(15,
                titre,
                nomJoueur, pvJoueurLabel,
                nomIA,    pvIALabel,
                attaquerBtn,
                retourBtn
        );
        combatLayout.setAlignment(Pos.CENTER);
        combatLayout.setStyle("-fx-padding: 30;");

        stage.setScene(new Scene(combatLayout, 800, 600));
    }
}
