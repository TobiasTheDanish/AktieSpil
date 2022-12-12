public class TransactionMenu implements IMenu {
    public void enter(Application application){

    }
    public void exit(Application application) {
        application.menuStack.pop();
    }
}
