# Магазин

## Описание
Программу-магазин, в которой пользователи заказывают товары. В процессе реализации вы должны применить принцип избегания магических чисел, DRY и как минимум 4 из 5 принципов SOLID, причём явно комментариями в коде указать по одному примеру применения каждого принципа в вашем решении со ссылками на конкретные места кода на GitHub.

## Основные возможности программы:
- Вывод доступных для покупки товаров
- Фильтрация товаров по ключевым словам, ценам, производителям
- Составление продуктовой корзины пользователя
- Трекинг заказа в системе доставки
- Возврат заказа
- Система рейтинга для товаров
- Простая рекомендательная система для покупок

## Пример структуры проекта:

1. `Product` - класс, представляющий продукт.
2. `User` - класс, представляющий пользователя.
3. `Order` - класс, представляющий заказ.
4. `Store` - класс, представляющий магазин.
5. `Main` - основной класс для взаимодействия с пользователем.

## Применение принципов чистого кода и SOLID

### Принцип избегания магических чисел
В классе `Order` для генерации уникальных идентификаторов используется статическая переменная `nextId`.

**Код:**
```java
private static int nextId = 1;
```
https://github.com/scheltsin/dz-nt/

[order.java, строка 4](https://github.com/scheltsin/dz-nt/blob/main/order.java#L4)

### Принцип DRY (Don't Repeat Yourself)
В методе `filterProducts` класса `Store` применяется фильтрация с использованием Stream API, что позволяет избежать повторяющегося кода.

**Код:**
```java
public List<Product> filterProducts(String keyword, Double minPrice, Double maxPrice, String manufacturer) {
    return products.stream()
            .filter(p -> (keyword == null || p.getName().contains(keyword)) &&
                         (minPrice == null || p.getPrice() >= minPrice) &&
                         (maxPrice == null || p.getPrice() <= maxPrice) &&
                         (manufacturer == null || p.getManufacturer().equals(manufacturer)))
            .collect(Collectors.toList());
}
```
[store.java, строки 18-27](https://github.com/scheltsin/dz-nt/blob/main/store.java#L18-L27)

### Принцип единственной ответственности (Single Responsibility Principle, SRP)
Класс `Product` отвечает только за представление продукта и его свойств.

**Код:**
```java
public class Product {
    private String name;
    private double price;
    private String manufacturer;
    private int rating;

    // Геттеры и конструктор
}
```
[product.java, строки 1-10](https://github.com/scheltsin/dz-nt/blob/main/product.java#L1-L10)

### Принцип открытости/закрытости (Open/Closed Principle, OCP)
Класс `Store` открыт для расширения (можно добавить новые методы фильтрации или сортировки), но закрыт для модификации.

**Код:**
```java
public class Store {
    // Поля и методы

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> filterProducts(String keyword, Double minPrice, Double maxPrice, String manufacturer) {
        return products.stream()
                .filter(p -> (keyword == null || p.getName().contains(keyword)) &&
                             (minPrice == null || p.getPrice() >= minPrice) &&
                             (maxPrice == null || p.getPrice() <= maxPrice) &&
                             (manufacturer == null || p.getManufacturer().equals(manufacturer)))
                .collect(Collectors.toList());
    }

    // Другие методы
}
```
[store.java, строки 1-40](https://github.com/scheltsin/dz-nt/blob/main/store.java#L1-L40)

### Принцип подстановки Барбары Лисков (Liskov Substitution Principle, LSP)
В данном проекте мы не использовали наследование, но если бы у нас были разные типы пользователей (например, `RegularUser`, `PremiumUser`), они могли бы использоваться в местах, где ожидается `User`.

### Принцип разделения интерфейсов (Interface Segregation Principle, ISP)
В данном примере мы не использовали интерфейсы, но если бы у нас были разные интерфейсы для различных частей функциональности, они бы были разделены на более мелкие, специфические интерфейсы.

### Принцип инверсии зависимостей (Dependency Inversion Principle, DIP)
Наше решение не включает инъекцию зависимостей, но можно было бы использовать DI-фреймворки для создания более гибкой и легко тестируемой системы.

## Инструкция по запуску
1. Склонируйте репозиторий на ваш локальный компьютер.
2. Откройте проект в вашей IDE.
3. Запустите класс `main` для взаимодействия с программой через консоль.
