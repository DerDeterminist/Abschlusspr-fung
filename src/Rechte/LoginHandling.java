package Rechte;

import app.Util;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginHandling {

    public void neuerNutzer(String name,String passwort){
        boolean nutzerSchonVorhanden = false;
        if (name==null) {
            Nutzer neuerNutzer = new Nutzer(name, passwort);
            for (Nutzer nutzer : Util.getAlleNutzer()) {
                if (nutzer.getName()==neuerNutzer.getName()){
                    nutzerSchonVorhanden = true;
                }
            }
            if (!nutzerSchonVorhanden){
                Util.getAlleNutzer().add(neuerNutzer);
                neuerNutzer.setAktuellerNutzer(neuerNutzer);
            }else {
                JOptionPane.showMessageDialog(null,"Dieser Benutzername ist schon vergeben");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Bitte geben Sie einen Benutzernamen ein");
        }
    }

    public void loginHandling(ArrayList<Nutzer> alleNutzer,String name,String passwort){

    }

}
