package app;

public class Nutzer {

    String name;
    String passwort;

    static Nutzer aktuellerNutzer;

    public Nutzer(String name, String passwort){
        this.name = name;
        this.passwort = passwort;
    }

    public static void setAktuellerNutzer(Nutzer aktuellerNutzerIn) {
        aktuellerNutzer = aktuellerNutzerIn;
    }

    public static Nutzer getAktuellerNutzer() {
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
