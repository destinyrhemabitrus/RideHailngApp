package miniBolt;

public class Driver {
    private String name;
    private String carModel;
    private int driverId;
    private boolean isAvailable;
    private String phoneNumber;

    public Driver(String name, String carModel,String phoneNumber, int driverId) {
        String trimmedPhoneNumber = phoneNumber.trim();
        if(trimmedPhoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone number can not be empty");
        }
        if(trimmedPhoneNumber.length()!=3){
            throw new IllegalArgumentException("Phone number must be 3 characters");
        }
        this.name = name;
        this.carModel = carModel;
        this.driverId = driverId;
        this.isAvailable = true;
        this.phoneNumber = trimmedPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getDriverId() {
        return driverId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void makeUnavailabe() {
        isAvailable = false;
    }

    public void makeAvailable() {
        isAvailable = true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
