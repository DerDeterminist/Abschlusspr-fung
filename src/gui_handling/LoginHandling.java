package gui_handling;

import app.Feld;
import app.Nutzer;
import dao.LesenUndSchreibenLernen;
import gui.Login;
import gui.TabbedPane;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

public class LoginHandling {
    Updates update = new Updates();
    Bindings bindings = new Bindings();


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
                Nutzer.aktuellerNutzer=neuerNutzer;
                new Feld("Weizenfeld "+neuerNutzer.getName(), PflanzenArten.Weizen,50,neuerNutzer.getName());
                new Feld("Maisfeld "+neuerNutzer.getName(),PflanzenArten.Mais,50,neuerNutzer.getName());
                new TabbedPane().starteProgramm(Login.getPrimaryStage());
            } else {
                update.updateTitle("Dieser Benutzername ist schon vergeben");
//                JOptionPane.showMessageDialog(null,"Dieser Benutzername ist schon vergeben");
            }
        } else {
            //todo warum wid der text nicht akutalisirt?
            update.updateTitle("Bitte geben Sie einen Benutzernamen ein");
//            JOptionPane.showMessageDialog(null,"Bitte geben Sie einen Benutzernamen ein");
        }
    }

    public void loginHandling(ArrayList<Nutzer> alleNutzer, String name, String passwort) {
        for (Nutzer nutzer : alleNutzer) {
            if (nutzer.getName().equals(name) && nutzer.getPasswort().equals(passwort)) {
                System.out.println("user überprüfung erfolgreich");
                Nutzer.aktuellerNutzer=nutzer;
                new TabbedPane().starteProgramm(Login.getPrimaryStage());
            }else {
                update.updateTitle("Falsche Anmeldedaten");
            }
        }
    }
}
