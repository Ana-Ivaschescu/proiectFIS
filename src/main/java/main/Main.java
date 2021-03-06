package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.PathHolder;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/welcome.fxml")));
        primaryStage.setTitle("BC App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


}

