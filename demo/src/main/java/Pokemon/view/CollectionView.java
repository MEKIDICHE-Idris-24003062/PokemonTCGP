package Pokemon.view;

import Pokemon.model.Carte;
import Pokemon.model.CollectionJoueur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionView {

    public void afficher(Stage stage) {
        Label titre = new Label("Ma Collection");
        titre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        FlowPane cartesPane = new FlowPane(10, 10);
        cartesPane.setAlignment(Pos.CENTER);

        List<Carte> collection = CollectionJoueur.getCartesPossedees();

        if (collection.isEmpty()) {
            cartesPane.getChildren().add(new Label("Aucune carte pour l’instant..."));
        } else {
            // Compter les occurrences et garder un exemple de carte
            Map<String, Integer> counts       = new LinkedHashMap<>();
            Map<String, Carte>   exempleCarte = new LinkedHashMap<>();

            for (Carte c : collection) {
                String key = c.getNom();
                counts.put(key, counts.getOrDefault(key, 0) + 1);
                exempleCarte.putIfAbsent(key, c);
            }

            // Afficher chaque carte unique avec quantité et dégâts
            counts.forEach((nom, quantite) -> {
                Carte carte = exempleCarte.get(nom);

                VBox box = new VBox(5);
                box.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: lightgreen;");
                box.setAlignment(Pos.CENTER);
                box.setPrefSize(120, 170);

                Label nomLabel    = new Label(carte.getNom() + (quantite > 1 ? " x" + quantite : ""));
                Label typeLabel   = new Label("Type   : " + carte.getType());
                Label pvLabel     = new Label("PV     : " + carte.getPv());
                Label degatsLabel = new Label("Dégâts : " + carte.getDegats());  // ← affichage des dégâts

                box.getChildren().addAll(nomLabel, typeLabel, pvLabel, degatsLabel);
                cartesPane.getChildren().add(box);
            });
        }

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        VBox layout = new VBox(20, titre, cartesPane, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        stage.setScene(new Scene(layout, 800, 600));
    }
}
