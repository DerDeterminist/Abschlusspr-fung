package app;

public class Nutzer {

    String name;
    String passwort;
    String speichart;
    String sprache;
    int maisGeerntet;
    int weizenGeerntet;

    public static Nutzer aktuellerNutzer;

    public Nutzer(String name, String passwort, String speicherart, String sprache){
        this.name = name;
        this.passwort = passwort;
        this.speichart = speicherart;
        this.sprache = sprache;
    }

    public Nutzer(String name, String passwort, String speicherart, String sprache,int maisGeerntet,int weizenGeerntet){
        this.name = name;
        this.passwort = passwort;
        this.speichart = speicherart;
        this.sprache = sprache;
        this.maisGeerntet = maisGeerntet;
        this.weizenGeerntet = weizenGeerntet;
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

    public String getSpeichart() { return speichart; }

    public void setSpeichart(String speichart) { this.speichart = speichart; }

    public String getSprache() { return sprache; }

    public void setSprache(String sprache) { this.sprache = sprache; }

    public int getMaisGeerntet() { return maisGeerntet; }

    public void setMaisGeerntet(int maisGeerntet) { this.maisGeerntet = maisGeerntet; }

    public int getWeizenGeerntet() { return weizenGeerntet; }

    public void setWeizenGeerntet(int weizenGeerntet) { this.weizenGeerntet = weizenGeerntet; }
}
