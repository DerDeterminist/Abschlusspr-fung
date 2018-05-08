package app;

import java.util.Locale;

import dao.LesenUndSchreibenLernen;
import gui.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    //todo datenbank; dokumentation; codestyle

    public static void main(String[] args) {

        I18n.i18nSetup(Locale.ENGLISH);

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