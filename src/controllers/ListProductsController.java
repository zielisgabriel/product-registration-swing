package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Product;
import repositories.InMemoryDatabase.ProductRepository;
import services.ListProductsService;

public class ListProductsController {
    private ProductRepository productRepository = new ProductRepository();
    private ListProductsService listProductsService = new ListProductsService(productRepository);
    
    public List<Product> execute() throws SQLException {
        List<Product> products = listProductsService.execute();
        return products;
    }
}
