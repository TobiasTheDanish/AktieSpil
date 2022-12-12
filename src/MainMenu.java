import java.util.Scanner;

public class MainMenu implements IMenu
{
    int balance;
    int previousTransaction;

private void displayUserOptions() {

}

        public void enter (Application application)
        {
            application.menuStack.push(new TransactionMenu());
        }

        @Override
        public void exit (Application application)
        {
            application.menuStack.pop();
        }

}