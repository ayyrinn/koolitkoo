package koolitkoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static koolitkoo.Koolitkoo.editProduct;


public class EditProductUI {
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JButton confirmButton;

    private int productId;
    private TampilProdukUI tampilProdukUI;

    private int getProductId () {
        return this.productId;
    }

    public EditProductUI(int productId, String brand, String name, TampilProdukUI tampilProdukUI) {
        JFrame currFrame = new JFrame("Edit Product");

        this.tampilProdukUI = tampilProdukUI;

        // Use the instance variables directly, not a new instance
        currFrame.setContentPane(panel1);
        currFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currFrame.pack();
        currFrame.setVisible(true);

        textField1.setText(name);
        textField2.setText(brand);
        this.productId = productId;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editProduct(getProductId(),textField2.getText(), textField1.getText());
                    // Close the EditProductUI
                    currFrame.dispose();
                    // Refresh the table data in TampilProdukUI
                    tampilProdukUI.refreshTableData();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

}
