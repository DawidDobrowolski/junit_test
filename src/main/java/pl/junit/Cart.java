package pl.junit;

import pl.junit.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    public void addOrderToCart(Order order){
        this.orders.add(order);
    }

    public void clearCart(){
        this.orders.clear();
    }

    public void simulateLargeOrder(){
        for (int i = 0; i < 1_000; i++) {
            Meal meal = new Meal(i%10,"Danie dnia nr. " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Number of orders in cart: " + this.orders.size());
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
