package org.stick;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Controller {

    @FXML
    private Label score;
    @FXML
    private Rectangle stick;
    @FXML
    private AnchorPane rootPane;

    Integer scoreCount;
    private boolean isMousePressed = false;
    private AnimationTimer lengthenTimer;
    private final double lengtheningSpeed = 1.0;
    private Stage stage;
    boolean gameEnd = false;
    long pressTime, elapsedTime;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void replay(ActionEvent actionEvent) throws IOException {
        Parent game = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/stick.fxml"))));
        Scene scene = new Scene(game);
        if (stage != null) {
            stage.setScene(scene);
        }
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void updateScore(ActionEvent actionEvent) {
        score.setText(scoreCount.toString());
    }

    public void start(ActionEvent actionEvent) throws IOException {
        Parent game = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/stick.fxml"))));
        Scene scene = new Scene(game);
        if (stage != null) {
            stage.setScene(scene);
        } else {
            System.out.println("Empty stage");
        }
    }

    public void game(javafx.scene.input.MouseEvent event) {

        pressTime = System.currentTimeMillis();
        isMousePressed = true;
        lengthenTimer.start();
    }

    public void initialize() {
        lengthenTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (isMousePressed) {
                    stick.setLayoutY(stick.getLayoutY() - lengtheningSpeed);
                    stick.setHeight(stick.getHeight() + lengtheningSpeed);
                }
            }
        };
    }

    public void stopLengthening(MouseEvent mouseEvent) {
        if (isMousePressed) {
            isMousePressed = false;
            lengthenTimer.stop();

            // Rotate the stick when the mouse is released
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), stick);
            rotateTransition.setByAngle(90);

            // Add an event handler to update the stick's position after rotation
            rotateTransition.setOnFinished(event -> {
                // Update the layoutY property after rotation
                stick.setLayoutX(stick.getLayoutX() + stick.getHeight());
                stick.setLayoutY(stick.getLayoutY() + stick.getHeight());
                animateCharacter();
                stick.setHeight(0);
                stick.rotateProperty().setValue(0);
                generatePillar();
                initialize();
            });

            rotateTransition.play();
        }
    }

    public void animateCharacter() {
        Image image = new Image("/mario.png"); // Replace with your image path
        ImageView imageView = new ImageView(image);

        // Set the initial position of the image
        imageView.setTranslateX(1);
        imageView.setTranslateY(215);

        // Create a translate transition to move the image horizontally
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), imageView);
        translateTransition.setToX(stick.getLayoutX()); // Set the final X position
        translateTransition.setToY(stick.getLayoutY());
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.play(); // Start animation
    }
    public Rectangle generatePillar() {
        Random random = new Random();

        double pillarWidth = random.nextDouble() * (100 - 50) + 50; // Random width between 50 and 100
        double pillarHeight = 200; // Set a fixed height for all pillars

        Rectangle pillar = new Rectangle(pillarWidth, pillarHeight);
        pillar.setFill(Color.BLACK); // Set the color as needed

        // Set the initial position of the pillar outside the scene
        pillar.setLayoutX(stick.getLayoutX() + pillarWidth*10);
        pillar.setLayoutY(301);

        rootPane.getChildren().add(pillar); // Add the pillar to the scene

        return pillar;
    }

}
