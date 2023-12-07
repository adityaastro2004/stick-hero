package org.stick;

import javafx.animation.AnimationTimer;
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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Controller {

    @FXML
    public ImageView mario;
    @FXML
    private Label score;
    @FXML
    private Rectangle stick;
    @FXML
    private AnchorPane rootPane;

    Rectangle currentPillar;

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

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public AnchorPane getRootPane() {
        return rootPane;
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
            score.setText("Game Over");
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
                stick.getTransforms().clear();
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
            rotateStick(stick);

        }
    }

    private void rotateStick(Rectangle stick) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getX());
        rotate.setPivotY(stick.getY() + stick.getHeight());
        stick.getTransforms().add(rotate);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (rotate.getAngle() < 90) {
                    rotate.setAngle(rotate.getAngle() + 1);
                } else {
                    stop();
                    animateCharacter();
//                    stick.setHeight(0);
                    stick.relocate(stick.getLayoutX(), stick.getLayoutY());
                    generatePillar();
                    initialize();

                }
            }
        }.start();
    }


    public void animateCharacter() {
        ImageView imageView = mario;

        imageView.setTranslateX(1);
        imageView.setTranslateY(215);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), imageView);
        translateTransition.setToX(stick.getX());
        translateTransition.setToY(stick.getY());
        translateTransition.setCycleCount(1);
        translateTransition.play();
    }

    public void checkStickLanding() {
        if (stick.getLayoutX() > currentPillar.getLayoutX() && stick.getLayoutX() < currentPillar.getLayoutX() + currentPillar.getWidth()) {
            scoreCount++;
            updateScore(null);
        } else {
            gameEnd = true;
        }
    }

    public void generatePillar() {
        Random random = new Random();

        double pillarWidth = random.nextDouble() * (100 - 50) + 50; // Random width between 50 and 100
        double pillarHeight = 275; // Set a fixed height for all pillars

        Rectangle pillar = new Rectangle(pillarWidth, pillarHeight);
        pillar.setFill(Color.BLACK); // Set the color as needed

        double gap =  pillarWidth;
        double newX = rootPane.getChildren().isEmpty()
                ? 0
                : rootPane.getChildren().get(rootPane.getChildren().size() - 1).getLayoutX() + pillarWidth + gap;

        pillar.setLayoutX(newX+218);
        pillar.setLayoutY(301);
        currentPillar = pillar;

        rootPane.getChildren().add(pillar); // Add the pillar to the scene
    }
}
