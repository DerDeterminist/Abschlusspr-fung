package gui;

import app.Feld;
import app.Nutzer;
import dao.LesenUndSchreibenLernen;
import gui_handling.System_exit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class TabbedPane {

    ListView<String> list = new ListView<String>();
    ArrayList<Feld> felder_des_Nutzers = new ArrayList<>();
    static int anzFelderDesNutzers = 0;
    static int davonMeis = 0;
    static int davonWeizen = 0;

    public void starteProgramm(Stage primaryStage){
        listenStatsGeneriren();

        //todo villeicht nächste zeile über
        primaryStage.setTitle("Bauernhof");
        primaryStage.setOnCloseRequest((e)-> {e.consume(); System_exit.exit();});

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(UNAVAILABLE);

        Tab tab_uebersicht = new Tab();
        tab_uebersicht.setText("Übersicht");
        tab_uebersicht.setContent(uebersicht_Contet());
        Tab tab_automaten = new Tab();
        tab_automaten.setText("Automaten");
        tab_automaten.setContent(automatne_Content());
        Tab tab_stats = new Tab();
        tab_stats.setText("Auswertung");
        tab_stats.setContent(stats_Content());
        Tab tab_rechte = new Tab();
        tab_rechte.setText("Einstellungen");
        tab_rechte.setContent(rechte_Content());
        tabPane.getTabs().addAll(tab_uebersicht,tab_automaten,tab_stats,tab_rechte);

        Scene scene = new Scene(tabPane,600,450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node uebersicht_Contet(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);

        border.setLeft(liste_FelderListe_aneigen());
        Label ueberschrift = new Label("Übersicht");
        border.setTop(ueberschrift);

        Label kurzeInfoUeberFelder = new Label("Anzal der Felder von "+Nutzer.getAktuellerNutzer().getName()+": "+anzFelderDesNutzers
                                                +"\ndavon Meis: "+davonMeis
                                                +"\ndavon Weizen; "+davonWeizen);
        gridPane.add(kurzeInfoUeberFelder,0,0);

        return stackPane;
    }

    private Node automatne_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        border.setCenter(gridPane);

        border.setLeft(liste_FelderListe_aneigen());
        Label ueberschrift = new Label("Automaten");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private Node stats_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        border.setCenter(gridPane);

        border.setLeft(liste_FelderListe_aneigen());
        Label ueberschrift = new Label("Auswertung");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private Node rechte_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        border.setCenter(gridPane);

        Label ueberschrift = new Label("Einstellungen");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private Node liste_FelderListe_aneigen(){

        //todo villeicht zusammenfassen
        ArrayList<String> namen_felder_des_Nutzers = new ArrayList<>();
        for (Feld feld : felder_des_Nutzers) {
                namen_felder_des_Nutzers.add(feld.getName());
        }

        ObservableList<String> items = FXCollections.observableArrayList (
                namen_felder_des_Nutzers);
        list.setItems(items);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(100,200);
        return list;
    }

    private ArrayList<String> getSeleked_fromList(){
        return(ArrayList<String>)list.getSelectionModel().getSelectedItems();
    }

    private void listenStatsGeneriren(){

        for (Feld feld : LesenUndSchreibenLernen.getFelder_inhalt()) {
            if (feld.getGehoertZuNutzer().equals(Nutzer.getAktuellerNutzer())){
                felder_des_Nutzers.add(feld);
                anzFelderDesNutzers++;
                if (feld.getPflanzenArten()== PflanzenArten.Weizen){
                    davonWeizen++;
                }
                if (feld.getPflanzenArten() == PflanzenArten.Weizen){
                    davonWeizen++;
                }
            }
        }
    }
}
