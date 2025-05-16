package repositories;

import java.sql.SQLException;
import java.util.List;

import models.Product;

public interface InMemoryDatabaseRepository {
    public void create(Product product) throws SQLException;
    public List<Product> list() throws SQLException;
}
