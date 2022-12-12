import java.util.ArrayList;
import java.util.Dictionary;

public class Portfolio {

    ArrayList<IEquity> equities;

    Dictionary<String, Integer> equityAmount;

    Dictionary<String, Integer> purchasePrices;

    public Portfolio(ArrayList<IEquity> equities) {
        this.equities = equities;
    }

    public float calculateTotalValue(){
        float sum = 0;
        for (IEquity iEquity:equities) {
            sum += iEquity.getPrice();
        }
        return sum;
    }
}
