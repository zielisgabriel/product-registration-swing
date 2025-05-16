

import controllers.CreateProductController;
import controllers.ListProductsController;
import databases.Database;
import models.Product;
import valueobjects.Name;
import valueobjects.Price;
import valueobjects.Quantity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        SwingInterface swingInterface = new SwingInterface();

        try {
            Database database = new Database();
            database.connect();
            
            Scanner scanner = new Scanner(System.in);

            ListProductsController listProductsController = new ListProductsController();
            List<Product> products = listProductsController.list();
            System.out.println(products.toArray()[1]);

            System.out.println("Digite o nome do produto:");
            String inputProductName = scanner.nextLine();
            System.out.println("Digite o preço do produto:");
            String inputProductPrice = scanner.nextLine();
            System.out.println("Digite a quantidade do produto:");
            String inputProductQuantity = scanner.nextLine();
            scanner.close();

            int priceStringToInteger = Integer.parseInt(inputProductPrice);
            BigDecimal priceIntegerToBigDecimal = new BigDecimal(priceStringToInteger);

            int quantityStringToInteger = Integer.parseInt(inputProductQuantity);
            
            Name productName = new Name(inputProductName);
            Price productPrice = new Price(priceIntegerToBigDecimal);
            Quantity productQuantity = new Quantity(quantityStringToInteger);

            productName.validate();
            productPrice.validate();
            productQuantity.validate();

            Product product = new Product(productName, productPrice, productQuantity);

            CreateProductController createProductController = new CreateProductController();
            createProductController.execute(product);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
