import java.util.ArrayList;
import java.util.List;

/**
 * Класс User представляет пользователя магазина.
 */
public class User {
    private String name;
    private List<Product> cart;

    /**
     * Конструктор для создания пользователя.
     * 
     * @param name Имя пользователя
     */
    public User(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getCart() {
        return cart;
    }

    /**
     * Добавление продукта в корзину пользователя.
     * 
     * @param product Продукт для добавления
     */
    public void addToCart(Product product) {
        cart.add(product);
    }

    /**
     * Удаление продукта из корзины пользователя.
     * 
     * @param product Продукт для удаления
     */
    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    /**
     * Очистка корзины пользователя.
     */
    public void clearCart() {
        cart.clear();
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', cart=" + cart + "}";
    }
}
