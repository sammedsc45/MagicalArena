package com.magicalarena;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    void testRollDice() {
        Dice dice = new Dice();
        int roll = dice.roll();
        assertTrue(roll >= 1 && roll <= 6, "Dice roll should be between 1 and 6");
    }
}