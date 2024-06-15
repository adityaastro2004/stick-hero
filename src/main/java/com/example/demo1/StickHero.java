package com.example.demo1;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class StickHero extends Animators {

    private static StickHero instance;

    @Override
    public ImageView getImage() {
        return image;
    }

    private ImageView image;
    private RotateTransition rotateTransition;
    private TranslateTransition transition;
    private ScaleTransition scaleTransition;
    private final List<Animation> runningAnimations = Scene2.getRunningAnimations();

    private StickHero(ImageView image) {    // private so cannot be called but is able to inherit from Animators
        super(image);
        assert image != null;
        if (image.getClass() == ImageView.class) {
            this.image = image;
        } else {
            throw new IllegalArgumentException("Image is not an ImageView");
        }
    }

    public static StickHero getInstance(ImageView image) {
        if (instance == null) {
            instance = new StickHero(image);
        }
        return instance;
    }

    public void moveOnStick() {
        // Your implementation for move_on_stick method
    }

    @Override
    public RotateTransition rotate(double angle, Duration duration) {
        RotateTransition rotate_1 = new RotateTransition();
        rotate_1.setDuration(Duration.millis(500));
        rotate_1.setNode(image);
        rotate_1.setByAngle(angle);

        return rotate_1;
    }

    @Override
    public TranslateTransition move(double x, double y, Duration duration) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(500));
        transition.setNode(image);
        transition.setByX(x);
        transition.setByY(y);

        return transition;
    }

    @Override
    public void scale(double factorX, double factorY, Duration duration) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(image);
        scale.setByX(factorX);
        scale.setByY(factorY);
        scale.setDuration(duration);
    }
}
