import java.awt.*;
import java.util.Scanner;

public class MainMenu implements IMenu
{

    private void displayUserOptions(Application application) {
        TextUI textUI = application.ui.asTextUI();
        String input;    //do makes sure that the loop always runs at least once.
        textUI.clearConsole();

        //Display options to the user, and prompt them to make a selection. Then save the selection in the input variable
        textUI.displayMessage("---------------------------------------------------------");
        textUI.displayMessage("What action do you wish to make?");
        textUI.displayMessage("1)   Search for stocks.\n" +
                                   "2)   View your portfolio.\n" +
                                   "3)   View your previous transactions.\n" +
                                   "4)   Make transaction.\n");
        input = textUI.getInput("Enter your selection, or press 'Q' to log out:");
        //Switch statement to perform different logic based on input
        switch (input.trim()) {
            case "q":
            case "Q":
                //If input is "Q" or "q" break out of the switch statement
                exit(application);
                break;
            case "1": {
                displayStocks(application);
                break;
            } case "2": {
                displayPortfolio();
                break;
            } case "3": {
                application.menuStack.push(new TransactionMenu("displayPrevTransactions"));
                break;
            } case "4": {
                application.menuStack.push(new TransactionMenu("makeTransaction"));
                break;
            }
            default:
                //If none of the above is reached, prompt the user to let them know.
                textUI.getInput("That was not a valid action. Press enter to try again.");
                textUI.clearConsole();
                break;
        }
    }


    private void displayPortfolio() {

    }

    private void displayStocks(Application application) {
        TextUI textUI = application.ui.asTextUI();
        FileIO fileIO = application.dataIO.asFileIO();

        while (true) {
            textUI.clearConsole();
            textUI.displayMessage("This is all available stocks:");
            textUI.printListOfEquities(application.getEquities());

            IEquity selectedEquity = null;

            while (selectedEquity == null) {

                textUI.displayMessage("Enter the number of the stock you would like to get a closer look at");
                String input = textUI.getInputOnLine("or press 'Q' to go back: ");
                if (input.trim().equalsIgnoreCase("Q")) return;

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

    }

    public void enter(Application application)
    {
        displayUserOptions(application);
    }

    @Override
    public void exit (Application application)
    {
        application.menuStack.pop();
    }

}