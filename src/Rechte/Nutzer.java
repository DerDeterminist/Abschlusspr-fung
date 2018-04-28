package Rechte;

import java.util.ArrayList;

public class Nutzer {

    String name;
    String passwort;

    Nutzer aktuellerNutzer;

    public Nutzer(String name, String passwort){
        this.name = name;
        this.passwort = passwort;
    }

    public void setAktuellerNutzer(Nutzer aktuellerNutzer) {
        this.aktuellerNutzer = aktuellerNutzer;
    }

    public Nutzer getAktuellerNutzer() {
        return aktuellerNutzer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
