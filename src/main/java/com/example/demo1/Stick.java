package com.example.demo1;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.List;

public class Stick extends Animators{

        Rectangle Image ;

        private RotateTransition rotateTransition ;
        private TranslateTransition transition ;

        private ScaleTransition scaleTransition ;

        public List<Animation> getRunningAnimations() {
                return RunningAnimations;
        }

        private final List<Animation> RunningAnimations = Scene2.getRunningAnimations();  // could be part of StickHeroInitializer too .

        public Stick(Rectangle Image) {
                super(Image) ;
                assert Image != null;
                if( Image.getClass() == Rectangle.class) {
                        this.Image = Image;
                }
                else {
                        throw new IllegalArgumentException("Image is not a Rectangle");
                }
        }


        public Duration fall( Duration duration) {   // junit
                RotateTransition rotate = rotate(90 , duration );
                TranslateTransition translate = move(Image.getHeight() / 2 , Image.getHeight() / 2 , duration );
                ParallelTransition parallel_0 = new ParallelTransition(rotate, translate);
                RunningAnimations.add(parallel_0);
                parallel_0.play();

                return duration;
        }

        @Override
        public RotateTransition rotate(double angle , Duration duration) {
                RotateTransition rotate_1 = new RotateTransition();
                rotate_1.setDuration(Duration.millis(500));
                rotate_1.setNode(Image);
                rotate_1.setByAngle(angle);

                return rotate_1;
        }
        @Override
        public TranslateTransition move(double x , double y , Duration duration) {
                TranslateTransition transition = new TranslateTransition();
                transition.setDuration(Duration.millis(500));
                transition.setNode(Image);
                transition.setByX(x);
                transition.setByY(y);

                return transition;
        }


        @Override
        public void scale(double factorX , double factorY , Duration duration) {
                ScaleTransition scale  = new ScaleTransition();
                scale.setNode(Image);
                scale.setByX(factorX);
                scale.setByY(factorY);
                scale.setDuration(duration);
        }





}
