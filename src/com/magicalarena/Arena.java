package com.magicalarena;

import java.io.IOException;

public class Arena {
    private Player playerA;
    private Player playerB;
    private final Dice dice;

    private volatile boolean gameRunning = true;
    private volatile long lastActionTime; // Tracks the time of the last action

    public Arena() {
        this.dice = new Dice();
    }

    public void initMatch(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void playMatch() {
        if (playerA == null || playerB == null) {
            throw new IllegalStateException("Players must be initialized before starting the match.");
        }

        System.out.println("\n=== Match starts between " + playerA.getName() +
                " and " + playerB.getName() + "! ===\n");

        System.out.println("Player 1: Press 's' to attack!");
        System.out.println("Player 2: Press 'k' to attack!");

        // Record the starting time
        lastActionTime = System.currentTimeMillis();

        // Start threads
        Thread inputThread = new Thread(this::listenForKeyPresses);
        Thread alertThread = new Thread(this::monitorInactivity);

        inputThread.setDaemon(true);
        alertThread.setDaemon(true);

        inputThread.start();
        alertThread.start();

        // Main game loop
        while (gameRunning) {
            if (!playerA.isAlive() || !playerB.isAlive()) {
                gameRunning = false;
            }
        }

        declareWinner();
    }

    private void listenForKeyPresses() {
        try {
            while (gameRunning) {
                if (System.in.available() > 0) { // Check if a key is pressed
                    int input = System.in.read();
                    char key = Character.toLowerCase((char) input);

                    synchronized (this) {
                        if (key == 's' && playerA.isAlive()) {
                            lastActionTime = System.currentTimeMillis();
                            playTurn(playerA, playerB);
                            printStats();
                            if (!playerB.isAlive()) gameRunning = false;
                        } else if (key == 'k' && playerB.isAlive()) {
                            lastActionTime = System.currentTimeMillis();
                            playTurn(playerB, playerA);
                            printStats();
                            if (!playerA.isAlive()) gameRunning = false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }

    private void monitorInactivity() {
        while (gameRunning) {
            try {
                Thread.sleep(1); // Check every millisecond
                long currentTime = System.currentTimeMillis();

                // If no action for 1 second, alert the players
                if (currentTime - lastActionTime > 1000) {
                    System.out.println("\n⚠️ Don't just stand there! Player 1, press 's' or Player 2, press 'k' to attack! ⚠️");
                    lastActionTime = currentTime; // Prevent spamming the message
                }
            } catch (InterruptedException e) {
                System.err.println("Error in inactivity monitor: " + e.getMessage());
            }
        }
    }

    private void playTurn(Player attacker, Player defender) {
        int attackDamage = attacker.attack(dice);
        int originalHealth = defender.getHealth();
        defender.defend(dice, attackDamage);
        int damageDone = originalHealth - defender.getHealth();

        System.out.println("\n" + attacker.getName() + " attacks " + defender.getName() +
                " for " + damageDone + " damage!");
        if (defender.isAlive()) {
            System.out.println(defender.getName() + " has " + defender.getHealth() + " health remaining.");
        } else {
            System.out.println(defender.getName() + " has fallen!");
        }
    }

    private void printStats() {
        System.out.println("\n=== Current Stats ===");
        System.out.println(playerA.getName() + " - Health: " + playerA.getHealth());
        System.out.println(playerB.getName() + " - Health: " + playerB.getHealth());
        System.out.println("=====================\n");
    }

    public void declareWinner() {
        Player winner = playerA.isAlive() ? playerA : playerB;
        String winnerMessage = "\n=== " + winner.getName() + " emerges victorious! ===\n" +
                "The arena erupts with cheers as " + winner.getName() +
                " claims eternal glory! ⚔️✨";
        System.out.println(winnerMessage);
    }

    // New methods for testability
    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }
}