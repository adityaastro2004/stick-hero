package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene1 implements Initializable {
    @FXML
    Button play ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        play.setOnAction(e -> {
            try {
                login();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(play);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(javafx.util.Duration.seconds(4));
        translate.setByY(25);
        translate.setAutoReverse(true);
        translate.play();


    }

    public void login() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/Scene2.fxml"));
        Scene scene = new Scene(loader.load() , 600 , 400);


        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));

        Game.getStage().setScene(scene);

        // This will be initialized when the Main class is run .


    }




}