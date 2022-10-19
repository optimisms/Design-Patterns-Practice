
public class FlightMonitor {
	
	public static void main(String[] args) {
	
		FlightFeed feed = new FlightFeed();

		StatusObserver status = new StatusObserver();
		feed.attach(status);

		DeltaObserver delta = new DeltaObserver(null);
		feed.attach(delta);

		feed.start();

	}
	
}