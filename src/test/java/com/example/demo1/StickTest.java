package com.example.demo1;

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StickTest {


    @Test
    void testRotateAnimation() {
        Rectangle rectangle = new Rectangle();
        Stick stick = new Stick(rectangle);
        double angle = 90.0;
        Duration duration = Duration.seconds(1);
        stick.rotate(angle, duration);
        assertEquals(0, stick.getRunningAnimations().size());
    }

    @Test
    void testMoveAnimation() {
        Rectangle rectangle = new Rectangle();
        Stick stick = new Stick(rectangle);
        double x = 10.0;
        double y = 20.0;
        Duration duration = Duration.seconds(1);
        stick.move(x, y, duration);
        assertEquals(0, stick.getRunningAnimations().size());
    }

    @Test
    void testScaleAnimation() {
        Rectangle rectangle = new Rectangle();
        Stick stick = new Stick(rectangle);
        double factorX = 1.5;
        double factorY = 2.0;
        Duration duration = Duration.seconds(1);
        stick.scale(factorX, factorY, duration);
        assertEquals(0, stick.getRunningAnimations().size());
    }


    @Test
    void testStickCreationWithValidRectangle() {
        Rectangle rectangle = new Rectangle();
        Stick stick = new Stick(rectangle);
        assertEquals(rectangle, stick.getImage());
    }
}
