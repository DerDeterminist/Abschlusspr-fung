package automaten;

import app.Feld;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

public class GießAutomat implements Automaten{

    @Override
    public void arbeiten(ArrayList<Feld> felder) {
        gießen(felder);
    }

    private void gießen(ArrayList<Feld> felder) {
        for (Feld feld : felder) {
            if (feld.getPflanzenArten() == PflanzenArten.Mais) {
                for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                    pflanze.setHoehe(pflanze.getHoehe() + PflanzenArten.Mais.getWachstum());
                }
            }
            if (feld.getPflanzenArten() == PflanzenArten.Weizen) {
                for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                    pflanze.setHoehe(pflanze.getHoehe() + PflanzenArten.Weizen.getWachstum());
                }
            }
        }
    }
}
