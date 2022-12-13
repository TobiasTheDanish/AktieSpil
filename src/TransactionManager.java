public class TransactionManager {


    public void makeTransaction(IEquity equity, int amount, User user) {
        IUI textUI = new TextUI();

        float userBalance = user.getPortfolio().getBalance();
        float stockPrice = equity.getPrice() * amount;


        if (userBalance >= stockPrice) {
            userBalance -= stockPrice;
            textUI.displayMessage("You have successfully bought " + equity.getName() + " .");
        } else {
            textUI.displayMessage("You don't have enough to buy " + equity.getName() + " .");
        }
    }
}
