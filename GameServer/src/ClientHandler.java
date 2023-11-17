import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket socket;
    private Game game;
    public ClientHandler(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }

    @Override
    public void run() {

        //Declare initial variables
        int currentID = 0;
        int numberWins = 0;
        int playerScore;
        int[] randomNumbers;

        try {
            //Declare Scanner and Writer objects
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Player Joined");

            //Create new player and insert into current player list
            game.createPlayer();
            Boolean gameEnd = false;
            while (!gameEnd)
            {
                System.out.println("Start!");
                currentID = game.getCurrentCount();
                playerScore = game.getPlayerScore(currentID);

                //Send over information for getClientInfo() to read
                writer.println(game.getListofPlayers());
                writer.println(game.getCurrentPlayerID(currentID));
                writer.println(game.getPlayerScore(currentID));
                writer.println(game.getPlayerScore(currentID));

                //Start game
                while(playerScore < 50)
                {
                    int guessCount = 10;
                    Boolean playing = true;

                    //Generate array of random integers
                    randomNumbers = game.generateNumbers();

                    while(playing)
                    {
                        String playerGuess = scanner.nextLine();

                        //Check if player wants to exit the game
                        if(playerGuess.equals("exit"))
                        {
                            playing = false;
                            break;
                        }

                        //Validate player's input
                        if (game.userInputValid(playerGuess) == false) {
                            writer.println("false");
                            writer.println("Invalid Input. Please Enter Number Again.");
                            continue;
                        } else
                        {
                            writer.println("true");
                            writer.println("");
                        }
                        int[] playerGuessArray = game.stringToArray(playerGuess);

                        //Check if input numbers are the same as generated numbers
                        //If they are not, provide feedback to user
                        if(!Arrays.equals(playerGuessArray, randomNumbers))
                        {
                            guessCount--;
                            String feedback = game.guessFeedback(playerGuessArray,randomNumbers);
                            writer.println("false");
                            writer.println(feedback);
                            continue;
                        }

                        //If the numbers are the same, confirm it with the user
                        writer.println("true");
                        System.out.println("Answer is Correct.");
                        playerScore += guessCount;
                        break;
                    }
                    if(!playing)
                    {
                        gameEnd = true;
                        break;
                    }
                }

                //Once player's score >50, break out of while loop
                System.out.println("Player has won.");
                writer.println(playerScore);

                //Set the number of wins in the player's profile and insert player into WinList
                numberWins++;
                game.setPlayerWinsNumber(currentID,numberWins);
                System.out.println("Number of Wins: " + numberWins);
                game.winList.put(game.getPlayer(currentID),numberWins);

                //Display list of players who won and lists of top 10 players with most number of wins
                writer.println(game.getListofWonPlayers());
                writer.println(game.getListofMostWon());
                writer.println("You have a total of " + numberWins + " win(s)!");

                //Give player the option to restart and play again
                if(scanner.nextLine().equals("restart"))
                {
                    game.setPlayerScore(currentID, 0);
                } else
                {
                    writer.println(game.getListofWonPlayers());
                    gameEnd = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Player " + game.getCurrentPlayerID(currentID) + " Left.");
            game.winList.put(game.getPlayer(currentID),numberWins);
            game.removePlayer(currentID);

        } finally {
            game.removePlayer(currentID);
            System.out.println("Player " + currentID + " disconnected.");
        }

    }
}
