package miniBolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassengerTest {

    @Test
    public void getName_returnsNameTest() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        assertEquals("Rhema", passenger.getName());
    }

    @Test
    public void getMemberId_returnsIdTest() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        assertEquals(1, passenger.getPassengerId());
    }

    @Test
    public void getPhoneNumber_returnsNumberTest() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        assertEquals("124", passenger.getPhoneNumber());
    }


    @Test
    public void phoneNumberMustBeGreaterThanTwoCharactersButNotgreaterThanThreeCharacterTest(){
        assertThrows(IllegalArgumentException.class,()-> new Passenger("Rhema",2000,"23",1),"Phone number must be 3 characters");
    }

    @Test
    public void phoneNumberCanNotBeBlankTest(){
        assertThrows(IllegalArgumentException.class,()-> new Passenger("Rhema",2000,"",1),"Phone number cannot be empty");
    }


    @Test
    public void getWalletBalance_returns2000Test() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        assertEquals(2000, passenger.getWalletBalance());
    }

    @Test
    public void deductWalletWith400_getWalletBalanceReturns1600Test() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        passenger.deductWallet(400);
        assertEquals(1600, passenger.getWalletBalance());
    }

    @Test
    public void deductAmountGreaterThanWalletBalance_throwsExceptionTest() {
        Passenger passenger = new Passenger("Rhema",2000,"124",1);
        assertThrows(IllegalArgumentException.class, ()-> passenger.deductWallet(2500),"Insufficient Ballance");
    }

}
