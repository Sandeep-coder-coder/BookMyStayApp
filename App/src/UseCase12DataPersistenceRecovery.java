import java.util.*;

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        String filePath = "inventory.txt";

        // LOAD existing data
        service.loadInventory(inventory, filePath);

        // DISPLAY current inventory
        System.out.println("\nCurrent Inventory:");

        Map<String, Integer> a = inventory.getRoomAvailability();

        System.out.println("Single: " + a.get("Single Room"));
        System.out.println("Double: " + a.get("Double Room"));
        System.out.println("Suite: " + a.get("Suite Room"));

        // SAVE data
        service.saveInventory(inventory, filePath);
    }
}