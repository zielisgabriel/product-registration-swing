package valueobjects;

import java.math.BigDecimal;

public class Price {
    private BigDecimal price;

    public Price(BigDecimal price) {
        this.price = price;
    }
    
    public void validate() throws Exception {
        if (this.price.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("O preço deve ser maior que 0.");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }
}
