package valueobjects;

public class Quantity {
    private int quantity;
    
    public Quantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void validate() throws Exception {
        if (quantity < 0) {
            throw new Exception("Please enter a valid quantity.");
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
