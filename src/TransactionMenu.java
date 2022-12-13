public class TransactionMenu implements IMenu{


String nameOfFunction;
    public TransactionMenu(String nameOfFunction) {
   this.nameOfFunction = nameOfFunction;
    }

    private static void makeTransaction() {


    }
    public void displayPrevTransactions () {

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