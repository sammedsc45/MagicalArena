package com.magicalarena;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player("Hero", 100, 10, 5);

        assertEquals("Hero", player.getName());
        assertEquals(100, player.getHealth());
        assertEquals(10, player.getStrength());
        assertEquals(5, player.getAttackValue());
    }

    @Test
    void testPlayerAttack() {
        Player player = new Player("Hero", 100, 10, 5);
        Dice dice = new Dice() {
            @Override
            public int roll() {
                return 3; // Mock dice roll
            }
        };

        int damage = player.attack(dice);
        assertEquals(15, damage); // Attack value (5) * dice roll (3)
    }

    @Test
    void testPlayerDefend() {
        Player player = new Player("Hero", 100, 10, 5);
        Dice dice = new Dice() {
            @Override
            public int roll() {
                return 2; // Mock dice roll
            }
        };

        player.defend(dice, 50); // Incoming damage is 50
        assertEquals(70, player.getHealth()); // Corrected: 100 - (50 - (10 * 2)) = 70
    }

    @Test
    void testPlayerIsAlive() {
        Player player = new Player("Hero", 100, 10, 5);

        assertTrue(player.isAlive());

        player.setHealth(0);
        assertFalse(player.isAlive());
    }
}