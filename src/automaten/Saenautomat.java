package automaten;

import app.Feld;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;


public class Saenautomat implements Automaten
{

   @Override
   public void arbeiten(Feld feld)
   {
      saeen(feld);
   }

   // Pflanzen hinzufügen, wenn weniger als Wert im Feld
   private synchronized void saeen(Feld feld)
   {
      new Thread(() ->
      {
         while( feld.getPflanzenliste().size() < feld.getPflanzenAnz() )
         {
            if( feld.getPflanzenArten() == PflanzenArten.Mais )
            {
               feld.getPflanzenliste().add(new Mais());
            }
            if( feld.getPflanzenArten() == PflanzenArten.Weizen )
            {
               feld.getPflanzenliste().add(new Weizen());
            }
         }
      }).start();
   }
}
