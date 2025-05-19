package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import controllers.CreateProductController;
import controllers.ListProductsController;
import models.Product;
import valueobjects.Name;
import valueobjects.Price;
import valueobjects.Quantity;

public class SwingScreen extends JFrame {
    private final JTextField tfName = new JTextField(20);
    private final JTextField tfPrice = new JTextField(20);
    private final JTextField tfQuantity = new JTextField(20);

    private final JButton registerButton = new JButton("Cadastrar");
    private final JButton listButton = new JButton("Listar");
    private final JButton clearButton = new JButton("Limpar");

    private final JTextArea taProducts = new JTextArea(8, 20);

    private final CreateProductController createProductController = new CreateProductController();
    private final ListProductsController listProductsController = new ListProductsController();

    public SwingScreen() {
        setTitle("Sistema de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.setBorder(new TitledBorder("Cadastro de Produtos"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4,4,4,4);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;

        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        formPanel.add(new JLabel("Nome:"), gridBagConstraints);
        gridBagConstraints.gridx = 1; gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(tfName, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        formPanel.add(new JLabel("Preço:"), gridBagConstraints);
        gridBagConstraints.gridx = 1; gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(tfPrice, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        formPanel.add(new JLabel("Quantidade:"), gridBagConstraints);
        gridBagConstraints.gridx = 1; gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        formPanel.add(tfQuantity, gridBagConstraints);

        registerPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.add(registerButton);
        buttonsPanel.add(listButton);
        buttonsPanel.add(clearButton);
        registerPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(registerPanel, BorderLayout.NORTH);

        taProducts.setEditable(false);
        JScrollPane scroll = new JScrollPane(taProducts);
        scroll.setBorder(new TitledBorder("Produtos Cadastrados:"));
        add(scroll, BorderLayout.CENTER);

        clearButton.addActionListener(this::onClear);
        registerButton.addActionListener(this::onRegister);
        listButton.addActionListener(this::onList);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onClear(ActionEvent ev) {
        tfName.setText("");
        tfPrice.setText("");
        tfQuantity.setText("");
    }

    private void onRegister(ActionEvent ev) {
        try {
            String nameValue = tfName.getText().trim();
            BigDecimal priceValue = new BigDecimal(tfPrice.getText().trim());
            int quantityValue = Integer.parseInt(tfQuantity.getText().trim());

            Name name = new Name(nameValue);
            Price price = new Price(priceValue);
            Quantity quantity = new Quantity(quantityValue);

            Product product = new Product(name, price, quantity);
            createProductController.execute(product);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            onClear(null);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Error: Preço ou Quantidade inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "SQL Error: Erro ao salvar no banco:\n" + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onList(ActionEvent ev) {
        try {
            List<Product> produtos = listProductsController.execute();
            taProducts.setText("");
            for (Product p : produtos) {
                taProducts.append(p.getId() + " - " + p.getName()
                        + " | R$ " + p.getPrice()
                        + " | Qtd: " + p.getQuantity()
                        + "\n");
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "SQL Error: Erro ao listar produtos:\n" + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
