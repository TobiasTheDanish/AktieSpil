public class MainMenu implements IMenu
{

    private void displayUserOptions(Application application) {
        TextUI textUI = application.ui.asTextUI();
        String input;    //do makes sure that the loop always runs at least once.
        textUI.clearConsole();

        //Display options to the user, and prompt them to make a selection. Then save the selection in the input variable
        textUI.displayMessage("---------------------------------------------------------");
        textUI.displayMessage("What action do you wish to make?");
        textUI.displayMessage("1)   View all stocks.\n" +
                                   "2)   View your portfolio.\n" +
                                   "3)   View your previous transactions.\n" +
                                   "4)   Simulate to next day.\n\n");
        textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
        input = textUI.getInput("Enter your selection, or press" + Colors.ANSI_CYAN + " Q " + Colors.ANSI_RESET + "to log out:");
        //Switch statement to perform different logic based on input
        switch (input.trim()) {
            case "q":
            case "Q":
                //If input is "Q" or "q" break out of the switch statement
                exit(application);
                break;
            case "1": {
                application.menuStack.push(new SearchMenu());
                break;
            } case "2": {
                application.menuStack.push(new PortfolioMenu());
                break;
            } case "3": {
                application.menuStack.push(new TransactionMenu("displayPrevTransactions"));
                break;
            } case "4":  {
                application.menuStack.push(new SimulationMenu());
                break;
            }
            default:
                //If none of the above is reached, prompt the user to let them know.
                textUI.getInput("That was not a valid action. Press" + Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET + "to try again.");
                textUI.clearConsole();
                break;
        }
    }


    @Override
    public void enter(Application application) {
        displayUserOptions(application);
    }

    @Override
    public void exit (Application application) {
        application.menuStack.pop();
    }

}