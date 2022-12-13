public class TransactionMenu implements IMenu{
    public final IDataIO dataIO = new FileIO();
    @Override
    public void enter(Application application) {
        buyMenu();
    }

    @Override
    public void exit(Application application) {

    }

    public void buyMenu(){
      asTextUI().clearConsole();
      asTextUI().displayMessage("Welcome to the Buy Menu");
      asTextUI().displayMessage("");
      asTextUI().displayMessage("1) View all stocks\n" +
                                      "2) Go back");
      String input = asTextUI().getInputOnLine("What would you like to do: ");
      String buyInput;
      String buyAmount = "0";
      switch (input){
          case "1":{
              asTextUI().clearConsole();
              //ToDo Print out all stocks available.
             // asTextUI().displayMessage();
              String stockInput = asTextUI().getInput("Which stock would you like to buy?");

              //ToDo make a case for every single stock.
              // (Just Copy/Paste and change the name, so the name/price is right)
              switch (stockInput){
                  case "1":{
                      asTextUI().clearConsole();
                      do {

                      asTextUI().displayMessage("1) Buy stock\n" +
                                                      "2) Go back");
                       buyInput = asTextUI().getInput("What would you like to do?");
                      } while (!buyInput.trim().equals("1") && !buyInput.trim().equals("2"));

                      if (buyInput.trim().equals("1")){
                          asTextUI().clearConsole();

                          //ToDo Måske en TryCatch eller lign. der sørger for at buyAmount KUN tager imod tal.
                          asTextUI().displayMessage("How many stocks of " + "" + " would you like to purchase?");
                          buyAmount = asTextUI().getInputOnLine("Quantity: ");


                          //##################
                          //Aner ikke hvorfor det her ikke virker????
                         // int amount = Interger.parseInt(buyAmount);


                          //ToDo call the TransactionManager.makeTransaction(stockname, buyAmount, user)
                      }
                  }
                  case "2":{
                      //ToDo Copy Case 1 and rewrite name/price so i matches the next stock

                  }
              }
          }
          case "2":{
              asTextUI().clearConsole();
          //ToDo Go back to MainMenu
          }
      }


    }


}
