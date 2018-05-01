package automaten;

import app.Feld;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

public class GießAutomat implements Automaten{

    @Override
    public void arbeiten(Feld feld) {
        gießen(feld);
    }

    // PflanzenHöhe +~ 1,5* Enum Wert
    private void gießen(Feld feld) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (feld.getPflanzenArten() == PflanzenArten.Mais) {
                    for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                        pflanze.setHoehe(pflanze.getHoehe() + PflanzenArten.Mais.getWachstum()+PflanzenArten.Mais.getWachstum()*Math.random());
                    }
                }
                if (feld.getPflanzenArten() == PflanzenArten.Weizen) {
                    for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                        pflanze.setHoehe(pflanze.getHoehe() + PflanzenArten.Weizen.getWachstum()+PflanzenArten.Weizen.getWachstum()*Math.random());
                    }
                }
            }
        });
    }
}
