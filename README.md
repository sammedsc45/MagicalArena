# 🏟️ Magical Arena - The Ultimate PvP Showdown ⚔️✨

Welcome to **Magical Arena**, an epic player-vs-player battle simulator where warriors clash, dice roll, and the fate of the combatants rests in your hands. Test your reflexes, strategy, and luck in this thrilling turn-based game where every second counts!

## 🌟 Features

* **Real-Time Key Battle**: Players press `s` or `k` to attack without hitting `Enter`. Engage in a seamless, fast-paced battle!
* **Inactivity Alerts**: Stay on your toes! If players don't act, they'll be reminded to attack with creative alerts.
* **Dynamic Stats**: View player health updates and damage details after each attack.
* **Strategic Gameplay**: Luck meets strategy! Damage is influenced by dice rolls, player stats, and quick thinking.
* **Victory Celebration**: The arena comes alive with a dramatic statement when the victor emerges!
* **Comprehensive Testing**: Thoroughly tested game mechanics ensure balanced and fair gameplay.

## 🚀 How to Play

1. Clone this repository:
```bash
git clone https://github.com/sammedsc45/MagicalArena.git
cd magical-arena
```

2. Compile the project:
```bash
javac -d . *.java
```

3. Run the game:
```bash
java com.magicalarena.Main
```

4. **Enter Player Names**: Start the game by entering the names of the two players.
5. **Attack Controls**:
   * Player 1: Press `s` to attack.
   * Player 2: Press `k` to attack.
6. **Stay Alert**: If no attack happens for a few seconds, an alert reminds you to act.
7. **Claim Victory**: The game ends when one player's health drops to zero, declaring the other as the winner with an epic statement.

## 🧪 Testing

The game includes comprehensive test suites to ensure reliability and fairness:

### Test Coverage

* **Arena Tests**: Verify match initialization, turn mechanics, and winner declaration
* **Player Tests**: Validate player stats, attack/defense calculations, and health management
* **Dice Tests**: Ensure fair and random roll generation within specified bounds

### Running Tests

Execute the test suite using JUnit:

```bash
javac -cp .:junit-platform-console-standalone-1.8.2.jar -d . *.java
java -jar junit-platform-console-standalone-1.8.2.jar --class-path . --scan-class-path
```

## 📂 Project Structure

```plaintext
project_root/
├── src/
│   ├── Main.java
│   └── com/
│       └── magicalarena/
│           ├── Arena.java
│           ├── Dice.java
│           └── Player.java
├── test/
│   └── com/
│       └── magicalarena/
│           ├── ArenaTest.java
│           ├── DiceTest.java
│           └── PlayerTest.java
```

## 🛠️ Tech Stack

* **Java**: Core programming language for implementing the game logic
* **JUnit**: Framework for comprehensive unit testing
* **Multithreading**: Enables simultaneous input handling and real-time alerts
* **OOP Design**: Ensures modularity and reusability with Player, Dice, and Arena classes

## 🌈 Why You'll Love Magical Arena

* **Fast-Paced Action**: Forget hitting `Enter` repeatedly — just attack and react!
* **Engaging Alerts**: Stay immersed with custom notifications during inactivity
* **Replay Value**: Each battle is unique, thanks to dice rolls and player strategies
* **Easy to Play, Hard to Master**: Perfect for casual players and strategic thinkers alike
* **Battle-Tested**: Thoroughly tested mechanics ensure fair and balanced gameplay

## 🏆 Future Enhancements

* **AI Opponents**: Play against a challenging computer-controlled player
* **Multiplayer Mode**: Expand battles across a network for remote duels
* **Customization**: Let players choose their stats, abilities, and special powers
* **Extended Test Coverage**: Add integration tests and performance benchmarks

## 🤝 Contributing

We welcome contributions to make **Magical Arena** even more exciting! Here's how you can help:

1. Fork the repository
2. Create a new branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push the branch: `git push origin feature-name`
5. Open a pull request

Don't forget to:
* Add tests for new features
* Ensure all tests pass
* Follow existing code style
* Update documentation as needed

## 🛡️ License

This project is licensed under the **MIT License**. Feel free to use, modify, and distribute it as you see fit. See the `LICENSE` file for details.

## 🎉 Acknowledgments

* Thanks to the players who make every battle in **Magical Arena** unforgettable!
* Inspired by the thrill of PvP combat and the unpredictability of dice rolls
* Special thanks to contributors who helped with testing and quality assurance

## 📣 Feedback

If you have ideas, suggestions, or just want to share your high-score moments, feel free to reach out or create an issue in this repository!

Let the battle begin. May the best player win! 🏅
