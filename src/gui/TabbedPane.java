package gui;

import app.Feld;
import app.I18n;
import app.Nutzer;
import app.Util;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class TabbedPane {
    public static ListView<String> listUebersicht = new ListView<String>();
    public static ListView<String> listAutomaten = new ListView<String>();
    public static ListView<String> listContent = new ListView<String>();

    public static ArrayList<Feld> felder_des_Nutzers = new ArrayList<>();
    static ObservableList<String> selektierteFelderDesNutzer = FXCollections.observableArrayList(new ArrayList<>());

    static int anzFelderDesNutzers = 0;
    static int davonMeis = 0;
    static int davonWeizen = 0;

    Label hinweisNeuesPw = new Label("");
    public void starteProgramm(Stage primaryStage){
        listenStatsGeneriren();

        //todo villeicht nächste zeile über
        primaryStage.setTitle(I18n.getText("farm"));
        primaryStage.setOnCloseRequest((e)-> {e.consume(); System_exit.exit();});

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(UNAVAILABLE);

        // tabPane haubtSene
        Tab tab_uebersicht = new Tab();
        tab_uebersicht.setText(I18n.getText("overview"));
        tab_uebersicht.setContent(uebersicht_Contet());
        Tab tab_automaten = new Tab();
        tab_automaten.setText(I18n.getText("features"));
        tab_automaten.setContent(automatne_Content());
        Tab tab_stats = new Tab();
        tab_stats.setText(I18n.getText("evaluation"));
        tab_stats.setContent(stats_Content());
        Tab tab_rechte = new Tab();
        tab_rechte.setText(I18n.getText("settings"));
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

        listUebersicht.setItems(liste_FelderListe_aneigen());
        listUebersicht.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listUebersicht.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab); });
        listUebersicht.setPrefSize(170,200);
        listUebersicht.setMaxSize(170,300);
        border.setLeft(listUebersicht);
        Label ueberschrift = new Label(I18n.getText("overview"));
        border.setTop(ueberschrift);

        Label kurzeInfoUeberFelder = new Label(I18n.getText("Number_of_fields_from")+" "+Nutzer.aktuellerNutzer.getName()+": "+anzFelderDesNutzers);
        kurzeInfoUeberFelder.setFont(Util.textFont);
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

        listAutomaten.setItems(liste_FelderListe_aneigen());
        listAutomaten.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listAutomaten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab);
            selektierteFelderDesNutzer = listAutomaten.getSelectionModel().getSelectedItems();
        });
        listAutomaten.setPrefSize(170,200);
        listAutomaten.setMaxSize(170,300);

        border.setLeft(listAutomaten);
        Label ueberschrift = new Label(I18n.getText("fields"));
        ueberschrift.setFont(new Font("Arial",20));
        border.setTop(ueberschrift);

        GridPane gridNeu = new GridPane();
        gridNeu.setHgap(10);
        gridNeu.setVgap(10);
        Label neu = new Label(I18n.getText("Data_for_new_field"));
        TextField name = new TextField();
        name.setPromptText(I18n.getText("Name_of_the_field"));
        TextField anz = new TextField();
        anz.setPromptText(I18n.getText("Maximum_number_of_plants"));
        ComboBox arten = new ComboBox();
        arten.setItems(Util.getPflanzenartenObservable());
        Button button_neu = new Button(I18n.getText("New_field"));
        gridNeu.add(neu,0,0);
        gridNeu.add(name,0,1);
        gridNeu.add(anz,0,2);
        gridNeu.add(arten,0,3);
        gridNeu.add(button_neu,0,4);
        border.setRight(gridNeu);


        Button ernen = new Button(I18n.getText("harvest"));
        Button geißen = new Button(I18n.getText("water"));
        Button saeen = new Button(I18n.getText("seed"));
        Button alles = new Button(I18n.getText("all_machines"));
        Button loeschen = new Button(I18n.getText("delete"));
        ernen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().erntenButton(getSeleked_fromList()));
        geißen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().geißenButton(getSeleked_fromList()));
        saeen.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().saeenButton(getSeleked_fromList()));
        alles.addEventHandler(ActionEvent.ACTION,(e)-> new TabbedPane_automatenHandling().allesButton(getSeleked_fromList()));
        button_neu.addEventHandler(ActionEvent.ACTION,(e)->
        {
           new TabbedPane_automatenHandling().neuButton(name.getText(),Util.StringToPflanzenart(arten.getSelectionModel().getSelectedItem().toString()),Integer.parseInt(anz.getText()));
           liste_FelderListe_aneigen();
        });
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

        gridPane.add(new Pie_Chart_geerntet().pieChart_geertntet(),1,1);
        gridPane.add(new BarChart_ErwarteErnte().BarChart_ErwarteErnte(),2,1);

        listContent.setItems(liste_FelderListe_aneigen());
        listContent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listContent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Tab tab = new Tab(newValue);
            tab.setContent(new BarChart_PflanzenHoehe().BarCartPfanenHoehe(newValue));
            konsoloe.getTabs().add(tab); });
        listContent.setPrefSize(170,200);
        listContent.setMaxSize(170,300);
        border.setLeft(listContent);
        Label ueberschrift = new Label(I18n.getText("fields"));
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
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        border.setCenter(gridPane);

        Label labelSpeicherart = new Label();
        labelSpeicherart.setFont(Util.textFont);
        labelSpeicherart.setText(I18n.getText("memory_Type"));
        labelSpeicherart.setTextFill(Color.WHITE);
        ObservableList<String> speicherarten = FXCollections.observableArrayList (".CSV",I18n.getText("Database"));
        ComboBox speicherart = new ComboBox();
        speicherart.setItems(speicherarten);
        speicherart.getSelectionModel().select(I18n.getText(Nutzer.aktuellerNutzer.getSpeichart()));
        speicherart.addEventHandler(ActionEvent.ACTION,(e)->speichernCombobox(speicherart.getSelectionModel().getSelectedItem().toString()));

        Label labelSprache = new Label();
        labelSprache.setFont(Util.textFont);
        labelSprache.setText((I18n.getText("language")));
        labelSprache.setTextFill(Color.WHITE);
        ObservableList<String> sprachen = FXCollections.observableArrayList (I18n.getText("German"),I18n.getText("English"));
        ComboBox comboBoxSprachen = new ComboBox();
        comboBoxSprachen.setItems(sprachen);
        comboBoxSprachen.getSelectionModel().select(I18n.getText(Nutzer.aktuellerNutzer.getSprache()));
        comboBoxSprachen.addEventHandler(ActionEvent.ACTION,(e)-> sprachenCombobox(comboBoxSprachen.getSelectionModel().getSelectedItem().toString()));

        Button buttonNeuesPw = new Button(I18n.getText("New_Password"));
        buttonNeuesPw.setFont(Util.textFont);
        PasswordField neuesPW1 = new PasswordField();
        neuesPW1.setPromptText(I18n.getText("password"));
        PasswordField neuesPW2 = new PasswordField();
        neuesPW2.setPromptText(I18n.getText("Confirm_Password"));
        buttonNeuesPw.addEventHandler(ActionEvent.ACTION,(e)-> neuesPW(neuesPW1.getText(),neuesPW2.getText()));

        gridPane.add(labelSpeicherart,1,1);
        gridPane.add(speicherart,1,2);
        gridPane.add(labelSprache,3,1);
        gridPane.add(comboBoxSprachen,3,2);
        gridPane.add(neuesPW1,5,1);
        gridPane.add(neuesPW2,5,2);
        gridPane.add(buttonNeuesPw,5,3);
        gridPane.add(hinweisNeuesPw,5,4);

        return stackPane;
    }

    private void neuesPW(String pw1,String pw2){
        if (pw1.equals(pw2)){
            Nutzer.aktuellerNutzer.setPasswort(pw1);
        }else {
            hinweisNeuesPw.setFont(Util.hinweisFont);
            hinweisNeuesPw.setTextFill(Color.RED);
            hinweisNeuesPw.setText(I18n.getText("Wrong_entry"));
        }
    }

    private void speichernCombobox(String selected){
        Nutzer.aktuellerNutzer.setSpeichart(selected);
    }

    private void sprachenCombobox(String selected){
        Nutzer.aktuellerNutzer.setSprache(selected);
    }

    private ObservableList liste_FelderListe_aneigen(){

        ArrayList<String> namen_felder_des_Nutzers = new ArrayList<>();
        for (Feld feld : felder_des_Nutzers) {
                namen_felder_des_Nutzers.add(feld.getName());
        }

        ObservableList<String> items = FXCollections.observableArrayList (
                namen_felder_des_Nutzers);

        return items;
    }

    private void listenStatsGeneriren(){

        try {
            for (Feld feld : LesenUndSchreibenLernen.getFelder_inhalt()) {
                if (feld.getGehoertZuNutzer().equals(Nutzer.aktuellerNutzer.getName())) {
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

    public List<String> getSeleked_fromList(){
        //ArrayList<String> ausgewaehlt = new ArrayList<>();
        //for (String item : list.getSelectionModel().getSelectedItems()) {
        //    ausgewaehlt.add(item);
        //}
        //return ausgewaehlt;
       return selektierteFelderDesNutzer;
    }

    public static Integer getVorrausichlicheErnte(Feld feld){

        int anzVorrausichtlicheErnte=0;
        for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
            if (pflanze.getHoehe()+pflanze.getPflanzenArten().getWachstum()*1.5>=pflanze.getPflanzenArten().getErntehöhe()){
                anzVorrausichtlicheErnte++;
            }
        }
        return anzVorrausichtlicheErnte;
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