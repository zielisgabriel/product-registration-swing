import databases.Database;
import ui.SwingScreen;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Database database = new Database();
            database.connect();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        SwingScreen swingInterface = new SwingScreen();
        swingInterface.setVisible(true);
    }
}
