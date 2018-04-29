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

        Feld feld = new Feld("sdf",PflanzenArten.Mais);
        GießAutomat gießAutomat = new GießAutomat();
        ErntAutomat erntAutomat = new ErntAutomat();
        Saenautomat saenautomat = new Saenautomat();
        for (int i=0;i<60;i++) {
            gießAutomat.arbeiten(LesenUndSchreibenLernen.getFelder_inhalt());
        }
        erntAutomat.arbeiten(LesenUndSchreibenLernen.getFelder_inhalt());
        saenautomat.arbeiten(LesenUndSchreibenLernen.getFelder_inhalt());

        for (Feld feld1 : LesenUndSchreibenLernen.getFelder_inhalt()) {
            System.out.println(feld1.getPflanzenliste().size());
            for (FeldPflanzen pflanze : feld1.getPflanzenliste()) {
                System.out.println(pflanze.getHoehe());
            }
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        new TabbedPane().starteProgramm(primaryStage);
        new Login().login(primaryStage);
    }
}
