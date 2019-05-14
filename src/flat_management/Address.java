package flat_management;

/**
 * Created by Сергей on 14.05.2019.
 */
public class Address {
    private String street;
    private int house;
    private int flatNumber;
    private Flat flat;

    public Address() {
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatNamber() {
        return Integer.toString(this.flatNumber);
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getHouse() {
        return Integer.toString(this.house);
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public Flat getFlat() {
        return this.flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
