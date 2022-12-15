public class PortfolioMenu implements IMenu
{
    @Override
    public void enter(Application application)
    {
        displayPortfolio(application);
    }

    private void displayPortfolio(Application application)
    {
        TextUI textUI = application.ui.asTextUI();
        TransactionManager transactionManager = new TransactionManager(application);
        textUI.clearConsole();
        textUI.printListOfEquities(application.getCurrentUser().getPortfolio().getEquities());
        IEquity selectedEquity = null;
        String sellInput = null;

        while (selectedEquity == null)
        {
            String stockInput = textUI.getInput("What stock would you like to look at?\nPress q to go back.");

            if (stockInput.equalsIgnoreCase("Q"))
            {
                exit(application);
                return;
            }

            try
            {
                int stockIndex = Integer.parseInt(stockInput);
                selectedEquity = application.getCurrentUser().getPortfolio().getEquities().get(stockIndex - 1);
            }
            catch (NumberFormatException e)
            {
                textUI.displayMessage("That was not a number. Try again.");
            }
            catch (IndexOutOfBoundsException e)
            {
                textUI.displayMessage("That number was too big or too small. Try again.");
            }
        }

        do {
            textUI.displayMessage("You selected " + selectedEquity.getName());
            textUI.displayMessage("1) Sell stocks.\n" +
                                          "2) Go back.");
            sellInput = textUI.getInput("What would you like to do with it?");
        } while (!sellInput.trim().equals("1") && !sellInput.trim().equals("2"));

        if(sellInput.trim().equals("1")) {
            textUI.clearConsole();

            while (true) {
                String goBack = textUI.getInput("How many stocks of " + selectedEquity.getName() + " would you like to sell?\nPress q to go back.");
                if (goBack.equalsIgnoreCase("Q"))
                {

                    break;
                }

                String amountInput = textUI.getInputOnLine("Quantity: ");

                try {
                    int sellAmount = Integer.parseInt(amountInput);

                    if (sellAmount <= 0) {
                        textUI.displayMessage("The amount cannot be negative or 0! Try again.");
                        continue;
                    }
                    transactionManager.sellStock(selectedEquity, sellAmount, application.getCurrentUser());
                    break;
                } catch (NumberFormatException e) {
                    textUI.displayMessage("That was not a number. Try again.");
                }
            }
        }
        else {
            exit(application);
        }
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}