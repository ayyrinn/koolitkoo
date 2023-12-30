/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koolitkoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author April
 */
public class Koolitkoo {
    
    static final String JDBC_URL = "jdbc:mysql://localhost/koolitkoo";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    public static class Skincare {

        int id;
        String type;

        public Skincare(int id, String type) {
            this.id = id;
            this.type = type;
        }

        public int getId() {
            return this.id;
        }

        public String getType() {
            return this.type;
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            return true;
        }

    }

    public static class CleansingBalm extends Skincare {

        public CleansingBalm() {
            super(1, "Cleansing Balm");
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {

            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Cleansing Oil")) {
                    return false;
                }
            }

            return true;
        }
    }

    public static class CleansingOil extends Skincare {

        public CleansingOil() {
            super(2, "Cleansing Oil");
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {

            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Cleansing Balm")) {
                    return false;
                }
            }

            return true;
        }
    }

    public static class Micellar extends Skincare {

        public Micellar() {
            super(3, "Micellar Water");
        }
    }

    public static class Cleanser extends Skincare {

        public Cleanser() {
            super(4, "Cleanser");
        }
    }

    public static class Exfoliator extends Skincare {

        public Exfoliator() {
            super(5, "Exfoliator");
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {

            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Retinol")) {
                    return false;
                }
            }

            return true;
        }
    }

    public static class Toner extends Skincare {

        public Toner() {
            super(6, "Toner");
        }
    }

    public static class Retinol extends Skincare {

        public Retinol() {
            super(7, "Retinol");
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {

            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Exfoliator")) {
                    return false;
                }
            }

            return true;
        }
    }

    public static class Serum extends Skincare {

        public Serum() {
            super(8, "Serum");
        }

        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0, flag = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Serum")) {
                    count++;
                }
                if (currProduct.getProductType().getType().equals("Retinol")) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                if (count == 3) {
                    return false;
                }
            } else {
                if (count == 2) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Moisturizer extends Skincare {

        public Moisturizer() {
            super(9, "Moisturizer");
        }
    }

    public static class FaceOil extends Skincare {

        public FaceOil() {
            super(10, "Face Oil");
        }
    }

    public static class Sunscreen extends Skincare {

        public Sunscreen() {
            super(11, "Sunscreen");
        }
    }

    public static class Products {

        Skincare productType;
        String productName;
        String productBrand;

        public Products(String brand, String name, Skincare type) {
            this.productType = type;
            this.productBrand = brand;
            this.productName = name;
        }

        public void setProductType(Skincare type) {
            productType = type;
        }

        public void setProductBrand(String brand) {
            productBrand = brand;
        }

        public void setProductName(String name) {
            productName = name;
        }

        public void getProducts(List<Products> products) {
            for (Products product : products) {
                System.out.println("  - Jenis: " + product.getProductType());
                System.out.println("    Brand: " + product.getProductBrand());
                System.out.println("    Nama : " + product.getProductName());
            }
        }

        public Skincare getProductType() {
            return productType;
        }

        public String getProductBrand() {
            return productBrand;
        }

        public String getProductName() {
            return productName;
        }
    }

    public static class Routine {

        int day;
        List<Products> morning;
        List<Products> night;

        public Routine(int day) {
            this.day = day;
            this.morning = new ArrayList<>();
            this.night = new ArrayList<>();
        }

        public String getDays() {
            String[] days = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
            return days[day - 1];
        }

        public List<Products> getMorning() {
            return morning;
        }

        public List<Products> getNight() {
            return night;
        }

        public void addMorning(Products product) {
            //int index = findIndex(product, morning);
            morning.add(product);
        }

        public void addNight(Products product) {
            //int index = findIndex(product, night);
            night.add(product);
        }

        private void displayDetails(List<Products> products) {
            products.sort(Comparator.comparingInt(p -> p.getProductType().getId()));
            for (Products product : products) {
                System.out.println("  - Jenis: " + product.getProductType().getType());
                System.out.println("    Brand: " + product.getProductBrand());
                System.out.println("    Nama: " + product.getProductName());
            }
        }

        public void display() {
            System.out.println("Rutin pada hari " + getDays() + ":");
            System.out.println("Pagi:");
            System.out.println(morning.size());
            displayDetails(morning);
            System.out.println("Malam:");
            displayDetails(night);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            initializeDatabase(connection);     
            Scanner scanner = new Scanner(System.in);

            while (true) {
                List<Products> productsList = getProductListDatabase(connection);
                System.out.println("\nMenu:");
                System.out.println("1. Tambah Produk");
                System.out.println("2. Tambah Produk pada Rutin");
                System.out.println("3. Tampilkan Rutin");
                System.out.println("4. Edit Product");
                System.out.println("5. Delete Product");
                System.out.println("6. Keluar");
                System.out.print("Pilih menu (1-3): ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        productsList.add(addProduct(connection));
                        break;
                    case 2:
                        addRoutine(connection, productsList);
                        break;
                    case 3:
                        displayRoutine(connection);
                        break;
                    case 4:
                        //editProduct();
                        break;
                    case 5:
                        //deleteProduct();
                        break;
                    case 6:
                        System.out.println("Terima kasih!");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    private static void initializeDatabase(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Create tables if not exists
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Skincare (" +
                    "id INT PRIMARY KEY," +
                    "type VARCHAR(255) NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Products (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "brand VARCHAR(255) NOT NULL," +
                    "name VARCHAR(255) NOT NULL," +
                    "type_id INT," +  // Fix: Change "productType" to "type_id"
                    "FOREIGN KEY (type_id) REFERENCES Skincare(id))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Routine (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "day INT NOT NULL," +
                    "PRIMARY KEY (id)," +
                    "UNIQUE KEY unique_day (day))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RoutineDetails (" +
                    "routine_day INT," +
                    "time_of_day VARCHAR(255)," +
                    "product_id INT," +
                    "FOREIGN KEY (routine_day) REFERENCES Routine(id) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (product_id) REFERENCES Products(id) ON UPDATE CASCADE ON DELETE CASCADE)");

            try (ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM Skincare")) {
                resultSet.next();
                int rowCount = resultSet.getInt(1);

                if (rowCount == 0) {
                    // Insert statements for each skincare type
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (1, 'Cleansing Balm')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (2, 'Cleansing Oil')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (3, 'Micellar Water')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (4, 'Cleanser')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (5, 'Exfoliator')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (6, 'Toner')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (7, 'Retinol')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (8, 'Serum')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (9, 'Moisturizer')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (10, 'Face Oil')");
                    stmt.executeUpdate("INSERT INTO Skincare VALUES (11, 'Sunscreen')");
                }
            }
            try (ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM Routine")) {
                resultSet.next();
                int routineRowCount = resultSet.getInt(1);

                if (routineRowCount == 0) {
                    for (int day = 1; day <= 7; day++) {
                        stmt.executeUpdate("INSERT INTO Routine VALUES (NULL, " + day + ")");
                    }
                }
            }
        }
    }


    private static final List<Routine> routineList = new ArrayList<>();

    public static void insertRoutine(Routine routine) {
        routineList.add(routine);
    }

    public static void displayRoutine(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM RoutineDetails")) {
                while (resultSet.next()) {
                    int day = resultSet.getInt("routine_day");
                    String timeOfDay = resultSet.getString("time_of_day");
                    Routine routine = getRoutineFromList(routineList, day);

                    int productID = resultSet.getInt("product_id");
                    Products product = getProductFromDatabase(connection, productID);

                    if (product != null) {
                        if (timeOfDay.equals("morning")) {
                            routine.addMorning(product);
                        } else if (timeOfDay.equals("night")) {
                            routine.addNight(product);
                        }
                    }
                }
            }
        }

        // Display the routines for each day
        for (Routine routine : routineList) {
            routine.display();
            System.out.println();
        }
    }
    
    private static List<Products> getProductsBySkincareType(Connection connection, int skincareTypeId) throws SQLException {
        List<Products> filteredProducts = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM Products WHERE productType = ?")) {
            pstmt.setInt(1, skincareTypeId);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("id");
                    String brand = resultSet.getString("brand");
                    String name = resultSet.getString("name");

                    Skincare type = getSkincareType(skincareTypeId);
                    Products product = new Products(brand, name, type);
                    product.setProductType(type);
                    product.setProductBrand(brand);
                    product.setProductName(name);

                    filteredProducts.add(product);
                }
            }
        }

        return filteredProducts;
    }

    
    private static Products getProductFromDatabase(Connection connection, int productID) throws SQLException {
        // Load product details from the Products table
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM Products WHERE id = ?")) {
            pstmt.setInt(1, productID);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int typeId = resultSet.getInt("productType");
                    Skincare type = getSkincareType(typeId);
                    String brand = resultSet.getString("brand");
                    String name = resultSet.getString("name");

                    return new Products(brand, name, type);
                }
            }
        }

        return null;
    }
    
    private static List<Products> getProductListDatabase(Connection connection) throws SQLException {
        // Load product details from the Products table
        List<Products> productList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Products");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int typeId = resultSet.getInt("productType");
                Skincare type = getSkincareType(typeId);
                String brand = resultSet.getString("brand");
                String name = resultSet.getString("name");

                productList.add(new Products(brand, name, type));
            }
        }

        return productList;
    }


    
    private static Skincare getSkincareType(int typeId) {
        // Map type ID to Skincare type
        switch (typeId) {
            case 1:
                return new CleansingBalm();
            case 2:
                return new CleansingOil();
            case 3:
                return new Micellar();
            case 4:
                return new Cleanser();
            case 5:
                return new Exfoliator();
            case 6:
                return new Toner();
            case 7:
                return new Retinol();
            case 8:
                return new Serum();
            case 9:
                return new Moisturizer();
            case 10:
                return new FaceOil();
            case 11:
                return new Sunscreen();
            default:
                return null;
        }
    }
    
    private static Routine getRoutineFromList(List<Routine> routineList, int day) {
        for (Routine routine : routineList) {
            if (routine.day == day) {
                return routine;
            }
        }

        Routine newRoutine = new Routine(day);
        routineList.add(newRoutine);
        return newRoutine;
    }

    private static Routine getRoutine(int day) {
        for (Routine routine : routineList) {
            if (routine.day == day) {
                return routine;
            }
        }
        Routine newRoutine = new Routine(day);
        routineList.add(newRoutine);
        return newRoutine;
    }

    public static Products addProduct(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jenis skincare:");
        System.out.println("1. Cleansing Balm");
        System.out.println("2. Cleansing Oil");
        System.out.println("3. Micellar");
        System.out.println("4. Cleanser");
        System.out.println("5. Exfoliator");
        System.out.println("6. Toner");
        System.out.println("7. Retinol");
        System.out.println("8. Serum");
        System.out.println("9. Moisturizer");
        System.out.println("10. Face Oil");
        System.out.println("11. Sunscreen");
        System.out.print("Pilih jenis skincare (1-11): ");

        int skincareType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan brand skincare: ");
        String brand = scanner.nextLine();

        System.out.print("Masukkan nama skincare:   ");
        String name = scanner.nextLine();

        Skincare inputProduct = getSkincareType(skincareType);
        
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO Products (brand, name, productType) VALUES (?, ?, ?)")) {
            pstmt.setString(1, brand);
            pstmt.setString(2, name);
            pstmt.setInt(3, inputProduct.getId());
            pstmt.executeUpdate();
        }
        
        System.out.println("Skincare product berhasil ditambahkan!");
        System.out.print("");
        return new Products(brand, name, inputProduct);
    }

    public static void addRoutine(Connection connection, List<Products> availableProducts) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan hari (1-7): ");
        int day = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Pilih pagi atau malam: ");
        String timeOfDay = scanner.nextLine().toLowerCase();

        if (!timeOfDay.equals("pagi") && !timeOfDay.equals("malam")) {
            System.out.println("Pilihan waktu tidak valid.");
            return;
        }

        System.out.println("Jenis skincare:");

        if (timeOfDay.equals("pagi")) {
            System.out.println("1. Cleanser");
            System.out.println("2. Toner");
            System.out.println("3. Serum");
            System.out.println("4. Moisturizer");
            System.out.println("5. Face Oil");
            System.out.println("6. Sunscreen");
            System.out.print("Pilih jenis skincare (1-6): ");
        } else if (timeOfDay.equals("malam")) {
            System.out.println("1. Cleansing Balm");
            System.out.println("2. Cleansing Oil");
            System.out.println("3. Micellar");
            System.out.println("4. Cleanser");
            System.out.println("5. Exfoliator");
            System.out.println("6. Toner");
            System.out.println("7. Retinol");
            System.out.println("8. Serum");
            System.out.println("9. Moisturizer");
            System.out.println("10. Face Oil");
            System.out.print("Pilih jenis skincare (1-10): ");
        }

        int[] morningIds = {4, 6, 8, 9, 10, 11};
        int[] nightIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int skincareType = scanner.nextInt();
        scanner.nextLine();

        int[] productIds = timeOfDay.equals("pagi") ? morningIds : nightIds;
        int[] adjustedProductIds = timeOfDay.equals("pagi") ? morningIds : nightIds;
        
        List<Products> filteredProducts = getProductsBySkincareType(connection, adjustedProductIds[skincareType - 1]);
        
        System.out.println(skincareType);

        for (int i = 0; i < filteredProducts.size(); i++) {
            System.out.println((i + 1) + ". Brand: " + filteredProducts.get(i).productBrand);
            System.out.println("   Name: " + filteredProducts.get(i).productName);
        }

        System.out.print("Pilih produk (1-" + filteredProducts.size() + "): ");
        int productSelected = scanner.nextInt();
        scanner.nextLine();

        Products selected = filteredProducts.get(productSelected - 1);

        Routine routine = getRoutine(day);

        boolean nightOrDay = timeOfDay.equals("pagi");

        boolean isCompatible = selected.getProductType().checkIfCompatible(routine, nightOrDay);

        if (timeOfDay.equals("pagi") && isCompatible) {
            addProductToRoutineInDatabase(connection, routine, selected, "morning");
        } else if (timeOfDay.equals("malam") && isCompatible) {
            addProductToRoutineInDatabase(connection, routine, selected, "night");
        }

        System.out.println("Skincare berhasil ditambahkan!");
    }
    
    private static void addProductToRoutineInDatabase(Connection connection, Routine routine, Products product, String timeOfDay) throws SQLException {
        // Insert routine details into the RoutineDetails table
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO RoutineDetails (routine_day, time_of_day, product_id) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, routine.day);
            pstmt.setString(2, timeOfDay);
            pstmt.setInt(3, getProductID(connection, product));
            pstmt.executeUpdate();
        }
    }
    
    private static int getProductID(Connection connection, Products product) throws SQLException {
        // Retrieve the ID of the product from the Products table
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT id FROM Products WHERE brand = ? AND name = ? AND productType = ?")) {
            pstmt.setString(1, product.getProductBrand());
            pstmt.setString(2, product.getProductName());
            pstmt.setInt(3, product.getProductType().getId());

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }

        return -1;
    }


}
