public class StatusObserver implements Observer{
    public StatusObserver() {}

    @Override
    public void update(Flight flight) {
        if (flight == null) { System.out.println("Flight is over."); }
        System.out.println(flight.toString());
    }
}
