package flight;

public class FlightData {
    public FlightData(
            int flightsFrom,
            int flightsTo,
            int totalBaggageArriving,
            int totalBaggageDeparting
    ) {
        this.flightsFrom = flightsFrom;
        this.flightsTo = flightsTo;
        this.totalBaggageArriving = totalBaggageArriving;
        this.totalBaggageDeparting = totalBaggageDeparting;
    }

    @Override
    public String toString() {
        return "FlightData{" +
                "flightsFrom=" + flightsFrom +
                ", flightsTo=" + flightsTo +
                ", totalBaggageArriving=" + totalBaggageArriving +
                ", totalBaggageDeparting=" + totalBaggageDeparting +
                '}';
    }

    public int flightsFrom;
    public int flightsTo;
    public int totalBaggageArriving;
    public int totalBaggageDeparting;
}
