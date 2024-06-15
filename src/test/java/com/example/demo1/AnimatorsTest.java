package com.example.demo1;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimatorsTest {

    private Animators animators;

    @BeforeEach
    void setUp() {
        Node image = new Rectangle(); // Replace with your actual Node type
        animators = new Animators(image);
    }
    @Test
    void testRotateTransition() {
        double angle = 90.0;
        Duration duration = Duration.seconds(1);
        RotateTransition rotateTransition = animators.rotate(angle, duration);

        assertNotNull(rotateTransition);
        assertEquals(animators.getImage(), rotateTransition.getNode());
        assertEquals(angle, rotateTransition.getByAngle());
    }
    @Test
    void testRotateTransitionDuration() {
        double angle = 90.0;
        Duration duration = Duration.seconds(1);
        RotateTransition rotateTransition = animators.rotate(angle, duration);

        assertNotNull(rotateTransition);
        assertEquals(duration, rotateTransition.getDuration());
    }
    @Test
    void testTranslateTransition() {
        double x = 10.0;
        double y = 20.0;
        Duration duration = Duration.seconds(1);
        TranslateTransition translateTransition = animators.move(x, y, duration);

        assertNotNull(translateTransition);
        assertEquals(animators.getImage(), translateTransition.getNode());
        assertEquals(x, translateTransition.getByX());
    }
    @Test
    void testTranslateTransitionDuration() {
        double x = 10.0;
        double y = 20.0;
        Duration duration = Duration.seconds(1);
        TranslateTransition translateTransition = animators.move(x, y, duration);

        assertNotNull(translateTransition);
        assertEquals(duration, translateTransition.getDuration());
    }
    @Test
    void testScaleTransition() {
        double factorX = 1.5;
        double factorY = 2.0;
        Duration duration = Duration.seconds(1);
        animators.scale(factorX, factorY, duration);
        ScaleTransition scaleTransition = animators.getScaleTransition();

        assertNotNull(scaleTransition);
        assertEquals(animators.getImage(), scaleTransition.getNode());
        assertEquals(factorX, scaleTransition.getByX());
    }
    @Test
    void testScaleTransitionDuration() {
        double factorX = 1.5;
        double factorY = 2.0;
        Duration duration = Duration.seconds(1);
        animators.scale(factorX, factorY, duration);
        ScaleTransition scaleTransition = animators.getScaleTransition();

        assertNotNull(scaleTransition);
        assertEquals(factorY, scaleTransition.getByY());
        assertEquals(duration, scaleTransition.getDuration());
    }
}
