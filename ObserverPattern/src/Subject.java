import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void attach(Observer toAttach) {
        observers.add(toAttach);
    }

    public void notifyObservers(Flight flight)
    {
        for (Observer curr : observers) { curr.update(flight); }
    }
}
