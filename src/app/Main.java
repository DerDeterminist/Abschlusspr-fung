package app;

import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import dao.LesenUndSchreibenLernen;
import gui.Login;
import gui.TabbedPane;
import javafx.application.Application;
import javafx.stage.Stage;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import javax.swing.*;


public class Main extends Application{

    public static void main(String[] args) {

        LesenUndSchreibenLernen.felderLesen();
        LesenUndSchreibenLernen.nutzerLesen();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        new TabbedPane().starteProgramm(primaryStage);
        new Login().login(primaryStage);
    }
}
