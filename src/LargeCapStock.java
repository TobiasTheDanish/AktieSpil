public class LargeCapStock extends AStock{

    public LargeCapStock(Range range, float riskOfBankruptcy, String name, float price) {
        super(range, riskOfBankruptcy, name, price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }
}
