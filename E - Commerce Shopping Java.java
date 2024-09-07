import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCartSystem {
    
    // Inner class for Product
    class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            
            return id + " - " + name + " - \u20B9" + price;
        }
    }

    // Inner class for Cart
    class Cart {
        private ArrayList<Product> products;

        public Cart() {
            products = new ArrayList<>();
        }

        // Add a product to the cart
        public void addProduct(Product product) {
            products.add(product);
            System.out.println(product.getName() + " has been added to your cart.");
        }

        // View the products in the cart
        public void viewCart() {
            if (products.isEmpty()) {
                System.out.println("Your cart is empty.");
                return;
            }

            System.out.println("Your Cart:");
            for (Product product : products) {
                System.out.println(product);
            }
            
            System.out.println("Total: \u20B9" + calculateTotal());
        }

        // Remove a product from the cart by product ID
        public void removeProduct(int productId) {
            for (Product product : products) {
                if (product.getId() == productId) {
                    products.remove(product);
                    System.out.println(product.getName() + " has been removed from your cart.");
                    return;
                }
            }
            System.out.println("Product not found in your cart.");
        }

        // Calculate the total price of products in the cart
        public double calculateTotal() {
            double total = 0;
            for (Product product : products) {
                total += product.getPrice();
            }
            return total;
        }
    }

    // Main method to run the shopping cart system
    public static void main(String[] args) {
        ShoppingCartSystem system = new ShoppingCartSystem(); // Outer class instance to access inner classes

        // Create some products (ID, Name, Price)
        HashMap<Integer, Product> productCatalog = new HashMap<>();
        productCatalog.put(1, system.new Product(1, "Laptop", 60000.0));
        productCatalog.put(2, system.new Product(2, "Smartphone", 15000.0));
        productCatalog.put(3, system.new Product(3, "Headphones", 2500.0));
        productCatalog.put(4, system.new Product(4, "Keyboard", 1500.0));
        productCatalog.put(5, system.new Product(5, "Monitor", 12000.0));

        Cart cart = system.new Cart(); // Inner class instance for cart
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Display Menu
        while (true) {
            System.out.println("\n--- Welcome to the E-commerce Shopping Cart System ---");
            System.out.println("1. View Product Catalog");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Display all products
                    System.out.println("\nProduct Catalog:");
                    for (Product product : productCatalog.values()) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    // Add product to the cart
                    System.out.print("Enter Product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    if (productCatalog.containsKey(productId)) {
                        cart.addProduct(productCatalog.get(productId));
                    } else {
                        System.out.println("Invalid Product ID.");
                    }
                    break;

                case 3:
                    // View the cart
                    cart.viewCart();
                    break;

                case 4:
                    // Remove product from the cart
                    System.out.print("Enter Product ID to remove from cart: ");
                    productId = scanner.nextInt();
                    cart.removeProduct(productId);
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Thank you for shopping with us. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
