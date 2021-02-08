package pl.junit.task2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;

class CargoRepositoryTest {

    @Test
    void afterAddingCargoItShouldCanBeFindByName() {
        //given
        Cargo cargo = new Cargo("Telewizor",26);
        CargoRepository cargoRepository = new CargoRepository();

        //when
        cargoRepository.addCargo(cargo);
        Cargo newCargo = cargoRepository.findCargoByName("Telewizor").get();

        //then
        assertThat(newCargo,sameInstance(cargo));
    }

    @Test
    void afterRemovingCargoItShouldNotBeFindByName() {
        //given
        Cargo cargo = new Cargo("Telewizor",26);
        CargoRepository cargoRepository = new CargoRepository();

        //when
        cargoRepository.addCargo(cargo);
        cargoRepository.removeCargo(cargo);
        boolean isCargo = cargoRepository.findCargoByName("Telewizor").isPresent();

        //then
        assertThat(isCargo,equalTo(false));
    }

    @Test
    void removeCargo() {
    }

    @Test
    void findCargoByName() {
    }
}