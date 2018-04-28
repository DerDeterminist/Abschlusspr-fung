package gui;

import Rechte.LoginHandling;
import Rechte.Nutzer;
import app.Util;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;

public class Login {

    public void login(Stage primaryStage){
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login");

        Label accPw = new Label("Passwort");
        Label accName = new Label("Benutzername");
        PasswordField accPwIn = new PasswordField();
        TextField accNameIn = new TextField();
        Button _newAcc = new Button("neues Konto");
        _newAcc.addEventHandler(ActionEvent.ACTION,(e) ->  new LoginHandling().neuerNutzer(accNameIn.getText(),accPwIn.getText()));
        Button login = new Button("login");
        login.addEventHandler(ActionEvent.ACTION,(e)-> new LoginHandling().loginHandling(new Util().getAlleNutzer(),accNameIn.getText(),accPwIn.getText()));

        StackPane p1 = new StackPane();
        File a = new File("res\\images\\the-simple-farm-logo-transpare.png");
        System.out.println(a.getAbsolutePath());
        ImageView background = null;
        try {
            background = new ImageView(new Image(new File("res\\images\\the-simple-farm-logo-transpare.png").toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        p1.getChildren().add(background);
        GridPane p1_2 = new GridPane();
        p1_2.setHgap(10);
        p1_2.setVgap(10);
        p1_2.setPadding(new Insets(0,10,0,10));
        p1_2.add(accName,4,2);
        p1_2.add(accPw,5,2);
        p1_2.add(accNameIn,4,3);
        p1_2.add(accPwIn,5,3);
        p1_2.add(_newAcc,4,4);
        p1_2.add(login,5,4);
        p1.getChildren().add(p1_2);



        Scene scene = new Scene(p1,600,450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
