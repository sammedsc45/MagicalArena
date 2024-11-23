import com.magicalarena.Arena;
import com.magicalarena.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** Welcome to Magical Arena ***\n");

        // Create first player
        System.out.println("Enter Player 1 name:");
        String player1Name = scanner.nextLine();
        System.out.println("Creating " + player1Name + " with stats: Health=100, Strength=5, Attack=10");
        Player player1 = new Player(player1Name, 100, 5, 10);

        // Create second player
        System.out.println("\nEnter Player 2 name:");
        String player2Name = scanner.nextLine();
        System.out.println("Creating " + player2Name + " with stats: Health=100, Strength=5, Attack=10");
        Player player2 = new Player(player2Name, 100, 5, 10);

        // Initialize and start the match
        Arena arena = new Arena();
        arena.initMatch(player1, player2);

        System.out.println("\nPress Enter to start the battle!");
        scanner.nextLine();

        arena.playMatch();

        scanner.close();
    }
}