import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToe {

    // Lists to store positions of player and computer
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        
        // Initial game board setup
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},};
        
        // Printing the initial game board
        printGameBoard(gameBoard);
        
        // Main game loop
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || computerPositions.contains(playerPos)) {
                System.out.println("Position is taken! Please choose another position.");
                playerPos = scan.nextInt();
            }
            
            // Placing player's piece on the board
            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
            
            // Computer's turn
            Random rand = new Random();
            int computerPos = rand.nextInt(9) +1;
            while(playerPositions.contains(computerPos) || computerPositions.contains(computerPos)) {
                computerPos = rand.nextInt(9) +1;
            }
            
            // Placing computer's piece on the board
            placePiece(gameBoard, computerPos, "computer");
            
            // Printing updated game board after computer's turn
            printGameBoard(gameBoard);
            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
        // Printing final game board
        printGameBoard(gameBoard);
    }
    
    // Method to print the game board
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    // Method to place player's or computer's piece on the board
    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else {
            symbol = 'O';
            computerPositions.add(pos);
        }
        switch(pos) {
            case 1: 
                gameBoard[0][0] = symbol;
                break;
            case 2: 
                gameBoard[0][2] = symbol;
                break;
            case 3: 
                gameBoard[0][4] = symbol;
                break;
            case 4: 
                gameBoard[2][0] = symbol;
                break;
            case 5: 
                gameBoard[2][2] = symbol;
                break;
            case 6: 
                gameBoard[2][4] = symbol;
                break;
            case 7: 
                gameBoard[4][0] = symbol;
                break;
            case 8: 
                gameBoard[4][2] = symbol;
                break;
            case 9: 
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    
    // Method to check the winner
    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(7,5,3);
        
        List<List<Integer>> winningConditions = new ArrayList<List<Integer>>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);
        
        for(List<Integer> l : winningConditions) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations! You won!";
            } else if(computerPositions.containsAll(l)){
                return "Computer wins! Better luck next time.";
            } else if(playerPositions.size() + computerPositions.size() == 9) {
                return "It's a tie!";
            }
        }
        return "";        
    }
}
