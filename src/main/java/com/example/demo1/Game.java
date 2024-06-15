package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {
    private static Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        initialize();
        try {
            loadFXML();
        } catch (IOException e) {
            // Handle the exception (log or show an error dialog)
            e.printStackTrace();
        }
        Thread musicThread = new Thread(() -> playBackgroundMusic("song.mp3"));
        musicThread.setDaemon(true); // Daemon thread so it doesn't prevent application shutdown
        musicThread.start();
    }


    private void initialize() {
        // Perform any initialization tasks here
    }


private void playBackgroundMusic(String audioFile) {
    Media media = new Media(Objects.requireNonNull(getClass().getResource(audioFile)).toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.play();
    try {
        Thread.sleep(10000); // Sleep for some time to prevent the loop from hogging resources
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
}

    private void loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("/com/example/demo1/Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 395);
        window.setScene(scene);
        window.setTitle("Hello Application");
        window.setResizable(false);
        window.show();
    }


    public  static Stage getStage() {
        return window;
    }
}
