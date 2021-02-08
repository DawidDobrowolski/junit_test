package pl.junit.task2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnitTest {

    @Test
    void newCreatedUnitHasFuelEqualToMaFuel() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);

        //when
        Unit unit = new Unit(coordinates, 0, 0);

        //then
        assertThat(unit.getFuel(), equalTo(0));

    }

    @Test
    void moveShouldCreateNewCoordinatesObject() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 100);

        ///when
        Coordinates newCoordinates = unit.move(10, 10);

        //then
        assertThat(newCoordinates, not(sameInstance(coordinates)));
    }

    @Test
    void moveShouldDecreaseFuelLevel() {
        //given
        int startFuel = 100;
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, startFuel, 100);

        ///when
        Coordinates newCoordinates = unit.move(10, 10);

        //then
        assertThat(unit.getFuel(), equalTo(startFuel - 20));
    }

    @Test
    void moveMoreThenFuelShouldThrowException() {
        //given
        int startFuel = 100;
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, startFuel, 100);

        //then
        assertThrows(IllegalStateException.class, () -> unit.move(50, 51));
    }

    @Test
    void tankUpCannotExceedMaxFuel() {
        //given
        int startFuel = 100;
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, startFuel, 100);

        //when
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), lessThanOrEqualTo(startFuel));
    }

    @Test
    void loadCargoShouldIncreaseTotalCargo() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);

        //then
        assertThat(unit.getCargo(),hasSize(3));
    }

    @Test
    void loadCargoShouldIncreaseCurrentCargoWeight() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);

        //then
        assertThat(unit.getLoad(),equalTo(205));
    }

    @Test
    void unloadCargoShouldDecreaseTotalCargo() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.unloadCargo(cargo1);

        //then
        assertThat(unit.getCargo(),hasSize(2));
    }

    @Test
    void unloadCargoShouldDecreaseCargoWeight() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.unloadCargo(cargo1);

        //then
        assertThat(unit.getLoad(),equalTo(170));
    }

    @Test
    void unloadAllCargoShouldEmptyAllCargoItems() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.unloadAllCargo();

        //then
        assertThat(unit.getCargo(),hasSize(0));
    }

    @Test
    void unloadAllCargoShouldEmptyCargoWeight() {
        //given
        Cargo cargo1 = new Cargo("Telewizor", 35);
        Cargo cargo2 = new Cargo("Pralka", 70);
        Cargo cargo3 = new Cargo("Lodówka", 100);
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 250);

        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        unit.loadCargo(cargo3);
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(),equalTo(0));
    }


}