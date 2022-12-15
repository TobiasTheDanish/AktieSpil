import java.util.ArrayList;
import java.util.Scanner;

public class TextUI implements IUI {
    private Scanner scanner = new Scanner(System.in);

    public void displayMessage(String msg){
        System.out.println(msg);
    }
    public void displayMessageOnLine(String msg){
        System.out.print(msg);
    }

    public String getInput(String msg){
        displayMessage(msg);
        return scanner.nextLine();
    }
    public String getInputOnLine(String msg){
        displayMessageOnLine(msg);
        return scanner.nextLine();
    }

    public void printListOfEquities(ArrayList<IEquity> equities){
        for (int i = 0; i < equities.size(); i++)
        {
            displayMessage((i+1)+")    " + equities.get(i).getName());
        }
    }

    public void printListOfEquitiesPlusAveragePrice(ArrayList<IEquity> equities, User user){
        for (int i = 0; i < equities.size(); i++)
        {
            String name = equities.get(i).getName();
            int amount = user.getPortfolio().getAmountOfEquity(equities.get(i));
            float averagePrice = user.getPortfolio().getAveragePrice(equities.get(i));
            float investment = averagePrice * amount;
            float stockReturn = user.getPortfolio().calculateStockReturn(equities.get(i));
            float stockReturnPercentage = (stockReturn / investment) * 100;
            displayMessage((i+1)+") " + name + " | Average purchase price: " + averagePrice + "$" + " | Amount: " + amount + " | Stock return: " + stockReturnPercentage + "% / " + stockReturn + "$");
        }
    }

    public void clearConsole(){
        for (int i = 0; i < 250; i++){
            System.out.println();
        }
    }
}
