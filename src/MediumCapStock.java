public class MediumCapStock extends AStock{

    public MediumCapStock(Range range, float riskOfBankruptcy, String name, float price) {
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
