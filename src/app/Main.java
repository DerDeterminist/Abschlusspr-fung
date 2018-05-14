package app;

import java.util.Locale;

import dao.LesenUndSchreibenLernen;
import gui.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
   public static void main(String[] args)
   {
      I18n.i18nSetup(Locale.GERMAN);

      // Daten aus CSV einlesen
      LesenUndSchreibenLernen.nutzerLesen();

      launch(args);
   }


   @Override
   public void start(Stage primaryStage)
   {
      new Login().login(primaryStage);
   }
}