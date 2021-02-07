package pl.junit.task;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @ParameterizedTest
    @CsvSource({"1000,10000", "200,200", "0,0", "10000,1000000"})
    void unitShouldHaveSetStartCoordinatesMaxFuelAndMaxCargoWeight(int maxFuel, int maxCargoWeight) {
        //when
        Unit unit = new Unit(null, maxFuel, maxCargoWeight);

        //then
        assertAll(
                () -> assertThat(unit.getFuel(), equalTo(maxFuel)),
                () -> assertThat(unit.getLoad(), equalTo(0))
        );
    }

    @ParameterizedTest
    @CsvSource({"100,60", "80,80", "0,0", "10,10"})
    void moveXYAndEnoughtFuel(int x, int y) {
        //given
        int maxFuel = 160;
        Coordinates coordinates = new Coordinates(0,0);
        Unit unit = new Unit(coordinates,maxFuel,800);

        //when
        Coordinates newCoordinates = unit.move(x,y);

        //then
        assertAll(
                () -> assertThat(newCoordinates.getX(),equalTo(coordinates.getX()+x)),
                () -> assertThat(newCoordinates.getY(),equalTo(coordinates.getY()+y)),
                () -> assertThat(unit.getFuel(),equalTo(maxFuel-x-y)),
                () -> assertThat(newCoordinates,not(sameInstance(coordinates)))
        );
    }

    @ParameterizedTest
    @CsvSource({"100,60", "80,80", "51,50", "70,40"})
    void moveXYAndNotEnoughtFuel(int x, int y) {
        //given
        int maxFuel = 100;
        Coordinates coordinates = new Coordinates(0,0);
        Unit unit = new Unit(coordinates,maxFuel,800);

        //then
        assertThrows(IllegalStateException.class,() -> unit.move(x,y));
    }

    @RepeatedTest(50)
    void tankUpFuel() {
        //given
        Unit unit = new Unit(null,50,800);

        //when
        unit.tankUp();

        //then
       assertAll(
               () -> assertThat(unit.getFuel(),greaterThanOrEqualTo(50)),
               () -> assertThat(unit.getFuel(),lessThanOrEqualTo(50))
       );
    }

    @Test
    void loadCargo() {
        //given
        Cargo cargo1 = new Cargo("Pralka",70);
        Cargo cargo2 = new Cargo("Telewizor",35);
        Cargo cargo3 = new Cargo("Lodówka",100);
        Cargo cargo4 = new Cargo("Komoda",150);
        Unit unit = new Unit(null,100, 355);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.loadCargo(cargo4);

        //then
        assertThat(unit.getLoad(),equalTo(cargo1.getWeight()+cargo2.getWeight()+ cargo3.getWeight()+ cargo4.getWeight()));
    }

    @Test
    void loadCargoExceedMaxWeight() {
        //given
        Cargo cargo = new Cargo("Komoda",201);
        Unit unit = new Unit(null,100, 200);

        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));
    }

    @Test
    void unloadCargo() {
        //given
        Cargo cargo1 = new Cargo("Pralka",70);
        Cargo cargo2 = new Cargo("Telewizor",35);
        Cargo cargo3 = new Cargo("Lodówka",100);
        Cargo cargo4 = new Cargo("Komoda",150);
        Unit unit = new Unit(null,100, 355);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.loadCargo(cargo4);
        unit.unloadCargo(cargo1);

        //then
        assertThat(unit.getLoad(),equalTo(cargo2.getWeight()+ cargo3.getWeight()+ cargo4.getWeight()));
    }

    @Test
    void unloadAllCargo() {
        //given
        Cargo cargo1 = new Cargo("Pralka",70);
        Cargo cargo2 = new Cargo("Telewizor",35);
        Cargo cargo3 = new Cargo("Lodówka",100);
        Cargo cargo4 = new Cargo("Komoda",150);
        Unit unit = new Unit(null,100, 355);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.loadCargo(cargo4);
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(),equalTo(0));
    }
}