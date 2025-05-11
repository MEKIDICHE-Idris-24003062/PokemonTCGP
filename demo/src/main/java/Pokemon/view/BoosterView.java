package Pokemon.view;

import Pokemon.model.Carte;
import Pokemon.model.CarteFactory;
import Pokemon.model.CollectionJoueur;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.List;

public class BoosterView {

    public void afficher(Stage stage) {
        Label titre = new Label("Ouverture du Booster !");
        titre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox cartesBox = new HBox(15);
        cartesBox.setAlignment(Pos.CENTER);

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        List<Carte> booster = CarteFactory.genererBooster(5);
        CollectionJoueur.ajouterCartes(booster);

        VBox layout = new VBox(20, titre, cartesBox, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);

        for (int i = 0; i < booster.size(); i++) {
            Carte carte = booster.get(i);
            VBox carteBox = creerCarteBox(carte);
            carteBox.setOpacity(0);
            cartesBox.getChildren().add(carteBox);

            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), carteBox);
            ft.setDelay(Duration.seconds(i * 0.3));
            ft.setToValue(1);
            ft.play();
        }
    }

    private VBox creerCarteBox(Carte carte) {
        VBox box = new VBox(5);
        box.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: lightblue;");
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(120, 150);

        Label nom = new Label(carte.getNom());
        Label type = new Label("Type : " + carte.getType());
        Label pv = new Label("PV : " + carte.getPv());

        box.getChildren().addAll(nom, type, pv);
        return box;
    }
}
