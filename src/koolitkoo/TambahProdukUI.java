package koolitkoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;
import static koolitkoo.Koolitkoo.addProductToDatabase;

public class TambahProdukUI {
    private JTextField textField1;
    private JComboBox skinCareTypeComboBox;
    private JButton tambahButton;
    private JButton cancelButton;
    private JTextField textField2;
    private JPanel panel;

    public TambahProdukUI() {
        JFrame currFrame = new JFrame("Insert New Product");

        // Use the instance variables directly, not a new instance
        currFrame.setContentPane(panel);
        currFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currFrame.pack();
        currFrame.setVisible(true);

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
        skinCareTypeComboBox.addItem(new ComboItem("Sunscreen", "11"));

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComboItem skinCareType = (ComboItem) skinCareTypeComboBox.getSelectedItem();

                int currSkinCareType = parseInt(skinCareType.getValue());

                try {
                    addProductToDatabase(textField1.getText(), textField2.getText(), currSkinCareType);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }
}
