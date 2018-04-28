package app;

import Rechte.Nutzer;

import java.util.ArrayList;

public class Util {
    public Util(){}
    public ArrayList<Feld> getFelder() {
        return felder;
    }

    public ArrayList<Nutzer> getAlleNutzer() {
        return alleNutzer;
    }

    public volatile ArrayList<Feld> felder = new ArrayList<>();
    ArrayList<Nutzer> alleNutzer = new ArrayList<>();
}
