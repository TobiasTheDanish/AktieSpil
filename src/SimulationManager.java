import java.util.Random;

public class SimulationManager {

    private int day;

    public SimulationManager(int day) {
        this.day = day;
    }

    public void simulateDay(User user){
        IUI textUI = new TextUI();

        textUI.displayMessage("Day: " + day);
        textUI.displayMessage("Your current portfolio is worth: " + user.getPortfolio().calculateTotalEquities());
        textUI.displayMessage("And your current balance is: " + user.getPortfolio().getBalance());
        textUI.displayMessage("Goal: " + user.getPortfolio().calculateTotalValue() + " / " + GameManager.getWinCondition());

    }

    public void simulateStock(IEquity equity){

        IUI textUI = new TextUI();
        Random rnd = new Random();
        float randomRange = rnd.nextFloat(); //TODO: (equity.getRange())

        if (randomRange >= 0){
            float increase = equity.getPrice() / 100 * randomRange;
            float newValue = equity.getPrice() + increase;
            textUI.displayMessage(equity.getName() + " has increased by " + randomRange + "% and is now worth " + newValue);
        } else if (randomRange < 0) {
            float decrease = equity.getPrice() / 100 * randomRange;
            float newValue = equity.getPrice() - decrease;
            textUI.displayMessage(equity.getName() + " has decreased by " + randomRange + "% and is now worth " + newValue);
        }
    }
}
