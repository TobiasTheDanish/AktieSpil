public class SearchMenu implements IMenu
{
    @Override
    public void enter(Application application)
    {
        displayStocks(application);
    }

    private void displayStocks(Application application) {
        TextUI textUI = application.ui.asTextUI();

        textUI.clearConsole();
        textUI.displayMessage("This is all available stocks:");
        textUI.printListOfEquities(application.getEquities());

        IEquity selectedEquity = null;

        while (selectedEquity == null) {

            textUI.displayMessage("Enter the number of the stock you would like to get a closer look at");
            String input = textUI.getInputOnLine("or press 'Q' to go back: ");

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
        textUI.displayMessage( "Risk of bankruptcy: " + Colors.ANSI_RED + selectedEquity.getRiskOfBankruptcy() + "%" + Colors.ANSI_RESET + ".");

        textUI.displayMessage("-------------------------");
        textUI.getInput("Press 'ENTER' to go back.");
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
