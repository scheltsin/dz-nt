import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс Store представляет магазин.
 */
public class Store {
    private List<Product> products;
    private List<Order> orders;

    /**
     * Конструктор для создания магазина.
     */
    public Store() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    /**
     * Добавление продукта в магазин.
     * 
     * @param product Продукт для добавления
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Фильтрация продуктов по заданным критериям.
     * 
     * @param keyword Ключевое слово для поиска по имени продукта
     * @param minPrice Минимальная цена продукта
     * @param maxPrice Максимальная цена продукта
     * @param manufacturer Производитель продукта
     * @return Список продуктов, соответствующих критериям
     */
    public List<Product> filterProducts(String keyword, Double minPrice, Double maxPrice, String manufacturer) {
        return products.stream()
                .filter(p -> (keyword == null || p.getName().contains(keyword)) &&
                             (minPrice == null || p.getPrice() >= minPrice) &&
                             (maxPrice == null || p.getPrice() <= maxPrice) &&
                             (manufacturer == null || p.getManufacturer().equals(manufacturer)))
                .collect(Collectors.toList());
    }

    /**
     * Создание заказа для пользователя.
     * 
     * @param user Пользователь, который делает заказ
     * @return Созданный заказ
     */
    public Order createOrder(User user) {
        Order order = new Order(user, new ArrayList<>(user.getCart()));
        orders.add(order);
        user.clearCart(); // Очистка корзины пользователя после создания заказа
        return order;
    }

    /**
     * Обновление статуса заказа.
     * 
     * @param orderId Идентификатор заказа
     * @param status Новый статус заказа
     */
    public void updateOrderStatus(int orderId, String status) {
        orders.stream()
              .filter(order -> order.getId() == orderId)
              .forEach(order -> order.setStatus(status));
    }

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Рекомендация продуктов пользователю на основе рейтинга.
     * 
     * @param user Пользователь, которому рекомендуются продукты
     * @return Список рекомендуемых продуктов
     */
    public List<Product> recommendProducts(User user) {
        // Пример простой рекомендации: рекомендовать продукты с наивысшим рейтингом
        return products.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getRating(), p1.getRating()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
