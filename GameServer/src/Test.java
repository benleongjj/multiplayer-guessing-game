import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {
    public static Boolean playAgain()
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
    public static void main(String[] args) {

        playAgain();

//        Game newGame = new Game();
//        String playerGuess = "4 5 5";
//        int[] playerGuessArray = newGame.stringToArray(playerGuess);
//        int[] array = {4, 5, 5};
//
//        System.out.println(Arrays.equals(playerGuessArray,array));

//        String userInput = "4 5 6";
//        if (Pattern.matches("^[0-9]\s[0-9]\s[0-9]", userInput)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
        
    }
}
