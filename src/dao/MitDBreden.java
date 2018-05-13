package dao;

import app.Feld;
import app.Nutzer;
import app.Util;
import gui.TabbedPane;
import pflanzen.FeldPflanzen;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.sql.*;
import java.util.ArrayList;

public class MitDBreden
{

   private static Statement statement;

   public static void createConnection()
   {
      try
      {
         Class.forName("com.mysql.jdbc.Driver");
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MYDB", "root", "");
         statement = connection.createStatement();
         statement.execute("CREATE DATABASE IF NOT EXISTS Pflanzen");
      }
      catch(ClassNotFoundException e)
      {
         e.printStackTrace();
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
   }

   public static ArrayList pflanzenLesen(String name)
   {
      Feld feld = Util.getFeldByName(name);
      ArrayList<FeldPflanzen> pflanzenArrayList = new ArrayList<>();
      try
      {
         ResultSet resultSet = statement.executeQuery("select * from" + feld.getName());
         while( resultSet.next() )
         {
            if( feld.getPflanzenArten() == PflanzenArten.Mais )
            {
               pflanzenArrayList.add(new Mais(resultSet.getDouble("hoehe")));
            }
            if( feld.getPflanzenArten() == PflanzenArten.Weizen )
            {
               pflanzenArrayList.add(new Weizen(resultSet.getDouble("hoehe")));
            }

         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      return pflanzenArrayList;
   }

   public static void pflanzenSchreiben(Feld feld)
   {
      try
      {
         statement.execute("CREATE TABLE IF NOT EXISTS Pflanzen." + feld.getName()
                           + "(nr INTEGER"
                           + "hoehe real" + ");");

         int couter = 0;
         for( FeldPflanzen feldPflanzen : feld.getPflanzenliste() )
         {
            statement.executeUpdate("INSERT INTO Pflanzen." + feld.getName() + "(hoehe) VALUES (" + feldPflanzen.getHoehe() + ");");
            couter++;
         }

         while( statement.execute("DELETE from orders " + feld.getName() + "IF EXISTS WHERE Nr = " + couter + " limit 1") )
         {
            couter++;
         }


      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
   }
}