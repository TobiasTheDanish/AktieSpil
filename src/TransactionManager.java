public class TransactionManager {

    private Application application;

    public TransactionManager(Application application) {
        this.application = application;
    }

    public boolean makeTransaction(IEquity equity, int amount, User user) {
        TextUI textUI = application.ui.asTextUI();

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;
        if (amount <= 0){
            return false;
        }

        if (userBalance >= stockPrice) {
            userBalance -= stockPrice;
            user.getPortfolio().setBalance(userBalance);
            textUI.displayMessage("You have successfully bought " + amount + " " + equity.getName() + " for " + equity.getPrice() + " each.");
            textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
            textUI.getInput("Press" + Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET + "to continue.");
            return true;
        } else {
            textUI.displayMessage("You don't have enough to buy " + amount + " " + equity.getName() + ".");
            textUI.getInput("Press" + Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET + "to continue.");
            return false;
        }
    }
}
