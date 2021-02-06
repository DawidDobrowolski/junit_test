package pl.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder(){
        this.order = new Order();
    }

    @AfterEach
    void cleanOrder(){
        this.order.cleanOrder();
    }

    @Test
    void testAssertArraysEquals() {
        //given
        int[] intArray1 = {1, 2, 3};
        int[] intArray2 = {1, 2, 3};

        //then
        assertArrayEquals(intArray1, intArray2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreatingOrder() {

        //when
        List<Meal> meals = order.getMeals();

        //then
        assertThat(meals, empty());
        assertThat(meals.size(), equalTo(0));
        assertThat(meals, hasSize(0));
        assertThat(meals, emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal = new Meal(14, "Kebab na cienkim");

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Meal meal1 = new Meal(14, "Kebab na cienkim");
        Meal meal2 = new Meal(16, "Kebab na grubym");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.removeMealFromOrder(meal1);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), not(contains(meal1)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        //given
        Meal meal1 = new Meal(14, "Kebab na cienkim");
        Meal meal2 = new Meal(16, "Kebab na grubym");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals().get(0), equalTo(meal1));
        assertThat(order.getMeals().get(1), equalTo(meal2));
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));
    }

    @Test
    void testIfTwoMealListsAreTheSame() {
        //given
        Meal meal1 = new Meal(14, "Kebab na cienkim");
        Meal meal2 = new Meal(16, "Kebab na grubym");
        Meal meal3 = new Meal(18, "Kebab talerz");
        List<Meal> meals1 = Arrays.asList(meal1, meal2, meal3);
        List<Meal> meals2 = Arrays.asList(meal1, meal2, meal3);

        //then
        assertThat(meals1, equalTo(meals2));
    }

}