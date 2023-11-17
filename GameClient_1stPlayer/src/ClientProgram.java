public class ClientProgram {

    public static void main(String[] args) {

        System.out.println("=========================");
        System.out.println("Welcome to the Game!");

        try {
            //Establish connection with game server
            Client client = new Client();
            Boolean gameActive = true;
            while(gameActive)
            {
                //Display player info: Current List of Players, Player ID, Current Score
                client.getClientInfo();
                int playerScore = client.getPlayerScore();

                //Start game
                while(playerScore < 50)
                {
                    int guessCount = 10;
                    Boolean guessing = true;
                    while (guessing)
                    {
                        //Player input number and send it over to server
                        client.guessNumber();
                        //Server validates if the input is in correct format
                        if(client.serverGuessCheck())
                        {
                            Boolean guessedCorrect = client.guessedCorrect();

                            //Server validates if numbers are correct
                            if(!guessedCorrect)
                            {
                                //Server provides game clues
                                client.getFeedback();
                                if(guessCount == 0)
                                {
                                    System.out.println("Game Over.");
                                } else {
                                    guessCount--;
                                    System.out.println("You have " + guessCount + " tries left.");
                                    System.out.println("----------------------------------------");
                                    continue;
                                }
                            }
                            else {
                                break;
                            }
                        }
                        else {}
                    }


                    //If number is correct, display message and increase the player score
                    System.out.println("*************************************");
                    System.out.println("Well done, you have guessed correctly!");
                    playerScore += guessCount;
                    System.out.println("Your Current Score is: " + playerScore);
                    System.out.println("*************************************");
                }
                System.out.println("=================");
                System.out.println("Congratulations, You Won The Game!");
                System.out.println("Your Final Score is: " + client.getPlayerScore());
                System.out.println("=================");

                //Display the list of players who won, and players with most number of wins
                client.getListOfWonPlayers();
                client.getListOfMostWon();
                client.getNumberOfWins();

                //Ask the player if they want to play again
                Boolean playAgain = client.playAgain();
                if (playAgain == true)
                {
                    playerScore = 0;
                    client.restartGame();
                } else {
                    client.noRestartGame();
                    break;
                }
            }
            client.getNumberOfWins();
            System.out.println("Thank You For Playing.");
            System.out.println("Game has ended.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
