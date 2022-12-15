public class GameManager {
    private final float winCondition;
    private final float loseCondition;

    public GameManager(float winCondition, float loseCondition)
    {
        this.winCondition = winCondition;
        this.loseCondition = loseCondition;
    }

    public boolean hasPlayerWon(User user){
        return user.getPortfolio().calculateTotalValue() >= winCondition;
    }

    public boolean hasPlayerLost(User user){
        return user.getPortfolio().calculateTotalValue() <= loseCondition;
    }

    public float getWinCondition() {
        return winCondition;
    }
}
