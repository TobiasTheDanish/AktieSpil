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
        textUI.displayMessage("1)   View all stocks.\n" +
                                   "2)   View your portfolio.\n" +
                                   "3)   View your previous transactions.\n" +
                                   "4)   Simulate to next day.\n\n");
        textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
        input = textUI.getInput("Enter your selection, or press" + Colors.ANSI_CYAN + " Q " + Colors.ANSI_RESET + "to log out:");
        input = textUI.getInput("Enter your selection, or press 'Q' to log out:");
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
                displayPortfolio(application);
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


    private void displayPortfolio(Application application) {
        TextUI textUI = application.ui.asTextUI();
       TransactionManager transactionManager = new TransactionManager(application);
        textUI.clearConsole();
        textUI.printListOfEquities(application.getCurrentUser().getPortfolio().equities);
        IEquity selectedEquity = null;
        String sellInput = null;

        while (selectedEquity == null)
        {
            String stockInput = textUI.getInput("What stock would you like to look at?");

            try
            {
                int stockIndex = Integer.parseInt(stockInput);
                selectedEquity = application.getCurrentUser().getPortfolio().equities.get(stockIndex-1);
            }
            catch (NumberFormatException e)
            {
                textUI.displayMessage("That was not a number. Try again.");
            }
            catch (IndexOutOfBoundsException e)
            {
                textUI.displayMessage("That number was too big or too small. Try again.");
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
                textUI.displayMessage("How many stocks of " + selectedEquity.getName() + " would you like to sell?");
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
                break;
            }
        }
        else {
            exit(application);
        }

    }

}







    private void displayStocks(Application application) {
        TransactionManager transactionManager = new TransactionManager(application);
        TextUI textUI = application.ui.asTextUI();
        FileIO fileIO = application.dataIO.asFileIO();

        while (true) {
            textUI.clearConsole();
            textUI.displayMessage("This is all available stocks:");
            textUI.printListOfEquities(application.getEquities());

            IEquity selectedEquity = null;

            while (selectedEquity == null) {

                textUI.displayMessage("Enter the number of the stock you would like to get a closer look at");
                String input = textUI.getInputOnLine("or press" + Colors.ANSI_CYAN + " Q " + Colors.ANSI_RESET + "to go back: ");
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

            while(true) {
                textUI.clearConsole();
                textUI.displayMessage("Name: " + selectedEquity.getName() + ".");
                textUI.displayMessage("Price: " + selectedEquity.getPrice() + ".");
                textUI.displayMessage("Min/max range pr. day: " + selectedEquity.getRange() + ".");
                textUI.displayMessage("Risk of bankruptcy: " + Colors.ANSI_RED + selectedEquity.getRiskOfBankruptcy() + "%" + Colors.ANSI_RESET + ".");
                textUI.displayMessage("-------------------------");
                textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
                String buyInput = textUI.getInput("Press" + Colors.ANSI_CYAN + " B " + Colors.ANSI_RESET + "to buy stock or press"+ Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET +"to go back: ");
                int amount = 0;
                if (buyInput.trim().equalsIgnoreCase("B")){
                    try
                    {
                     textUI.displayMessage("How many stocks would you like to buy for " + selectedEquity.getPrice() + " each.");
                     amount = Integer.parseInt(textUI.getInputOnLine("Quantity: "));
                     if(transactionManager.makeTransaction(selectedEquity, amount, application.getCurrentUser())){
                            return;
                     }
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
            }
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