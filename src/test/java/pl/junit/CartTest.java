package pl.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.junit.order.Order;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;


@DisplayName("Test cases for Cart")
class CartTest {

//    @Disabled
    @Test
    @DisplayName("Cart is able to process 1 000 orders in 100 ms")
    void simulateLargeOrderTest() {
        //given
        Cart cart = new Cart();

        //when + then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Cart cart = new Cart();
        Order order = new Order();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(),allOf( //wyświetla pierwszy niespełniony warunek
                notNullValue(),
                hasSize(1),
                not(emptyCollectionOf(Order.class)),
                is(not(empty()))
        ));

        assertAll( //wyświetla wszystkie niespełnione warunki
                () -> assertThat(cart.getOrders(),notNullValue()),
                () -> assertThat(cart.getOrders(),hasSize(1)),
                () -> assertThat(cart.getOrders(),not(emptyCollectionOf(Order.class))),
                () -> assertThat(cart.getOrders(),is(not(empty()))),
                () -> assertThat(cart.getOrders().get(0).getMeals(),empty())
        );

    }

}