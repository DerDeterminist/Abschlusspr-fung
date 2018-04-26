package pflanzen;

public class Mais extends FeldPflanzen {

   public Mais(){
       name = "Mais";
       hoehe = -10d;
       pflanzenArten = PflanzenArten.Mais;
   }

   public Mais(double hoehe) {
       name = "Mais";
       this.hoehe = hoehe;
       pflanzenArten = PflanzenArten.Mais;
   }
    public Mais(String name,double hoehe,PflanzenArten pflanzenArten){
        this.name = name;
        this.hoehe = hoehe;
        this.pflanzenArten = pflanzenArten;
    }

}
