public class TransactionMenu implements IMenu {


    int previousTransaction;

    public void enter(Application application){

    }
    public void exit(Application application) {
        application.menuStack.pop();
    }
}
