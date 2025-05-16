package models;


import java.math.BigDecimal;

import valueobjects.Name;
import valueobjects.Price;
import valueobjects.Quantity;

public class Product {
    private int id;
    private Name name;
    private Price price;
    private Quantity quantity;

    public Product(Name name, Price price, Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
