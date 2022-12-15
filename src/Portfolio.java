import java.util.*;

public class Portfolio {

    private ArrayList<IEquity> equities;
    Map<String, Integer> equityAmount;
    Map<String, Float> purchasePrices;
    private float balance = 0.0f;

    public Portfolio(ArrayList<IEquity> equities) {
        this.equities = equities;
        this.equityAmount = new HashMap<>();
        this.purchasePrices = new HashMap<>();
    }

    public void addToPortfolio(IEquity equity, int amount) {
        if (purchasePrices.containsKey(equity.getName())) {
            float newAveragePrice = calcNewAveragePrice(equity.getName(), equity.getPrice(), amount);
            purchasePrices.replace(equity.getName(), newAveragePrice);

            equityAmount.replace(equity.getName(), calcNewTotalAmount(equity.getName(), amount));
        }
        else {
            equities.add(equity);
            purchasePrices.put(equity.getName(), equity.getPrice());
            equityAmount.put(equity.getName(), amount);
        }
    }

    public void removeFromPortfolio(IEquity equity, int amount) throws IndexOutOfBoundsException {
        String equityName = equity.getName();

        if (amount > equityAmount.get(equityName))
        {
            throw new IndexOutOfBoundsException("The amount given to removeFromPortfolio(), was bigger than the amount in the portfolio");
        }
        else if (amount == equityAmount.get(equityName))
        {
            equities.remove(equity);
            equityAmount.remove(equityName);
            purchasePrices.remove(equityName);
        }
        else if (purchasePrices.containsKey(equityName))
        {
            int oldAmount = equityAmount.get(equityName);
            int newAmount = oldAmount-amount;
            equityAmount.replace(equityName, newAmount);
        }
    }

    public float calculateTotalValue(){
        return calculateTotalEquities() + balance;
    }

    public float calculateTotalEquities(){
        float sum = 0;
        for (IEquity equity : equities) {
            sum += equity.getPrice() * equityAmount.get(equity.getName());
        }
        return sum;
    }

    public float calculateTotalReturn() {
        float roi = 0.0f;
        for (IEquity equity : equities) {
            roi += calculateStockReturn(equity);
        }
        return roi;
    }

    public float calculateStockReturn(IEquity equity){
        return (equity.getPrice() - getAveragePrice(equity)) * getAmountOfEquity(equity);
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public ArrayList<IEquity> getEquities()
    {
        return equities;
    }

    public float getAveragePrice(IEquity equity)
    {
        return purchasePrices.get(equity.getName());
    }

    public int getAmountOfEquity(IEquity equity) {
        return equityAmount.get(equity.getName());
    }

    private float calcNewAveragePrice(String equityName, float newPrice, int newAmount)
    {
        float averagePrice = 0.0f;

        if (purchasePrices.containsKey(equityName))
        {
            float sum = 0.0f;
            float oldAverage = purchasePrices.get(equityName);
            int oldAmount = equityAmount.get(equityName);

            for (int i = 0; i < oldAmount; i++)
            {
                sum += oldAverage;
            }

            for (int i = 0; i < newAmount; i++)
            {
                sum += newPrice;
            }
            averagePrice = sum/(oldAmount+newAmount);
        }

        return averagePrice;
    }

    private int calcNewTotalAmount(String equityName, int amount) {
        int total = 0;
            if (equityAmount.containsKey(equityName))
            {
                total += equityAmount.get(equityName);
                total += amount;
            }
        return total;
    }


}
