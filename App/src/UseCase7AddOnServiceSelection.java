import java.util.*;

class Service{
    private String serviceName;
    private double cost;

    public Service(String serviceName,double cost){
        this.serviceName=serviceName;
        this.cost=cost;
    }

    public String getServiceName(){
        return serviceName;
    }

    public double getCost(){
        return cost;
    }
}

class AddOnServiceManager{
    private Map<String,List<Service>> m;

    public AddOnServiceManager(){
        m=new HashMap<>();
    }

    public void addService(String id,Service s){
        m.putIfAbsent(id,new ArrayList<>());
        m.get(id).add(s);
    }

    public double calculateTotalServiceCost(String id){
        double sum=0;
        if(m.containsKey(id)){
            for(Service s:m.get(id)) sum+=s.getCost();
        }
        return sum;
    }
}

public class UseCase7AddOnServiceSelection{
    public static void main(String[] args){
        System.out.println("Add-On Service Selection");

        String id="Single-1";

        AddOnServiceManager manager=new AddOnServiceManager();

        Service s1=new Service("Breakfast",500.0);
        Service s2=new Service("Spa",1000.0);

        manager.addService(id,s1);
        manager.addService(id,s2);

        double total=manager.calculateTotalServiceCost(id);

        System.out.println("Reservation ID: "+id);
        System.out.println("Total Add-On Cost: "+total);
    }
}