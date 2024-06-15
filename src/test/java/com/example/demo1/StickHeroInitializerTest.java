package com.example.demo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StickHeroInitializerTest {

    private StickHeroInitializer stickHeroInitializer;

    @BeforeEach
    void setUp() {
        stickHeroInitializer = new StickHeroInitializer();
    }


    @Test
    void testIncreaseCherryCount() {
        int k = stickHeroInitializer.getCherryCount();
        stickHeroInitializer.increaseCherryCount();
        assertEquals(k + 1, stickHeroInitializer.getCherryCount());
    }

    @Test
    void testSetCurrentScore() {
        assertEquals(1, stickHeroInitializer.setCurrentScore());
        assertEquals(2, stickHeroInitializer.setCurrentScore());
        assertEquals(2, stickHeroInitializer.getHighScore()); // High score should not change
    }

    @Test
    void testSetHighScore() {
        assertEquals(10 , stickHeroInitializer.setHighScore(5, 10));
        assertEquals(15, stickHeroInitializer.setHighScore(15, 10));
    }

    @Test
    void testSetCherryScore() {
        assertEquals(1, stickHeroInitializer.setCherryScore(0));
        assertEquals(2, stickHeroInitializer.setCherryScore(1));
    }

    // Add more tests as needed for the remaining methods in StickHeroInitializer
}
