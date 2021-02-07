package pl.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(20);

        //when
        int discountedPrice = meal.getDiscountedPrice(4);

        //then
        assertEquals(16, discountedPrice);

        //hamcrest test
        assertThat(discountedPrice, equalTo(16));

        //assertJ test
        Assertions.assertThat(discountedPrice).isEqualTo(16);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(14);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);

        //hamcrest test
        assertThat(meal1, sameInstance(meal2));

        //assertJ test
        Assertions.assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(14);
        Meal meal2 = new Meal(14);

        //then
        assertNotSame(meal1, meal2);

        //hamcrest test
        assertThat(meal1, not(sameInstance(meal2)));

        //assertJ test
        Assertions.assertThat(meal1).isNotSameAs(meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pizza");

        //then
        assertEquals(meal1, meal2);

        //hamcrest test
        assertThat(meal1, equalTo(meal2));

        //assertJ test
        Assertions.assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThenThePrice() {
        //given
        Meal meal = new Meal(8, "2 x frytki");

        //when + then
        assertThrows(IllegalStateException.class, () -> meal.getDiscountedPrice(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 12, 16, 19})
    void mealPriceShouldBeLowerThen20(int mealPrice) {
        assertThat(mealPrice, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealWithNameAndPrice")
    void checkIfNameContainsBurgerAndPriceGreaterThan12(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(12));
    }

    private static Stream<Arguments> createMealWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 12),
                Arguments.of("Cheeseburger", 14)
        );
    }

    @ParameterizedTest
    @MethodSource("createNameCakes")
    void checkIfCakeNameEndsWithCake(String cakeName) {
        assertThat(cakeName, endsWith("cake"));
    }


    private static Stream<String> createNameCakes() {
        List<String> cakeNames = List.of("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {8, 12, 13})
    void mealPriceShouldBeLowerThen14(int mealPrice) {
        if (mealPrice > 12) {
            throw new IllegalArgumentException();
        }
        assertThat(mealPrice, lessThan(14));
    }

    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2, "Kebab"));
        order.addMealToOrder(new Meal(6, 3, "Frytki"));
        order.addMealToOrder(new Meal(12, 4, "Pizza"));

        Collection<DynamicTest> dynamicTestCollection = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(50));
            };
            String name = "Test numer: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTestCollection.add(dynamicTest);
        }
        return dynamicTestCollection;
    }

    private static int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

}