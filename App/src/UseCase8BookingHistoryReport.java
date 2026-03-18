import java.util.*;

class BookingHistory{
    private List<Reservation> list;

    public BookingHistory(){
        list=new ArrayList<>();
    }

    public void addReservation(Reservation r){
        list.add(r);
    }

    public List<Reservation> getConfirmedReservations(){
        return list;
    }
}

class BookingReportService{
    public void generateReport(BookingHistory h){
        System.out.println("\nBooking History Report");
        for(Reservation r:h.getConfirmedReservations()){
            System.out.println("Guest: "+r.getGuestName()+", Room Type: "+r.getRoomType());
        }
    }
}

public class UseCase8BookingHistoryReport{
    public static void main(String[] args){
        System.out.println("Booking History and Reporting");

        BookingHistory h=new BookingHistory();

        h.addReservation(new Reservation("Abhi","Single"));
        h.addReservation(new Reservation("Subha","Double"));
        h.addReservation(new Reservation("Vanmathi","Suite"));

        BookingReportService s=new BookingReportService();
        s.generateReport(h);
    }
}