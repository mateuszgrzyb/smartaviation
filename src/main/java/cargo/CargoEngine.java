package cargo;

import flight.FlightJSON;
import misc.JSONException;

import java.text.ParseException;
import java.util.Date;



public class CargoEngine {

    public CargoEngine(FlightJSON flightJSON, CargoJSON cargoJSON) {
        this.flightJSON = flightJSON;
        this.cargoJSON = cargoJSON;
    }

    public CargoData getCargoData(int flightNumber, Date date) throws ParseException, JSONException {
        int flightId = flightJSON.getFlightId(flightNumber, date);

        int cargoWeight = cargoJSON.getCargoWeight(flightId);
        int baggageWeight = cargoJSON.getBaggageWeight(flightId);

        return new CargoData(cargoWeight, baggageWeight, cargoWeight+baggageWeight);
    }


    private final FlightJSON flightJSON;
    private final CargoJSON cargoJSON;
}


