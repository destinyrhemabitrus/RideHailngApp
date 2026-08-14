package miniBolt;

import java.util.Scanner;

public class RideApp {
    static RideService rideService = new RideService();

    public static void main(String[] args){
        gotoMenu();
    }

    public static void gotoMenu(){
        System.out.println("""
                === MiniBolt Taxi App ===
                1. Register Passenger
                2. Register Driver
                3. Book a Ride
                4. Complete Ride
                5. Exit
                """);
        Scanner inputCollector = new Scanner(System.in);
        int option = inputCollector.nextInt();
        switch (option){
            case 1:
                try{
                    registerPassenger(inputCollector);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
                gotoMenu();
                break;
            case 2:
                try{
                    registerDriver(inputCollector);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
                gotoMenu();
                break;
            case 3:
                try{
                    bookRide(inputCollector);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
                gotoMenu();
                break;
            case 4:
                try{
                    completeRide(inputCollector);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
                gotoMenu();
                break;
            case 5:
                System.out.println("Thank you for using our service!!");
                break;
            default:
                System.out.println("invalid option");
                gotoMenu();
                break;

        }

    }

    private static void completeRide(Scanner inputCollector) {
        System.out.println("Enter ride id");
        int id = inputCollector.nextInt();
        rideService.completeRide(id);
        System.out.println("Trip completed successfully! Driver is now available again.");
    }

    private static void bookRide(Scanner inputCollector) {
        System.out.println("Enter passenger id");
        int id = inputCollector.nextInt();

        System.out.println("Enter distance in kilometer");
        double distance = inputCollector.nextDouble();

        Ride ride = rideService.bookRide(id,distance);
        System.out.printf("Ride #%d booked successfully! Driver %s (%s) is on the way. Fare: ₦%.2f%n",ride.getRideId(),ride.getDriver().getName(),ride.getDriver().getCarModel(),ride.getFair());

    }

    private static void registerDriver(Scanner inputCollector) {
        inputCollector.nextLine();
        System.out.println("Enter Driver name");
        String name = inputCollector.nextLine();

        System.out.println("Enter car model ");
        String carModel = inputCollector.nextLine();

        System.out.println("Enter your three digit unique phone number");
        String phoneNumber = inputCollector.nextLine();
        Driver driver = rideService.registerDriver(name,carModel,phoneNumber);

        System.out.printf("Driver %s has been registered with ID:%d%n",driver.getName(),driver.getDriverId());
    }

    private static void registerPassenger(Scanner inputCollector) {
        inputCollector.nextLine();
        System.out.println("Enter passenger name");
        String name = inputCollector.nextLine();

        System.out.println("Enter initial ballance");
        double initialBallance = inputCollector.nextDouble();

        inputCollector.nextLine();
        System.out.println("Enter your three digit unique phone number");
        String phoneNumber = inputCollector.nextLine();
        Passenger passenger = rideService.registerPassenger(name,initialBallance,phoneNumber);

        System.out.printf("Passenger: %s has been registered with ID:%d%n",passenger.getName(),passenger.getPassengerId());
    }
}
