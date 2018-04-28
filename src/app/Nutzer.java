package app;

public class Nutzer {

    String name;
    String passwort;

    public Nutzer(String name, String passwort){
        this.name = name;
        this.passwort = passwort;
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
