package org.stick;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Main extends Application {

    Scene scene;
    @FXML
    AnchorPane rootPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader endLoader = new FXMLLoader(getClass().getResource("/scene2.fxml"));
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/stick.fxml"));
        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("/start.fxml"));
        Parent end = endLoader.load();
        Parent start = startLoader.load();
        Parent game = gameLoader.load();
        Controller controller = startLoader.getController();
        scene = new Scene(start);
        primaryStage.setScene(scene);
        controller.setStage(primaryStage);
        primaryStage.show();
    }
}
