package koolitkoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Koo {
    
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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Produk pada Rutin");
            System.out.println("2. Tampilkan Rutin");
            System.out.println("3. Edit Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayRoutine();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
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
    
    public static void editProduct() {
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

        Routine routine = getRoutine(day);

        List<Skincare> products = (timeOfDay.equals("pagi")) ? routine.getMorning() : routine.getNight();

        if (products.isEmpty()) {
            System.out.println("Tidak ada skincare yang tersedia untuk diedit.");
            return;
        }

        System.out.println("Skincare yang tersedia pada waktu " + timeOfDay + ":");
        for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". Brand: " + products.get(i).getBrand());
                System.out.println("   Nama: " + products.get(i).getName());
        }

        System.out.print("Masukkan angka skincare yang ingin diedit: ");
        int skincareNumber = scanner.nextInt();

        if (skincareNumber < 1 || skincareNumber > products.size()) {
            System.out.println("Angka skincare tidak valid.");
            return;
        }

        Skincare editProduct = products.get(skincareNumber - 1);

        System.out.print("Masukkan brand baru: ");
        String newBrand = scanner.nextLine(); // Consume the newline character
        newBrand = scanner.nextLine();
        editProduct.setBrand(newBrand);

        System.out.print("Masukkan nama baru: ");
        String newName = scanner.nextLine();
        editProduct.setName(newName);

        System.out.println("Skincare berhasil diubah!");
    }
    
    public static void deleteProduct() {
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

        Routine routine = getRoutine(day);

        List<Skincare> products = (timeOfDay.equals("pagi")) ? routine.getMorning() : routine.getNight();

        if (products.isEmpty()) {
            System.out.println("Tidak ada skincare yang tersedia untuk dihapus.");
            return;
        }

        System.out.println("Skincare yang tersedia pada waktu " + timeOfDay + ":");
        for (int i = 0; i < products.size(); i++) {
            Skincare product = products.get(i);
            System.out.println((i + 1) + ". Brand: " + product.getBrand());
            System.out.println("   Nama: " + product.getName());
        }

        System.out.print("Masukkan angka skincare yang ingin dihapus: ");
        int skincareNumber = scanner.nextInt();

        if (skincareNumber < 1 || skincareNumber > products.size()) {
            System.out.println("Angka skincare tidak valid.");
            return;
        }

        Skincare deleteProduct = products.get(skincareNumber - 1);

        products.remove(deleteProduct);
        System.out.println("Skincare berhasil dihapus dari rutin.");
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
    
    public static void addProduct() {
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

        System.out.print("Masukkan nama skincare:   ");
        String name = scanner.nextLine();

        Skincare inputProduct = null;

        switch (timeOfDay) {
            case "pagi":
                switch (skincareType) {
                    case 1:
                        inputProduct = new Cleanser(brand, name);
                        break;
                    case 2:
                        inputProduct = new Toner(brand, name);
                        break;
                    case 3:
                        if (countSerum(routine.getNight()) >= 3) {
                            System.out.println("Batas maksimal Serum adalah 3.");
                            return;
                        }
                        inputProduct = new Serum(brand, name);
                        break;
                    case 4:
                        inputProduct = new Moisturizer(brand, name);
                        break;
                    case 5:
                        inputProduct = new FaceOil(brand, name);
                        break;
                    case 6:
                        inputProduct = new Sunscreen(brand, name);
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
                        } else if (cleansingType.equals("cleansing oil")) {
                            if (hasCleansingBalm(routine.getNight())) {
                                System.out.println("Tidak bisa menambahkan Cleansing Oil setelah Cleansing Balm.");
                                return;
                            }
                            inputProduct = new CleansingOil(brand, name);
                        } else {
                            System.out.println("Pilihan tipe tidak valid.");
                            return;
                        }
                        break;
                    case 2:
                        inputProduct = new Micellar(brand, name);
                        break;
                    case 3:
                        inputProduct = new Cleanser(brand, name);
                        break;
                    case 4:
                        if (hasRetinol(routine.getNight())) {
                            System.out.println("Tidak bisa menambahkan Exfoliator setelah Retinol.");
                            return;
                        }
                        inputProduct = new Exfoliator(brand, name);
                        break;
                    case 5:
                        inputProduct = new Toner(brand, name);
                        break;
                    case 6:
                        if (hasExfoliator(routine.getNight())) {
                            System.out.println("Tidak bisa menambahkan Retinol setelah Exfoliator.");
                            return;
                        }
                        inputProduct = new Retinol(brand, name);
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
                        break;
                    case 8:
                        inputProduct = new Moisturizer(brand, name);
                        break;
                    case 9:
                        inputProduct = new FaceOil(brand, name);
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
        } else {
            routine.addNight(inputProduct);
        }

        System.out.println("Skincare berhasil ditambahkan!");
    
    }
 
}
