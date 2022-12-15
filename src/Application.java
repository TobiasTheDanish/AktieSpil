import DataStructures.Stack.*;

import java.util.ArrayList;

public class Application {
    public final IStack<IMenu> menuStack = new Stack<>();
    public final IUI ui = new TextUI();
    public final IDataIO dataIO = new FileIO();
    private ArrayList<IEquity> equities = new ArrayList<>();
    private User currentUser = null;
    private GameManager gameManager = null;

    public void run() {
        equities = dataIO.readEquityData("src/Data/Stocks.csv");

        menuStack.push(new MainMenu());
        menuStack.push(new StartMenu());

        while (!menuStack.isEmpty()) {
            menuStack.peek().enter(this);
        }

        ui.displayMessage("Logging out...");
    }

    public ArrayList<IEquity> getEquities()
    {
        return equities;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager)
    {
        if (this.gameManager != null)
        {
            throw new RuntimeException("The GameManager instance of Application, cannot be set more than once!\nThis would overwrite the win and loss conditions.");
        }
        this.gameManager = gameManager;
    }
}