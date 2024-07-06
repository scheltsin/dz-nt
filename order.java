import java.util.List;

/**
 * Класс Order представляет заказ в магазине.
 */
public class Order {
    private static int nextId = 1; // Статическая переменная для генерации уникальных идентификаторов заказов

    private int id;
    private User user;
    private List<Product> products;
    private String status;

    /**
     * Конструктор для создания заказа.
     * 
     * @param user Пользователь, который сделал заказ
     * @param products Список продуктов в заказе
     */
    public Order(User user, List<Product> products) {
        this.id = nextId++;
        this.user = user;
        this.products = products;
        this.status = "Processing"; // Начальный статус заказа
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", user=" + user + ", products=" + products + ", status='" + status + "'}";
    }
}
