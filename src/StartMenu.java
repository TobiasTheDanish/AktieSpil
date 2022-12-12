import java.util.Scanner;

public class StartMenu implements IMenu
{
    @Override
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
