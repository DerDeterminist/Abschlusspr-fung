package dao;

import app.Nutzer;
import app.Feld;
import pflanzen.FeldPflanzen;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.io.*;
import java.util.ArrayList;

public class LesenUndSchreibenLernen
{

   public static ArrayList<Nutzer> nutzerInhalt = new ArrayList<>();
   public static ArrayList<String> felderNamen = new ArrayList<>();

   public static void felderSchreiben()
   {

      PrintWriter pw = null;
      try
      {
         pw = new PrintWriter(new File("res\\index\\" + Nutzer.aktuellerNutzer.getName() + "-index.csv"));
         for( Feld feld : Nutzer.aktuellerNutzer.getNutzerFelder() )
         {
            pw.println(feld.getName() + ";" + feld.getPflanzenArten() + ";" + feld.getPflanzenAnz() + ";" + feld.getGehoertZuNutzer());
            if( Nutzer.aktuellerNutzer.getSpeichart() == 0 )
            {
               pflanzenSchreiben(feld);
            }
            else
            {
               MitDBreden.pflanzenSchreiben(feld);
            }

         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         if( pw != null )
         {
            pw.close();
         }
      }
   }


   public static void felderLesen()
   {
      try
      {
         BufferedReader br = new BufferedReader(new FileReader(new File("res\\index\\" + Nutzer.aktuellerNutzer.getName() + "-index.csv")));

         String line = null;
         while( (line = br.readLine()) != null )
         {
            felderNamen.add(line);
            PflanzenArten temp = null;
            String[] objekte = line.split(";");
            if( objekte[1].equals("Mais") )
            {
               temp = PflanzenArten.Mais;
            }
            if( objekte[1].equals("Weizen") )
            {
               temp = PflanzenArten.Weizen;
            }
            if( objekte[3].equals(Nutzer.aktuellerNutzer.getName()) )
            {
               if( Nutzer.aktuellerNutzer.getSpeichart() == 0 )
               {
                  Nutzer.aktuellerNutzer.getNutzerFelder()
                        .add(new Feld(objekte[0], temp, pflanzenLesen(objekte[0]), Integer.parseInt(objekte[2]), objekte[3]));
               }
               if( Nutzer.aktuellerNutzer.getSpeichart() == 1 )
               {
                  Nutzer.aktuellerNutzer.getNutzerFelder()
                        .add(new Feld(objekte[0], temp, MitDBreden.pflanzenLesen(objekte[0]), Integer.parseInt(objekte[2]), objekte[3]));
               }
            }


         }
         br.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }

   private static void pflanzenSchreiben(Feld feld)
   {

      PrintWriter pw = null;
      try
      {
         pw = new PrintWriter(new File("res\\felder\\" + Nutzer.aktuellerNutzer.getName() + "-" + feld.getName() + ".csv"));
         for( FeldPflanzen pflanze : feld.getPflanzenliste() )
         {
            pw.println(pflanze.getName() + ";" + pflanze.getHoehe() + ";" + pflanze.getPflanzenArten());
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         if( pw != null )
         {
            pw.close();
         }
      }

   }

   private static ArrayList pflanzenLesen(String dateiName)
   {
      ArrayList<FeldPflanzen> pflanzenArtenArrayList = new ArrayList<>();

      try
      {
         BufferedReader br = new BufferedReader(
               new FileReader(new File("res\\felder\\" + Nutzer.aktuellerNutzer.getName() + "-" + dateiName + ".csv")));

         String line = null;
         while( (line = br.readLine()) != null )
         {

            PflanzenArten temp = null;
            String[] objekte = line.split(";");
            if( objekte[2].equals("Mais") )
            {
               temp = PflanzenArten.Mais;
               pflanzenArtenArrayList.add(new Mais(objekte[0], Double.parseDouble(objekte[1]), temp));
            }
            if( objekte[2].equals("Weizen") )
            {
               temp = PflanzenArten.Weizen;
               pflanzenArtenArrayList.add(new Weizen(objekte[0], Double.parseDouble(objekte[1]), temp));
            }
         }
         br.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      return pflanzenArtenArrayList;
   }


   public static void nutzerSchreiben()
   {

      PrintWriter pw = null;
      try
      {
         pw = new PrintWriter(new File("res\\index\\nutzer.csv"));
         for( Nutzer nutzer : nutzerInhalt )
         {
            pw.println(nutzer.getName() + ";" + nutzer.getPasswort() + ";" + nutzer.getSpeichart() + ";" + nutzer.getSprache() + ";" + nutzer
                  .getMaisGeerntet() + ";" + nutzer.getWeizenGeerntet());
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         if( pw != null )
         {
            pw.close();
         }
      }
   }

   public static ArrayList nutzerLesen()
   {

      try
      {
         BufferedReader br = new BufferedReader(new FileReader(new File("res\\index\\nutzer.csv")));

         String line = null;
         while( (line = br.readLine()) != null )
         {

            String[] objekte = line.split(";");
            nutzerInhalt
                  .add(new Nutzer(objekte[0], objekte[1], Integer.parseInt(objekte[2]), Integer.parseInt(objekte[3]), Integer.parseInt(objekte[4]),
                        Integer.parseInt(objekte[5])
                  ));
         }
         br.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      return nutzerInhalt;
   }
}