package com.example.demo1;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.*;
import java.util.Random;

public class StickHeroInitializer {

    public static final int MIN_DISTANCE = 275;         // The lower bound should be greater than the width of the player .
    public static final int MAX_DISTANCE = 350;

    public static final int MAX_WIDTH = 150 ;
    public static final int MIN_WIDTH = 80 ;

    private final Random rand ;
    private int highscore;
    private int num_of_cherries;


    private int currentscore = 0;

    private int  score ;

    private static final String HIGHSCORE_FILE = "highscore.txt";
    private static final String CHERRY_COUNT_FILE = "cherry_count.txt";
    private static final String CURRENT_SCORE_FILE = "current_score.txt";


    public void loadCurrentCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CURRENT_SCORE_FILE))) {
            String currentscorestr = reader.readLine();
            score = (currentscorestr != null) ? Integer.parseInt(currentscorestr.trim()) : 0;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void saveCurrentCount() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CURRENT_SCORE_FILE))) {
            writer.write(Integer.toString(score));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void loadCherryCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CHERRY_COUNT_FILE))) {
            String cherryCountStr = reader.readLine();
            num_of_cherries = (cherryCountStr != null) ? Integer.parseInt(cherryCountStr.trim()) : 0;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void saveCherryCount() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHERRY_COUNT_FILE))) {
            writer.write(Integer.toString(num_of_cherries));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void increaseCherryCount() {
        num_of_cherries++;
        saveCherryCount();
    }

    public int getCherryCount() {
        return num_of_cherries;
    }


    public StickHeroInitializer() {
        this.rand = new Random();
        loadCherryCount();
    }

    //    public void akshatsethighscore()
//    {
//        highscore = 0;
//    }
    public void akshatsethighscore() {
        highscore = loadHighScore();
    }

    public int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String highScoreStr = reader.readLine();
            return (highScoreStr != null) ? Integer.parseInt(highScoreStr.trim()) : 0;
        } catch (IOException | NumberFormatException e) {
            return 0; // In case of any error, return default high score as 0
        }
    }

    public void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.write(Integer.toString(highscore));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }



    public double RandomDistance()
    {

        double distance = rand.nextInt(MAX_DISTANCE - MIN_DISTANCE + 1) + MIN_DISTANCE;
        while(distance < 150) {
            distance = rand.nextInt(MAX_DISTANCE - MIN_DISTANCE + 1) + MIN_DISTANCE;
        }

        return distance;
    }

    public double RandomWidth()
    {
        double width = rand.nextInt(MAX_WIDTH - MIN_WIDTH + 1) + MIN_WIDTH;
        return width;
    }

    public double RandomCherryPosition(double distance)                         // If cherry present , use this to calculate the position of the cherry
    {
        double cherryPosition = rand.nextInt((int)distance - 50 + 1) + 50;
        return cherryPosition;
    }

    public int CherryCheck(double distance)                                     // 1 means cherry is present , 0 means cherry is not present
    {
        int cherryCheck = rand.nextInt(2);
        if(cherryCheck == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public int setCurrentScore()
    {

        score++;
        if (score>highscore)
        {
            highscore = score;
        }
        return score;
    }
//    public int setHighCurrentScore()
//    {
//        highscore++;
//        return highscore;
//    }



    public int setHighScore(int score , int highScore)
    {
        System.out.println(score);
        System.out.println(highScore);
        if(score > highScore)
        {
            highScore = score;
        }
        return highScore;
    }

    public int setCherryScore(int cherryScore)
    {
        cherryScore++;
        return cherryScore;
    }

    public int getCurrentScore()
    {
        return score;
    }
    public int getHighScore()
    {
        return highscore;
    }

    public int getCherryScore()
    {
        return num_of_cherries;
    }


}
