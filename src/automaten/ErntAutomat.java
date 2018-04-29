package automaten;

import app.Feld;
import pflanzen.FeldPflanzen;

import java.util.ArrayList;

public class ErntAutomat implements Automaten{
    @Override
    public void arbeiten(Feld feld) {
        erten(feld);
    }

    private void erten(Feld feld){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<FeldPflanzen> zuErnten = new ArrayList<>();
                for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                    if (pflanze.getHoehe()>pflanze.getPflanzenArten().getWachstum()){
                        zuErnten.add(pflanze);
                    }
                }
                feld.getPflanzenliste().removeAll(zuErnten);
            }
        });
    }
}
