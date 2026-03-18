import java.util.*;

class Reservation{
    private String guestName;
    private String roomType;

    public Reservation(String guestName,String roomType){
        this.guestName=guestName;
        this.roomType=roomType;
    }

    public String getGuestName(){
        return guestName;
    }

    public String getRoomType(){
        return roomType;
    }
}

class BookingRequestQueue{
    private Queue<Reservation> q;

    public BookingRequestQueue(){
        q=new LinkedList<>();
    }

    public void addRequest(Reservation r){
        q.offer(r);
    }

    public Reservation getNextRequest(){
        return q.poll();
    }

    public boolean hasPendingRequests(){
        return !q.isEmpty();
    }
}

public class UseCase5BookingRequestQueue{
    public static void main(String[] args){
        System.out.println("Booking Request Queue");

        BookingRequestQueue b=new BookingRequestQueue();

        Reservation r1=new Reservation("Abhi","Single");
        Reservation r2=new Reservation("Subha","Double");
        Reservation r3=new Reservation("Vanmathi","Suite");

        b.addRequest(r1);
        b.addRequest(r2);
        b.addRequest(r3);

        while(b.hasPendingRequests()){
            Reservation r=b.getNextRequest();
            System.out.println("Processing booking for Guest: "+r.getGuestName()+", Room Type: "+r.getRoomType());
        }
    }
}