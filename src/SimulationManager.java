import java.util.ArrayList;
import java.util.Random;

public class SimulationManager
{
    private final Random random = new Random();
    private int day;

    public SimulationManager() {}

    public SimulationManager(int day) {
        this.day = day;
    }

    public void simulateDay(Application application)
    {
        IUI textUI = application.ui.asTextUI();
        ArrayList<IEquity> equities = application.getEquities();

        for (IEquity equity : equities)
        {
            int index = equities.indexOf(equity);
            equities.set(index, simulateEquity(equity));
        }
        textUI.displayMessage("Day: " + day);
        textUI.displayMessage("Your current portfolio is worth: " + application.getCurrentUser().getPortfolio().calculateTotalEquities());
        textUI.displayMessage("And your current balance is: " + application.getCurrentUser().getPortfolio().getBalance());
        textUI.displayMessage("Goal: " + application.getCurrentUser().getPortfolio().calculateTotalValue() + " / " + GameManager.getWinCondition());
    }

    private IEquity simulateEquity(IEquity equity)
    {
        //Simulates the equity's bankruptcy state
        {
            if (equity.isBankrupt())
            {
                return equity;
            }

            float bankruptcy = random.nextFloat() * 100;

            equity.setIsBankrupt(bankruptcy <= equity.getRiskOfBankruptcy());
        }

        //Simulates the equity's increase/decrease in value
        {
            Range range = equity.getRange();

            float rangeMedian = (range.max + range.min) / 2;
            float numInRange = range.max - range.min;

            int weight = random.nextInt(101);
            float increaseRangeModifier = 0f;

            if (weight <= 35)
            {
                increaseRangeModifier = 0.10f;
            }
            else if (weight <= 60)
            {
                increaseRangeModifier = 0.20f;
            }
            else if (weight <= 75)
            {
                increaseRangeModifier = 0.40f;
            }
            else if (weight <= 85)
            {
                increaseRangeModifier = 0.60f;
            }
            else if (weight <= 95)
            {
                increaseRangeModifier = 0.80f;
            }
            else
            {
                increaseRangeModifier = 1.00f;
            }

            int increaseRange = random.nextInt((int) (numInRange * increaseRangeModifier));

            float increasePercentage = (float) (random.nextInt(
                    (int) rangeMedian + increaseRange) - increaseRange / 2) / 100;
            float increase = equity.getPrice() * increasePercentage;
            equity.setPrice(equity.getPrice() + increase);

            return equity;
        }
    }
}
