package pl.junit.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CargoTest {


    @ParameterizedTest
    @CsvSource({"Telewizory,2000","Lodówki,9000","Zeszyty z Kubusiem Puchatkiem,140"})
    void cargoShouldHaveSetNameAndWeight(String name, int weight){
        //given when
        Cargo cargo = new Cargo(name,weight);

        //then
        assertThat(cargo.getWeight(),equalTo(weight));
    }

}