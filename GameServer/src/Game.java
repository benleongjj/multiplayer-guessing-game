import java.util.*;
import java.util.regex.Pattern;

public class Game {
    public Map<Integer, Player> currentPlayers = new HashMap<>();
    public Map<Player, Integer> winList = new HashMap<>();
    public Map<Player, Integer> topFrequencySingleGuess = new HashMap<>();
    public static int idCount = 100;

    public Game()
    {

        Player dummy1 = new Player(91,14, 3);
        Player dummy2 = new Player(92,25, 3);
        Player dummy3 = new Player(93,34,2);
        Player dummy4 = new Player(94,30,3);
        Player dummy5 = new Player(95,41,5);
        Player dummy6 = new Player(96,0, 6);
        Player dummy7 = new Player(97,0, 7);
        Player dummy8 = new Player(98,0,4);
        Player dummy9 = new Player(99,0,8);
        Player dummy10 = new Player(100,0,6);

        currentPlayers.put(dummy1.getPlayerId(),dummy1);
        currentPlayers.put(dummy2.getPlayerId(),dummy2);
        currentPlayers.put(dummy3.getPlayerId(),dummy3);
        currentPlayers.put(dummy4.getPlayerId(),dummy4);
        currentPlayers.put(dummy5.getPlayerId(),dummy5);

        winList.put(dummy1,dummy1.getNumberOfWins());
        winList.put(dummy2,dummy2.getNumberOfWins());
        winList.put(dummy3,dummy3.getNumberOfWins());
        winList.put(dummy4,dummy4.getNumberOfWins());
        winList.put(dummy5,dummy5.getNumberOfWins());
        winList.put(dummy6,dummy6.getNumberOfWins());
        winList.put(dummy7,dummy7.getNumberOfWins());
        winList.put(dummy8,dummy8.getNumberOfWins());
        winList.put(dummy9,dummy9.getNumberOfWins());
        winList.put(dummy10,dummy10.getNumberOfWins());

    }

    public void createPlayer()
    {
        idCount++;
        int playerId = idCount;
        int playerScore = 0;
        int playerNumberWins = 0;
        Player player = new Player(playerId, playerScore, playerNumberWins);
        currentPlayers.put(playerId,player);
    }

    public void removePlayer(int currentID)
    {
        currentPlayers.remove(currentID);
    }
    public List<Integer> getListofPlayers()
    {
        List<Integer> result = new ArrayList<Integer>();
        for (Player player: currentPlayers.values()) {
            result.add(player.getPlayerId());
        }
        return result;
    }
    public String getListofWonPlayers()
    {
        String wonPlayers = "Winner List: ";
        for (Map.Entry<Player, Integer> wonPlayer :
                winList.entrySet()) {
            wonPlayers = wonPlayers + "Player " + wonPlayer.getKey().getPlayerId() + ": "
                    + wonPlayer.getKey().getPlayerScore() + " Points, ";
        }
        return wonPlayers;
    }

    public String getListofMostWon()
    {
        String wonPlayers = "Most Win List:";
        HashMap<Player, Integer> sortedWinList = sortByValue(winList);
        for (Map.Entry<Player, Integer> wonPlayer :
                sortedWinList.entrySet()) {
            wonPlayers = wonPlayers + "Player " + wonPlayer.getKey().getPlayerId() + ": "
                    + wonPlayer.getValue() + " Win(s), ";
        }
        return wonPlayers;
    }

    public static HashMap<Player, Integer> sortByValue(Map<Player, Integer> hm) {

        List<Map.Entry<Player, Integer> > list
                = new LinkedList<>(
                hm.entrySet());

        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        HashMap<Player, Integer> temp
                = new LinkedHashMap<>();
        for (Map.Entry<Player, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public int getCurrentCount()
    {
        return idCount;
    }

    public Player getPlayer(int currentID)
    {
        return currentPlayers.get(currentID);
    }
    public int getCurrentPlayerID(int currentID)
    {
        int playerID = currentPlayers.get(currentID).getPlayerId();
        return playerID;
    }

    public int[] stringToArray(String numbers)
    {
        String[] string = numbers.split(" ");
        int[] arr = new int[string.length];
        for (int i = 0; i < string.length; i++) {
            arr[i] = Integer.valueOf(string[i]);
        }
        return arr;
    }

    public Boolean userInputValid(String userInput)
    {
        if(Pattern.matches("^[0-9]\s[0-9]\s[0-9]",userInput)){
            return true;
        } else {
            return false;
        }
    }
    public int[] generateNumbers()
    {
        int[] randomNumbers = new int[3];
        int min = 1; int max = 10;
        for (int i=0; i<3; i++)
        {
            randomNumbers[i] = (int) (Math.random() * (max - min)) + min;
        }
        System.out.println("Must guess: " + Arrays.toString(randomNumbers));
        return randomNumbers;
    }

    public int getPlayerScore(int playerID)
    {
        int playerScore = currentPlayers.get(playerID).getPlayerScore();
        return playerScore;
    }

    public void setPlayerScore(int playerID, int newScore)
    {
        currentPlayers.get(playerID).setPlayerScore(newScore);
    }

    public void setPlayerWinsNumber(int playerID, int numberOfWins)
    {
        currentPlayers.get(playerID).setNumberOfWins(numberOfWins);
    }
    public String guessFeedback(int[] playerGuess, int[] randomNumbers)
    {
        String completeFeedback = "";
        String feedback;
        for(int i=0; i<playerGuess.length; i++)
        {
            if(playerGuess[i] < randomNumbers[i]) {
                feedback = (i+1) + " number must be higher. ";
            } else if (playerGuess[i] > randomNumbers[i]) {
                feedback = (i+1) + " number must be lower. ";
            } else
            {
                feedback = (i+1) + " is correct. ";
            }
            completeFeedback = completeFeedback + feedback;
        }
        return completeFeedback;
    }

}
