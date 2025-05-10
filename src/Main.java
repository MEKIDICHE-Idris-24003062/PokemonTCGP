import javafx.application.Application;
import javafx.stage.Stage;
import view.AccueilView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AccueilView accueil = new AccueilView();
        accueil.afficher(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
