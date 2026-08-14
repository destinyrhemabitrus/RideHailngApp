package miniBolt;

public class Passenger {
    private String name;
    private String phoneNumber;
    private int passengerId;
    private double walletBalance;
    public Passenger(String name, double initialBallance,String phoneNumber, int passengerId) {
        String trimmedPhoneNumber = phoneNumber.trim();
        if(trimmedPhoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(trimmedPhoneNumber.length()!=3){
            throw new IllegalArgumentException("Phone number must be 3 characters");
        }
        this.name = name;
        this.walletBalance = initialBallance;
        this.phoneNumber = trimmedPhoneNumber;
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void deductWallet(double amount) {
        validateAmount(amount);
        walletBalance = walletBalance - amount;

    }

    private void validateAmount(double amount) {
        boolean amountIsGreaterThanWalletballance = amount > walletBalance;
        if(amountIsGreaterThanWalletballance) throw new IllegalArgumentException("insufficient funds");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
