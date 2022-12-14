import java.util.*;

public class Portfolio {

    ArrayList<IEquity> equities;
    Map<String, Integer> equityAmount;
    Map<String, Float> purchasePrices;
    private float balance;

    public Portfolio(ArrayList<IEquity> equities, float balance) {
        this.equities = equities;
        this.balance = balance;
        this.equityAmount = new HashMap<>();
        this.purchasePrices = new HashMap<>();
    }

    public void addToPortfolio(IEquity equity, int amount) {
        equities.add(equity);

        if (purchasePrices.containsKey(equity.getName())) {
            float newAveragePrice = calcNewAveragePrice(equity.getName(), equity.getPrice(), amount);
            purchasePrices.replace(equity.getName(), newAveragePrice);

            equityAmount.replace(equity.getName(), calcNewTotalAmount(equity.getName(), amount));
        }
        else {
            purchasePrices.put(equity.getName(), equity.getPrice());
            equityAmount.put(equity.getName(), amount);
        }
    }

    public void removeFromPortfolio(IEquity equity, int amount) throws IndexOutOfBoundsException {
        String equityName = equity.getName();

        if (amount > equityAmount.get(equityName))
        {
            throw new IndexOutOfBoundsException("The amount given to removeFromPortfolio(), was bigger than the amount int the portfolio");
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
            sum += (purchasePrices.get(equity.getName()) * equityAmount.get(equity.getName()));
        }
        return sum;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
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
