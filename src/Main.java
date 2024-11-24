import com.magicalarena.Arena;
import com.magicalarena.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display welcome banner and game rules
        System.out.println("""
            
            ⚔️ === WELCOME TO THE MAGICAL ARENA === ⚔️
            
            🌟 Hear ye, hear ye, brave warriors! 🌟
            
            In this ancient arena of magic and might, two champions shall engage
            in an epic duel that will echo through the ages!
            
            === THE SACRED RULES OF COMBAT ===
            
            🎮 CONTROLS:
            • Player 1 - Press 's' to unleash your power!
            • Player 2 - Press 'k' to strike your foe!
            
            ⚔️ COMBAT BASICS:
            • Each warrior begins with 100 health points
            • Your strikes will deal varying damage based on your strength and luck
            • The battle continues until one warrior falls
            • Swift reactions are key - the arena grows restless with inactivity!
            
            🎯 STRATEGY TIPS:
            • Time your attacks wisely
            • Watch your health closely
            • Don't let your guard down!
            
            May fortune favor the bold! ⚔️
            =======================================
            
            """);

        // Create first player
        System.out.println("🎭 Enter the name of our first champion:");
        String player1Name = scanner.nextLine();
        System.out.println("⚔️ Behold! " + player1Name + " steps forth with:\n" +
                "   ❤️ Health: 100 | 💪 Strength: 5 | ⚔️ Attack: 10");

        Player player1 = new Player(player1Name, 100, 5, 10);

        // Create second player
        System.out.println("\n🎭 Enter the name of our second champion:");
        String player2Name = scanner.nextLine();
        System.out.println("⚔️ Behold! " + player2Name + " steps forth with:\n" +
                "   ❤️ Health: 100 | 💪 Strength: 5 | ⚔️ Attack: 10");

        Player player2 = new Player(player2Name, 100, 5, 10);

        // Initialize and start the match
        Arena arena = new Arena();
        arena.initMatch(player1, player2);

        System.out.println("\n🔥 Press Enter to begin this legendary battle! 🔥");
        scanner.nextLine();

        arena.playMatch();

        scanner.close();
    }
}