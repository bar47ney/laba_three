package flat_management;

/**
 * Created by Сергей on 14.05.2019.
 */
public class Worker extends Human {
    public Worker() {
    }

    public String work() {
        return "DO HARD!!!";
    }

    public String getInfo() {
        return super.getInfo() + this.work();
    }
}
