package koolitkoo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static koolitkoo.Koolitkoo.*;

class DisplayProduct {
    private Koolitkoo.Products product;

    public DisplayProduct(Koolitkoo.Products product) {
        this.product = product;
    }

    public Koolitkoo.Products getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return product.getProductBrand() + " - " + product.getProductName();
    }
}


public class TampilRutinUI {
    private JComboBox hariComboBox;
    private JPanel panel;
    private JButton deleteButton;
    private JList list1;
    private JList list2;

    public void populateList() throws SQLException {
        // map skincaretype
        Map<String, Integer> skincareTypeToIndex = new HashMap<>();
        skincareTypeToIndex.put("Cleansing Balm", 1);
        skincareTypeToIndex.put("Cleansing Oil", 2);
        skincareTypeToIndex.put("Micellar", 3);
        skincareTypeToIndex.put("Cleanser", 4);
        skincareTypeToIndex.put("Exfoliator", 5);
        skincareTypeToIndex.put("Toner", 6);
        skincareTypeToIndex.put("Retinol", 7);
        skincareTypeToIndex.put("Serum", 8);
        skincareTypeToIndex.put("Moisturizer", 9);
        skincareTypeToIndex.put("Face Oil", 10);
        skincareTypeToIndex.put("Sunscreen", 11);

        List<Koolitkoo.Routine> routineList = getRoutine();

        int currDay = parseInt(((ComboItem) hariComboBox.getSelectedItem()).getValue());

        DefaultListModel listModel1 = new DefaultListModel();
        DefaultListModel listModel2 = new DefaultListModel();

        List<Koolitkoo.Products> morningProducts = new ArrayList<>();
        List<Koolitkoo.Products> nightProducts = new ArrayList<>();

        for (Koolitkoo.Routine currRoutine : routineList) {
            if (currRoutine.day == currDay) {
                morningProducts.addAll(currRoutine.getMorning());
                nightProducts.addAll(currRoutine.getNight());
            }
        }

        Comparator<Products> comparator = Comparator.comparingInt(p -> skincareTypeToIndex.get(p.getProductType().getType()));
        morningProducts.sort(comparator);
        nightProducts.sort(comparator);

        // Populate listModel1 for morning routine
        for (Koolitkoo.Products morningRoutine : morningProducts) {
            listModel1.addElement(new DisplayProduct(morningRoutine));
        }

        // Populate listModel2 for night routine
        for (Koolitkoo.Products nightRoutine : nightProducts) {
            listModel2.addElement(new DisplayProduct(nightRoutine));
        }

        list1.setModel(listModel1);
        list2.setModel(listModel2);
    }




    public TampilRutinUI() {
        hariComboBox.addItem(new ComboItem("Senin", "1"));
        hariComboBox.addItem(new ComboItem("Selasa", "2"));
        hariComboBox.addItem(new ComboItem("Rabu", "3"));
        hariComboBox.addItem(new ComboItem("Kamis", "4"));
        hariComboBox.addItem(new ComboItem("Jumat", "5"));
        hariComboBox.addItem(new ComboItem("Sabtu", "6"));
        hariComboBox.addItem(new ComboItem("Minggu", "7"));

        JFrame currFrame = new JFrame("Display Routine");

        // Use the instance variables directly, not a new instance
        currFrame.setContentPane(panel);
        currFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currFrame.pack();
        currFrame.setVisible(true);


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the selected product
                    DisplayProduct selectedProduct = (DisplayProduct) list1.getSelectedValue();
                    if (selectedProduct == null) {
                        selectedProduct = (DisplayProduct) list2.getSelectedValue();
                    }
                    if (selectedProduct != null) {
                        // Delete the product from the routine
                        deleteProductFromRoutine(parseInt(((ComboItem) hariComboBox.getSelectedItem()).getValue()), getProductID(selectedProduct.getProduct()), selectedProduct == list1.getSelectedValue() ? "morning" : "night");
                        // Refresh the lists
                        populateList();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        hariComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populateList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
