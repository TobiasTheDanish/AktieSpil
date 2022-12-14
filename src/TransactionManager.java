public class TransactionManager {

    private Application application;

    public TransactionManager(Application application)
    {
        this.application = application;
    }

    public void makeTransaction(IEquity equity, int amount, User user) {
        TextUI textUI = application.ui.asTextUI();

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;


        if (userBalance >= stockPrice) {
            userBalance -= stockPrice;
            user.getPortfolio().setBalance(userBalance);
            textUI.displayMessage("You have successfully bought " + equity.getName() + " .");
        } else {
            textUI.displayMessage("You don't have enough to buy " + equity.getName() + " .");
        }
    }
}
