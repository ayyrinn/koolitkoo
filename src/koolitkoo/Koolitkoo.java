
package koolitkoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author April
 */
public class Koolitkoo {

    static final String JDBC_URL = "jdbc:mysql://0.tcp.ap.ngrok.io:12114/koolitkoo";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "KontolKuda";

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
        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Micellar Water")) {
                    count++;
                }
            }
            if (count == 1) {
                return false;
            }
            return true;
        }
    }

    public static class Cleanser extends Skincare {

        public Cleanser() { super(4, "Cleanser"); }
        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Cleanser")) {
                    count++;
                }
            }
            if (count == 1) {
                return false;
            }
            return true;
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

        public Toner() { super(6, "Toner"); }
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
        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Moisturizer")) {
                    count++;
                }
            }
            if (count == 2) {
                    return false;
            }
            return true;
        }
    }

    public static class FaceOil extends Skincare {

        public FaceOil() {
            super(10, "Face Oil");
        }
        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Face Oil")) {
                    count++;
                }
            }
            if (count == 1) {
                return false;
            }
            return true;
        }
    }

    public static class Sunscreen extends Skincare {

        public Sunscreen() { super(11, "Sunscreen"); }
        public boolean checkIfCompatible(Routine rutin, boolean dayOrNight) {
            int count = 0;
            List<Products> selectedProducts = dayOrNight ? rutin.getMorning() : rutin.getNight();

            for (Products currProduct : selectedProducts) {
                if (currProduct.getProductType().getType().equals("Sunscreen")) {
                    count++;
                }
            }
            if (count == 1) {
                return false;
            }
            return true;
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

        public List<Products> getMorning() {
            return morning;
        }

        public List<Products> getNight() {
            return night;
        }

        public void addMorning(Products product) {
            morning.add(product);
        }

        public void addNight(Products product) {
            night.add(product);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            initializeDatabase(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                List<Products> productsList = getProductListDatabase();
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
                        //productsList.add(addProduct(connection));
                        break;
                    case 2:
                        //addRoutine(connection, productsList);
                        break;
                    case 3:
//                        displayRoutine();
                        break;
                    case 4:
//                        editProduct();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeDatabase(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Create tables if not exists
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS skincare (" + "id INT PRIMARY KEY," + "type VARCHAR(255) NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (" + "id INT PRIMARY KEY AUTO_INCREMENT," + "brand VARCHAR(255) NOT NULL," + "name VARCHAR(255) NOT NULL," + "type_id INT," +  // Fix: Change "productType" to "type_id"
                    "FOREIGN KEY (type_id) REFERENCES skincare(id))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS routine (" + "id INT PRIMARY KEY AUTO_INCREMENT," + "day INT NOT NULL," + "PRIMARY KEY (id)," + "UNIQUE KEY unique_day (day))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS routineDetails (" + "details_id INT PRIMARY KEY AUTO_INCREMENT," + "routine_day INT," + "time_of_day VARCHAR(255)," + "product_id INT," + "FOREIGN KEY (routine_day) REFERENCES routine(id) ON UPDATE CASCADE ON DELETE CASCADE," + "FOREIGN KEY (product_id) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE)");

            try (ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM skincare")) {
                resultSet.next();
                int rowCount = resultSet.getInt(1);

                if (rowCount == 0) {
                    // Insert statements for each skincare type
                    stmt.executeUpdate("INSERT INTO skincare VALUES (1, 'Cleansing Balm')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (2, 'Cleansing Oil')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (3, 'Micellar Water')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (4, 'Cleanser')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (5, 'Exfoliator')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (6, 'Toner')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (7, 'Retinol')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (8, 'Serum')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (9, 'Moisturizer')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (10, 'Face Oil')");
                    stmt.executeUpdate("INSERT INTO skincare VALUES (11, 'Sunscreen')");
                }
            }
            try (ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM routine")) {
                resultSet.next();
                int routineRowCount = resultSet.getInt(1);

                if (routineRowCount == 0) {
                    for (int day = 1; day <= 7; day++) {
                        stmt.executeUpdate("INSERT INTO routine VALUES (NULL, " + day + ")");
                    }
                }
            }
        }
    }

    public static List<Routine> getRoutine() throws SQLException {
        List<Routine> routineList = new ArrayList<>();

        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM routinedetails")) {
                while (resultSet.next()) {
                    int day = resultSet.getInt("routine_day");
                    String timeOfDay = resultSet.getString("time_of_day");
                    Routine routine = getRoutineFromList(routineList, day);

                    int productID = resultSet.getInt("product_id");
                    Products product = getProductFromDatabase(productID);

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

        return routineList;
    }


    public static List<Products> getProductsBySkincareType(int skincareTypeId) throws SQLException {
        List<Products> filteredProducts = new ArrayList<>();

        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM products WHERE productType = ?")) {
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


    private static Products getProductFromDatabase(int productID) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        // Load product details from the Products table
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM products WHERE id = ?")) {
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

    public static List<Products> getProductListDatabase() throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        // Load product details from the Products table
        List<Products> productList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM products"); ResultSet resultSet = pstmt.executeQuery()) {

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

    public static void addProductToDatabase(String nama, String brand, int productType) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO products (brand, name, productType) VALUES (?, ?, ?)")) {
            pstmt.setString(1, brand);
            pstmt.setString(2, nama);
            pstmt.setInt(3, productType);
            pstmt.executeUpdate();
        }
    }


    public static void addProductToRoutineInDatabase(int day, int productId, String timeOfDay) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        // Insert routine details into the RoutineDetails table
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO routinedetails (routine_day, time_of_day, product_id) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, day);
            pstmt.setString(2, timeOfDay);
            pstmt.setInt(3, productId);
            pstmt.executeUpdate();
        }
    }

    public static int getProductID(Products product) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        // Retrieve the ID of the product from the Products table
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM products WHERE brand = ? AND name = ? AND productType = ?")) {
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


    public static void editProduct(int productId, String newBrand, String newName) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        // Check if the product with the given ID exists
        Products selectedProduct = getProductFromDatabase(productId);
        if (selectedProduct == null) {
            return;
        }


        // Update the product in the database
        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE products SET brand = ?, name = ? WHERE id = ?")) {
            pstmt.setString(1, newBrand.isEmpty() ? selectedProduct.getProductBrand() : newBrand);
            pstmt.setString(2, newName.isEmpty() ? selectedProduct.getProductName() : newName);
            pstmt.setInt(3, productId);
            pstmt.executeUpdate();
        }
    }


    public static void deleteProductFromRoutine(int day, int productId, String timeOfDay) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM routinedetails WHERE routine_day = ? AND product_id = ? AND time_of_day = ?")) {
            pstmt.setInt(1, day);
            pstmt.setInt(2, productId);
            pstmt.setString(3, timeOfDay);
            pstmt.executeUpdate();
        }
    }


    public static void deleteProductID(int productId) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

        // Retrieve the ID of the product from the Products table
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM products WHERE id = ?")) {
            pstmt.setString(1, String.valueOf(productId));
            pstmt.executeUpdate();

        }
    }
}

