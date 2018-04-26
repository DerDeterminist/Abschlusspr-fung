package automaten;

import app.Feld;
import pflanzen.FeldPflanzen;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.util.ArrayList;
import java.util.List;

public class Saenautomat implements Automaten{

    @Override
    public void arbeiten(ArrayList<Feld> felder) {
        saeen(felder);
    }

    private void saeen(ArrayList<Feld> felder){
        for (Feld feld : felder) {
            while (feld.getPflanzenliste().size() < feld.getPflanzenAnz()) {
                if (feld.getPflanzenArten() == PflanzenArten.Mais) {
                    feld.getPflanzenliste().add(new Mais());
                }
                if (feld.getPflanzenArten() == PflanzenArten.Weizen) {
                    feld.getPflanzenliste().add(new Weizen());
                }
            }
        }
    }
}
