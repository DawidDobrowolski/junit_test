package pl.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
        assertThat(discountedPrice,equalTo(16));

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
        assertThat(meal1,sameInstance(meal2));

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
        assertThat(meal1,not(sameInstance(meal2)));

        //assertJ test
        Assertions.assertThat(meal1).isNotSameAs(meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1 = new Meal(10,"pizza");
        Meal meal2 = new Meal(10,"pizza");

        //then
        assertEquals(meal1,meal2);

        //hamcrest test
        assertThat(meal1,equalTo(meal2));

        //assertJ test
        Assertions.assertThat(meal1).isEqualTo(meal2);
    }

}