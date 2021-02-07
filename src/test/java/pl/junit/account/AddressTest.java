package pl.junit.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Wiśniowa, 10","Baśniowa, 4","'Romka, Tomka i Atomka', 26"})
    void checkAddressCorrectness(String streetName, String houseNumber){
        assertThat(streetName,notNullValue());
        assertThat(streetName,containsString("a"));
        assertThat(houseNumber,notNullValue());
        assertThat(houseNumber.length(),lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void checkAddressCorrectnessFromFile(String streetName, String houseNumber){
        assertThat(streetName,notNullValue());
        assertThat(streetName,containsString("a"));
        assertThat(houseNumber,notNullValue());
        assertThat(houseNumber.length(),lessThan(8));
    }

}