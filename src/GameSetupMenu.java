public class GameSetupMenu implements IMenu
{
    private void setup(Application application)
    {
        float startBalance = 100000;
        float winCondition = 1000000;
        float lossCondition = 10000;
        TextUI textUI = application.ui.asTextUI();

        textUI.clearConsole();

        textUI.displayMessage("---------------------------------------");
        textUI.displayMessage("\tWelcome " + application.getCurrentUser().getUsername() + ", to the Game Of Stocks!");
        textUI.displayMessage("---------------------------------------\n");

        textUI.displayMessage("Now you have to select your start capital, and your goals for this game.");
        textUI.displayMessage("You will start the game with "+ startBalance +"$ in capital, by default.");
        String balanceInput = "";
        while (true) {
            balanceInput = textUI.getInput(
            "Press " + Colors.ANSI_CYAN + "'D'" + Colors.ANSI_RESET + " if you want to play with the default,\n" +
                 "or press " + Colors.ANSI_CYAN + "'C'" + Colors.ANSI_RESET + " if you want to customize.");
            if (balanceInput.equalsIgnoreCase("D") || balanceInput.equalsIgnoreCase("C"))
            {
                break;
            }
            textUI.displayMessage("That was not valid input, please try again:\n");
        }

        if (balanceInput.equalsIgnoreCase("C")) {
            while (true)
            {
                String newStartBalance = textUI.getInputOnLine("You chose to customize.\n" +
                                                              "Please enter the capital you wish to start with: ");

                try {
                    startBalance = Float.parseFloat(newStartBalance);

                    if (startBalance < 15000 || startBalance > 500000)
                    {
                        throw new IndexOutOfBoundsException();
                    }

                    textUI.getInput("Your start capital has been " + Colors.ANSI_GREEN + "updated!" + Colors.ANSI_RESET +"\n" +
                                         "Press " + Colors.ANSI_CYAN + "'ENTER'" + Colors.ANSI_RESET + " to continue.");
                    break;
                }
                catch (NumberFormatException e) {
                    textUI.displayMessage("That was not a number! Please try again.");
                }
                catch (IndexOutOfBoundsException e) {
                    textUI.displayMessage("Start capital is not allowed to be less than 15000, or greater than 500000. Try again.");
                }

            }
        }

        textUI.clearConsole();

        textUI.displayMessage("Now with the start capital locked in, we move on to your goals for the game.");
        textUI.displayMessage("You now have to select the capital, you aim to achieve. If you achieve this, you win the game!");
        textUI.displayMessage("By default your goal is to reach a capital of "+ winCondition +"$.");

        String winConInput = "";
        while (true)
        {
            winConInput = textUI.getInput(
                "Press " + Colors.ANSI_CYAN + "'D'" + Colors.ANSI_RESET + " if you want to play with the default,\n" +
                        "or press " + Colors.ANSI_CYAN + "'C'" + Colors.ANSI_RESET + " if you want to customize.");

            if (balanceInput.equalsIgnoreCase("D") || balanceInput.equalsIgnoreCase("C"))
            {
                break;
            }
            textUI.displayMessage("That was not valid input, please try again:\n");
        }

        if (winConInput.equalsIgnoreCase("C")) {
            while (true)
            {
                String newWinCon = textUI.getInputOnLine("You chose to customize.\n" +
                                                              "Please enter the capital you want as you goal: ");

                try {
                    winCondition = Float.parseFloat(newWinCon);

                    if (winCondition <= startBalance + 5000)
                    {
                        throw new IndexOutOfBoundsException();
                    }

                    textUI.getInput("Your goal has been " + Colors.ANSI_GREEN + "updated!" + Colors.ANSI_RESET +"\n" +
                                            "Press " + Colors.ANSI_CYAN + "'ENTER'" + Colors.ANSI_RESET + " to continue.");
                    break;
                }
                catch (NumberFormatException e) {
                    textUI.displayMessage("That was not a number! Please try again.");
                } catch (IndexOutOfBoundsException e) {
                    textUI.displayMessage("Win condition is not allowed to be less than start capital + 5000$. Try again.");
                }
            }
        }

        textUI.clearConsole();

        textUI.displayMessage("You now have to select the capital, which you intend to stay above. If you go below this you lose.");
        textUI.displayMessage("By default your goal is to stay above a capital of "+ lossCondition +"$.");

        String lossConInput = "";
        while (true)
        {
            lossConInput = textUI.getInput(
                    "Press " + Colors.ANSI_CYAN + "'D'" + Colors.ANSI_RESET + " if you want to play with the default,\n" +
                            "or press " + Colors.ANSI_CYAN + "'C'" + Colors.ANSI_RESET + " if you want to customize.");

            if (balanceInput.equalsIgnoreCase("D") || balanceInput.equalsIgnoreCase("C"))
            {
                break;
            }
            textUI.displayMessage("That was not valid input, please try again:\n");
        }

        if (lossConInput.equalsIgnoreCase("C")) {
            while (true)
            {
                String newLossCon = textUI.getInputOnLine("You chose to customize.\n" +
                                                                 "Please enter the capital you want as you goal: ");

                try {
                    lossCondition = Float.parseFloat(newLossCon);

                    if (winCondition >= startBalance - 5000)
                    {
                        throw new IndexOutOfBoundsException();
                    }

                    textUI.getInput("Your goal has been " + Colors.ANSI_GREEN + "updated!" + Colors.ANSI_RESET);
                    break;
                }
                catch (NumberFormatException e) {
                    textUI.displayMessage("That was not a number! Please try again.");
                } catch (IndexOutOfBoundsException e) {
                    textUI.displayMessage("Loss condition is not allowed to be greater than start capital - 5000$. Try again.");
                }
            }
        }


        application.setGameManager(new GameManager(startBalance, winCondition, lossCondition));
    }

    @Override
    public void enter(Application application)
    {
        setup(application);
        exit(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
