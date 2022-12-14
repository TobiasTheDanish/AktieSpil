import javax.xml.soap.Text;
import java.util.Random;

public class SimulateManager {
    TextUI textUI = new TextUI();
    public void simulateDay(){

    }


    public void simulateStock(IEquity equity){

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
