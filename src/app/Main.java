package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import dao.LesenUndSchreibenLernen;
import dao.MitDBreden;
import gui.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
   //todo datenbank; dokumentation; codestyle

   public static void main(String[] args)
   {


      //        MitDBreden.createConnection();
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