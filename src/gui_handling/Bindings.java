package gui_handling;

import gui.Login;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Bindings implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Updates task = new Updates();

        new Login().getHinweis().textProperty().bind(task.messageProperty());
    }
}

