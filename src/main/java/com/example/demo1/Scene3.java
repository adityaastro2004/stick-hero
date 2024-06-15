package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.*;

public class Scene3 {
    @FXML
    Button play_again_button;

    @FXML
    Button exit_button;


    @FXML
    Button revive;

    @FXML
    Label label;

    public static int CherrySparkles  = 0 ;

    public void setLabeltext(String s) {
        label.setFont(Font.font("System", FontWeight.BOLD, 20));
        label.setTextAlignment(TextAlignment.LEFT);
        label.setText(s);
    }

    public void handlerevive()
    {
        CherrySparkles = 1 ;
        String filePath = "cherry_count.txt";
        int currentCherryCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            currentCherryCount = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (currentCherryCount >= 5) {
            currentCherryCount -= 5;

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(Integer.toString(currentCherryCount));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                handlePlayAgain(); // Call the play again method or perform required action
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Insufficient Cherries");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough cherries to revive.\n You need 5 cherries for a revive button");
            alert.showAndWait();
            // Perform some action if cherries are less than 5
        }
        // here see how many cherries are there by opening adn closing the file cherry count and then if it is greater then 5 then do the same as handle play again and make the number of cherries reduce it by 5
        // also start the game by his old current score only not by zero


    }

    public void handleExit() {
        String filePath = "cherry_count.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String highScoreFilePath = "highscore.txt";

        try (
                FileWriter highScoreWriter = new FileWriter(highScoreFilePath)) {

            highScoreWriter.write("0");

        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }


    public void handlePlayAgain() throws IOException {
        String CURRENT_SCORE_FILE3 = "current_score.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CURRENT_SCORE_FILE3))) {
            writer.write(Integer.toString(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CherrySparkles = 1 ;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/Scene2.fxml"));
        Scene scene = new Scene(loader.load() , 600 , 400);

        Scene2 scene2Controller = loader.getController();
//        scene2Controller.initializeCurrentScore(currentScore);


        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));

        Game.getStage().setScene(scene);

    }


    @FXML
    public void initialize() {
        play_again_button.setOnAction(event -> {
            try {
                handlePlayAgain();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        exit_button.setOnAction(event -> handleExit());
    }

}
