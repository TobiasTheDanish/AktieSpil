public interface IEquity {

    String getName();

    float getPrice();

    void setPrice(float price);

    Range getRange();

    float getRiskOfBankruptcy();

    boolean isBankrupt();

    void setIsBankrupt(boolean isBankrupt);
}
