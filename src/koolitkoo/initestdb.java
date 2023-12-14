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
import java.util.List;
import java.util.Scanner;

public class initestdb {
    
    static final String DB_URL = "jdbc:mysql://0.tcp.ap.ngrok.io:19281";
    static final String USER = "root";
    static final String PASS = "KontolKuda";

    public static class Skincare{
        int id;
        String type;
        String brand;
        String name;
        
        public Skincare(int id, String type, String brand, String name){
            this.id = id;
            this.type = type;
            this.brand = brand;
            this.name = name;
        }
        
        public int getId(){
            return this.id;
        }
        
        public String getType(){
            return this.type;
        }
        
        public String getBrand(){
            return this.brand;
        }

        public String getName(){
            return this.name;
        }

        public void setBrand(String brand){
            this.brand = brand;
        }

        public void setName(String name){
            this.name = name;
        }
    }
    
    public static class CleansingBalm extends Skincare{
        public CleansingBalm(String brand, String name){
            super(1, "Cleansing Balm", brand, name);
        }
    }
    
    public static class CleansingOil extends Skincare{
        public CleansingOil(String brand, String name){
            super(2, "Cleansing Oil", brand, name);
        }
    }
    
    public static class Micellar extends Skincare{
        public Micellar(String brand, String name){
            super(3, "Micellar Water", brand, name);
        }
    }
    
    public static class Cleanser extends Skincare{
        public Cleanser(String brand, String name){
            super(4, "Cleanser", brand, name);
        }
    }
    
    public static class Exfoliator extends Skincare{
        public Exfoliator(String brand, String name){
            super(5, "Exfoliator", brand, name);
        }
    }
    
    public static class Toner extends Skincare{
        public Toner(String brand, String name){
            super(6, "Toner", brand, name);
        }
    }
    
    public static class Retinol extends Skincare{
        public Retinol(String brand, String name){
            super(7, "Retinol", brand, name);
        }
    } 
    
    public static class Serum extends Skincare{
        public Serum(String brand, String name){
            super(8, "Serum", brand, name);
        }
    }   
    
    public static class Moisturizer extends Skincare{
        public Moisturizer(String brand, String name){
            super(9, "Moisturizer", brand, name);
        }
    } 
    
    public static class FaceOil extends Skincare{
        public FaceOil(String brand, String name){
            super(10, "Face Oil", brand, name);
        }
    }     
    
    public static class Sunscreen extends Skincare{
        public Sunscreen(String brand, String name){
            super(11, "Sunscreen", brand, name);
        }
    }   
    
    public static class Routine {
        int day;
        List<Skincare> morning;
        List<Skincare> night;

        public Routine(int day) {
            this.day = day;
            this.morning = new ArrayList<>();
            this.night = new ArrayList<>();
        }

        public String getDays() {
            String[] days = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
            return days[day - 1];
        }

        public List<Skincare> getMorning() {
            return morning;
        }

        public List<Skincare> getNight() {
            return night;
        }
        
        public void addMorning(Skincare product) {
            int index = findIndex(product, morning);
            morning.add(index, product);
        }

        public void addNight(Skincare product) {
            int index = findIndex(product, night);
            night.add(index, product);
        }
        
        public void display() {
            System.out.println("Rutin pada hari " + getDays() + ":");
            System.out.println("Pagi:");
            displayDetails(morning);
            System.out.println("Malam:");
            displayDetails(night);
        }

        private void displayDetails(List<Skincare> products) {
            for (Skincare product : products) {
                System.out.println("  - Jenis: " + product.getType());
                System.out.println("    Brand: " + product.getBrand());
                System.out.println("    Nama: " + product.getName());
            }
        }
        
        private int findIndex(Skincare newProduct, List<Skincare> routine) {
            int index = 0;
            for (Skincare existingProduct : routine) {
                int compareResult = newProduct.getId() - existingProduct.getId();
                if (compareResult < 0) {
                    break;
                } else if (compareResult == 0) {
                    compareResult = newProduct.getBrand().compareTo(existingProduct.getBrand());
                    if (compareResult < 0 || (compareResult == 0 && newProduct.getName().compareTo(existingProduct.getName()) < 0)) {
                        break;
                    }
                }
                index++;
            }
            return index;
        }
    }
    
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            createTables(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Tambah Produk pada Rutin");
                System.out.println("2. Tampilkan Rutin");
                System.out.println("3. Tampilkan Produk");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu (1-3): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addProduct(connection);
                        break;
                    case 2:
                        displayRoutine(connection);
                        break;
                    case 3:
                        displayProducts(connection);
                        break;
                    case 4:
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
    
    private static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS skincaretype (id INT PRIMARY KEY AUTO_INCREMENT, type VARCHAR(255) NOT NULL)");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM skincaretype");
            resultSet.next();
            int rowCount = resultSet.getInt("count");

            if (rowCount == 0) {
                statement.executeUpdate("INSERT INTO skincaretype (type) VALUES " +
                        "('Cleansing Balm'), ('Cleansing Oil'), ('Micellar Water'), " +
                        "('Cleanser'), ('Exfoliator'), ('Toner'), ('Retinol'), ('Serum'), " +
                        "('Moisturizer'), ('Face Oil'), ('Sunscreen')");
                System.out.println("Data inserted into skincaretype table.");
            } else {
                System.out.println("skincaretype table already has data. Skipping insertion.");
            }
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS products (id INT PRIMARY KEY AUTO_INCREMENT, type_id INT NOT NULL, brand VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, FOREIGN KEY (type_id) REFERENCES skincaretype(id))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS routine (id INT PRIMARY KEY AUTO_INCREMENT, day INT NOT NULL, daytime ENUM('morning', 'night') NOT NULL)");

            resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM routine");
            resultSet.next();
            rowCount = resultSet.getInt("count");

            if (rowCount == 0) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS routine (id INT PRIMARY KEY AUTO_INCREMENT, day INT NOT NULL, daytime ENUM('morning', 'night') NOT NULL)");
                statement.executeUpdate("INSERT INTO routine (day, daytime) VALUES " +
                        "(1, 'morning'), (1, 'night'), (2, 'morning'), (2, 'night'), " +
                        "(3, 'morning'), (3, 'night'), (4, 'morning'), (4, 'night'), " +
                        "(5, 'morning'), (5, 'night'), (6, 'morning'), (6, 'night'), " +
                        "(7, 'morning'), (7, 'night')");
                System.out.println("Data inserted into routine table.");
            } else {
                System.out.println("Routine table already has data. Skipping insertion.");
            }

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS routineproducts (routine_id INT NOT NULL, product_id INT NOT NULL, FOREIGN KEY (routine_id) REFERENCES Routine(id), FOREIGN KEY (product_id) REFERENCES Products(id), PRIMARY KEY (routine_id, product_id))");
        }
    }
    
    private static final List<Routine> routineList = new ArrayList<>();

    public static void insertRoutine(Routine routine) {
        routineList.add(routine);
    }
    
    private static void insertRoutineProduct(Connection connection, int day, String timeOfDay, int productId) {
        try {
            String insertRoutineSql = "INSERT INTO routine (day, daytime) VALUES (?, ?)";
            String insertRoutineProductSql = "INSERT INTO routineproducts (routine_id, product_id) VALUES (?, ?)";
            try (PreparedStatement routineStatement = connection.prepareStatement(insertRoutineSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement routineProductStatement = connection.prepareStatement(insertRoutineProductSql)) {

                routineStatement.setInt(1, day);
                routineStatement.setString(2, timeOfDay);
                routineStatement.executeUpdate();

                ResultSet generatedKeys = routineStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int routineId = generatedKeys.getInt(1);
                    routineProductStatement.setInt(1, routineId);
                    routineProductStatement.setInt(2, productId);
                    routineProductStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   private static void displayRoutine(Connection connection) throws SQLException {
        // Sample SQL query to retrieve routine data with product details
        String sql = "SELECT r.id AS routine_id, r.day, r.daytime, p.id AS product_id, p.brand, p.name, st.type " +
                    "FROM routine r " +
                    "JOIN routineproducts rp ON r.id = rp.routine_id " +
                    "JOIN products p ON rp.product_id = p.id " +
                    "JOIN skincaretype st ON p.type_id = st.id " +
                    "ORDER BY st.id, p.brand, p.name";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            List<Routine> routines = new ArrayList<>();
            while (resultSet.next()) {
                int routineId = resultSet.getInt("routine_id");
                int day = resultSet.getInt("day");
                String timeOfDay = resultSet.getString("daytime");
                int productId = resultSet.getInt("product_id");
                String brand = resultSet.getString("brand");
                String name = resultSet.getString("name");
                String typeName = resultSet.getString("type");

                // Build Routine object
                Routine routine = new Routine(day);
                routine.display(); 

                // Display product details
                System.out.println("  - Jenis: " + typeName);
                System.out.println("    Brand: " + brand);
                System.out.println("    Nama: " + name);

                routines.add(routine);
            }

        }
    }

            private static void displayProducts(Connection connection) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

                System.out.println("Skincare Products:");
                System.out.printf("%-5s%-15s%-15s%n", "ID", "Brand", "Name");
                System.out.println("---------------------------------");

                while (resultSet.next()) {
                    int productId = resultSet.getInt("id");
                    String brand = resultSet.getString("brand");
                    String name = resultSet.getString("name");

                    System.out.printf("%-5d%-15s%-15s%n", productId, brand, name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
    }
}
    
    private static int countSerum(List<Skincare> products) {
        int count = 0;
        for (Skincare product : products) {
            if (product instanceof Serum) {
                count++;
            }
        }
        return count;
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
    
    private static boolean hasCleansingBalm(List<Skincare> products) {
        for (Skincare product : products) {
            if (product instanceof CleansingBalm) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCleansingOil(List<Skincare> products) {
        for (Skincare product : products) {
            if (product instanceof CleansingOil) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean hasExfoliator(List<Skincare> products) {
        for (Skincare product : products) {
            if (product instanceof Exfoliator) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRetinol(List<Skincare> products) {
        for (Skincare product : products) {
            if (product instanceof Retinol) {
                return true;
            }
        }
        return false;
    }
    
    
    public static void addProduct(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan hari (1-7): ");
        int day = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Pilih pagi atau malam: ");
        String timeOfDay = scanner.nextLine().toLowerCase();
        
        if(!timeOfDay.equals("pagi") && !timeOfDay.equals("malam")) {
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
            System.out.println("1. Cleansing Balm/Oil");
            System.out.println("2. Micellar");
            System.out.println("3. Cleanser");
            System.out.println("4. Exfoliator");
            System.out.println("5. Toner");
            System.out.println("6. Retinol");
            System.out.println("7. Serum");
            System.out.println("8. Moisturizer");
            System.out.println("9. Face Oil");
            System.out.print("Pilih jenis skincare (1-9): ");
        }
        
        int skincareType = scanner.nextInt();
        scanner.nextLine();
        
        Routine routine = getRoutine(day);

        System.out.print("Masukkan brand skincare: ");
        String brand = scanner.nextLine();

        System.out.print("Masukkan nama skincare: ");
        String name = scanner.nextLine();

        Skincare inputProduct = null;

        switch (timeOfDay) {
            case "pagi":
                switch (skincareType) {
                    case 1:
                        inputProduct = new Cleanser(brand, name);
                        skincareType = 4;
                        break;
                    case 2:
                        inputProduct = new Toner(brand, name);
                        skincareType = 6;
                        break;
                    case 3:
                        if (countSerum(routine.getNight()) >= 3) {
                            System.out.println("Batas maksimal Serum adalah 3.");
                            return;
                        }
                        inputProduct = new Serum(brand, name);
                        skincareType = 8;
                        break;
                    case 4:
                        inputProduct = new Moisturizer(brand, name);
                        skincareType = 9;
                        break;
                    case 5:
                        inputProduct = new FaceOil(brand, name);
                        skincareType = 10;
                        break;
                    case 6:
                        inputProduct = new Sunscreen(brand, name);
                        skincareType = 11;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        return;
                }
                break;
            case "malam":
                switch (skincareType) {
                    case 1:
                        System.out.print("Pilih tipe (Cleansing Balm/Cleansing Oil): ");
                        String cleansingType = scanner.nextLine().toLowerCase();
                        if(cleansingType.equals("cleansing balm")) {
                            if (hasCleansingOil(routine.getNight())) {
                                System.out.println("Tidak bisa menambahkan Cleansing Balm setelah Cleansing Oil.");
                                return;
                            }
                            inputProduct = new CleansingBalm(brand, name);
                            skincareType = 1;
                        } else if (cleansingType.equals("cleansing oil")) {
                            if (hasCleansingBalm(routine.getNight())) {
                                System.out.println("Tidak bisa menambahkan Cleansing Oil setelah Cleansing Balm.");
                                return;
                            }
                            inputProduct = new CleansingOil(brand, name);
                            skincareType = 2;
                        } else {
                            System.out.println("Pilihan tipe tidak valid.");
                            return;
                        }
                        break;
                    case 2:
                        inputProduct = new Micellar(brand, name);
                        skincareType = 3;
                        break;
                    case 3:
                        inputProduct = new Cleanser(brand, name);
                        skincareType = 4;
                        break;
                    case 4:
                        if (hasRetinol(routine.getNight())) {
                            System.out.println("Tidak bisa menambahkan Exfoliator setelah Retinol.");
                            return;
                        }
                        inputProduct = new Exfoliator(brand, name);
                        skincareType = 5;
                        break;
                    case 5:
                        inputProduct = new Toner(brand, name);
                        skincareType = 6;
                        break;
                    case 6:
                        if (hasExfoliator(routine.getNight())) {
                            System.out.println("Tidak bisa menambahkan Retinol setelah Exfoliator.");
                            return;
                        }
                        inputProduct = new Retinol(brand, name);
                        skincareType = 7;
                        break;
                    case 7:
                        if (hasRetinol(routine.getNight())) {
                            if (countSerum(routine.getNight()) >= 2) {
                                System.out.println("Batas maksimal Serum setelah Retinol adalah 2.");
                                return;
                            }
                        } else {
                            if (countSerum(routine.getNight()) >= 3) {
                                System.out.println("Batas maksimal Serum adalah 3.");
                                return;
                            }
                        }
                        inputProduct = new Serum(brand, name);
                        skincareType = 8;
                        break;
                    case 8:
                        inputProduct = new Moisturizer(brand, name);
                        skincareType = 9;
                        break;
                    case 9:
                        inputProduct = new FaceOil(brand, name);
                        skincareType = 10;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        return;
                }
                break;
            default:
                System.out.println("Pilihan waktu tidak valid.");
                return;
        }

        if (timeOfDay.equals("pagi")) {
            routine.addMorning(inputProduct);
            timeOfDay = "morning";
        } else {
            routine.addNight(inputProduct);
            timeOfDay = "night";
        }
        
        try {
            String insertProductSql = "INSERT INTO products (type_id, brand, name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductSql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, skincareType);
                preparedStatement.setString(2, brand);
                preparedStatement.setString(3, name);
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    insertRoutineProduct(connection, routine.day, timeOfDay, productId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Skincare berhasil ditambahkan!");
    
    }
 
}
