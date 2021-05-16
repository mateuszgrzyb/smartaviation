package flight;

import cargo.CargoJSON;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class FlightEngine {

    public FlightEngine(CargoJSON cargoJSON, FlightJSON flightJSON) {
        this.cargoJSON = cargoJSON;
        this.flightJSON = flightJSON;
    }

    public FlightData getFlightData(String iataCode, Date date) throws ParseException {

        Set<Integer> incomingFlightIds = flightJSON.getIncomingFlightIds(iataCode, date);
        Set<Integer> outgoingFlightIds = flightJSON.getOutgoingFlightIds(iataCode, date);

        int flightsFrom = outgoingFlightIds.size();
        int flightsTo = incomingFlightIds.size();
        int totalBaggageArriving = cargoJSON.getTotalBaggage(incomingFlightIds);
        int totalBaggageDeparting = cargoJSON.getTotalBaggage(outgoingFlightIds);

        return new FlightData(
                flightsFrom,
                flightsTo,
                totalBaggageArriving,
                totalBaggageDeparting
        );
    }

    private final CargoJSON cargoJSON;
    private final FlightJSON flightJSON;
}
