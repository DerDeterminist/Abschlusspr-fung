package gui;

import app.I18n;
import app.Util;
import gui_handling.LoginHandling;
import dao.LesenUndSchreibenLernen;
import gui_handling.System_exit;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Login
{

   public Label hinweis = new Label("");

   static Stage primaryStage;

   public void login(Stage primaryStage)
   {
      Login.primaryStage = primaryStage;
      hinweis.setTextFill(Color.RED);
      hinweis.setFont(Util.hinweisFont);

      primaryStage.setResizable(false);
      primaryStage.setTitle(I18n.getText("login"));
      primaryStage.setOnCloseRequest((e) -> {
         e.consume();
         System_exit.exit();
      });


      PasswordField accPwIn = new PasswordField();
      accPwIn.setPromptText(I18n.getText("password"));
      TextField accNameIn = new TextField();
      accNameIn.setPromptText(I18n.getText("username"));
      Button _newAcc = new Button("new_account");
      _newAcc.addEventHandler(ActionEvent.ACTION, (e) -> new LoginHandling().neuerNutzer(accNameIn.getText(), accPwIn.getText(), hinweis));
      Button login = new Button(I18n.getText("login"));
      login.addEventHandler(
            ActionEvent.ACTION,
            (e) -> new LoginHandling().loginHandling(LesenUndSchreibenLernen.nutzerInhalt, accNameIn.getText(), accPwIn.getText(), hinweis)
      );

      StackPane p1 = new StackPane();
      ImageView background = null;
      try
      {
         background = new ImageView(new Image(new File("res\\images\\the-simple-farm-logo-transpare.png").toURL().toString()));
      }
      catch(MalformedURLException e)
      {
         e.printStackTrace();
      }

      p1.getChildren().add(background);
      GridPane p1_2 = new GridPane();
      p1_2.setHgap(10);
      p1_2.setVgap(10);
      p1_2.setPadding(new Insets(0, 10, 0, 10));
      p1_2.add(accNameIn, 4, 3);
      p1_2.add(accPwIn, 5, 3);
      p1_2.add(_newAcc, 4, 4);
      p1_2.add(login, 5, 4);
      p1_2.add(hinweis, 4, 5);
      p1.getChildren().add(p1_2);


      Scene scene = new Scene(p1, 600, 450);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   public static Stage getPrimaryStage()
   {
      return primaryStage;
   }
}
