public class TransactionMenu implements IMenu
{
	private String nameOfFunction;
	private TransactionManager transactionManager = null;

	public TransactionMenu(String nameOfFunction)
	{
		this.nameOfFunction = nameOfFunction;
	}

	@Override
	public void enter(Application application)
	{
		transactionManager = new TransactionManager(application);
		TextUI textUI = application.ui.asTextUI();
		switch (nameOfFunction)
		{
			case "makeTransaction":
			{
				makeTransaction(application);
				break;
			}
			case "displayPrevTransactions":
			{
				displayPrevTransactions(application);
				break;
			}
			default:
				//If none of the above is reached, prompt the user to let them know.
				textUI.getInput("That was not a valid action. Press enter to try again.");
				textUI.clearConsole();
				break;
		}
	}

	@Override
	public void exit(Application application)
	{
		application.menuStack.pop();
	}

	private void makeTransaction(Application application) {
		// evt en switch case her med en lille menu ligesom i mainmenu
		// menuen kunne have mulighederne: 1: show list of available stocks press q to exit menu
		TextUI textUI = application.ui.asTextUI();
		textUI.clearConsole();
		textUI.displayMessage("Welcome to the Buy Menu");
		textUI.displayMessage("");
		textUI.displayMessage("1) View all stocks\n" +
				                   "2) Go back");
		String input = textUI.getInputOnLine("What would you like to do: ");
		switch (input)
		{
			case "1":
			{
				textUI.clearConsole();
				textUI.printListOfEquities(application.getEquities());

				IEquity selectedEquity = null;

				while (selectedEquity == null)
				{
					String stockInput = textUI.getInput("Enter number of the stock you would like to buy?");

					try
					{
						int stockIndex = Integer.parseInt(stockInput);
						selectedEquity = application.getEquities().get(stockIndex-1);
					}
					catch (NumberFormatException e)
					{
						textUI.displayMessage("That was not a number. Try again.");
					}
					catch (IndexOutOfBoundsException e)
					{
						textUI.displayMessage("That number was too big or too small. Try again.");
					}
				}

				textUI.clearConsole();
				String buyInput;
				do {
					textUI.displayMessage("You selected " + selectedEquity.getName());
					textUI.displayMessage("1) Buy stock\n" +
												  "2) Go back");
					buyInput = textUI.getInput("What would you like to do?");
				} while (!buyInput.trim().equals("1") && !buyInput.trim().equals("2"));

				if (buyInput.trim().equals("1"))
				{
					textUI.clearConsole();

					while (true)
					{
						textUI.displayMessage("How many stocks of " + selectedEquity.getName() + " would you like to purchase?");
						textUI.displayMessage("Press 'Q' to return");
						String amountInput = textUI.getInputOnLine("Quantity: ");

						if (amountInput.equalsIgnoreCase("Q")) break;

						try {
							int buyAmount = Integer.parseInt(amountInput);

							if (buyAmount <= 0) {
								textUI.displayMessage("The amount cannot be negative or 0! Try again.");
								continue;
							}

							transactionManager.makeTransaction(selectedEquity, buyAmount, application.getCurrentUser());
							break;
						}
						catch (NumberFormatException e) {
							textUI.displayMessage("That was not a number. Try again.");
						}
					}

					break;
				}
				else {
					exit(application);
					break;
				}

			}
			case "2":{
				textUI.clearConsole();
				exit(application);
				break;
			}
		}
	}

	public void displayPrevTransactions(Application application) {
		// evt en switch case her med en lille menu
		// evt 1: display previous transactions press q to exit menu og en default som de andre
		exit(application);
	}
}
