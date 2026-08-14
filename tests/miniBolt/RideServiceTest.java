package miniBolt;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RideServiceTest {
    @Test
    public void passengerCountIsZeroTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getPassengerCount());
    }

    @Test
    public void registerOnePassenger_passengerCountIsOneTest(){
        RideService rideService = new RideService();
        rideService.registerPassenger("johnson",10000,"123");
        assertEquals(1,rideService.getPassengerCount());
    }

    @Test
    public void registerTwoPassengers_passengerCountIsTwoTest(){
        RideService rideService = new RideService();
        rideService.registerPassenger("johnson",10000,"123");
        rideService.registerPassenger("johnwig",10000,"125");
        assertEquals(2,rideService.getPassengerCount());
    }

    @Test
    public void registerOnePassengers_findPassengerByIdReturnsPassengerTest(){
        RideService rideService = new RideService();
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        assertEquals(passenger,rideService.findPassengerById(1));
    }

    @Test
    public void registerTwoPassengers_findSecondPassengerByIdReturnsSecondPassengerTest(){
        RideService rideService = new RideService();
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("Faith",10000,"128");
        assertEquals(passengerTwo,rideService.findPassengerById(2));
    }

    @Test
    public void driverCountIsZeroTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
    }

    @Test
    public void registerOneDriver_driverCountIsOneTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
        rideService.registerDriver("Balogun","Toyota","234");
        assertEquals(1,rideService.getDriverCount());
    }

    @Test
    public void registerTwoDrivers_driverCountIsTwoTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
        rideService.registerDriver("Balogun","Toyota","234");
        rideService.registerDriver("Ben","Kia","204");
        assertEquals(2,rideService.getDriverCount());
    }

    @Test
    public void registerOneDriver_findDriverByIdReturnsDriverTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
        Driver driverOne = rideService.registerDriver("Balogun","Toyota","234");
        assertEquals(driverOne,rideService.findDriverById(1));
    }

    @Test
    public void registerTwoDrivers_findSecondDriverByIdReturnsSecondDriverTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
        Driver driverOne = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Efe","Toyota","934");
        assertEquals(driverTwo,rideService.findDriverById(2));
    }

    @Test
    public void bookRide_getRideCountIsOneTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Ride ride = rideService.bookRide(passenger.getPassengerId(),40);
        assertEquals(1,rideService.getRideCount());
    }

    // to look review after finishing unhappy path
    @Test
    public void bookTwoRides_getRideCountIsTwoTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Boniface","Toyota","034");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("john",10000,"103");
        Ride rideOne = rideService.bookRide(passenger.getPassengerId(),40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(),50);
        assertEquals(2,rideService.getRideCount());
    }

    @Test
    public void bookRide_findRideById_returnsRideTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Ride ride = rideService.bookRide(passenger.getPassengerId(),40);
        assertEquals(ride,rideService.findRideById(ride.getRideId()));
    }

    @Test
    public void bookTwoRides_getActiveRideCountIsTwoTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Boniface","Toyota","034");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("john",10000,"103");
        Ride rideOne = rideService.bookRide(passenger.getPassengerId(),40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(),50);
        assertEquals(2,rideService.getActiveRideCount());
    }


    @Test
    public void bookRide_completeRide_getActiveRideCountIs0_rideIsCompletedReturnsTrueTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Ride ride = rideService.bookRide(passenger.getPassengerId(),40);
        assertFalse(ride.isCompleted());
        rideService.completeRide(ride.getRideId());
        assertEquals(0,rideService.getActiveRideCount());
        assertTrue(ride.isCompleted());
    }

    @Test
    public void bookTwoRides_completeFirstRide_getActiveRideCountIsOne_firstRideIsCompletedReturnsTrue_secondRideIsCompleteReturnsFalseTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Boniface","Toyota","034");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("john",10000,"103");
        Ride rideOne = rideService.bookRide(passenger.getPassengerId(),40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(),50);
        assertFalse(rideOne.isCompleted());
        assertFalse(rideTwo.isCompleted());
        assertEquals(2,rideService.getActiveRideCount());
        rideService.completeRide(rideOne.getRideId());
        assertEquals(1,rideService.getActiveRideCount());
        assertTrue(rideOne.isCompleted());
        assertFalse(rideTwo.isCompleted());
    }


    @Test
    public void bookTwoRides_driverOneAndDriverTwoIsAvailbleReturnsFalse_completeTwoRides_driverOneAndDriverTwoIsAvailbleReturnsTrueTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Boniface","Toyota","034");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("john",10000,"103");
        Ride rideOne = rideService.bookRide(passenger.getPassengerId(),40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(),50);
        assertFalse(driver.isAvailable());
        assertFalse(driverTwo.isAvailable());
        assertEquals(2,rideService.getActiveRideCount());
        rideService.completeRide(rideOne.getRideId());
        rideService.completeRide(rideTwo.getRideId());
        assertTrue(driver.isAvailable());
        assertTrue(driverTwo.isAvailable());
        assertEquals(0,rideService.getActiveRideCount());

    }

    //faluire tests

    @Test
    public void registerTwoPassengersWithThesamePhoneNumber_throwsException_getPassengerCountReturnsOneTest(){
        RideService rideService = new RideService();
        rideService.registerPassenger("johnson",10000,"123");
        assertThrows(IllegalArgumentException.class,()-> rideService.registerPassenger("johnson",10000,"123"),"Passenger already Exists");
        assertEquals(1,rideService.getPassengerCount());
    }

    @Test
    public void registerTwoDriversWithThesamePhoneNumber_throwsException_getDriverCountIsOneTest(){
        RideService rideService = new RideService();
        assertEquals(0,rideService.getDriverCount());
        rideService.registerDriver("Balogun","Toyota","234");
        assertThrows(IllegalArgumentException.class,()-> rideService.registerDriver("Balogun","Toyota","234"),"Driver already Exists");
        assertEquals(1,rideService.getDriverCount());
    }


    @Test
    public void bookRideWithNoRegisteredDriver_throwsExceptionTest(){
        RideService rideService = new RideService();
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        assertThrows(NoSuchElementException.class,()->rideService.bookRide(passenger.getPassengerId(),40),"No available driver at the moment");
    }

    @Test
    public void registerTwoDriversThreePassengers_bookThreeRides_thirdBookingthrowsExceptionTest(){
        RideService rideService = new RideService();
        Driver driverOne = rideService.registerDriver("Balogun","Toyota","234");
        Driver driverTwo = rideService.registerDriver("Boniface","Toyota","034");
        Passenger passengerOne = rideService.registerPassenger("johnson",10000,"123");
        Passenger passengerTwo = rideService.registerPassenger("john",10000,"103");
        Passenger passengerThree = rideService.registerPassenger("Cain",10000,"193");
        Ride rideOne = rideService.bookRide(passengerOne.getPassengerId(),40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(),50);
        assertThrows(NoSuchElementException.class,()->rideService.bookRide(passengerThree.getPassengerId(),50),"No available driver at the moment");
    }

    @Test
    public void registerOnePassengerWithPaswordLessThanThreeCharacters_throwsExceptionTest(){
        RideService rideService = new RideService();
        assertThrows(IllegalArgumentException.class,()->rideService.registerPassenger("johnson",10000,"23"),"Phone number must be 3 characters");
    }

    @Test
    public void registerOnePassengerWithBlankPassword_throwsExceptionTest(){
        RideService rideService = new RideService();
        assertThrows(IllegalArgumentException.class,()->rideService.registerPassenger("johnson",10000,""),"Phone number cannot be empty");
    }

    @Test
    public void registerOneDriverWithPaswordLessThanThreeCharacters_throwsExceptionTest(){
        RideService rideService = new RideService();
        assertThrows(IllegalArgumentException.class,()->rideService.registerDriver("Balogun","Toyota","23"),"Phone number must be 3 characters");
    }

    @Test
    public void registerOneDriverWithBlankPassword_throwsExceptionTest(){
        RideService rideService = new RideService();
        assertThrows(IllegalArgumentException.class,()->rideService.registerDriver("Balogun","Toyota",""),"Phone number cannot be empty");
    }

    @Test
    public void bookRideWithNegativeDistance_throwsExceptionTest(){
        RideService rideService = new RideService();
        Driver driver = rideService.registerDriver("Balogun","Toyota","234");
        Passenger passenger = rideService.registerPassenger("johnson",10000,"123");
        assertThrows(IllegalArgumentException.class,()-> rideService.bookRide(passenger.getPassengerId(),-40),"Distance can not be Zero or negative");
    }

    @Test
    public void getRides_returnsAllAvailableRidesTest() {
        RideService rideService = new RideService();
        Driver driverOne = rideService.registerDriver("Balogun", "Toyota", "234");
        Driver driverTwo = rideService.registerDriver("Boniface", "Toyota", "034");
        Passenger passengerOne = rideService.registerPassenger("johnson", 10000, "123");
        Passenger passengerTwo = rideService.registerPassenger("john", 10000, "103");
        Passenger passengerThree = rideService.registerPassenger("Cain", 10000, "193");
        Ride rideOne = rideService.bookRide(passengerOne.getPassengerId(), 40);
        Ride rideTwo = rideService.bookRide(passengerTwo.getPassengerId(), 50);
        ArrayList<Ride> rides = rideService.getRides();
        assertEquals(2, rides.size());
        assertTrue(rides.contains(rideOne));
        assertTrue(rides.contains(rideTwo));
    }


    @Test
    public void getRidesWhenNORideExists_throwsExceptionTest() {
        RideService rideService = new RideService();
        Driver driverOne = rideService.registerDriver("Balogun", "Toyota", "234");
        Driver driverTwo = rideService.registerDriver("Boniface", "Toyota", "034");
        Passenger passengerOne = rideService.registerPassenger("johnson", 10000, "123");
        Passenger passengerTwo = rideService.registerPassenger("john", 10000, "103");
        Passenger passengerThree = rideService.registerPassenger("Cain", 10000, "193");
        assertThrows(IllegalArgumentException.class,()-> rideService.getRides(),"No rides found");
    }

    @Test
    public void completeRideWithInvalideRideId_throwsExceptionTest() {
        RideService rideService = new RideService();
        assertThrows(IllegalArgumentException.class,()-> rideService.completeRide(1),"ride not found");
    }

    @Test
    public void bookRideWithUnregisteredPassenger_throwsExceptionTest() {
        RideService rideService = new RideService();
        Driver driverOne = rideService.registerDriver("Balogun", "Toyota", "234");
        Driver driverTwo = rideService.registerDriver("Boniface", "Toyota", "034");
        Passenger passengerOne = rideService.registerPassenger("johnson", 10000, "123");
        assertThrows(IllegalArgumentException.class,()-> rideService.bookRide(2, 40),"Passenger does not exists");
    }


}
