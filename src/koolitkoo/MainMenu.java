package koolitkoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainMenu extends Container {
    private JPanel panel1;
    private JButton tambahProdukButton;
    private JButton tambahProdukKeRutinButton;
    private JButton tampilkanProdukButton;
    private JButton tampilkanRutinButton;


    public MainMenu() {
        tambahProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TambahProdukUI newProductUI = new TambahProdukUI();
            }
        });


        tambahProdukKeRutinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { TambahProdukRutinUI newProductRoutineUI = new TambahProdukRutinUI();

            }
        });
        tampilkanProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TampilProdukUI newRoutineUI = new TampilProdukUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        tampilkanRutinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { TampilRutinUI newRoutineUI = new TampilRutinUI();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");

        frame.setContentPane(new MainMenu().panel1);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
