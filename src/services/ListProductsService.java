package services;

import java.sql.SQLException;
import java.util.List;

import models.Product;
import repositories.InMemoryDatabaseRepository;

public class ListProductsService {
    private InMemoryDatabaseRepository inMemoryDatabaseRepository;

    public ListProductsService(InMemoryDatabaseRepository inMemoryDatabaseRepository) {
        this.inMemoryDatabaseRepository = inMemoryDatabaseRepository;
    }

    public List<Product> execute() throws SQLException {
        List<Product> products = inMemoryDatabaseRepository.list();
        return products;
    }
}
