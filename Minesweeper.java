
//imported Random class from the util package to randomly place mines on the board
//import Scanner class
import java.util.*;

public class Minesweeper {
    // Scanner object for input
    private Random random = new Random();

    // Data members
    private char[][] board; // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private char[][] nums; // Array to store numbers
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number
    // of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.nums = new char[rows][cols];
        this.gameOver = false;

        // Call methods to initialize the board and place mines (DONE)
        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean status) {
        this.gameOver = status;

    }

    // Method to initialize the game board with empty values
    private void initializeBoard() {
        // Implement this method (Done!)
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.board[i][j] = '?';
                this.mines[i][j] = false;
                this.revealed[i][j] = false;
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // Implement this method (Done!)
        for (int i = 0; i < numMines; i++) {
            int x = random.nextInt(this.rows);
            int y = random.nextInt(this.cols);

            // makes sure slot is not already taken by bomb
            while (this.mines[x][y] == true) {
                x = random.nextInt(this.rows);
                y = random.nextInt(this.cols);
            }
            this.mines[x][y] = true;
            ;
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // Implement this method
        int count = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.mines[i][j]) {
                    continue;
                }
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < this.rows && y >= 0 && y < this.cols && this.mines[x][y]) {
                            count += 1;
                        }
                    }
                }
                this.nums[i][j] = (char) (count + '0');
                count = 0;
            }
        }
    }

    // Method to display the current state of the board (done)
    public void displayBoard() {
        // Implement this method (Done!)
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag) (Done!)
    public void playerMove(int row, int col, String action) {
        // Implement this method
        if (action.equals("r")) {
            revealCell(row, col);
        }
        if (action.equals("f")) {
            if (this.board[row][col] == 'F') {
                unflagCell(row, col);
            } else {
                flagCell(row, col);
            }
        }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        // Implement this method
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; i < this.cols; i++) {
                if (!this.revealed[i][j] && !this.mines[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        if (this.mines[row][col] && this.revealed[row][col]) {
            return true;
        }
        return false;
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        // Implement this method
        if (!this.revealed[row][col]) {
            if (this.mines[row][col]) {
                this.board[row][col] = 'X';
            } else {
                this.board[row][col] = this.nums[row][col];
            }
        } else {
            System.out.println("Already revealed!");
            return;
        }

        this.revealed[row][col] = true;
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        // Implement this method (done)
        if (!this.revealed[row][col]) {
            this.board[row][col] = 'F'; // flagCell(row,col)
        } else {
            System.out.println("Already revealed!");
        }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        this.board[row][col] = '?';
    }
}