import java.util.Scanner;

public class MainMenu implements IMenu
{
    public void enter(Application application)
    {
        String input = application.ui.asTextUI().getInput("Entered main menu. Press enter to exit.");
        exit(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
