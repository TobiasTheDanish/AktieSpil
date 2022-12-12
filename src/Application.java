import DataStructures.Stack.*;

public class Application {
    public final IStack<IMenu> menuStack = new Stack<>();
    public final IUI ui = new TextUI();
    public final IDataIO dataIO = new FileIO();
    private User currentUser = null;

    public void run() {
        menuStack.push(new MainMenu());
        menuStack.push(new StartMenu());

        while (!menuStack.isEmpty()) {
            menuStack.peek().enter(this);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}