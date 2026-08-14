package miniBolt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RideTest {
    @Test
    public void getRideId_returnsOneTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertEquals(1,ride.getRideId());
    }

    @Test
    public void getDistanceInKm_returnsDistanceTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertEquals(100,ride.getDistanceInKm());
    }

    @Test
    public void getFair_returns5000Test(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertEquals(5000,ride.getFair());
    }


    @Test
    public void getPassenger_returnsPassengerTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertEquals(passenger,ride.getPassenger());
    }

    @Test
    public void getDriver_returnsDriverTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertEquals(driver,ride.getDriver());
    }

    @Test
    public void isCompleted_returnsFalseTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertFalse(ride.isCompleted());
    }

    @Test
    public void isCompleted_returnsFalse_driverIsAvailableReturnsFalseTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        Ride ride = new Ride(100,5000,passenger,driver,1);
        assertFalse(ride.isCompleted());
        assertFalse(driver.isAvailable());
    }

    @Test
    public void completeRide_isCompletedReturnsTrueTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        Ride ride = new Ride(100,5000,passenger,driver,1);
        ride.completeRide();
        assertTrue(ride.isCompleted());
    }

    @Test
    public void completeRide_isCompletedReturnsTrue_driverIsAvailableReturnsTrueTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        Ride ride = new Ride(100,5000,passenger,driver,1);
        ride.completeRide();
        assertTrue(ride.isCompleted());
        assertTrue(driver.isAvailable());
    }

    @Test
    public void completeRide_whenRideHasAlreadyBeenCompleted_throwsExceptionTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        Ride ride = new Ride(100,5000,passenger,driver,1);
        ride.completeRide();
        assertTrue(driver.isAvailable());
        assertThrows(IllegalStateException.class,()->ride.completeRide(),"Ride is already completed");

    }

    @Test
    public void ZeroOrNegativeDistanceInKm_throwsExceptionTest(){
        Passenger passenger = new Passenger("Rhema",10000, "345",1);
        Driver driver = new Driver("Richard","Toyota","123",1);
        driver.makeUnavailabe();
        assertThrows(IllegalArgumentException.class,()-> new Ride(-100,5000,passenger,driver,1),"Distance can not be Zero or negative");
    }


}

