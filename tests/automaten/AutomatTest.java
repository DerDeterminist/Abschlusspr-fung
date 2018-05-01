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

//      feld.getPflanzenliste().get(0).setHoehe(0);
//        assertTrue(feld.getPflanzenliste().get(0).getHoehe()==-0);

        for (int i=0;i<51;i++){
            gießAutomat.arbeiten(feld);
            Thread.sleep(100);
        }


        for (FeldPflanzen pflanzen : feld.getPflanzenliste()) {
            assertTrue(pflanzen.getHoehe()==15);
        }

        erntAutomat.arbeiten(feld);

        assertTrue(0==feld.getPflanzenliste().size());

        saenautomat.arbeiten(feld);

        assertTrue(feld.getPflanzenliste().size()==feld.getPflanzenAnz());
    }
}