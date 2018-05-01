package app;

import dao.LesenUndSchreibenLernen;
import gui.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {

        // Daten aus CSV einlesen
        LesenUndSchreibenLernen.felderLesen();
        LesenUndSchreibenLernen.nutzerLesen();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Login().login(primaryStage);
    }
}