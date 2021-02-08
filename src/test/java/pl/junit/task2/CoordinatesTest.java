package pl.junit.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {

    @Test
    void copyCoordinatesShouldCreateNewObject() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates newCoordinates = Coordinates.copy(coordinates,20,20);

        //then
        assertThat(newCoordinates,not(sameInstance(coordinates)));
    }
    
    @Test
    void copyCoordinatesShouldThrowExceptionWhenXYOutOfRange(){
        //given
        Coordinates coordinates = new Coordinates(10,80);

        //then
        assertThrows(IllegalArgumentException.class,() -> Coordinates.copy(coordinates,91,21));
    }

    @Test
    void copyCoordinatesShouldChangeXYValues(){
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates newCoordinates = Coordinates.copy(coordinates,20,20);

        //then
        assertAll(
                () -> assertThat(newCoordinates.getX(),equalTo(coordinates.getX()+20)),
                () -> assertThat(newCoordinates.getY(),equalTo(coordinates.getY()+20))
        );
    }

    @ParameterizedTest
    @CsvSource({"-1,-1","101,101","-1,101","101,-1"})
    void createCoordinatesWithXYOutOfRangeShouldThrowException(int x, int y){
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(x,y));
    }


}