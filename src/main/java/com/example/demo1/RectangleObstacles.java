package com.example.demo1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class RectangleObstacles {

    private final Rectangle rectangle;

    public RectangleObstacles( double width) {

        this.rectangle = new Rectangle(width, 190);
        this.rectangle.setFill(Color.BLACK);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
class ObstacleKey {
    private final double width;

    public ObstacleKey(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ObstacleKey that = (ObstacleKey) obj;
        return Double.compare(that.width, width) == 0 ;
    }

    @Override
    public int hashCode() {
        return  Double.hashCode(width);
    }
}




