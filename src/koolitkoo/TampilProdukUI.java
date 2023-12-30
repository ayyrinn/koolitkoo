package koolitkoo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import static koolitkoo.Koolitkoo.*;

public class TampilProdukUI {
    private JTable table1;
    private JPanel panel1;
    private JButton editButton;
    private JButton deleteButton;

    public TampilProdukUI() throws SQLException {
        List<Koolitkoo.Products> productList = getProductListDatabase();

        Object[][] data = productList.stream()
                .map(product -> new Object[]{product.getProductType().getType(), product.getProductBrand(), product.getProductName(), product})
                .toArray(Object[][]::new);

        table1.setModel(new DefaultTableModel(data,
                new String[]{"Jenis","Brand","Nama"}
        ));
        JFrame currFrame = new JFrame("Display Product");

        // Use the instance variables directly, not a new instance
        currFrame.setContentPane(panel1);
        currFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currFrame.pack();
        currFrame.setVisible(true);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                try {
                    Products selectedProduct = (Products) data[row][3];
                    new EditProductUI(getProductID(selectedProduct), selectedProduct.getProductBrand(), selectedProduct.getProductName(), TampilProdukUI.this);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                try {
                    deleteProductID(getProductID((Products) data[row][3]));
                    // Refresh the table data
                    List<Koolitkoo.Products> updatedProductList = getProductListDatabase();
                    Object[][] updatedData = updatedProductList.stream()
                            .map(product -> new Object[]{product.getProductType().getType(), product.getProductBrand(), product.getProductName(), product})
                            .toArray(Object[][]::new);
                    table1.setModel(new DefaultTableModel(updatedData,
                            new String[]{"Jenis","Brand","Nama"}
                    ));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public void refreshTableData() throws SQLException {
        List<Koolitkoo.Products> updatedProductList = getProductListDatabase();
        Object[][] updatedData = updatedProductList.stream()
                .map(product -> new Object[]{product.getProductType().getType(), product.getProductBrand(), product.getProductName(), product})
                .toArray(Object[][]::new);
        table1.setModel(new DefaultTableModel(updatedData,
                new String[]{"Jenis","Brand","Nama"}
        ));
    }
}
