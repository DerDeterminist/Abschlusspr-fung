package gui_handling;

import app.Feld;
import app.Nutzer;
import app.Util;
import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import dao.LesenUndSchreibenLernen;
import gui.TabbedPane;
import pflanzen.PflanzenArten;

import java.util.ArrayList;
import java.util.List;

public class TabbedPane_automatenHandling {

    public void erntenButton(List<String> ausgewaelt){
        ErntAutomat erntAutomat = new ErntAutomat();
        for (Feld feld  : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
               erntAutomat.arbeiten(feld);
            }
        }
    }
    public void geißenButton(List<String> ausgewaelt){
        GießAutomat gießAutomat = new GießAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
                gießAutomat.arbeiten(feld);
            }
        }
    }
    public void saeenButton(List<String> ausgewaelt){
        Saenautomat saenautomat = new Saenautomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
                saenautomat.arbeiten(feld);
            }
        }
    }
    public void allesButton(List<String> ausgewaelt){
        Saenautomat saenautomat = new Saenautomat();
        GießAutomat gießAutomat = new GießAutomat();
        ErntAutomat erntAutomat = new ErntAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
                saenautomat.arbeiten(feld);
                gießAutomat.arbeiten(feld);
                erntAutomat.arbeiten(feld);
                //todo fragen: muss die nächste zeile oder reicht beim programm beenden?
                LesenUndSchreibenLernen.felderSchreiben();
            }
        }
    }
    public void neuButton(String name,PflanzenArten pflanzenArten,int pflanzenAnz){

        Feld feld = new Feld(name,pflanzenArten,pflanzenAnz, Nutzer.aktuellerNutzer.getName());
        TabbedPane.felder_des_Nutzers.add(feld);
    }

    public void loeschenButton(List<String> ausgewaelt) {
        for (String s : ausgewaelt) {
            LesenUndSchreibenLernen.getFelder_inhalt().remove(Util.getFeldByName(s));
        }
    }


//    public static TableView tabelleErstellen(String feldname){
//        TableView table = new TableView();
//        table.setPrefHeight(250);
//        TableColumn name = new TableColumn("Name");
//        name.setCellValueFactory(new PropertyValueFactory<FeldPflanzen,String>("name"));
//        TableColumn hoehe = new TableColumn("Höhe");
//        //todo höhe und pflanzenart wird nicht angezeigt
//        name.setCellValueFactory(new PropertyValueFactory<FeldPflanzen,Double>("hoehe"));
//        TableColumn pflanzenart = new TableColumn("Pflanzenart");
//        name.setCellValueFactory(new PropertyValueFactory<FeldPflanzen, PflanzenArten>("pflanzenArten"));
//        table.getColumns().addAll(name,hoehe,pflanzenart);
//        table.setItems(Util.getFeldpflanzenObservable(Util.getFeldByName(feldname)));
//        return table;
//    }
}
