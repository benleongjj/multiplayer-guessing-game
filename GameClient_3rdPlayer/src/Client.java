import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements AutoCloseable{

    final int port = 7000;
    private Scanner reader;
    private PrintWriter writer;
    public int assignedID;
    public int playerScore;

    public Client() throws Exception {
        Socket socket = new Socket("localhost", port);
        reader = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void getClientInfo()
    {
        String players = reader.nextLine();
        System.out.println("=========================");
        System.out.println("Current List of Players: " + players);
        assignedID = Integer.parseInt(reader.nextLine());
        playerScore = Integer.parseInt(reader.nextLine());
        System.out.println("Your Player ID: " + assignedID);
        System.out.println("Your Current Score is: " + playerScore);
        System.out.println("=========================");
    }

    public void guessNumber()
    {
        System.out.println("Please guess 3 numbers between 1 to 10.");
        System.out.println("Type in this format: number + space + number + space + number");
        System.out.println("To exit, please type 'exit'.");
        System.out.print("Enter Your Numbers: ");
        Scanner in = new Scanner(System.in);
        String guessString = in.nextLine();
        writer.println(guessString);
        if(guessString.equals("exit"))
        {
            System.out.println("You have ended the game. Goodbye!");
            System.exit(0);
        }
    }

    public boolean serverGuessCheck()
    {
        Boolean inputValidity = Boolean.valueOf(reader.nextLine());
        String serverMessage = reader.nextLine();
        if(serverMessage.equals(""))
        {
            System.out.println(serverMessage);
        } else {
            System.out.println("----------------------");
            System.out.println(serverMessage);
            System.out.println("----------------------");
        }
        return inputValidity;
    }
    public boolean guessedCorrect()
    {
        Boolean guessedCorrect = Boolean.parseBoolean(reader.nextLine());
        return guessedCorrect;
    }

    public int getPlayerScore()
    {
        playerScore = Integer.parseInt(reader.nextLine());
        return playerScore;
    }

    public void getNumberOfWins()
    {
        String wins = reader.nextLine();
        System.out.println(wins);
    }

    public void getFeedback()
    {
        StringBuilder input = new StringBuilder();
        String line;
        System.out.println("----------------------------------------");
        System.out.println("Game Clues: ");
        System.out.println(reader.nextLine());
        System.out.println("----------------------------------------");
    }

    public void getListOfWonPlayers()
    {
//        while (reader.hasNextLine())
//        {
            System.out.println(reader.nextLine());
//        }
    }

    public void getListOfMostWon()
    {
        System.out.println(reader.nextLine());
    }

    public Boolean playAgain()
    {
        Boolean inputValid = false;
        while(!inputValid) {
            System.out.println("Would you like to play again? Type 'yes' or 'no'");
            Scanner in = new Scanner(System.in);
            String playAgain = in.nextLine();
            if (playAgain.equals("yes")) {
                return true;
            } else if (playAgain.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid Input.");
                inputValid = false;
            }
        }
        return true;
    }

    public void restartGame()
    {
        String restart = "restart";
        writer.println(restart);
        System.out.println("Restarting Game...");
    }

    public void noRestartGame()
    {
        String restart = "No Restart";
        writer.println(restart);
    }

    @Override
    public void close() throws Exception {

    }

}
