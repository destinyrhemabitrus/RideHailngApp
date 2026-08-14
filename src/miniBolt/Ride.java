package miniBolt;

public class Ride {
    private int rideId;
    private double distanceInKm;
    private double fare;
    private Passenger passenger;
    private Driver driver;
    private boolean isCompleted;

    public Ride(double distanceInkm, double fare, Passenger passenger,Driver driver, int rideId) {
        if(distanceInkm<=0){
            throw new IllegalArgumentException("Distance can not be Zero or negative");
        }
        this.distanceInKm = distanceInkm;
        this.rideId = rideId;
        this.fare = fare;
        this.passenger = passenger;
        this.driver= driver;
        this.isCompleted = false;
    }

    public int getRideId() {
        return rideId;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public double getFair() {
        return fare;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeRide() {
        if(isCompleted) throw new IllegalStateException("Ride is already completed");
        isCompleted = true;
        driver.makeAvailable();
    }
}
