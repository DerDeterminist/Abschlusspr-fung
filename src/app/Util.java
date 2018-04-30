package app;

import dao.LesenUndSchreibenLernen;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import org.omg.CORBA.MARSHAL;
import pflanzen.FeldPflanzen;
import pflanzen.PflanzenArten;

import java.util.ArrayList;

public class Util {

    public static Font ueberschriftFont = new Font("Arial",18);
    public static Font textFont = new Font("Arial",15);

    public static Feld getFeldByName(String name) {
        Feld dasEineFeld = null;
        for (Feld feld : LesenUndSchreibenLernen.getFelder_inhalt()) {
            if (feld.getName().equals(name)) {
                dasEineFeld = feld;
            }
        }
        return dasEineFeld;
    }

    public static ObservableList<FeldPflanzen> getFeldpflanzenObservable(Feld feld){
        ObservableList<FeldPflanzen> rueckgabe = FXCollections.observableArrayList();
        rueckgabe.addAll(feld.getPflanzenliste());
        return rueckgabe;
    }
    public static ObservableList<String> getPflanzenartenObservable() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll(PflanzenArten.Mais.name(),PflanzenArten.Weizen.name());
        return options;
    }
    public static PflanzenArten StringToPflanzenart(String name){
        if (name.equals(PflanzenArten.Mais.name())){
           return PflanzenArten.Mais;
        }else {
            return PflanzenArten.Weizen;
        }
    }

    //idee um gui upzudaten

    public static void run(Runnable treatment) {
        if(treatment == null) throw new IllegalArgumentException("The treatment to perform can not be null");

        if(Platform.isFxApplicationThread()) treatment.run();
        else Platform.runLater(treatment);
    }
}
