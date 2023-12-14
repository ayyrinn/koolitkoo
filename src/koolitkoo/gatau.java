/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koolitkoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author April
 */
public class gatau {

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
                if (currProduct.getProductType().equals("Cleansing Oil")) {
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
                if (currProduct.getProductType().equals("Cleansing Balm")) {
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
                if (currProduct.getProductType().equals("Retinol")) {
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
                if (currProduct.getProductType().equals("Exfoliator")) {
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
                if (currProduct.getProductType().equals("Serum")) {
                    count++;
                }
                if (currProduct.getProductType().equals("Retinol")) {
                    flag = 1;
                }
            }
            if(flag == 0){
                if(count == 3) return false;
            } else {
                if(count == 2) return false;
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
                System.out.println("    Nama: " + product.getProductName());
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
            for (Products product : products) {
                System.out.println("  - Jenis: " + product.getProductType().getType());
                System.out.println("    Brand: " + product.getProductBrand());
                System.out.println("    Nama: " + product.getProductName());
            }
        }

         public void display() {
            System.out.println("Rutin pada hari " + getDays() + ":");
            System.out.println("Pagi:");
            displayDetails(morning);
            System.out.println("Malam:");
            displayDetails(night);
        }

//        private int findIndex(Products newProduct, List<Products> routine) {
//            int index = 0;
//            for (Products existingProduct : routine) {
//                int compareResult = newProduct.get() - existingProduct.get();
//                if (compareResult < 0) {
//                    break;
//                } else if (compareResult == 0) {
//                    compareResult = newProduct.getProductBrand().compareTo(existingProduct.getProductBrand());
//                    if (compareResult < 0 || (compareResult == 0 && newProduct.getProductName().compareTo(existingProduct.getProductName()) < 0)) {
//                        break;
//                    }
//                }
//                index++;
//            }
//            return index;
//        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Products> productsList = new ArrayList<>();

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

            Products tempProduct;

            switch (choice) {
                case 1:
                    tempProduct = addProduct();
                    productsList.add(tempProduct);
                    break;
                case 2:
                    addRoutine(productsList);
                    break;
                case 3:
                    displayRoutine();
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
    }

    private static final List<Routine> routineList = new ArrayList<>();

    public static void insertRoutine(Routine routine) {
        routineList.add(routine);
    }

    public static void displayRoutine() {
        if (routineList.isEmpty()) {
            System.out.println("Skincare Routine belum ada.");
            return;
        }
        System.out.println("Skincare Routine List:");
        for (Routine routine : routineList) {
            routine.display();
            System.out.println();
        }
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

    public static Products addProduct() {
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

        Skincare inputProduct = null;

        switch (skincareType) {
            case 1:
                inputProduct = new CleansingBalm();
                break;
            case 2:
                inputProduct = new CleansingOil();
                break;
            case 3:
                inputProduct = new Micellar();
                break;
            case 4:
                inputProduct = new Cleanser();
                break;
            case 5:
                inputProduct = new Exfoliator();
                break;
            case 6:
                inputProduct = new Toner();
                break;
            case 7:
                inputProduct = new Retinol();
                break;
            case 8:
                inputProduct = new Serum();
                break;
            case 9:
                inputProduct = new Moisturizer();
                break;
            case 10:
                inputProduct = new FaceOil();
                break;
            case 11:
                inputProduct = new Sunscreen();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                addProduct();
        }
        System.out.println("Skincare product berhasil ditambahkan!");
        return new Products(brand, name, inputProduct);
    }

    public static void addRoutine(List<Products> availableProducts) {
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
        int[] nightIds = {1, 2, 3, 4, 6, 7, 8, 9, 10};

        int skincareType = scanner.nextInt();
        scanner.nextLine();

        int[] productIds = timeOfDay.equals("pagi") ? morningIds : nightIds;

        List<Products> filteredProducts = new ArrayList<Products>();

        for (Products availableProduct : availableProducts) {
            if (availableProduct.getProductType().getId() == productIds[skincareType]) {
                filteredProducts.add(availableProduct);
            }
        }

        for (Products filteredProduct : filteredProducts) {
            System.out.println(filteredProduct.productBrand);
            System.out.println(filteredProduct.productName);
        }

        int productSelected = scanner.nextInt();
        scanner.nextLine();

        Products selected = filteredProducts.get(productSelected);

        Routine routine = getRoutine(day);
        
        boolean nightOrDay = timeOfDay.equals("pagi");
        
        boolean isCompatible = selected.getProductType().checkIfCompatible(routine,nightOrDay );

        if (timeOfDay.equals("pagi") && isCompatible) {
            routine.addMorning(selected);
        } else if (timeOfDay.equals("malam") && isCompatible){
            routine.addNight(selected);
        }

        System.out.println("Skincare berhasil ditambahkan!");
    }
}
