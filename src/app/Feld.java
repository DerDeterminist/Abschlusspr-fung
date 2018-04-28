package app;

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


    String name;

    ArrayList<FeldPflanzen> pflanzenliste = new ArrayList<>();
    public volatile ArrayList<Feld> felder = new ArrayList<>();


    public Feld(String name,PflanzenArten pflanzenArten){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        feldEinrichten();
    }

    public Feld(String name,PflanzenArten pflanzenArten,ArrayList<FeldPflanzen> pflanzenliste){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        this.pflanzenliste = pflanzenliste;
        feldEinrichten();
    }

    public Feld(String name,PflanzenArten pflanzenArten,ArrayList<FeldPflanzen> pflanzenliste,int pflanzenAnz){
        this.pflanzenArten = pflanzenArten;
        this.name = name;
        this.pflanzenliste = pflanzenliste;
        this.pflanzenAnz = pflanzenAnz;
        feldEinrichten();
    }

    private void feldEinrichten(){
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
        felder.add(this);
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

    public ArrayList<Feld> getFelder() {
        return felder;
    }
}
