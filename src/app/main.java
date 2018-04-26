package app;

import automaten.ErntAutomat;
import automaten.GießAutomat;
import automaten.Saenautomat;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

public class main {
    public static void main(String[] args) {

        Feld feld = new Feld("sdf",PflanzenArten.Mais);
        GießAutomat gießAutomat = new GießAutomat();
        ErntAutomat erntAutomat = new ErntAutomat();
        Saenautomat saenautomat = new Saenautomat();
        for (int i=0;i<60;i++) {
            gießAutomat.arbeiten(feld.getFelder());
        }
        erntAutomat.arbeiten(feld.getFelder());
        saenautomat.arbeiten(feld.getFelder());

        for (Feld feld1 : feld.getFelder()) {
            System.out.println(feld1.getPflanzenliste().size());
            for (FeldPflanzen pflanze : feld1.getPflanzenliste()) {
                System.out.println(pflanze.getHoehe());
            }
        }
    }
}
