package pl.junit.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UnitServiceTest {

    @Mock
    private CargoRepository cargoRepository;
    @Mock
    private UnitRepository unitRepository;
    @InjectMocks
    private UnitService unitService;

    @Test
    void addCargoByNameShouldLoadCargoToUnit() {
        //given
        Cargo cargo = new Cargo("Telewizor", 35);
        Unit unit = new Unit(new Coordinates(0, 0), 100, 100);
        given(cargoRepository.findCargoByName(any(String.class))).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "Telewizor");

        //then
        then(cargoRepository).should().findCargoByName("Telewizor");
        assertThat(unit.getCargo(), contains(cargo));
    }

    @Test
    void addNonExistingCargoShouldThrowException() {
        //given
        Unit unit = new Unit(new Coordinates(0, 0), 100, 100);
        given(cargoRepository.findCargoByName(any(String.class))).willReturn(Optional.empty());

        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "Test unit"));
    }

    @Test
    void getExistingUnitOnShouldReturnUnit() {
        //given
        Unit unit = new Unit(new Coordinates(0,0),100,100);
        given(unitRepository.getUnitByCoordinates(any(Coordinates.class))).willReturn(unit);

        //when
        Unit newUnit = unitService.getUnitOn(new Coordinates(0,0));

        //then
        assertThat(newUnit,sameInstance(unit));
    }

    @Test
    void getNotExistingUnitOnShouldThrowException() {
        //given
        given(unitRepository.getUnitByCoordinates(any(Coordinates.class))).willReturn(null);

        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(0,0)));
    }
}