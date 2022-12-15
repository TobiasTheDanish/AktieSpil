public class GameManager {
    private final float startBalance;
    private final float winCondition;
    private final float loseCondition;

    public GameManager(float startBalance, float winCondition, float loseCondition)
    {
        this.startBalance = startBalance;
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

    public float getStartBalance()
    {
        return startBalance;
    }

    public float getLossCondition()
    {
        return loseCondition;
    }
}
