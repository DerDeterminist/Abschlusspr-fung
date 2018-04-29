package gui_handling;

import app.Nutzer;
import dao.LesenUndSchreibenLernen;
import gui.Login;
import gui.TabbedPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class LoginHandling {

    public void neuerNutzer(String name, String passwort) {
        boolean nutzerSchonVorhanden = false;


        if (name != null) {
            Nutzer neuerNutzer = new Nutzer(name, passwort);
            for (Nutzer nutzer : (ArrayList<Nutzer>) LesenUndSchreibenLernen.nutzerLesen()) {
                if (nutzer.getName() == neuerNutzer.getName()) {
                    nutzerSchonVorhanden = true;
                }
            }
            if (!nutzerSchonVorhanden) {
                LesenUndSchreibenLernen.getNutzer_inhalt().add(neuerNutzer);
                neuerNutzer.setAktuellerNutzer(neuerNutzer);
            } else {
                new Login().setHinweistext("Dieser Benutzername ist schon vergeben");
//                JOptionPane.showMessageDialog(null,"Dieser Benutzername ist schon vergeben");
            }
        } else {
            //todo warum wid der text nicht akutalisirt?
            new Login().setHinweistext("Bitte geben Sie einen Benutzernamen ein");
//            JOptionPane.showMessageDialog(null,"Bitte geben Sie einen Benutzernamen ein");
        }
    }

    public void loginHandling(ArrayList<Nutzer> alleNutzer, String name, String passwort) {
        for (Nutzer nutzer : alleNutzer) {
            if (nutzer.getName().equals(name) && nutzer.getPasswort().equals(passwort)) {
                System.out.println("user überprüfung erfolgreich");
                Nutzer.setAktuellerNutzer(nutzer);
                new TabbedPane().starteProgramm(Login.getPrimaryStage());
            }else {
                new Login().setHinweistext("Falsche Anmeldedaten");
            }
        }
    }
}
