public class SmallCapStock extends AStock{

    public SmallCapStock(Range range, float riskOfBankruptcy, String name, float price) {
        super(range, riskOfBankruptcy, name, price);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public void setPrice(float price) {

    }
}