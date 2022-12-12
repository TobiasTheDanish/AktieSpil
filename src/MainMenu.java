import java.util.Scanner;

public class MainMenu implements IMenu
{
    public void enter(Application application)
    {
        exit(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
