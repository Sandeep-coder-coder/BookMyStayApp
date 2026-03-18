import java.util.*;

class RoomInventory{
    private Map<String,Integer> roomAvailability;

    public RoomInventory(){
        roomAvailability=new HashMap<>();
        roomAvailability.put("Single Room",5);
        roomAvailability.put("Double Room",3);
        roomAvailability.put("Suite Room",2);
    }

    public Map<String,Integer> getRoomAvailability(){
        return roomAvailability;
    }
}

public class UseCase3InventorySetup{
    public static void main(String[] args){
        System.out.println("Hotel Room Inventory Status\n");

        RoomInventory inventory=new RoomInventory();
        Map<String,Integer> a=inventory.getRoomAvailability();

        Room s=new SingleRoom();
        Room d=new DoubleRoom();
        Room su=new SuiteRoom();

        System.out.println("Single Room:");
        s.displayRoomDetails();
        System.out.println("Available Rooms: "+a.get("Single Room")+"\n");

        System.out.println("Double Room:");
        d.displayRoomDetails();
        System.out.println("Available Rooms: "+a.get("Double Room")+"\n");

        System.out.println("Suite Room:");
        su.displayRoomDetails();
        System.out.println("Available Rooms: "+a.get("Suite Room"));
    }
}