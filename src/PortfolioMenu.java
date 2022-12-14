public class PortfolioMenu implements IMenu
{
    @Override
    public void enter(Application application)
    {
        displayPortfolio(application);
    }

    private void displayPortfolio(Application application)
    {

    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
