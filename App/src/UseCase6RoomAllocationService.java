import java.util.*;

class RoomAllocationService{
    private Set<String> allocatedRoomIds;
    private Map<String,Set<String>> assignedRoomsByType;

    public RoomAllocationService(){
        allocatedRoomIds=new HashSet<>();
        assignedRoomsByType=new HashMap<>();
    }

    public void allocateRoom(Reservation r,RoomInventory inventory){
        Map<String,Integer> a=inventory.getRoomAvailability();
        String type=r.getRoomType()+" Room";

        if(a.get(type)>0){
            String id=generateRoomId(r.getRoomType());

            allocatedRoomIds.add(id);

            assignedRoomsByType.putIfAbsent(r.getRoomType(),new HashSet<>());
            assignedRoomsByType.get(r.getRoomType()).add(id);

            inventory.updateAvailability(type,a.get(type)-1);

            System.out.println("Booking confirmed for Guest: "+r.getGuestName()+", Room ID: "+id);
        }
    }

    private String generateRoomId(String type){
        assignedRoomsByType.putIfAbsent(type,new HashSet<>());
        int n=assignedRoomsByType.get(type).size()+1;
        return type+"-"+n;
    }
}

public class UseCase6RoomAllocationService{
    public static void main(String[] args){
        System.out.println("Room Allocation Processing");

        BookingRequestQueue q=new BookingRequestQueue();
        q.addRequest(new Reservation("Abhi","Single"));
        q.addRequest(new Reservation("Subha","Single"));
        q.addRequest(new Reservation("Vanmathi","Suite"));

        RoomInventory inventory=new RoomInventory();
        RoomAllocationService s=new RoomAllocationService();

        while(q.hasPendingRequests()){
            s.allocateRoom(q.getNextRequest(),inventory);
        }
    }
}