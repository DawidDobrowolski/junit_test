package pl.junit;

import java.util.ArrayList;
import java.util.List;

public class Order {

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
