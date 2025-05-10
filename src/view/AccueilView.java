package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccueilView {

    public void afficher(Stage primaryStage) {
        Label titre = new Label("Pokémon TCG - Java Edition");
        titre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button boosterBtn = new Button("Ouvrir un booster");
        Button collectionBtn = new Button("Voir ma collection");
        Button combatBtn = new Button("Combattre");
        Button quitterBtn = new Button("Quitter");

        boosterBtn.setOnAction(e -> new BoosterView().afficher(primaryStage));
        collectionBtn.setOnAction(e -> System.out.println("Page collection à venir"));
        combatBtn.setOnAction(e -> System.out.println("Page combat à venir"));
        quitterBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20, titre, boosterBtn, collectionBtn, combatBtn, quitterBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
