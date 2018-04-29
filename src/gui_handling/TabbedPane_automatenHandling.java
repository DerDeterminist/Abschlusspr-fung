package gui_handling;

import app.Feld;
import automaten.Automaten;
import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import dao.LesenUndSchreibenLernen;
import gui.TabbedPane;

import java.util.ArrayList;

public class TabbedPane_automatenHandling {

    public void erntenButton(ArrayList<String> ausgewaelt){
        ErntAutomat erntAutomat = new ErntAutomat();
        for (Feld feld  : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
               erntAutomat.arbeiten(feld);
            }
        }
    }
    public void geißenButton(ArrayList<String> ausgewaelt){
        GießAutomat gießAutomat = new GießAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
                gießAutomat.arbeiten(feld);
            }
        }
    }
    public void saeenButton(ArrayList<String> ausgewaelt){
        Saenautomat saenautomat = new Saenautomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())){
                saenautomat.arbeiten(feld);
            }
        }
    }
    public void allesButton(ArrayList<String> ausgewaelt){
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
}
