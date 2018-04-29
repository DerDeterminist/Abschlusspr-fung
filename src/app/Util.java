package app;

import dao.LesenUndSchreibenLernen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pflanzen.FeldPflanzen;

import java.util.ArrayList;

public class Util {

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
}
