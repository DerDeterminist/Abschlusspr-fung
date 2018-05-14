package app;

import java.util.ArrayList;

public class Nutzer
{

   String name;
   String passwort;
   int speichart;
   int sprache;
   int maisGeerntet;
   int weizenGeerntet;
   ArrayList<Feld> NutzerFelder = new ArrayList<>();

   public static Nutzer aktuellerNutzer;

   public Nutzer(String name, String passwort, Integer speicherart, Integer sprache)
   {
      this.name = name;
      this.passwort = passwort;
      this.speichart = speicherart;
      this.sprache = sprache;
   }

   public Nutzer(String name, String passwort, Integer speicherart, Integer sprache, int maisGeerntet, int weizenGeerntet)
   {
      this.name = name;
      this.passwort = passwort;
      this.speichart = speicherart;
      this.sprache = sprache;
      this.maisGeerntet = maisGeerntet;
      this.weizenGeerntet = weizenGeerntet;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getPasswort()
   {
      return passwort;
   }

   public void setPasswort(String passwort)
   {
      this.passwort = passwort;
   }

   public Integer getSpeichart()
   {
      return speichart;
   }

   public void setSpeichart(Integer speichart)
   {
      this.speichart = speichart;
   }

   public Integer getSprache()
   {
      return sprache;
   }

   public void setSprache(Integer sprache)
   {
      this.sprache = sprache;
   }

   public int getMaisGeerntet()
   {
      return maisGeerntet;
   }

   public int getWeizenGeerntet()
   {
      return weizenGeerntet;
   }

   public ArrayList<Feld> getNutzerFelder()
   {
      return NutzerFelder;
   }
}
