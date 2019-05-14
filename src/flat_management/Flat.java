package flat_management; /**
 * Created by Сергей on 14.05.2019.
 */
import java.util.ArrayList;
import java.util.ListIterator;

public class Flat {
    private Address address = new Address();
    private Room room;
    private ArrayList<Human> tenants = new ArrayList();

    public Flat() {
    }

    public ArrayList<Human> getTenants() {
        return this.tenants;
    }

    public void acceptTenant(Human human) {
        this.tenants.add(human);
    }

    public void evictTenant(ListIterator<Human> tenantIter) {
        tenantIter.remove();
    }

    public void allocateRoom() {
        this.setRoom(new Room());
    }

    public void vacateRoom() {
        this.setRoom((Room)null);
        this.tenants.clear();
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(String street, int house, int flatNumber) {
        this.address.setFlatNumber(flatNumber);
        this.address.setHouse(house);
        this.address.setStreet(street);
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
