import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Scanner input = new Scanner(System.in);
    private static Store store = new Store();
    public static void main(String[] args) throws Exception {
        store.readProductListFromXml("resources/products.xml");

        int menuInput = -1;
        while(menuInput != 0) {
            switch (menuInput) {
                case 1 -> menuAdd();
                case 2 -> menuRemove();
                case 3 -> menuModify();
                case 4 -> menuSort();
                case 5 -> menuExport();
                case 6 -> menuImport();
            }

            System.out.println("\n[PRODUCT LIST]");
            System.out.println("Name\t\t\t Id\tPrice\t   Amount");
            store.printProducts();
            System.out.println("ADD[1] REMOVE[2] MODIFY[3] SORT[4] EXPORT[5] IMPORT[6] EXIT[0]");
            System.out.print("Choose menu:");

            try {
                menuInput = input.nextInt();
                input.nextLine();
                if(menuInput < 0 || menuInput > 6) {
                    System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine();
            }
        }

        store.saveProductListToXml("resources/products.xml");
    }
    

    private static void menuAdd() {
        store.addProduct(createNewProduct());
    }

    private static void menuRemove() {
        System.out.print("Enter id of product to remove:");
        
        int removeId;
        try {
            removeId = input.nextInt();
            input.nextLine();
            int removeIndex = store.getProductIndexById(removeId);

            if(removeIndex != -1) {
                store.removeProduct(removeIndex);
            } else {
                System.out.println("Product with Id:" + removeId + " is not in the list.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Id can only be a number.");
        }
        
    }

    private static void menuModify() {
        System.out.print("Enter id of product to modify:");
        
        int modifyId;
        try {
            modifyId = input.nextInt();
            input.nextLine();
            int modifyIndex = store.getProductIndexById(modifyId);

            if(modifyIndex != -1) {
                modifyChooseMenu(modifyIndex);
            } else {
                System.out.println("Product with Id:" + modifyId + " is not in the list.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Id can only be a number.");
        }
    }

    private static void menuSort() {
        System.out.println("Sort by:");
        System.out.println("NAME[0] ID[1] PRICE[2] AMOUNT[3]");
        int sortType = -1;
        while(sortType == -1) {
            try {
                sortType = input.nextInt();
                input.nextLine();
                store.sortList(sortType);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine();
            }
        }
    }

    private static void menuExport() {
        System.out.print("Enter destiantion filepath:");
        String filePath = input.nextLine();
        store.saveProductListToXml(filePath);
    }

    private static void menuImport() {
        System.out.print("Enter filepath to list:");
        String filePath = input.nextLine();
        store.readProductListFromXml(filePath);
    }


    private static Product createNewProduct() {
        System.out.print("Enter product name:");
        String name = input.nextLine();

        int id = 0;
        while(id == 0) {
            System.out.print("Enter product id:");
            try {
                id = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Id can only be a number.");
                input.nextLine();
            }
        }

        float price = 0f;
        while(price <= 0.0f)  {
            System.out.print("Enter product price:");
            try {
                price = input.nextFloat();
                input.nextLine();
                if(price < 0f) {
                    System.out.println("Price cannot be less than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Price can only be a numebr.");
                input.nextLine();
            }
        }

        int amount = 0;
        while(amount == 0) {
            System.out.print("Enter product amount:");
            try {
                amount = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Amount can only be a number.");
                input.nextLine();
            }
        }

        return new Product(name, id, price, amount);
    }

    private static void modifyChooseMenu(int modifyIndex) {
        int chooseModify = -1;
        while(chooseModify != 0) {
            System.out.println("Modifing: " + store.getProductByIndex(modifyIndex).getName());
            System.out.println("NAME[1] ID[2] PRICE[3] AMOUNT[4] DONE[0]");
            try {
                chooseModify = input.nextInt();
                input.nextLine();
                if(chooseModify < 0 || chooseModify > 4) {
                    System.out.println("Invalid input.");
                }
                switch (chooseModify) {
                    case 1:
                        System.out.print("Enter new name:");
                        String newName = input.nextLine();
                        store.getProductByIndex(modifyIndex).setName(newName);
                        break;
                    case 2:
                        System.out.print("Enter new id:");
                        int newId = input.nextInt();
                        input.nextLine();
                        store.getProductByIndex(modifyIndex).setId(newId);
                        break;
                    case 3:
                        System.out.print("Enter new price:");
                        float newPrice = input.nextFloat();
                        input.nextLine();
                        store.getProductByIndex(modifyIndex).setPrice(newPrice);
                        break;
                    case 4:
                        System.out.print("Enter new amount:");
                        int newAmount = input.nextInt();
                        input.nextLine();
                        store.getProductByIndex(modifyIndex).setAmount(newAmount);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine();
            }
        }
    }

}
