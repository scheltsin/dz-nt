import java.util.Scanner;

/**
 * Основной класс для взаимодействия с пользователем.
 */
public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        User user = new User("Иван Иванов");

        // Добавление продуктов в магазин
        store.addProduct(new Product("Ноутбук", 1000.0, "Dell", 5));
        store.addProduct(new Product("Смартфон", 700.0, "Samsung", 4));
        store.addProduct(new Product("Планшет", 300.0, "Apple", 5));
        
        Scanner scanner = new Scanner(System.in);
        String command;
        
        do {
            System.out.println("Введите команду (view, filter, cart, order, status, recommend, exit):");
            command = scanner.nextLine();
            
            switch (command) {
                case "view":
                    // Вывод всех продуктов в магазине
                    store.getProducts().forEach(System.out::println);
                    break;
                    
                case "filter":
                    // Фильтрация продуктов по критериям
                    System.out.println("Введите ключевое слово:");
                    String keyword = scanner.nextLine();
                    System.out.println("Введите минимальную цену:");
                    double minPrice = scanner.nextDouble();
                    System.out.println("Введите максимальную цену:");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();  // поглощение новой строки
                    System.out.println("Введите производителя:");
                    String manufacturer = scanner.nextLine();
                    store.filterProducts(keyword, minPrice, maxPrice, manufacturer).forEach(System.out::println);
                    break;
                    
                case "cart":
                    // Добавление продукта в корзину пользователя
                    System.out.println("Введите имя продукта для добавления в корзину:");
                    String productName = scanner.nextLine();
                    store.getProducts().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(productName))
                            .findFirst()
                            .ifPresent(user::addToCart);
                    System.out.println("Корзина: " + user.getCart());
                    break;
                    
                case "order":
                    // Создание заказа для пользователя
                    Order order = store.createOrder(user);
                    System.out.println("Заказ создан: " + order);
                    break;
                    
                case "status":
                    // Обновление статуса заказа
                    System.out.println("Введите ID заказа для обновления статуса:");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();  // поглощение новой строки
                    System.out.println("Введите новый статус:");
                    String status = scanner.nextLine();
                    store.updateOrderStatus(orderId, status);
                    break;
                    
                case "recommend":
                    // Рекомендация продуктов пользователю
                    store.recommendProducts(user).forEach(System.out::println);
                    break;
                    
                case "exit":
                    System.out.println("Выход...");
                    break;
                    
                default:
                    System.out.println("Недопустимая команда.");
                    break;
            }
        } while (!command.equalsIgnoreCase("exit"));
        
        scanner.close();
    }
}
