package automaten;

import app.Feld;
import org.junit.Test;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import static org.junit.Assert.*;

public class AutomatTest {

    @Test
    public void AutomatenTest() throws Exception {
        Feld feld = new Feld("test", PflanzenArten.Mais);
        ErntAutomat erntAutomat = new ErntAutomat();
        GießAutomat gießAutomat = new GießAutomat();
        Saenautomat saenautomat = new Saenautomat();

        assertTrue(feld.getPflanzenliste().size()==feld.getPflanzenAnz());


        for (int i=0;i<51;i++){
            gießAutomat.arbeiten(feld);
            // da mutltithreading
            Thread.sleep(150);
        }


        for (FeldPflanzen pflanzen : feld.getPflanzenliste()) {
            assertTrue(pflanzen.getHoehe()>100);
        }

        erntAutomat.arbeiten(feld);

        // da mutltithreading
       Thread.sleep(150);

        assertTrue(0==feld.getPflanzenliste().size());

        saenautomat.arbeiten(feld);

        // da mutltithreading
        Thread.sleep(150);

        assertTrue(feld.getPflanzenliste().size()==feld.getPflanzenAnz());
    }
}