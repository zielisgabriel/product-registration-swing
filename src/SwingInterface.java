import javax.swing.*;

public class SwingInterface {
    private JTextArea textArea;

    public SwingInterface() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setSize(600, 400);

        this.textArea = new JTextArea("Cadastro de produtos");
        jFrame.add(this.textArea);
    }
}
