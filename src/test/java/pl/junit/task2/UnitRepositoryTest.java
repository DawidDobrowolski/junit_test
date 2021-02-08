package pl.junit.task2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;

class UnitRepositoryTest {

    @Test
    void addedUnitShouldBeFoundByCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 100);
        UnitRepository unitRepository = new UnitRepository();

        //when
        unitRepository.addUnit(unit);
        Unit newUnit = unitRepository.getUnitByCoordinates(coordinates);

        //then
        assertThat(newUnit, sameInstance(unit));
    }

    @Test
    void removedUnitShouldNotBeFoundByCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 100);
        UnitRepository unitRepository = new UnitRepository();

        //when
        unitRepository.addUnit(unit);
        unitRepository.removeUnit(unit);
        Unit newUnit = unitRepository.getUnitByCoordinates(coordinates);

        //then
        assertThat(newUnit, nullValue());
    }


    @Test
    void removedByCoordinatesUnitShouldNotBeFoundByCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        Unit unit = new Unit(coordinates, 100, 100);
        UnitRepository unitRepository = new UnitRepository();

        //when
        unitRepository.addUnit(unit);
        unitRepository.removeUnit(coordinates);
        Unit newUnit = unitRepository.getUnitByCoordinates(coordinates);

        //then
        assertThat(newUnit, nullValue());
    }

}
