public class TransactionManager {



    public void makeTransaction(IEquity equity, int amount, User user){

      float userBalance = user.getPortfolio().getBalance();
      float stockPrice = equity.getPrice() * amount;


      if (userBalance >= stockPrice){
          userBalance -= stockPrice;
          //########################################
          //Kan ikke få lov at bruge TextUI herinde!
          //ToDo use TextUI.displayMessage("You have successfully bought " + equity.getName() + " .");
          }
      else {
          //ToDo use TextUI.displayMessage("You don't have enough to buy " + equity.getName() + " .");
          //########################################
      }
    }

}
