package gui_handling;

import app.Feld;
import app.Nutzer;
import app.i18n;
import dao.LesenUndSchreibenLernen;
import gui.Login;
import gui.TabbedPane;
import javafx.scene.control.Label;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

public class LoginHandling {

    public void neuerNutzer(String name, String passwort,Label hinweis) {
        boolean nutzerSchonVorhanden = false;


        if (!name.equals("")) {
            Nutzer neuerNutzer = new Nutzer(name, passwort,".CSV","deutsch");
            for (Nutzer nutzer : (ArrayList<Nutzer>) LesenUndSchreibenLernen.nutzerLesen()) {
                if (nutzer.getName() == neuerNutzer.getName()) {
                    nutzerSchonVorhanden = true;
                }
            }
            if (!nutzerSchonVorhanden) {
                LesenUndSchreibenLernen.getNutzer_inhalt().add(neuerNutzer);
                Nutzer.aktuellerNutzer=neuerNutzer;
                new Feld("Weizenfeld "+neuerNutzer.getName(), PflanzenArten.Weizen,50,neuerNutzer.getName());
                new Feld("Maisfeld "+neuerNutzer.getName(),PflanzenArten.Mais,50,neuerNutzer.getName());
                new TabbedPane().starteProgramm(Login.getPrimaryStage());
            } else {
                hinweis.setText("Dieser Benutzername ist schon vergeben");
            }
        } else {
            hinweis.setText("Bitte geben Sie einen Benutzernamen ein");
        }
    }

    public void loginHandling(ArrayList<Nutzer> alleNutzer, String name, String passwort, Label hinweis) {
        for (Nutzer nutzer : alleNutzer) {
            if (nutzer.getName().equals(name) && nutzer.getPasswort().equals(passwort)) {
                Nutzer.aktuellerNutzer=nutzer;
                // todo bug
//                i18n.i18nSetup(Nutzer.aktuellerNutzer.getSprache());
                new TabbedPane().starteProgramm(Login.getPrimaryStage());
            }else {
                hinweis.setText("Falsche Anmeldedaten");
            }
        }
    }
}
