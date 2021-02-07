package pl.junit.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.junit.order.Order;
import pl.junit.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTestWithAnnotation {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CartHandler cartHandler;
    @Captor
    private ArgumentCaptor<Cart> captor;

    @Test
    void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldSendToReject() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldSendToRejectWithArgumentMatcher() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));

        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false, true, false, true);

        //them
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //them
        assertThrows(IllegalStateException.class, () -> cartHandler.canHandleCart(cart));
    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        then(cartHandler).should().sendToPrepare(captor.capture());
        assertThat(captor.getValue().getOrders(), hasSize(1));
        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        given(cartHandler.canHandleCart(cart)).willReturn(true);
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(cartResult.getOrders(), hasSize(1));
        assertThat(cartResult.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void ShouldAnswerWhenProceedCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        // 1 sposób (2 zapisy)
        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            cart.clearCart();
            return true;
        }).when(cartHandler).canHandleCart(cart);

        //when
        Cart cartResult = cartService.processCart(cart);

        //them
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(cartResult.getOrders(),hasSize(0));

    }
}
