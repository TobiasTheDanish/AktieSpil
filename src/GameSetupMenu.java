public class GameSetupMenu implements IMenu
{

    private void setup(Application application)
    {

    }

    @Override
    public void enter(Application application)
    {
        setup(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
