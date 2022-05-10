import java.util.Comparator;

public class Product {
    private String name;
    private int id;
    private float price;
    private int amount;


    public Product() {
    }

    public Product(String name, int id, float price, int amount) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    

    @Override
    public String toString() {
        return "[" +
            "Name:" + getName() +
            "Id:" + getId() +
            "Price:" + getPrice() +
            "Amount:" + getAmount() + 
            "]";
    }

    public void printProduct() {
        String[] attributeStrings = {this.name,
                                     String.valueOf(this.id),
                                     String.valueOf(this.price),
                                     String.valueOf(this.amount)};

        String nameFormat = "%-24s";
        String idFormat = "%-6s";
        String priceFormat = "%-10s";
        String amountFormat = "%-10s";
        String formatInfo = nameFormat + " " + idFormat + " " + priceFormat + " " + amountFormat;

        // for(int i = 0; i < attributeStrings.length; i++) {
        //     System.out.format(formatInfo, attributeStrings[i], attributeStrings[i], attributeStrings[i], attributeStrings[i]);
        //     System.out.println();
        // }
        System.out.format(formatInfo, attributeStrings[0], attributeStrings[1], attributeStrings[2], attributeStrings[3]);
        System.out.println();
    }

    public static Comparator<Product> productNameComparator = new Comparator<Product>() {
        public int compare(Product a, Product b) {
            String nameA = a.getName().toUpperCase(); 
            String nameB = b.getName().toUpperCase();

            return nameA.compareTo(nameB);
        }
    };

    public static Comparator<Product> productIdComparator = new Comparator<Product>() {
        public int compare(Product a, Product b) {
            Integer idA = a.getId();
            Integer idB = b.getId();

            return idA.compareTo(idB);
        }
    };

    public static Comparator<Product> productPriceComparator = new Comparator<Product>() {
        public int compare(Product a, Product b) {
            Float priceA = a.getPrice();
            Float priceB = b.getPrice();
            
            return priceA.compareTo(priceB);
        }
    };

    public static Comparator<Product> productAmountComparator = new Comparator<Product>() {
        public int compare(Product a, Product b) {
            Integer amountA = a.getAmount();
            Integer amountB = b.getAmount();

            return amountA.compareTo(amountB);
        }
    };

}
