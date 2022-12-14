public class TransactionManager {

    private Application application;

    public TransactionManager(Application application) {
        this.application = application;
    }

    public void makeTransaction(IEquity equity, int amount, User user) {
        TextUI textUI = application.ui.asTextUI();

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;


        if (userBalance >= stockPrice) {
            userBalance -= stockPrice;
            user.getPortfolio().setBalance(userBalance);
            user.getPortfolio().addToPortfolio(equity, amount);
            textUI.displayMessage("You have successfully bought " + amount + " " + equity.getName() + " for " + equity.getPrice());
            textUI.getInput("Press ENTER to continue.");
        } else {
            textUI.displayMessage("You don't have enough to buy " + amount + " " + equity.getName() + " .");
            textUI.getInput("Press ENTER to continue.");
        }
    }
}
