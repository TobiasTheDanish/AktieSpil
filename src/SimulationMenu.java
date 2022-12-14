public class SimulationMenu implements IMenu
{
    private SimulationManager simulationManager;
    private static int simulationCount = 0;

    public SimulationMenu()
    {
        simulationCount++;
        simulationManager = new SimulationManager(simulationCount);
    }

    @Override
    public void enter(Application application)
    {
        simulationManager.simulateDay(application);
        exit(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }
}
