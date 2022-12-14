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
                                   "4)   Make transaction.\n" +
                                   "5)   Simulate to next day.\n");
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
                displayPortfolio();
                break;
            } case "3": {
                application.menuStack.push(new TransactionMenu("displayPrevTransactions"));
                break;
            } case "4": {
                application.menuStack.push(new TransactionMenu("makeTransaction"));
                break;
            }
            case "5": {
                application.menuStack.push(new SimulationMenu());
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

    @Override
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