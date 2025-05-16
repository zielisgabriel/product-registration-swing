package services;

import java.sql.SQLException;

import models.Product;
import repositories.InMemoryDatabaseRepository;

public class CreateProductService {
    private InMemoryDatabaseRepository inMemoryDatabaseRepository;

    public CreateProductService(InMemoryDatabaseRepository inMemoryDatabaseRepository) {
        this.inMemoryDatabaseRepository = inMemoryDatabaseRepository;
    }

    public void execute(Product product) throws SQLException {
        inMemoryDatabaseRepository.create(product);
    }
}
