package Pokemon.view;
// CombatView.java
import Pokemon.model.Carte;
import Pokemon.model.CarteFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class CombatView {

    private int pvJoueur;
    private int pvIA;
    private Label pvJoueurLabel;
    private Label pvIALabel;

    public void afficher(Stage stage) {
        // Choisir un Pokémon pour chaque joueur
        Carte joueur = CarteFactory.genererBooster(1).get(0);
        Carte ia = CarteFactory.genererBooster(1).get(0);

        pvJoueur = joueur.getPv();
        pvIA = ia.getPv();

        Label titre = new Label("Combat Pokémon !");
        titre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nomJoueur = new Label("Ton Pokémon : " + joueur.getNom());
        Label nomIA = new Label("Adversaire : " + ia.getNom());

        pvJoueurLabel = new Label("PV : " + pvJoueur);
        pvIALabel = new Label("PV : " + pvIA);

        Button attaquerBtn = new Button("Attaquer");
        attaquerBtn.setOnAction(e -> attaquer(joueur, ia));

        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));

        VBox layout = new VBox(20, titre, nomJoueur, pvJoueurLabel, nomIA, pvIALabel, attaquerBtn, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
    }

    private void attaquer(Carte joueur, Carte ia) {
        int degats = 20; // attaque fixe pour l’instant

        // Le joueur attaque l’IA
        pvIA -= degats;
        if (pvIA < 0) pvIA = 0;

        // Si l’IA est KO, fin du combat
        if (pvIA == 0) {
            pvIALabel.setText("PV : 0 - Gagné !");
            return;
        }

        // L’IA attaque le joueur
        pvJoueur -= 15; // dégâts IA
        if (pvJoueur < 0) pvJoueur = 0;

        // Afficher PV mis à jour
        pvJoueurLabel.setText("PV : " + pvJoueur + (pvJoueur == 0 ? " - Perdu !" : ""));
        pvIALabel.setText("PV : " + pvIA);
    }
}
