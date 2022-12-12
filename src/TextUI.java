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
        return scanner.next();
    }
    public String getInputOnLine(String msg){
        displayMessageOnLine(msg);
        return scanner.next();
    }

    public void printPortfolio(ArrayList<IEquity> equities){
        //print portfolio
    }

    public void clearConsole(){
        for (int i = 0; i < 250; i++){
            System.out.println();
        }
    }
}
