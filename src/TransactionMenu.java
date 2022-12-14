public class TransactionMenu implements IMenu{


String nameOfFunction;
    public TransactionMenu(String nameOfFunction) {
   this.nameOfFunction = nameOfFunction;
    }

    private static void makeTransaction() {
// evt en switch case her med en lille menu ligesom i mainmenu
        // menuen kunne have mulighederne: 1: show list of available stocks press q to exit menu

    }
    public void displayPrevTransactions () {
    // evt en switch case her med en lille menu
        // evt 1: display previous transactions press q to exit menu og en default som de andre
    }
    public void enter (Application application){
        TextUI textUI = application.ui.asTextUI();
        switch (nameOfFunction) {
            case "makeTransaction": {
                makeTransaction();
                break;
            }
            case "displayPrevTransactions": {
                displayPrevTransactions();
                break;
            }
            default:
                //If none of the above is reached, prompt the user to let them know.
                textUI.getInput("That was not a valid action. Press enter to try again.");
                textUI.clearConsole();
                break;
        }
    }

    public void exit (Application application){
        application.menuStack.pop();
    }
}