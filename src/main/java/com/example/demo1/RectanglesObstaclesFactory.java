package com.example.demo1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class RectanglesObstaclesFactory {
    private static final RectangleObstacles DEFAULT = new RectangleObstacles( 40 );
    private static final java.util.Map<ObstacleKey, RectangleObstacles> Cache = new java.util.HashMap<>();

    public static Rectangle createRectangleObstacle(double width) {

        ObstacleKey obstacleKey = new ObstacleKey(width);
        RectangleObstacles obstacle = Cache.get(obstacleKey);

        obstacle = new RectangleObstacles(width);
        Cache.put(obstacleKey, obstacle);


        return obstacle.getRectangle();
    }
}
