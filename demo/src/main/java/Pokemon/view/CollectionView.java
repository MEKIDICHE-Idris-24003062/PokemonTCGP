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

        FlowPane cartesPane = new FlowPane();
        cartesPane.setHgap(10);
        cartesPane.setVgap(10);
        cartesPane.setAlignment(Pos.CENTER);

        List<Carte> collection = CollectionJoueur.getCartesPossedees();

        if (collection.isEmpty()) {
            cartesPane.getChildren().add(new Label("Aucune carte pour l’instant..."));
        } else {
            // Compter les occurrences par nom de carte
            Map<String, Integer> counts = new LinkedHashMap<>();
            Map<String, Carte> exempleCartes = new LinkedHashMap<>();

            for (Carte c : collection) {
                String key = c.getNom();
                counts.put(key, counts.getOrDefault(key, 0) + 1);
                exempleCartes.putIfAbsent(key, c);
            }

            // Pour chaque carte unique, afficher une seule vignette avec quantité
            counts.forEach((nom, quantite) -> {
                Carte exemple = exempleCartes.get(nom);
                VBox box = new VBox(5);
                box.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: lightgreen;");
                box.setAlignment(Pos.CENTER);
                box.setPrefSize(120, 150);

                Label nomLabel = new Label(exemple.getNom() + (quantite > 1 ? " x" + quantite : ""));
                Label typeLabel = new Label("Type : " + exemple.getType());
                Label pvLabel = new Label("PV : " + exemple.getPv());

                box.getChildren().addAll(nomLabel, typeLabel, pvLabel);
                cartesPane.getChildren().add(box);
            });
        }

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        VBox layout = new VBox(20, titre, cartesPane, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
    }
}
