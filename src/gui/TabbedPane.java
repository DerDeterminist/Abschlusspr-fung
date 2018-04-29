package gui;

import app.Feld;
import app.Nutzer;
import dao.LesenUndSchreibenLernen;
import gui_handling.System_exit;
import gui_handling.TabbedPane_automatenHandling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class TabbedPane {
    ListView<String> list = new ListView<String>();

    static ArrayList<Feld> felder_des_Nutzers = new ArrayList<>();
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

        Scene scene = new Scene(tabPane,850,600);
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

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(170,200);
        border.setLeft(list);
        Label ueberschrift = new Label("Übersicht");
        border.setTop(ueberschrift);

        Label kurzeInfoUeberFelder = new Label("Anzal der Felder von "+Nutzer.getAktuellerNutzer().getName()+": "+anzFelderDesNutzers
                                                +"\ndavon Meisfelder: "+davonMeis
                                                +"\ndavon Weizenfelder; "+davonWeizen);
        gridPane.add(kurzeInfoUeberFelder,0,0);

        return stackPane;
    }

    private Node automatne_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(170,200);
        border.setLeft(list);
        Label ueberschrift = new Label("Felder                          Automaten");
        ueberschrift.setFont(new Font("Arial",20));
        border.setTop(ueberschrift);


        Button ernen = new Button("Ernten");
        Button geißen = new Button("Gießen");
        Button saeen = new Button("Säen");
        Button alles = new Button("alle Automaten");
        ernen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().erntenButton(getSeleked_fromList()));
        geißen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().geißenButton(getSeleked_fromList()));
        saeen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().saeenButton(getSeleked_fromList()));
        alles.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().allesButton(getSeleked_fromList()));
        gridPane.add(ernen,3,2);
        gridPane.add(geißen,4,2);
        gridPane.add(saeen,5,2);
        gridPane.add(alles,6,2);

        return stackPane;
    }

    private Node stats_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(170,200);
        border.setLeft(list);
        Label ueberschrift = new Label("Felder");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private Node rechte_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        stackPane.getChildren().addAll(border);
        GridPane gridPane = new GridPane();
        border.setCenter(gridPane);

        Label ueberschrift = new Label("Felder");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private ObservableList liste_FelderListe_aneigen(){

        //todo villeicht zusammenfassen
        ArrayList<String> namen_felder_des_Nutzers = new ArrayList<>();
        for (Feld feld : felder_des_Nutzers) {
                namen_felder_des_Nutzers.add(feld.getName());
        }

        ObservableList<String> items = FXCollections.observableArrayList (
                namen_felder_des_Nutzers);
        list.setItems(items);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(170,200);
        return items;
    }

    private void listenStatsGeneriren(){

        try {
            for (Feld feld : LesenUndSchreibenLernen.getFelder_inhalt()) {
                if (feld.getGehoertZuNutzer().equals(Nutzer.getAktuellerNutzer().getName())) {
                    felder_des_Nutzers.add(feld);
                    anzFelderDesNutzers++;
                    if (feld.getPflanzenArten() == PflanzenArten.Weizen) {
                        davonWeizen++;
                    }
                    if (feld.getPflanzenArten() == PflanzenArten.Weizen) {
                        davonWeizen++;
                    }
                }
            }
        }
        catch (NullPointerException e){//wenn nutzer kein feld
        }
    }

    public ArrayList<String> getSeleked_fromList(){
        ArrayList<String> ausgewaehlt = new ArrayList<>();
        for (String item : list.getSelectionModel().getSelectedItems()) {
            ausgewaehlt.add(item);
        }
        return ausgewaehlt;
    }

    public static ArrayList<Feld> getFelder_des_Nutzers() {
        return felder_des_Nutzers;
    }
}
