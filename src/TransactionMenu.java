public class TransactionMenu implements IMenu{
	public final IDataIO dataIO = new FileIO();
	@Override
	public void enter(Application application) {
		buyMenu(application);
		exit(application);
	}

	@Override
	public void exit(Application application) {
		application.menuStack.pop();
	}

	public void buyMenu(Application application){
		TextUI textUI = application.ui.asTextUI();
		textUI.clearConsole();
		textUI.displayMessage("Welcome to the Buy Menu");
		textUI.displayMessage("");
		textUI.displayMessage("1) View all stocks\n" +
									  "2) Go back");
		String input = textUI.getInputOnLine("What would you like to do: ");
		String buyInput;
		String buyAmount = "0";
		switch (input){
		  case "1":{
			  textUI.clearConsole();
			  //ToDo Print out all stocks available.
			 // textUI.displayMessage();
			  String stockInput = textUI.getInput("Which stock would you like to buy?");

			  //ToDo make a case for every single stock. DONT DO THIS!!!!!
			  // (Just Copy/Paste and change the name, so the name/price is right) NO PLEASE DONT :(
			  switch (stockInput){
				  case "1":{
					  textUI.clearConsole();
					  do {

					  textUI.displayMessage("1) Buy stock\n" +
													  "2) Go back");
					   buyInput = textUI.getInput("What would you like to do?");
					  } while (!buyInput.trim().equals("1") && !buyInput.trim().equals("2"));

					  if (buyInput.trim().equals("1")){
						  textUI.clearConsole();

						  //ToDo Måske en TryCatch eller lign. der sørger for at buyAmount KUN tager imod tal.
						  textUI.displayMessage("How many stocks of " + "" + " would you like to purchase?");
						  buyAmount = textUI.getInputOnLine("Quantity: ");


						  //##################
						  //Aner ikke hvorfor det her ikke virker????
						 // int amount = Interger.parseInt(buyAmount);


						  //ToDo call the TransactionManager.makeTransaction(stockname, buyAmount, user)
					  }
				  }
				  case "2":{
					  //ToDo Copy Case 1 and rewrite name/price so i matches the next stock... NOOOO PLEEEEASE ;(((

				  }
			  }
		  }
		  case "2":{
			  textUI.clearConsole();
		  //ToDo Go back to MainMenu
		  }
	  }


	}


}
