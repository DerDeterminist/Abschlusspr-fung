package gui_handling;

import app.Feld;
import app.I18n;
import app.Nutzer;
import app.Util;
import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import dao.LesenUndSchreibenLernen;
import gui.TabbedPane;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.util.ArrayList;
import java.util.List;

public class TabbedPane_automatenHandling {

    public void erntenButton(List<String> ausgewaelt) {
        ErntAutomat erntAutomat = new ErntAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())) {
                erntAutomat.arbeiten(feld);
            }
        }
    }

    public void geißenButton(List<String> ausgewaelt) {
        GießAutomat gießAutomat = new GießAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())) {
                gießAutomat.arbeiten(feld);
            }
        }
    }

    public void saeenButton(List<String> ausgewaelt) {
        Saenautomat saenautomat = new Saenautomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())) {
                saenautomat.arbeiten(feld);
            }
        }
    }

    public void allesButton(List<String> ausgewaelt) {
        Saenautomat saenautomat = new Saenautomat();
        GießAutomat gießAutomat = new GießAutomat();
        ErntAutomat erntAutomat = new ErntAutomat();
        for (Feld feld : TabbedPane.getFelder_des_Nutzers()) {
            if (ausgewaelt.contains(feld.getName())) {
                saenautomat.arbeiten(feld);
                gießAutomat.arbeiten(feld);
                erntAutomat.arbeiten(feld);
                //todo fragen: muss die nächste zeile oder reicht beim programm beenden?
                LesenUndSchreibenLernen.felderSchreiben();
            }
        }
    }

    public void neuButton(String name, PflanzenArten pflanzenArten, int pflanzenAnz) {

        PflanzenArten pflanzenArten1 = PflanzenArten.Mais;
        if (pflanzenArten.equals(I18n.getText("wheat"))) {
            pflanzenArten1 = PflanzenArten.Weizen;
        }
        Feld feld = new Feld(name, pflanzenArten1, pflanzenAnz, Nutzer.aktuellerNutzer.getName());
        TabbedPane.listAutomaten.getItems().add(feld.getName());
        TabbedPane.listContent.getItems().add(feld.getName());
        TabbedPane.listUebersicht.getItems().add(feld.getName());
    }

    public void loeschenButton(List<String> ausgewaelt) {
        for (String s : ausgewaelt) {
            LesenUndSchreibenLernen.getFelder_inhalt().remove(Util.getFeldByName(s));
        }
    }
}
