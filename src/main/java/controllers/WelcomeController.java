package controllers;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import utils.PathHolder;

public class WelcomeController {

    @FXML
    public Text welcomeMessage;

    public void handleSignUpAction() {
        try {
            Stage stage = (Stage) welcomeMessage.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/sign_up.fxml")));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
            System.out.println("sign up");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleSignInAction() {
        try {
            Stage stage = (Stage) welcomeMessage.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/sign_in.fxml")));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
            System.out.println("sign in");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
