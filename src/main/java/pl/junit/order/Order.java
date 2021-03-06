package pl.junit.order;

import pl.junit.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal){
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }

    public int getTotalOrderValue(){
        int sum = meals.stream().mapToInt(m->m.getPrice()*m.getQuantity()).sum();
        if(sum < 0){
            throw new IllegalStateException("Price limit exceeded");
        }
        return sum;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public void cleanOrder(){
        this.meals.clear();
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
