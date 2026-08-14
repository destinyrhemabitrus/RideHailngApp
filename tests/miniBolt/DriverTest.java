package miniBolt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    @Test
    public void getName_returnsNameTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        assertEquals("Richard",driver.getName());
    }
    @Test
    public void phoneNumberMustBeGreaterThanTwoCharactersButNotgreaterThanThreeCharacterTest(){
        assertThrows(IllegalArgumentException.class,()-> new Driver("Richard","Toyota","13",1),"Phone number must be 3 characters");
    }

    @Test
    public void phoneNumberCanNotBeBlankTest(){
        assertThrows(IllegalArgumentException.class,()-> new Driver("Richard","Toyota","",1),"Phone cannot be empty");
    }


    @Test
    public void getCarModel_returnsModelTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        assertEquals("Toyota",driver.getCarModel());
    }

    @Test
    public void getDriverId_returnsOneTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        assertEquals(1,driver.getDriverId());
    }

    @Test
    public void getPhoneNumber_returnsPhoneNumberTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        assertEquals("123",driver.getPhoneNumber());
    }

    @Test
    public void isAvailable_returnsTrueTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        assertTrue(driver.isAvailable());
    }

    @Test
    public void makeUnavailable_isAvailableReturnsFalseTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        assertFalse(driver.isAvailable());
    }

    @Test
    public void makeUnavailable_makeAvailable_isAvailableReturnsTrueTest(){
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        assertFalse(driver.isAvailable());
        driver.makeAvailable();
        assertTrue(driver.isAvailable());
    }

}