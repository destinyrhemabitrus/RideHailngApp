package miniBolt;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RideService {
    private int passengerCount;
    private int driverCount;
    private int rideCount;
    private double ratePerKm = 500;
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    private ArrayList<Driver> drivers = new ArrayList<Driver>();
    private ArrayList<Ride> rides = new ArrayList<Ride>();

    public int getPassengerCount() {
        return passengerCount;
    }

    public Passenger registerPassenger(String name, double initialBallance , String phoneNumber) {
        Passenger passengerFound = findPassengerByPhoneNumber(phoneNumber);
        if(!(passengerFound == null)){
            throw new IllegalArgumentException("Passenger already Exists");
        }
        Passenger passenger = new Passenger(name,initialBallance,phoneNumber,++passengerCount);
        passengers.add(passenger);
        return passenger;
    }

    public Passenger findPassengerById(int passengerId) {
        for(Passenger passenger : passengers){
            if(passenger.getPassengerId()==passengerId){
                return passenger;
            }
        }
        return null;
    }

    public Passenger findPassengerByPhoneNumber(String phoneNumber) {
        for(Passenger passenger : passengers){
            if(passenger.getPhoneNumber().equals(phoneNumber)){
                return passenger;
            }
        }
        return null;
    }

    public Driver findDriverByPhoneNumber(String phoneNumber) {
        for(Driver driver : drivers){
            if(driver.getPhoneNumber().equals(phoneNumber)){
                return driver;
            }
        }
        return null;
    }


    public int getDriverCount() {
        return driverCount;
    }

    public Driver registerDriver(String name, String carModel, String phoneNumber) {
        Driver driverFound = findDriverByPhoneNumber(phoneNumber);
        if(!(driverFound==null)){
            throw new IllegalArgumentException("Driver already exists");
        }
        Driver driver = new Driver(name,carModel,phoneNumber,++driverCount);
        drivers.add(driver);
        return driver;
    }

    public Driver findDriverById(int driverId) {
        for(Driver driver : drivers){
            if(driver.getDriverId()==driverId){
                return driver;
            }
        }
        return null;
    }

    public Ride bookRide(int passengerId, double distanceInKm) {
        Passenger passenger = findPassengerById(passengerId);
        if(passenger == null){
            throw new IllegalArgumentException("Passenger does not exists");
        }
        Driver driver = findAvailableDriver();
        if(driver==null){
            throw new NoSuchElementException("No available driver at the moment");
        }
        driver.makeUnavailabe();
        double fare = distanceInKm * ratePerKm;
        Ride ride = new Ride(distanceInKm,fare,passenger,driver,++rideCount);
        rides.add(ride);
        return ride;
    }

    public int getRideCount() {
        return rideCount;
    }

    public Ride findRideById(int rideId) {
        for(Ride ride : rides){
            if(ride.getRideId() == rideId){
                return ride;
            }
        }
        return null;
    }

    public Driver findAvailableDriver() {
        for(Driver driver : drivers){
            if(driver.isAvailable() == true){
                return driver;
            }
        }
        return null;
    }

    public int getActiveRideCount() {
        int count = 0;
        for(Ride ride : rides){
            if(ride.isCompleted()==false){
                count = count + 1;
            }
        }
        return count;
    }

    public void completeRide(int rideId) {
        Ride ride = findRideById(rideId);
        if(ride == null){
            throw new IllegalArgumentException("ride not found");
        }
        ride.completeRide();
    }

    public ArrayList<Ride> getRides() {
        if(rides.isEmpty()){
            throw new IllegalArgumentException("No rides found");
        }
        return rides;
    }
}
