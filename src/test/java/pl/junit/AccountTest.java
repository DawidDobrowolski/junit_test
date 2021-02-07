package pl.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Tag("majorTests")
    @Test
    void newAccountShouldBeNotActiveAfterCreation(){
        //given
        //when
        Account account = new Account();

        //then
        assertFalse(account.isActive());

        //hamcrest test
        assertThat(account.isActive(),equalTo(false));
        assertThat(account.isActive(),is(false));

        //assertJ test
        Assertions.assertThat(account.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account account = new Account();

        //when
        account.activate();

        //then
        assertTrue(account.isActive());

        //hamcrest test
        assertThat(account.isActive(),equalTo(true));

        //assertJ test
        Assertions.assertThat(account.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);

        //hamcrest test
        assertThat(address,nullValue());

        //assertJ test
        Assertions.assertThat(address).isNull();
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Aleje Jerozolimskie","162");
        Account account =  new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultDeliveryAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultDeliveryAddress);

        //hamcrest test
        assertThat(defaultDeliveryAddress,notNullValue());

        //assertJ test
        Assertions.assertThat(address).isNotNull();
    }

    @Tag("majorTests")
    @RepeatedTest(10)
    void newAccountWithNotNullDeliveryAddressShouldBeActive(){
        //given
        Address address = new Address("Długa","48");

        //when
        Account account = new Account(address);

        //then
        //sprawdzane tylko jeśli spełniony warunek
        assumingThat(address != null,
                () -> assertTrue(account.isActive())
        );
    }

    @Test
    void setWrongEmailShouldThrowException(){
        //given
        Account account = new Account();

        //when + then
        assertThrows(IllegalArgumentException.class,() -> account.setEmail("wrong email"));
    }

    @Test
    void validEmailShouldBeSet(){
        //given
        Account account = new Account();

        //when
        account.setEmail("test@test.pl");

        //when + then
        assertThat(account.getEmail(),equalTo("test@test.pl"));
    }

}