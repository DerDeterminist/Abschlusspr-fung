package app;

import dao.LesenUndSchreibenLernen;
import pflanzen.FeldPflanzen;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Feld {

    PflanzenArten pflanzenArten;
    int pflanzenAnz = 50;
    String gehoertZuNutzer;
    String name;
    ArrayList<FeldPflanzen> pflanzenliste = new ArrayList<>();


    public Feld(String name,PflanzenArten pflanzenArten){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        feldEinrichten();
    }

    public Feld(String name,PflanzenArten pflanzenArten,int pflanzenAnz,String gehoertZuNutzer){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        this.pflanzenAnz = pflanzenAnz;
        this.gehoertZuNutzer = gehoertZuNutzer;
        feldEinrichten();
    }

    public Feld(String name,PflanzenArten pflanzenArten,ArrayList<FeldPflanzen> pflanzenliste,int pflanzenAnz,String gehoertZuNutzer){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        this.pflanzenliste = pflanzenliste;
        this.pflanzenAnz = pflanzenAnz;
        this.gehoertZuNutzer = gehoertZuNutzer;
    }

    public void feldEinrichten(){
        if (pflanzenArten == PflanzenArten.Weizen) {
            for (int i = 0; i < pflanzenAnz; i++) {
                pflanzenliste.add(new Weizen());
            }
        }
        if (pflanzenArten == PflanzenArten.Mais) {
            for (int i = 0; i < pflanzenAnz; i++) {
                pflanzenliste.add(new Mais());
            }
        }
        LesenUndSchreibenLernen.getFelder_inhalt().add(this);
    }


    public String getGehoertZuNutzer() { return gehoertZuNutzer; }

    public void setGehoertZuNutzer(String gehoertZuNutzer) {
        this.gehoertZuNutzer = gehoertZuNutzer;
    }

    public PflanzenArten getPflanzenArten() {
        return pflanzenArten;
    }

    public void setPflanzenArten(PflanzenArten pflanzenArten) {
        this.pflanzenArten = pflanzenArten;
    }

    public int getPflanzenAnz() {
        return pflanzenAnz;
    }

    public void setPflanzenAnz(int pflanzenAnz) {
        this.pflanzenAnz = pflanzenAnz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FeldPflanzen> getPflanzenliste() {
        return pflanzenliste;
    }

    public void setPflanzenliste(ArrayList<FeldPflanzen> pflanzenliste) {
        this.pflanzenliste = pflanzenliste;
    }
}
