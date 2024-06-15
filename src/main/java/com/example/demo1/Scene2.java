package com.example.demo1;

// should be able to change background
// can alertbox for pause , technically the user should not be permitted to do anything while the game is paused .
// crossing or "Resume" button - inverts the pause button , and the game can execute .
// cherry sparkles when collected .
// singleton for character , factory for rectangles  - can execute different colors for each rectangle .
// multithreading ke aur uses .
// oopify to apply junit . main thing . will take most time - 8 hrs bound ?? to oopify .
// Revive . the cherries need to be deducted .
// check / improve multithreading usecases .

/*

 User can only tap in the pane to invert and collect cherries .
 User can extend stick only once  during one instance - not implemented yet .

 */

//controller for scene 2 (game scene) , all the logic .

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.animation.Animation;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Scene2 implements Initializable  {
    private final StickHeroInitializer Engine = new StickHeroInitializer() ; ;
    private  static final List<Animation> RunningAnimations = new ArrayList<>();
    private double cherryXCoordinate = 0 , firstclickxcoordinate ,  clickreleasedxcoordinate , r2distance  , r2width , r1width ;
    private int number_of_cherries = 0 , NighOrDay = 1   , ObstacleRevived = 0 ;

    private Rectangle r1 , r2 , r3 , stick ;

    private Button b1 ,b2 ;
    private Stick Stick ; private StickHero StickHero ;
    private boolean isMousePressed = false , isPaused  = false ;



    @FXML
    AnchorPane AnchorPane1 ;

    @FXML
    private ImageView TreeImage ;
    @FXML
    private  ImageView cherrii ;
    @FXML
    private ImageView PauseButton ;
    @FXML
    private Label CherryCount ;
    @FXML
    private Pane CherryPane ;
    @FXML
    private Button PlayerScore ;
    private ImageView StickImage , cherryImage ;






    @Override
    public void initialize(URL url , ResourceBundle rb )
    {

        Engine.loadCurrentCount();
        CherryCount.setText(Integer.toString(Engine.getCherryScore()));
        String s = ""+Engine.getCurrentScore();
        PlayerScore.setText(s);
        cherrii.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Cherries.png"))));
        CherryPane.setVisible(false);
        Engine.akshatsethighscore();

        if (!AnchorPane1.getChildren().contains(PlayerScore)) {
            AnchorPane1.getChildren().add(PlayerScore);
        }


        r2distance = (double) Engine.RandomDistance(); r2width = (double) Engine.RandomWidth(); r1width = (double) Engine.RandomWidth();
        // create a new AnchorPane and add it to the existing AnchorPane1 , call translate transition so that the r2 meets anchor left , basic arithmetic mei yahi kar raha hoon
        r1 = RectanglesObstaclesFactory.createRectangleObstacle(r1width ) ;
        r2 = RectanglesObstaclesFactory.createRectangleObstacle(r2width ) ;
        AnchorPane.setBottomAnchor(r1, 0.0); AnchorPane.setBottomAnchor(r2, 0.0); AnchorPane.setLeftAnchor(r2, r2distance);
        Image cImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("stickhero.png")));
        StickImage = new ImageView(cImage);
        StickImage.setFitHeight(40); StickImage.setFitWidth(50);
        AnchorPane.setBottomAnchor(StickImage, 187.0); AnchorPane.setLeftAnchor(StickImage, r1width - 35); AnchorPane1.getChildren().addAll(r1, r2, StickImage);
        StickHero = com.example.demo1.StickHero.getInstance(StickImage);


        PauseButton.setOnMouseClicked(e -> handlePauseButtonClick());

        CreateNewInstance();
    }

    public static List<Animation> getRunningAnimations() {
        return RunningAnimations;
    }

    private void setPauseButtonImage() {
        // Set the image based on the current state
        String imageName = isPaused ? "pause.png" : "play.png";
        PauseButton.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName))));  // convert this to decorator pattern
    }

    private void handlePauseButtonClick() {
        for (Animation animation : RunningAnimations) {
            if (isPaused) {
                if (animation.getStatus() == Animation.Status.PAUSED) {
                    animation.play();
                }
            } else {
                if (animation.getStatus() == Animation.Status.RUNNING) {
                    animation.pause();
                }
            }
        }

        isPaused = !isPaused;

        setPauseButtonImage();
    }
    private  void sparkle() {
        ColorAdjust colorAdjust = new ColorAdjust();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0)),
                new KeyFrame(Duration.millis(200), new KeyValue(colorAdjust.brightnessProperty(), 0.8)),
                new KeyFrame(Duration.millis(300), new KeyValue(colorAdjust.brightnessProperty(), 0))
        );
        cherrii.setEffect(colorAdjust);
        CherryCount.setEffect(colorAdjust);
        timeline.setCycleCount(4);
        timeline.play();
    }

    public void CreateNewInstance() {
        if (Scene3.CherrySparkles == 1) {

            sparkle();
            Scene3.CherrySparkles = 0;

        }
        if( ObstacleRevived == 1 ){
            StickImage.setLayoutX(StickImage.getLayoutX()- r2distance);
            ObstacleRevived = 0 ;
        }
        Timeline timeline = new Timeline();
        RunningAnimations.add(timeline);

        stick = new Rectangle(4.0, 5.0); AnchorPane.setBottomAnchor(stick, 187.0); AnchorPane.setLeftAnchor(stick, r1width - 5);
        AnchorPane1.getChildren().add(stick); stick.setFill(Color.BLACK);
        Stick = new Stick(stick);

        cherryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Cherries.png"))));
        cherryImage.setFitHeight(25); cherryImage.setFitWidth(25);
        double middlePoint = r1width + (r2distance - r1width) / 2.0; // Calculate the middle point between r1 and r2
        double cherryPosition = r1width + (middlePoint - r1width) * Math.random(); // Calculate cherry position between r1 and x
        AnchorPane.setBottomAnchor(cherryImage, 155.0); AnchorPane.setLeftAnchor(cherryImage, cherryPosition);
        AnchorPane1.getChildren().add(cherryImage);
        cherryXCoordinate = cherryPosition;
        System.out.println("cherryXCoordinate " + cherryXCoordinate);

        StickImage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                KeyValue kv = new KeyValue(Stick.Image.heightProperty(), 400); KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
            }
        });
        StickImage.setOnMouseReleased(e -> {
            timeline.stop();
            Duration Inner_p_0  =  Stick.fall(Duration.millis(800)) ;     // rotate the stick
            PauseTransition inner_pause_0 = new PauseTransition(Inner_p_0);  RunningAnimations.add(inner_pause_0); inner_pause_0.play();
            inner_pause_0.setOnFinished(event -> {
                try {
                    NextStep();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        });
    }
    public void NextStep() throws IOException
    {
        double stickEndPosition = r1.getWidth() + stick.getHeight() ;
        double r2Left = AnchorPane.getLeftAnchor(r2);
        if (stickEndPosition >= r2Left && stickEndPosition < r2Left + r2.getWidth())
        {
            PauseTransition pause_1 = new PauseTransition(Duration.millis(100)); RunningAnimations.add(pause_1); pause_1.play();
            pause_1.setOnFinished(event ->
                    {
                        TranslateTransition translate1 = new TranslateTransition() ;
                        RunningAnimations.add(translate1);
                        translate1.setDuration(Duration.millis(3500));  // to collect cherries
                        translate1.setNode(StickImage); translate1.setByX(r2distance + r2.getWidth() - r1.getWidth() );
                        translate1.play();
                        ScaleTransition scale = new ScaleTransition() ;
                        TranslateTransition translate2 = new TranslateTransition() ;
                        scale.setDuration(Duration.millis(100));
                        scale.setNode(StickImage); scale.setToY(-1);
                        translate2.setDuration(Duration.millis(100)); translate2.setNode(StickImage); translate2.setByY(40);
                        ParallelTransition parallel = new ParallelTransition(scale , translate2 ) ;
                        RunningAnimations.add(parallel);
                        CherryPane.setOpacity(0.3); CherryPane.setVisible(true);

                        CherryPane.setOnMousePressed(e -> {
                            isMousePressed = true;
                            parallel.play();
                            CherryCount.setText(Integer.toString(Integer.parseInt(CherryCount.getText())));
                            firstclickxcoordinate =  StickImage.localToScene(StickImage.getBoundsInLocal()).getMinX() ;
                            System.out.println("First Click Coordinate " + firstclickxcoordinate);
                            double cherryX = cherryImage.localToScene(cherryImage.getBoundsInLocal()).getMinX();
                            System.out.println("cherryX " + cherryX);
                            AnimationTimer cherryCollector = new AnimationTimer() {
                                @Override
                                public void handle(long now) {
                                    if (Stick.Image.getRotate() == 90) {
                                        double stickX = StickImage.localToScene(StickImage.getBoundsInLocal()).getMinX()  ;
                                        double cherryRange = 2.0;
                                        boolean once = true ;
                                        if (isMousePressed && (stickX >= cherryX - cherryRange && (stickX <= cherryX + cherryRange )) && once ){
                                            System.out.println("Cherry Collected");
                                            cherryImage.setVisible(false); }
                                            once = false ;

                                    }
                                }
                            };
                            cherryCollector.start();

                        });


                        AnimationTimer stickHeroCollisionDetector ;
                        stickHeroCollisionDetector = new AnimationTimer() {
                                @Override
                                public void handle(long now) {
                                    double r2Left = AnchorPane.getLeftAnchor(r2);

                                    int flag = 0 ;
                                    if (isMousePressed && StickImage.localToScene(StickImage.getBoundsInLocal()).getMaxX()  - 20 >= r2Left && flag == 0) {
                                        flag = 1 ;
                                        parallel.pause(); translate1.pause();
                                        System.out.println("Stick Hero Inverted");
                                        try {
                                                StickImage.setLayoutX(-10);
                                                GameOver();
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        };

                                    }



                            };
                            System.out.println("Stick Hero Collision Detector Started");

                            stickHeroCollisionDetector.start();



                        AnimationTimer finalStickHeroCollisionDetector = stickHeroCollisionDetector;
                        CherryPane.setOnMouseReleased(e -> {
                            finalStickHeroCollisionDetector.stop();
                            isMousePressed = false;
                            parallel.pause();
                            scale.setToY(1);
                            translate2.setByY(-40);
                            parallel.play();
                            CherryPane.setVisible(false);
                            clickreleasedxcoordinate = StickImage.localToScene(StickImage.getBoundsInLocal()).getMinX();
                            System.out.println("click released  x-coordinate " + clickreleasedxcoordinate);
                        });




                        PauseTransition pause_2 = new PauseTransition(Duration.millis(3500));
                        RunningAnimations.add(pause_2);
                        pause_2.play();
                        pause_2.setOnFinished(e ->{
                            if (cherryXCoordinate >= firstclickxcoordinate && cherryXCoordinate <= clickreleasedxcoordinate) {
                                number_of_cherries++;
                                Engine.increaseCherryCount();
                                System.out.println("done hurrah--------------------------------------------------------------------------------  :     "+number_of_cherries);
                                CherryCount.setText(Integer.toString(Integer.parseInt(CherryCount.getText()) + 1));
                            }
                            firstclickxcoordinate = 0;
                            clickreleasedxcoordinate = 0;
                            cherryXCoordinate = 0;
                            // flag condtion
                            TransitionToNextScene();
                        });
                    }
            );
        }
        else
        {
            TranslateTransition t3 = new TranslateTransition() ;
            t3.setDuration(Duration.millis(1000));
            t3.setNode(StickImage);
            t3.setByX(Stick.Image.getHeight() + 15 );
            t3.play() ;
            PauseTransition pause_3 = new PauseTransition(Duration.millis(1000));
            RunningAnimations.add(pause_3);
            pause_3.play();
            pause_3.setOnFinished(event -> {
                TranslateTransition t1 = new TranslateTransition();
                t1.setDuration(Duration.millis(1000));
                t1.setNode(StickImage);
                t1.setByY(300); // r1 is 190
                RotateTransition r1 = new RotateTransition();
                r1.setDuration(Duration.millis(500));
                r1.setNode(StickImage);
                r1.setByAngle(360);
                r1.setCycleCount(2);
                ParallelTransition p3 = new ParallelTransition(t1, r1);
                RunningAnimations.add(p3);
                p3.play();
                PauseTransition pause1 = new PauseTransition(Duration.millis(1000));
                RunningAnimations.add(pause1);
                pause1.play();
                pause1.setOnFinished(e -> {
                    try {
                        GameOver();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });



        }
    }

    public void TransitionToNextScene()
    {
        AnchorPane1.getChildren().remove(cherryImage);
        TranslateTransition t1 = new TranslateTransition() ; TranslateTransition t2 = new TranslateTransition() ; TranslateTransition t3 = new TranslateTransition() ;
        t1.setDuration(Duration.millis(100)); t1.setNode(r2); t1.setByX(-(AnchorPane.getLeftAnchor(r2)));
        t2.setDuration(Duration.millis(100)); t2.setNode(r1); t2.setByX(-(AnchorPane.getLeftAnchor(r2)));
        t3.setDuration(Duration.millis(100)); t3.setNode(StickImage); t3.setByX(-(AnchorPane.getLeftAnchor(r2)));
        AnchorPane1.getChildren().remove(stick);  // removing stick
        PauseTransition pause_4 = new PauseTransition(Duration.millis(80));
        RunningAnimations.add(pause_4);
        pause_4.play();
        pause_4.setOnFinished(e -> {
            ParallelTransition p1 = new ParallelTransition(t1, t2, t3 );
            RunningAnimations.add(p1);
            p1.play();
            PauseTransition pause_5 = new PauseTransition(Duration.millis(150));
            RunningAnimations.add(pause_5);
            pause_5.play();
            pause_5.setOnFinished( e2 -> {
                r3 = RectanglesObstaclesFactory.createRectangleObstacle(Engine.RandomWidth());  //design pattern - factory
                r2distance = (double) Engine.RandomDistance();
                AnchorPane.setBottomAnchor(r3, 0.0); AnchorPane.setLeftAnchor(r3, r2distance);
                AnchorPane1.getChildren().add(r3);
                r1 = r2 ; r1width = r2width ;       r2 = r3 ; r2width = r3.getWidth() ;
                if(NighOrDay == 0){
                    TreeImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("41524.jpg")))) ;
                    NighOrDay = 1 ;
                }
                else {
                    TreeImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("night.png"))));
                    NighOrDay = 0 ;
                }
                Engine.setCurrentScore();   Engine.setHighScore(Engine.getCurrentScore(),Engine.getHighScore());
                PlayerScore.setText(Integer.toString(Engine.getCurrentScore()));
                CreateNewInstance();
            });

        });
    }
    public void GameOver() throws IOException {
        Engine.saveCurrentCount();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/Scene3.fxml"));
        Scene scene = new Scene(loader.load() , 600 , 400);
        Scene3 scene3Controller = loader.getController();
        scene3Controller.setLabeltext("High Score: "+Engine.getHighScore()+"\nYour Score: "+Engine.getCurrentScore()+"\nCherries: "+Engine.getCherryScore());
        Engine.setHighScore(Engine.getCurrentScore(), Engine.getHighScore());  Engine.saveHighScore();
        Game.getStage().setScene(scene);
    }

    public void initializeCurrentScore() {
        String currentScoreFilePath = "current_score.txt";
        int currentScore2 = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(currentScoreFilePath))) {
            currentScore2 = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Engine.setCurrentScore();


    }
}