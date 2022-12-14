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
            user.getPortfolio().addToPortfolio(equity, amount);
            textUI.displayMessage("You have successfully bought " + amount + " " + equity.getName() + " for " + equity.getPrice()*amount + "$.");
            textUI.displayMessage(Colors.ANSI_YELLOW + "Balance: " + application.getCurrentUser().getPortfolio().getBalance() + Colors.ANSI_RESET);
            textUI.getInput("Press" + Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET + "to continue.");
            return true;

        } else {
            textUI.displayMessage("You don't have enough to buy " + amount + " " + equity.getName() + ".");
            textUI.getInput("Press" + Colors.ANSI_CYAN + " ENTER " + Colors.ANSI_RESET + "to continue.");
            return false;
        }
    }

    public void sellStock(IEquity equity, int amount, User user) {
        TextUI textUI = application.ui.asTextUI();

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;

        userBalance += stockPrice;

        user.getPortfolio().setBalance(userBalance);
        user.getPortfolio().removeFromPortfolio(equity,amount);
        textUI.displayMessage("You have successfully sold " + amount + " " + equity.getName() + " for " + equity.getPrice()*amount + "$ (Each: " + equity.getPrice() + "$)");
        textUI.getInput("Press ENTER to continue.");
    }
}
