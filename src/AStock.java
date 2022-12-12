public abstract class AStock implements IEquity {

    Range range;
    float riskOfBankruptcy;
    String name;
    float price;

    public AStock(Range range, float riskOfBankruptcy, String name, float price) {
        this.range = range;
        this.riskOfBankruptcy = riskOfBankruptcy;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", Price: " + price + '\'' +
                "Range: " + range + '\'' +
                "Risk Of Bankruptcy: " + riskOfBankruptcy;
    }
}
