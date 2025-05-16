package controllers;

import java.sql.SQLException;

import models.Product;
import repositories.InMemoryDatabase.ProductRepository;
import services.CreateProductService;

public class CreateProductController {
    ProductRepository productRepository = new ProductRepository();

    public void execute(Product product) throws SQLException {
        CreateProductService createProductService = new CreateProductService(productRepository);
        createProductService.execute(product);
        System.out.println("Saved successfully!");
    }
}
