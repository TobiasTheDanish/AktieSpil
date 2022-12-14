public abstract class AStock implements IEquity {

    Range range;
    float riskOfBankruptcy;
    String name;
    float price;
    boolean isBankrupt;

    public AStock(Range range, float riskOfBankruptcy, String name, float price) {
        this.range = range;
        this.riskOfBankruptcy = riskOfBankruptcy;
        this.name = name;
        this.price = price;
        this.isBankrupt = false;
    }

    @Override
    public float getRiskOfBankruptcy()
    {
        return riskOfBankruptcy;
    }

    @Override
    public Range getRange()
    {
        return range;
    }
    @Override
    public boolean isBankrupt()
    {
        return isBankrupt;
    }
    @Override
    public void setIsBankrupt(boolean isBankrupt)
    {
        if (isBankrupt)
        {
            this.price = 0;
        }

        this.isBankrupt = isBankrupt;
    }

    @Override
    public String toString() {
        return name + ", Price: " + price + '\'' +
                ", Range: [" + range.min + ", " + range.max + "]'" +
                ", Risk Of Bankruptcy: " + riskOfBankruptcy;
    }
}
