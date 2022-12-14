public class GameManager {

    //TODO: Avoid static if possible
    private static float winCondition;
    private float loseCondition;


    public void chooseWinLoseCondition(float winCon, float loseCon){

        winCondition = winCon;
        loseCondition = loseCon;
    }

    public boolean hasPlayerWon(User user){
        if(user.getPortfolio().calculateTotalValue() >= winCondition){
            return true;
        } else {
            return false;
        }
    }

    public boolean hasPlayerLost(User user){

        if(user.getPortfolio().calculateTotalValue() <= loseCondition){
            return true;
        } else {
            return false;
        }
    }

    public static float getWinCondition() {
        return winCondition;
    }
}
