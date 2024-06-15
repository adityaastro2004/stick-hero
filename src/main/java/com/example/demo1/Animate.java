package com.example.demo1;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public interface Animate {

    RotateTransition rotate(double angle , Duration duration);
    TranslateTransition move(double x , double y , Duration duration);

    void scale(double factorX , double factorY , Duration duration);

}
