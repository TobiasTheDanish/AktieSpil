import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        Map<String, Float> preSimulationPrices = new HashMap<>();
        textUI.displayMessage("Simulating day " + day);
        float oldPortfolioValue = application.getCurrentUser().getPortfolio().calculateTotalEquities();

        for (int i = 0; i < equities.size(); i++)
        {
            preSimulationPrices.put(equities.get(i).getName(),equities.get(i).getPrice());
            StringBuilder string = new StringBuilder(140);
            try {
                int current = i;
                equities.set(i, simulateEquity(equities.get(i)));

                Thread.sleep(50);
                string.append('\r').append(++current).append("/").append(equities.size()).append(" equities simulated.");
                textUI.displayMessageOnLine(string.toString());
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        if (application.getGameManager().hasPlayerLost(application.getCurrentUser()))
        {
            textUI.displayMessage("YOU LOST!!!");
            while (!application.menuStack.isEmpty())
            {
                application.menuStack.pop();
            }
        }
        else if (application.getGameManager().hasPlayerWon(application.getCurrentUser()))
        {
            textUI.displayMessage("YOU WON!");

            while (!application.menuStack.isEmpty())
            {
                application.menuStack.pop();
            }
        }

        textUI.displayMessage("\nYour portfolio is now worth: " + application.getCurrentUser().getPortfolio().calculateTotalEquities() + "$");
        textUI.displayMessage("It grew by: " + (application.getCurrentUser().getPortfolio().calculateTotalEquities() - oldPortfolioValue) + "$ over night.");
        textUI.displayMessage("Goal: " + application.getCurrentUser().getPortfolio().calculateTotalValue() + " / " + application.getGameManager().getWinCondition());
        String input = textUI.getInput("Press" + Colors.ANSI_CYAN + " 'M'" + Colors.ANSI_RESET + " to view more info,\n" +
                                "press" + Colors.ANSI_CYAN + " 'Q'" + Colors.ANSI_RESET + " to return to the main menu.");
        while (!input.equalsIgnoreCase("Q") && ! input.equalsIgnoreCase("M"))
        {
            input = textUI.getInput("Invalid input. Try again.");
        }

        if (input.equalsIgnoreCase("M"))
        {
            displaySimulationInfo(application, preSimulationPrices);
        }
    }

    private void displaySimulationInfo(Application application, Map<String,Float> preSimulationPrices)
    {
        TextUI textUI = application.ui.asTextUI();

        Portfolio userPortfolio = application.getCurrentUser().getPortfolio();

        textUI.displayMessage("Here is an overview of your stocks: ");
        for (IEquity equity : userPortfolio.getEquities())
        {
            float investment = userPortfolio.getAveragePrice(equity) * userPortfolio.getAmountOfEquity(equity);
            float initialValue = preSimulationPrices.get(equity.getName());
            float increase = equity.getPrice() - initialValue;
            float increasePercentage = (increase/initialValue) * 100;
            float stockReturn = userPortfolio.calculateStockReturn(equity);
            float stockReturnPercentage = (stockReturn/investment) * 100;

            if (!equity.isBankrupt()) {
                String increaseText = increase > 0 ? Colors.ANSI_GREEN + increase + Colors.ANSI_RESET : Colors.ANSI_RED + increase + Colors.ANSI_RESET;
                String increasePercentageText = increasePercentage > 0 ? Colors.ANSI_GREEN + increasePercentage + Colors.ANSI_RESET : Colors.ANSI_RED + increasePercentage + Colors.ANSI_RESET;
                String stockReturnText = stockReturn > 0 ? Colors.ANSI_GREEN + stockReturn + Colors.ANSI_RESET : Colors.ANSI_RED + stockReturn + Colors.ANSI_RESET;
                String stockReturnPercentageText = stockReturnPercentage > 0 ? Colors.ANSI_GREEN + stockReturnPercentage + Colors.ANSI_RESET : Colors.ANSI_RED + stockReturnPercentage + Colors.ANSI_RESET;
                textUI.displayMessage("-\t" + equity.getName() + " increased by " + increaseText + "$ / " + increasePercentageText + "%\n" +
                                              "\tYesterday it was worth " + initialValue + "$, today it is worth " + equity.getPrice() +"$\n" +
                                              "\tReturn on investment: " + stockReturnText + "$ / " + stockReturnPercentageText + "%" + "\n");
            } else {
                textUI.displayMessage("-\tOH NO! " + equity.getName() + " has gone" + Colors.ANSI_RED + " bankrupt...\n" +Colors.ANSI_RESET +
                                              "\tYou have lost your investment of: " +Colors.ANSI_RED + investment + Colors.ANSI_RESET + "$\n");
            }
        }

        textUI.displayMessage("");
        textUI.displayMessage("Your portfolios total return: " + userPortfolio.calculateTotalReturn() + "$");
        textUI.getInput("Press" + Colors.ANSI_CYAN + " 'ENTER'" + Colors.ANSI_RESET + " to continue");
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

            float numInRange = range.max - range.min;

            int weight = random.nextInt(101);
            float increaseRangeModifier;

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

            float increaseRange = random.nextFloat((numInRange * increaseRangeModifier));

            float increasePercentage = ((random.nextFloat(increaseRange)) - increaseRange/2) / 100;
            float increase = equity.getPrice() * increasePercentage;
            equity.setPrice(equity.getPrice() + increase);

            return equity;
        }
    }
}
