import java.util.*;

class InvalidBookingException extends Exception{
    public InvalidBookingException(String message){
        super(message);
    }
}

class ReservationValidator{
    public void validate(String guestName,String roomType,RoomInventory inventory) throws InvalidBookingException{
        if(guestName==null || guestName.trim().isEmpty()){
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if(!(roomType.equals("Single")||roomType.equals("Double")||roomType.equals("Suite"))){
            throw new InvalidBookingException("Invalid room type selected.");
        }

        Map<String,Integer> a=inventory.getRoomAvailability();
        String key=roomType+" Room";

        if(!a.containsKey(key) || a.get(key)<=0){
            throw new InvalidBookingException("Room not available.");
        }
    }
}

public class UseCase9ErrorHandlingValidation{
    public static void main(String[] args){
        System.out.println("Booking Validation");

        Scanner sc=new Scanner(System.in);

        RoomInventory inventory=new RoomInventory();
        ReservationValidator v=new ReservationValidator();
        BookingRequestQueue q=new BookingRequestQueue();

        try{
            System.out.print("Enter guest name: ");
            String name=sc.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String type=sc.nextLine();

            v.validate(name,type,inventory);

            q.addRequest(new Reservation(name,type));

            System.out.println("Booking request added successfully.");

        }catch(InvalidBookingException e){
            System.out.println("Booking failed: "+e.getMessage());
        }finally{
            sc.close();
        }
    }
}