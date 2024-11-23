package com.magicalarena;

public class Player {
    private int health;
    private int strength;
    private int attack;
    private String name;

    // Constructor
    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    // Calculate attack damage
    public int attack(Dice dice) {
        int roll = dice.roll();
        return attack * roll;
    }

    // Handle defending against an attack
    public void defend(Dice dice, int attackDamage) {
        int defendRoll = dice.roll();
        int damage = Math.max(0, attackDamage - (strength * defendRoll));
        health = Math.max(0, health - damage);
    }

    // Check if player is still alive
    public boolean isAlive() {
        return health > 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttackValue() {
        return attack;
    }

    // Additional method for testability
    public void setHealth(int health) {
        this.health = health;
    }
}