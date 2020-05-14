package controllers;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class WelcomeController {

    @FXML
    public Text welcomeMessage;
    public void handleSignInAction()
    {
       /*try {
            Stage stage = (Stage) welcomeMessage.getScene().getWindow();
            Parent sign_in_root = FXMLLoader.load(getClass().getResource("../fxml/sign_in.fxml"));
            Scene scene = new Scene(sign_in_root, 600, 400);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
       System.out.println("Sign in");
    }
    public void handleSignUpAction() {
        try {
            Stage stage = (Stage) welcomeMessage.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(getClass().getResource("../fxml/sign_up.fxml"));
            Scene scene = new Scene(sign_up_root, 600, 400);
            stage.setScene(scene);
            System.out.println("sign up");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}