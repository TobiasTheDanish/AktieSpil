import java.util.ArrayList;
import java.util.Dictionary;

public class Portfolio {

    ArrayList<IEquity> equities;

    Dictionary<String, Integer> equityAmount;

    Dictionary<String, Integer> purchasePrices;
    private float balance;

    public Portfolio(ArrayList<IEquity> equities, float balance) {
        this.equities = equities;
        this.balance = balance;
    }

    public float calculateTotalValue(){
        float sum = 0;
        for (IEquity iEquity:equities) {
            sum += iEquity.getPrice();
        }
        sum += balance;
        return sum;
    }

    public float calculateTotalEquities(){
        float sum = 0;
        for (IEquity iEquity:equities) {
            sum += iEquity.getPrice();
        }
        return sum;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
