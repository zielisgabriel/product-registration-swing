package databases;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection = null;
    private final String url = "jdbc:sqlite:./src/databases/shop.db";
    
    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Database connection established!");
            
            Statement statement = this.connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price NUMERIC NOT NULL, quantity INTEGER NOT NULL);";
            statement.execute(sql);
        } catch (Exception e) {
            throw new Exception("Failed to connect to database");
        }
    }

    public String getUrl() {
        return url;
    }
}
