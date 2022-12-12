import java.util.Scanner;

public class MainMenu implements IMenu
{

    
    public void enter(Application application)
    {
        application.menuStack.push(new TransactionMenu());
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
