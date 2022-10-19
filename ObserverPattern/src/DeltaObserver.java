public class DeltaObserver implements Observer {
    private Flight oldFlight;

    public DeltaObserver(Flight firstFlight)
    {
        oldFlight = firstFlight;
    }

    @Override
    public void update(Flight newFlight) {
        if (oldFlight == null || newFlight == null)
        {
            oldFlight = newFlight;
            return;
        }

        //print statement
        System.out.println("Change in longitude: " + (newFlight.longitude - oldFlight.longitude));
        System.out.println("Change in latitude: " + (newFlight.latitude - oldFlight.latitude));
        System.out.println("Change in velocity: " + (newFlight.velocity - oldFlight.velocity));
        System.out.println("Change in altitude: " + (newFlight.geo_altitude - oldFlight.geo_altitude));
        oldFlight = newFlight;
    }
}
