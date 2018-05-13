package gui_handling;

import app.Feld;
import app.I18n;
import app.Nutzer;
import dao.LesenUndSchreibenLernen;
import dao.MitDBreden;
import gui.Login;
import gui.TabbedPane;
import javafx.scene.control.Label;
import pflanzen.PflanzenArten;

import java.util.ArrayList;
import java.util.Locale;

public class LoginHandling
{

   public void neuerNutzer(String name, String passwort, Label hinweis)
   {
      boolean nutzerSchonVorhanden = false;


      if( !name.equals("") )
      {
         Nutzer neuerNutzer = new Nutzer(name, passwort, 0, 0);
         for( Nutzer nutzer : (ArrayList<Nutzer>) LesenUndSchreibenLernen.nutzerLesen() )
         {
            if( nutzer.getName() == neuerNutzer.getName() )
            {
               nutzerSchonVorhanden = true;
            }
         }
         if( !nutzerSchonVorhanden )
         {
            LesenUndSchreibenLernen.nutzerInhalt.add(neuerNutzer);
            Nutzer.aktuellerNutzer = neuerNutzer;
            new Feld(I18n.getText("wheat_field") + neuerNutzer.getName(), PflanzenArten.Weizen, 50, neuerNutzer.getName());
            new Feld(I18n.getText("maisfeld") + neuerNutzer.getName(), PflanzenArten.Mais, 50, neuerNutzer.getName());
            new TabbedPane().starteProgramm(Login.getPrimaryStage());
         }
         else
         {
            hinweis.setText(I18n.getText("Username_is_already_taken"));
         }
      }
      else
      {
         hinweis.setText(I18n.getText("please_enter_user_name"));
      }
   }

   public void loginHandling(ArrayList<Nutzer> alleNutzer, String name, String passwort, Label hinweis)
   {
      for( Nutzer nutzer : alleNutzer )
      {
         if( nutzer.getName().equals(name) && nutzer.getPasswort().equals(passwort) )
         {
            Nutzer.aktuellerNutzer = nutzer;
            LesenUndSchreibenLernen.felderLesen();
            if (Nutzer.aktuellerNutzer.getSprache()==0){
               I18n.i18nSetup(Locale.GERMAN);
            }
            else {
               I18n.i18nSetup(Locale.ENGLISH);
            }
            if( Nutzer.aktuellerNutzer.getSpeichart() == 1 )
            {
               MitDBreden.createConnection();
            }
            new TabbedPane().starteProgramm(Login.getPrimaryStage());
         }
         else
         {
            hinweis.setText(I18n.getText("wrong_credentials"));
         }
      }
   }
}
