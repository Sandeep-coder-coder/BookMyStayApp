import java.util.*;

class CancellationService{
    private Stack<String> releasedRoomIds;
    private Map<String,String> map;

    public CancellationService(){
        releasedRoomIds=new Stack<>();
        map=new HashMap<>();
    }

    public void registerBooking(String id,String type){
        map.put(id,type);
    }

    public void cancelBooking(String id,RoomInventory inventory){
        if(!map.containsKey(id)) return;

        String type=map.get(id);
        String key=type+" Room";

        Map<String,Integer> a=inventory.getRoomAvailability();
        inventory.updateAvailability(key,a.get(key)+1);

        releasedRoomIds.push(id);
        map.remove(id);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: "+type);
    }

    public void showRollbackHistory(){
        System.out.println("\nRollback History (Most Recent First):");
        while(!releasedRoomIds.isEmpty()){
            System.out.println("Released Reservation ID: "+releasedRoomIds.pop());
        }
    }
}

public class UseCase10BookingCancellation{
    public static void main(String[] args){
        System.out.println("Booking Cancellation");

        RoomInventory inventory=new RoomInventory();
        CancellationService c=new CancellationService();

        String id="Single-1";

        c.registerBooking(id,"Single");

        c.cancelBooking(id,inventory);

        c.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: "+inventory.getRoomAvailability().get("Single Room"));
    }
}