package com.example.demo1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
         String HIGHSCORE_FILE2 = "highscore.txt";
         String CHERRY_COUNT_FILE2 = "cherry_count.txt";
         String CURRENT_SCORE_FILE2 = "current_score.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CURRENT_SCORE_FILE2))) {
            writer.write(Integer.toString(0));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHERRY_COUNT_FILE2))) {
            writer.write(Integer.toString(0));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE2))) {
            writer.write(Integer.toString(0));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        Game.main(args);
    }
}
