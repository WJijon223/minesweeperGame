import java.util.*;

public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        while (!game.getGameOver()) {
            game.displayBoard();

            // Get player input for row, col, and action (reveal or flag)
            System.out.print("Enter your x-position: ");
            int x = scnr.nextInt();
            System.out.print("Enter your y-position: ");
            int y = scnr.nextInt();
            System.out.print("Enter your action ('r' for reveal / 'f' for flag): ");
            String action = scnr.next();

            // For now, just simulate a move (to be replaced with real player input logic)
            game.playerMove(x, y, action);

            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(x, y)) {
                System.out.println("Game Over! You hit a mine.");
                scnr.close();
                game.setGameOver(true);
            }
        }
    }
}