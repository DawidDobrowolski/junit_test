package pl.junit.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @ParameterizedTest
    @CsvSource({"1,2", "23,0", "100,0", "100,1", "50,50"})
    void checkCoordinatesInRange0To100(int x, int y) {
        //when
        Coordinates coordinates = new Coordinates(x, y);

        //then
        assertAll(
                () -> assertThat(coordinates.getX(), equalTo(x)),
                () -> assertThat(coordinates.getY(), equalTo(y))
        );
    }

    @ParameterizedTest
    @CsvSource({"1,-2", "23,101", "100,-1", "-1,100", "-50,150"})
    void checkCoordinatesOutOfRange0To100(int x, int y) {
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(x, y));
    }

    @Test
    void copyShouldReturnObject(){
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates newCoordinates = Coordinates.copy(coordinates,0,0);

        //then
        assertThat(newCoordinates,not(sameInstance(coordinates)));
        assertThat(newCoordinates,equalTo(coordinates));
    }

    @ParameterizedTest
    @CsvSource({"-10,2", "80,90", "55,-5", "0,0", "50,15"})
    void copyCoordinatesAndMoveWithXYValuesAndNotExceedRange(int x, int y) {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates newCoordinates = Coordinates.copy(coordinates,x,y);

        //then
        assertAll(
                () -> assertThat(newCoordinates.getX(),equalTo(coordinates.getX()+x)),
                () -> assertThat(newCoordinates.getY(),equalTo(coordinates.getX()+y))
        );

    }

    @ParameterizedTest
    @CsvSource({"-11,2", "80,91", "-55,-5", "-1,91", "91,-11"})
    void copyCoordinatesAndMoveWithXYValuesAndExceedRange(int x, int y) {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //then
        assertThrows(IllegalArgumentException.class, () -> Coordinates.copy(coordinates,x,y));
    }

}