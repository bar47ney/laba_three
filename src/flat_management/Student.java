package flat_management;

/**
 * Created by Сергей on 14.05.2019.
 */
public class Student extends Human {
    public Student() {
    }

    public String study() {
        return "Study HARD!!!";
    }

    public String getInfo() {
        return super.getInfo() + this.study();
    }
}