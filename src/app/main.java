package app;

import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import gui.Login;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import java.io.File;
import java.net.MalformedURLException;


public class main extends Application{
    public static void main(String[] args) {

        Feld feld = new Feld("sdf",PflanzenArten.Mais);
        GießAutomat gießAutomat = new GießAutomat();
        ErntAutomat erntAutomat = new ErntAutomat();
        Saenautomat saenautomat = new Saenautomat();
        for (int i=0;i<60;i++) {
            gießAutomat.arbeiten(new Util.getFelder());
        }
        erntAutomat.arbeiten(feld.getFelder());
        saenautomat.arbeiten(feld.getFelder());

        for (Feld feld1 : feld.getFelder()) {
            System.out.println(feld1.getPflanzenliste().size());
            for (FeldPflanzen pflanze : feld1.getPflanzenliste()) {
                System.out.println(pflanze.getHoehe());
            }
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Login().login(primaryStage);
    }
}
