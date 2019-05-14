package flat_management;

/**
 * Created by Сергей on 14.05.2019.
 */
public abstract class Human {
    private String firstName;
    private String lastName;
    private Address address;
    private Flat flat;

    public Human() {
    }

    public void moveIn() {
        this.setAddress(new Address());
    }

    public void moveOut() {
        this.setAddress((Address)null);
        this.setFlat((Flat)null);
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getInfo() {
        return this.firstName + " " + this.lastName + " ";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Flat getFlat() {
        return this.flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
