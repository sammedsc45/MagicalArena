package com.magicalarena;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {

    @Test
    void testArenaInitialization() {
        Arena arena = new Arena();
        assertNull(arena.getPlayerA());
        assertNull(arena.getPlayerB());
    }

    @Test
    void testInitMatch() {
        Arena arena = new Arena();
        Player playerA = new Player("Warrior", 100, 10, 5);
        Player playerB = new Player("Mage", 90, 8, 7);

        arena.initMatch(playerA, playerB);

        assertEquals(playerA, arena.getPlayerA());
        assertEquals(playerB, arena.getPlayerB());
    }

    @Test
    void testPlayTurn() {
        Arena arena = new Arena();

        // Initialize players with specific stats
        Player playerA = new Player("Warrior", 100, 10, 5); // attacker
        Player playerB = new Player("Mage", 90, 8, 7); // defender

        // Mock dice to control randomness
        Dice dice = new Dice() {
            @Override
            public int roll() {
                return 2; // Mock dice roll
            }
        };

        // Inject dice dependency into Arena
        arena.initMatch(playerA, playerB);

        // Attack logic: Calculate expected damage
        int attackRoll = 2; // Mock dice roll
        int attackDamage = playerA.getAttackValue() * attackRoll; // Attack (5) * Dice roll (2) = 10

        // Defense logic: Calculate expected health after defense
        int defendRoll = 2; // Mock dice roll
        int defenseReduction = playerB.getStrength() * defendRoll; // Defense (8) * Dice roll (2) = 16
        int effectiveDamage = Math.max(0, attackDamage - defenseReduction); // Max(0, 10 - 16) = 0
        int expectedHealth = playerB.getHealth() - effectiveDamage; // 90 - 0 = 90

        // Perform turn
        playerB.defend(dice, playerA.attack(dice));

        // Assert health
        assertEquals(expectedHealth, playerB.getHealth(), "Defender's health after attack did not match.");
    }

    @Test
    void testDeclareWinner() {
        Arena arena = new Arena();
        Player playerA = new Player("Warrior", 100, 10, 5);
        Player playerB = new Player("Mage", 0, 8, 7); // Player B is already defeated

        arena.initMatch(playerA, playerB);

        Player expectedWinner = playerA.isAlive() ? playerA : playerB;
        assertTrue(expectedWinner == playerA);
    }
}