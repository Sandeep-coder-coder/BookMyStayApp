import java.util.*;

class RoomSearchService{
    public void searchAvailableRooms(RoomInventory inventory,Room singleRoom,Room doubleRoom,Room suiteRoom){
        Map<String,Integer> a=inventory.getRoomAvailability();

        if(a.get("Single Room")>0){
            System.out.println("Single Room:");
            singleRoom.displayRoomDetails();
            System.out.println("Available: "+a.get("Single Room")+"\n");
        }

        if(a.get("Double Room")>0){
            System.out.println("Double Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: "+a.get("Double Room")+"\n");
        }

        if(a.get("Suite Room")>0){
            System.out.println("Suite Room:");
            suiteRoom.displayRoomDetails();
            System.out.println("Available: "+a.get("Suite Room"));
        }
    }
}

public class UseCase4RoomSearch{
    public static void main(String[] args){
        System.out.println("Room Search\n");

        RoomInventory inventory=new RoomInventory();

        Room s=new SingleRoom();
        Room d=new DoubleRoom();
        Room su=new SuiteRoom();

        RoomSearchService service=new RoomSearchService();
        service.searchAvailableRooms(inventory,s,d,su);
    }
}