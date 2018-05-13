package pflanzen;

public class Weizen extends FeldPflanzen
{

   public Weizen()
   {
      name = "Weizen";
      hoehe = -10d;
      pflanzenArten = PflanzenArten.Weizen;
   }

   public Weizen(double hoehe)
   {
      name = "Weizen";
      this.hoehe = hoehe;
      pflanzenArten = PflanzenArten.Weizen;
   }

   public Weizen(String name, double hoehe, PflanzenArten pflanzenArten)
   {
      this.name = name;
      this.hoehe = hoehe;
      this.pflanzenArten = pflanzenArten;
   }
}
