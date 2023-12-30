package koolitkoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static koolitkoo.Koolitkoo.*;

class ComboItem
{
    private String key;
    private String value;

    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}

class ComboProduct
{
    private String key;
    private Koolitkoo.Products value;

    public ComboProduct(String key, Koolitkoo.Products value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public Koolitkoo.Products getValue()
    {
        return value;
    }
}


public class TambahProdukRutinUI {
    private JComboBox timeOfDayComboBox;
    private JComboBox skinCareTypeComboBox;
    private JComboBox productComboBox;
    private JButton tambahButton;
    private JButton cancelButton;
    private JComboBox hariComboBox;

    private JPanel panel;

    public void UpdateProductsList() throws SQLException {
        productComboBox.removeAllItems();

        ComboItem skinCareType = (ComboItem) skinCareTypeComboBox.getSelectedItem();

        int currSkinCareType = parseInt(skinCareType.getValue());

        List<Koolitkoo.Products> filteredProducts = null;
        try {
            filteredProducts = getProductsBySkincareType(currSkinCareType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(Koolitkoo.Products currProduct : filteredProducts ) {
            productComboBox.addItem(new ComboProduct(currProduct.getProductBrand()+" - "+currProduct.getProductName(),currProduct));
        }

    }

    public TambahProdukRutinUI(){
        JFrame currFrame = new JFrame("Insert Product to Routine");

        // Use the instance variables directly, not a new instance
        currFrame.setContentPane(panel);
        currFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currFrame.pack();
        currFrame.setVisible(true);

        timeOfDayComboBox.addItem(new ComboItem("Pagi", "morning"));
        timeOfDayComboBox.addItem(new ComboItem("Malam", "night"));

        hariComboBox.addItem(new ComboItem("Senin", "1"));
        hariComboBox.addItem(new ComboItem("Selasa", "2"));
        hariComboBox.addItem(new ComboItem("Rabu", "3"));
        hariComboBox.addItem(new ComboItem("Kamis", "4"));
        hariComboBox.addItem(new ComboItem("Jumat", "5"));
        hariComboBox.addItem(new ComboItem("Sabtu", "6"));
        hariComboBox.addItem(new ComboItem("Minggu", "7"));


        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem currDayItem = (ComboItem) hariComboBox.getSelectedItem();
                int currDay = parseInt(currDayItem.getValue());
                ComboItem selectedTimeOfDay = (ComboItem) timeOfDayComboBox.getSelectedItem();

                ComboProduct currProductItem = ((ComboProduct) productComboBox.getSelectedItem());
                int currProductId = 0;
                try {
                    currProductId = getProductID(currProductItem.getValue());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String currTimeOfDay = selectedTimeOfDay.getValue();

                System.out.println(currProductId);

                try {
                    addProductToRoutineInDatabase(currDay, currProductId, currTimeOfDay);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        timeOfDayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skinCareTypeComboBox.removeAllItems();
                ComboItem selectedTimeOfDay = (ComboItem) timeOfDayComboBox.getSelectedItem();

                if (selectedTimeOfDay.getValue().equals("morning")) {
                    skinCareTypeComboBox.addItem(new ComboItem("Cleanser", "4"));
                    skinCareTypeComboBox.addItem(new ComboItem("Toner", "7"));
                    skinCareTypeComboBox.addItem(new ComboItem("Serum", "8"));
                    skinCareTypeComboBox.addItem(new ComboItem("Moisturizer", "9"));
                    skinCareTypeComboBox.addItem(new ComboItem("Face Oil", "10"));
                    skinCareTypeComboBox.addItem(new ComboItem("Sunscreen", "11"));
                } else {
                    skinCareTypeComboBox.addItem(new ComboItem("Cleansing Balm", "1"));
                    skinCareTypeComboBox.addItem(new ComboItem("Cleansing Oil", "2"));
                    skinCareTypeComboBox.addItem(new ComboItem("Micellar", "3"));
                    skinCareTypeComboBox.addItem(new ComboItem("Cleanser", "4"));
                    skinCareTypeComboBox.addItem(new ComboItem("Exfoliator", "5"));
                    skinCareTypeComboBox.addItem(new ComboItem("Toner", "6"));
                    skinCareTypeComboBox.addItem(new ComboItem("Retinol", "7"));
                    skinCareTypeComboBox.addItem(new ComboItem("Serum", "8"));
                    skinCareTypeComboBox.addItem(new ComboItem("Moisturizer", "9"));
                    skinCareTypeComboBox.addItem(new ComboItem("Face Oil", "10"));
                }

                // Notify ComboBoxModel about the changes
                skinCareTypeComboBox.revalidate();
                skinCareTypeComboBox.repaint();
            }
        });


        skinCareTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UpdateProductsList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
