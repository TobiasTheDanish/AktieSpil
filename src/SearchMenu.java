public class SearchMenu implements IMenu
{
    @Override
    public void enter(Application application)
    {
        displayStocks(application);
    }

    private void displayStocks(Application application) {
        TransactionManager transactionManager = new TransactionManager(application);
        TextUI textUI = application.ui.asTextUI();
        FileIO fileIO = application.dataIO.asFileIO();

        textUI.clearConsole();
        textUI.displayMessage("This is all available stocks:");
        textUI.printListOfEquities(application.getEquities());

        IEquity selectedEquity = null;

        while (selectedEquity == null) {

            textUI.displayMessage("Enter the number of the stock you would like to get a closer look at");
            String input = textUI.getInputOnLine("or press" + Colors.ANSI_CYAN + " 'Q' " + Colors.ANSI_RESET + "to go back: ");
            if (input.trim().equalsIgnoreCase("Q")) {
                exit(application);
                return;
            }

            try {
                int stockIndex = Integer.parseInt(input);
                selectedEquity = application.getEquities().get(stockIndex - 1);
            } catch (NumberFormatException e) {
                textUI.displayMessage("That was not a number. Try again.");
            } catch (IndexOutOfBoundsException e) {
                textUI.displayMessage("That number was too big or too small. Try again.");
            }
        }

        textUI.clearConsole();
        textUI.displayMessage("Name: " + selectedEquity.getName() + ".");
        textUI.displayMessage("Price: " + selectedEquity.getPrice() + ".");
        textUI.displayMessage("Min/max range pr. day: " + selectedEquity.getRange() + ".");
        textUI.displayMessage("Risk of bankruptcy: " + Colors.ANSI_RED + selectedEquity.getRiskOfBankruptcy() + "%" + Colors.ANSI_RESET + ".");
        textUI.displayMessage("-------------------------");
        textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
        String buyInput = textUI.getInput("Press" + Colors.ANSI_CYAN + " 'B' " + Colors.ANSI_RESET + "to buy stock or press"+ Colors.ANSI_CYAN + " 'ENTER' " + Colors.ANSI_RESET +"to go back: ");
        int amount = 0;
        if (buyInput.trim().equalsIgnoreCase("B")) {
            while (true) {
                try {

                    textUI.displayMessage("How many stocks would you like to buy for " + selectedEquity.getPrice() + " each.\nPress Q to go back.");
                    String amountInput = textUI.getInput("Quantity: ");
                    if (amountInput.equalsIgnoreCase("Q")) {

                        return;
                    }
                    amount = Integer.parseInt(amountInput);

                    if (transactionManager.makeTransaction(selectedEquity, amount, application.getCurrentUser())) {
                        exit(application);
                        break;
                    }
                } catch (NumberFormatException e) {
                    textUI.displayMessage("That was not a number. Try again.");
                } catch (IndexOutOfBoundsException e) {
                    textUI.displayMessage("That number was too big or too small. Try again.");
                }
            }
        }
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
