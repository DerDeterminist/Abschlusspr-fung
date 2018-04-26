package automaten;

import app.Feld;
import pflanzen.FeldPflanzen;

import java.util.ArrayList;

public class ErntAutomat implements Automaten{
    @Override
    public void arbeiten(ArrayList<Feld> felder) {
        erten(felder);
    }

    private void erten(ArrayList<Feld> felder){
        for (Feld feld : felder) {
            ArrayList<FeldPflanzen> zuErnten = new ArrayList<>();
            for (FeldPflanzen pflanze : feld.getPflanzenliste()) {
                if (pflanze.getHoehe()>pflanze.getPflanzenArten().getWachstum()){
                    zuErnten.add(pflanze);
                }
            }
            feld.getPflanzenliste().removeAll(zuErnten);
        }
    }
}
