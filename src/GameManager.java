public class GameManager {

    //TODO: Avoid static if possible
    private static float winCondition;
    private float loseCondition;


    public void chooseWinLoseCondition(float winCon, float loseCon){

        winCondition = winCon;
        loseCondition = loseCon;
    }

    public boolean hasPlayerWon(User user){
        return user.getPortfolio().calculateTotalValue() >= winCondition;
    }

    public boolean hasPlayerLost(User user){
        return user.getPortfolio().calculateTotalValue() <= loseCondition;
    }

    public static float getWinCondition() {
        return winCondition;
    }
}
