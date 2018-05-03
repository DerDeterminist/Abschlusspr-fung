package app;

import automaten.GießAutomat;
import dao.LesenUndSchreibenLernen;
import gui.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import pflanzen.PflanzenArten;

public class Main extends Application{

    public static void main(String[] args) {

        // Daten aus CSV einlesen
        LesenUndSchreibenLernen.felderLesen();
        LesenUndSchreibenLernen.nutzerLesen();

        Feld feld = new Feld("test", PflanzenArten.Mais);
        new GießAutomat().arbeiten(feld);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Login().login(primaryStage);
    }
}