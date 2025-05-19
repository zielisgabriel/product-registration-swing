package repositories.InMemoryDatabase;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databases.Database;
import models.Product;
import repositories.InMemoryDatabaseRepository;
import valueobjects.Name;
import valueobjects.Price;
import valueobjects.Quantity;

public class ProductRepository implements InMemoryDatabaseRepository {
    Database database = new Database();

    private Connection connection = null;
    private String url = database.getUrl();

    @Override
    public void create(Product product) throws SQLException {
        try {
            connection = DriverManager.getConnection(this.url);

            String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public List<Product> list() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        final String sql = "SELECT * FROM products;";
        
        try {
            connection = DriverManager.getConnection(this.url);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
        
                Product product = new Product(new Name(name), new Price(price), new Quantity(quantity));
                product.setId(id);

                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return products;
    }
}
