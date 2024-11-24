package com.magicalarena;

import java.io.IOException;
import java.util.Random;

public class Arena {
    private Player playerA;
    private Player playerB;
    private final Dice dice;
    private final Random random = new Random();

    private volatile boolean gameRunning = true;
    private volatile long lastActionTime;

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

        System.out.println("\n🏟️ === The magical arena rumbles as " + playerA.getName() +
                " and " + playerB.getName() + " enter the battlefield! === 🏟️\n");

        System.out.println("⚔️ Player 1: Press 's' to unleash your power!");
        System.out.println("⚔️ Player 2: Press 'k' to strike your foe!\n");

        lastActionTime = System.currentTimeMillis();

        Thread inputThread = new Thread(this::listenForKeyPresses);
        Thread alertThread = new Thread(this::monitorInactivity);

        inputThread.setDaemon(true);
        alertThread.setDaemon(true);

        inputThread.start();
        alertThread.start();

        while (gameRunning) {
            if (!playerA.isAlive() || !playerB.isAlive()) {
                gameRunning = false;
            }
        }

        declareWinner();
    }

    private String getRandomAttackMessage(Player attacker, Player defender, int damage) {
        String[] messages = {
                "🗡️ %s unleashes a mighty blow upon %s, dealing %d damage!",
                "⚡ %s channels ancient magic against %s for %d devastating damage!",
                "💥 With lightning speed, %s strikes %s for %d damage!",
                "🔥 %s's powerful attack burns %s for %d damage!",
                "⚔️ %s executes a perfect combat maneuver, hitting %s for %d damage!",
                "✨ %s summons mystical energy, blasting %s for %d damage!",
                "💫 %s performs a legendary technique against %s, dealing %d damage!",
                "⭐ %s's masterful strike catches %s off guard for %d damage!"
        };
        return String.format(messages[random.nextInt(messages.length)],
                attacker.getName(), defender.getName(), damage);
    }

    private String getRandomDefenseMessage(Player defender, int healthRemaining) {
        if (healthRemaining <= 0) {
            String[] fallMessages = {
                    "💀 %s crumbles to the ground, defeated!",
                    "⚰️ %s falls in glorious combat!",
                    "🏴 %s's journey ends here...",
                    "🌟 %s fought bravely, but could not withstand the assault!"
            };
            return String.format(fallMessages[random.nextInt(fallMessages.length)],
                    defender.getName());
        } else {
            String[] survivalMessages = {
                    "💪 %s stands strong with %d health remaining!",
                    "🛡️ %s endures the assault, holding on with %d health!",
                    "✨ %s maintains their ground, %d health points remain!",
                    "⚔️ %s refuses to fall, continuing with %d health!"
            };
            return String.format(survivalMessages[random.nextInt(survivalMessages.length)],
                    defender.getName(), healthRemaining);
        }
    }

    private void listenForKeyPresses() {
        try {
            while (gameRunning) {
                if (System.in.available() > 0) {
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
                Thread.sleep(1000);
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastActionTime > 1000) {
                    String[] taunts = {
                            "\n⚠️ The crowd grows restless! Show them some action! ⚠️",
                            "\n⚠️ Time for glory! Press your attack keys! ⚠️",
                            "\n⚠️ The arena awaits your next move! ⚠️",
                            "\n⚠️ Warriors, show us your strength! ⚠️"
                    };
                    System.out.println(taunts[random.nextInt(taunts.length)]);
                    lastActionTime = currentTime;
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

        System.out.println("\n" + getRandomAttackMessage(attacker, defender, damageDone));
        System.out.println(getRandomDefenseMessage(defender, defender.getHealth()));
    }

    private void printStats() {
        System.out.println("\n🏰 === Battle Status === 🏰");
        System.out.println("⚔️ " + playerA.getName() + " - Health: " + playerA.getHealth());
        System.out.println("⚔️ " + playerB.getName() + " - Health: " + playerB.getHealth());
        System.out.println("========================\n");
    }

    public void declareWinner() {
        Player winner = playerA.isAlive() ? playerA : playerB;
        String[] victoryMessages = {
                "\n🎉 === %s claims a legendary victory! === 🎉\n" +
                        "The crowd roars as %s stands triumphant in the arena! ⚔️✨",

                "\n🏆 === Glory belongs to %s! === 🏆\n" +
                        "The tales of %s's victory will echo through the ages! ⚔️✨",

                "\n👑 === Champion %s rises! === 👑\n" +
                        "Let it be known that %s conquered all challengers! ⚔️✨",

                "\n⭐ === Victory for %s! === ⭐\n" +
                        "Songs will be sung of %s's magnificent triumph! ⚔️✨"
        };
        String message = String.format(victoryMessages[random.nextInt(victoryMessages.length)],
                winner.getName(), winner.getName());
        System.out.println(message);
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }
}