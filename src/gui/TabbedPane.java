package gui;

import app.Feld;
import app.Nutzer;
import app.Util;
import dao.LesenUndSchreibenLernen;
import gui_handling.System_exit;
import gui_handling.TabbedPane_automatenHandling;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ImageView background = null;
        try {
            background = new ImageView(new Image(new File("res\\images\\wallpaper-gluecksklee.jpg").toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(background,border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);
        TabPane konsoloe = new TabPane();
        konsoloe.setMaxHeight(300);
        border.setBottom(konsoloe);

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab); });
        list.setPrefSize(170,200);
        list.setMaxSize(170,300);
        border.setLeft(list);
        Label ueberschrift = new Label("Übersicht");
        border.setTop(ueberschrift);

        Label kurzeInfoUeberFelder = new Label("Anzal der Felder von "+Nutzer.getAktuellerNutzer().getName()+": "+anzFelderDesNutzers);
        gridPane.add(kurzeInfoUeberFelder,0,0);
        gridPane.add(new Pie_Chart_Pflanzenarten().pieChart_pflanzenarten(),0,1);

        return stackPane;
    }

    private Node automatne_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        ImageView background = null;
        try {
            background = new ImageView(new Image(new File("res\\images\\wallpaper-gluecksklee.jpg").toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(background,border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);
        TabPane konsoloe = new TabPane();
        konsoloe.setMaxHeight(300);
        border.setBottom(konsoloe);

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab); });
        list.setPrefSize(170,200);
        list.setMaxSize(170,300);
        border.setLeft(list);
        Label ueberschrift = new Label("Felder                          Automaten");
        ueberschrift.setFont(new Font("Arial",20));
        border.setTop(ueberschrift);

        GridPane gridNeu = new GridPane();
        gridNeu.setHgap(10);
        gridNeu.setVgap(10);
        Label neu = new Label("Daten für neues Feld");
        TextField name = new TextField();
        name.setPromptText("Name des Feldes");
        TextField anz = new TextField();
        anz.setPromptText("Maximale Anzal der Pflanzen");
        ComboBox arten = new ComboBox();
        Button button_neu = new Button("Neues Feld");
        arten.setItems(Util.getPflanzenartenObservable());
        gridNeu.add(neu,0,0);
        gridNeu.add(name,0,1);
        gridNeu.add(anz,0,2);
        gridNeu.add(arten,0,3);
        gridNeu.add(button_neu,0,4);
        border.setRight(gridNeu);


        Button ernen = new Button("Ernten");
        Button geißen = new Button("Gießen");
        Button saeen = new Button("Säen");
        Button alles = new Button("alle Automaten");
        Button loeschen = new Button("Löschen");
        ernen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().erntenButton(getSeleked_fromList()));
        geißen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().geißenButton(getSeleked_fromList()));
        saeen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().saeenButton(getSeleked_fromList()));
        alles.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().allesButton(getSeleked_fromList()));
        button_neu.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().neuButton(name.getText(),Util.StringToPflanzenart(arten.getSelectionModel().getSelectedItem().toString()),Integer.parseInt(anz.getText())));
        loeschen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().loeschenButton(getSeleked_fromList()));
        gridPane.add(ernen,3,2);
        gridPane.add(geißen,4,2);
        gridPane.add(saeen,5,2);
        gridPane.add(alles,6,2);
        gridPane.add(loeschen,7,2);

        return stackPane;
    }

    private Node stats_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        ImageView background = null;
        try {
            background = new ImageView(new Image(new File("res\\images\\wallpaper-gluecksklee.jpg").toURL().toString()));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(background,border);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0,10,0,10));
        border.setCenter(gridPane);
        TabPane konsoloe = new TabPane();
        konsoloe.setMaxHeight(300);
        border.setBottom(konsoloe);

        ListView<String> list = new ListView<String>();
        list.setItems(liste_FelderListe_aneigen());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab); });
        list.setPrefSize(170,200);
        list.setMaxSize(170,300);
        border.setLeft(list);
        Label ueberschrift = new Label("Felder");
        border.setTop(ueberschrift);

        return stackPane;
    }

    private Node rechte_Content(){
        StackPane stackPane = new StackPane();
        BorderPane border = new BorderPane();
        ImageView background = null;
        try {
            background = new ImageView(new Image(new File("res\\images\\stats.jpg").toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(background,border);
        GridPane gridPane = new GridPane();
        border.setCenter(gridPane);

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
                    if (feld.getPflanzenArten() == PflanzenArten.Mais) {
                        davonMeis++;
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

    public static Map getVorrausichlicheErnte(){
        HashMap<String,Integer> vorrausichlicheErnte = new HashMap<>();
        for (Feld feld : getFelder_des_Nutzers()) {
            int anzVorrausichtlicheErnte=0;
            for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                if (pflanze.getHoehe()+pflanze.getPflanzenArten().getWachstum()*1.5>=pflanze.getPflanzenArten().getErntehöhe()){
                    anzVorrausichtlicheErnte++;
                }
            }
            vorrausichlicheErnte.put(feld.getName(),anzVorrausichtlicheErnte);
        }
        return vorrausichlicheErnte;
    }

    public static ArrayList getDruchschnitlicheHoehe(){
        ArrayList<String> durchschnittlicheHoehe = new ArrayList<>();
        for (Feld feld : getFelder_des_Nutzers()) {
            double gesamtHoehe =0;
            for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                gesamtHoehe += pflanze.getHoehe();
            }
            durchschnittlicheHoehe.add(feld.getName().toString()+";+"+String.valueOf(gesamtHoehe/feld.getPflanzenliste().size()));
        }
        return durchschnittlicheHoehe;
    }

    public static ArrayList<Feld> getFelder_des_Nutzers() {
        return felder_des_Nutzers;
    }

    public static int getAnzFelderDesNutzers() {
        return anzFelderDesNutzers;
    }

    public static int getDavonMeis() {
        return davonMeis;
    }

    public static int getDavonWeizen() {
        return davonWeizen;
    }
}