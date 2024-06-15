package com.example.demo1;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animators implements Animate{

    private Node Image ;
    private RotateTransition rotateTransition ;
    private TranslateTransition transition ;

    private ScaleTransition scaleTransition ;
    public Animators(Node image) {     // polymorphism
        this.Image = image;

    }

    @Override
    public RotateTransition rotate(double angle , Duration duration) {
        RotateTransition rotate_1 = new RotateTransition();
        rotate_1.setDuration(duration);
        rotate_1.setNode(Image);
        rotate_1.setByAngle(angle);
        return rotate_1;

    }

    @Override
    public TranslateTransition move(double x , double y , Duration duration) {
        transition = new TranslateTransition();
        transition.setDuration(duration);
        transition.setNode(Image);
        transition.setByX(x);

        return transition;

    }



    @Override
    public void scale(double factorX , double factorY , Duration duration) {
        scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(duration);
        scaleTransition.setNode(Image);
        scaleTransition.setByX(factorX);
        scaleTransition.setByY(factorY);

    }



    public RotateTransition getRotateTransition() {
        return rotateTransition;
    }

    public void setRotateTransition(RotateTransition rotateTransition) {
        this.rotateTransition = rotateTransition;
    }

    public Node getImage() {
        return Image;
    }

    public void setImage(Node image) {
        Image = image;
    }

    public TranslateTransition getTransition() {
        return transition;
    }

    public void setTransition(TranslateTransition transition) {
        this.transition = transition;
    }

    public ScaleTransition getScaleTransition() {
        return scaleTransition;
    }

    public void setScaleTransition(ScaleTransition scaleTransition) {
        this.scaleTransition = scaleTransition;
    }
}
