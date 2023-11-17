public class Player {

    private int playerId;
    private int playerScore;
    private int numberOfWins;

    public Player(int playerId, int playerScore, int numberOfWins) {
        this.playerId = playerId;
        this.playerScore = playerScore;
        this.numberOfWins = numberOfWins;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
}
