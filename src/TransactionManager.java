public class TransactionManager {

    public final IUI ui = new TextUI();

    public void makeTransaction(IEquity equity, int amount, User user) {

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;


        if (userBalance >= stockPrice) {
            userBalance -= stockPrice;
            ui.displayMessage("You have successfully bought " + equity.getName() + " .");
        } else {
            ui.displayMessage("You don't have enough to buy " + equity.getName() + " .");
        }
    }
}
