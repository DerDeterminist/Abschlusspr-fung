package gui_handling;

import app.I18n;
import dao.LesenUndSchreibenLernen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class System_exit {

    public static void exit(){


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(I18n.getText("close"));
        alert.setHeaderText(I18n.getText("you_quit?"));
//        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            LesenUndSchreibenLernen.felderSchreiben();
            LesenUndSchreibenLernen.nutzerSchreiben();
            System.exit(0);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}
