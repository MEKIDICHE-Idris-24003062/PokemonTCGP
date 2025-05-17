package Pokemon.view;

import Pokemon.model.Carte;
import Pokemon.model.CollectionJoueur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.Map.Entry;

public class CollectionView {

    private static final int ITEMS_PER_PAGE = 20;

    private List<Entry<String, Integer>> grouped; // (nom, quantité)
    private Map<String, Carte> exempleCartes;     // nom → carte exemple
    private int currentPage = 0;

    private FlowPane cartesPane;
    private Button prevBtn;
    private Button nextBtn;

    public void afficher(Stage stage) {
        // 1) Regrouper les cartes par nom
        List<Carte> all = CollectionJoueur.getCartesPossedees();
        Map<String, Integer> counts = new LinkedHashMap<>();
        exempleCartes = new LinkedHashMap<>();
        for (Carte c : all) {
            String nom = c.getNom();
            counts.put(nom, counts.getOrDefault(nom, 0) + 1);
            exempleCartes.putIfAbsent(nom, c);
        }
        // Transformer en liste d’entrées pour pagination
        grouped = new ArrayList<>(counts.entrySet());

        // Top : titre
        Label titre = new Label("Ma Collection");
        titre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(titre, new Insets(10,0,10,0));

        // Center : flow pane pour cartes
        cartesPane = new FlowPane(10, 10);
        cartesPane.setPadding(new Insets(10));
        cartesPane.setAlignment(Pos.CENTER);

        // Bottom navigation
        prevBtn = new Button("Précédent");
        nextBtn = new Button("Suivant");
        prevBtn.setOnAction(e -> {
            if (currentPage > 0) {
                currentPage--; updatePage();
            }
        });
        nextBtn.setOnAction(e -> {
            if ((currentPage + 1) * ITEMS_PER_PAGE < grouped.size()) {
                currentPage++; updatePage();
            }
        });
        HBox nav = new HBox(20, prevBtn, nextBtn);
        nav.setAlignment(Pos.CENTER);
        nav.setPadding(new Insets(10));

        // Bouton retour à l'accueil
        Button retourBtn = new Button("Retour à l'accueil");
        retourBtn.setOnAction(e -> new AccueilView().afficher(stage));
        HBox retourBox = new HBox(retourBtn);
        retourBox.setAlignment(Pos.CENTER);
        retourBox.setPadding(new Insets(10));

        // Layout global
        VBox root = new VBox(10, titre, cartesPane, nav, retourBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Scene
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        // Afficher la première page
        updatePage();
    }

    private void updatePage() {
        cartesPane.getChildren().clear();

        int start = currentPage * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, grouped.size());
        List<Entry<String,Integer>> pageItems = grouped.subList(start, end);

        for (Entry<String,Integer> entry : pageItems) {
            String nom = entry.getKey();
            int quantite = entry.getValue();
            Carte exemple = exempleCartes.get(nom);

            VBox box = new VBox(5);
            box.setPrefSize(120, 170);
            box.setAlignment(Pos.TOP_CENTER);
            box.setPadding(new Insets(8));
            box.setStyle("-fx-border-color: black; -fx-background-color: lightgreen;");

            Label nomLabel = new Label(nom + (quantite > 1 ? " x" + quantite : ""));
            nomLabel.setStyle("-fx-font-weight: bold;");

            Label typeLabel = new Label("Type   : " + exemple.getType());
            Label pvLabel   = new Label("PV     : " + exemple.getPv());
            Label dmgLabel  = new Label("Dégâts : " + exemple.getDegats());

            box.getChildren().addAll(nomLabel, typeLabel, pvLabel, dmgLabel);
            cartesPane.getChildren().add(box);
        }

        prevBtn.setDisable(currentPage == 0);
        nextBtn.setDisable((currentPage + 1) * ITEMS_PER_PAGE >= grouped.size());
    }
}
