package flight;

import com.fasterxml.jackson.databind.JsonNode;
import misc.DateComparator;
import misc.JSON;
import misc.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightJSON extends JSON {
    public FlightJSON(String resource, DateFormat format) throws IOException {
        super(resource);
        this.format = format;
    }

    public FlightJSON(InputStream stream, DateFormat format) throws IOException {
        super(stream);
        this.format = format;
    }

    public int getFlightId(int flightNumber, Date date) throws ParseException, JSONException {
        for (JsonNode flight: getArray()) {
            int jsonFlightNumber = flight.path("flightNumber").asInt();
            Date jsonDate = format.parse(flight.path("departureDate").asText());
            if (jsonFlightNumber == flightNumber && jsonDate.equals(date)) {
                return flight.path("flightId").asInt();
            }
        }
        throw new JSONException(
            String.format(
                "Flight with number %d at date %s not found",
                flightNumber,
                date.toString()
            )
        );
    }

    private final DateFormat format;

    public DateFormat getFormat() {
        return format;
    }

    private Set<Integer> getFlightIds(String iataCode, Date date, String type) throws ParseException {
        Set<Integer> flightIds = new HashSet<>();
        for (JsonNode flight: getArray()) {
            String jsonIATACode = flight.path(type).asText();
            Date jsonDate = format.parse(flight.path("departureDate").asText());
            if (jsonIATACode.equals(iataCode) && DateComparator.equalDates(jsonDate, date)) {
                flightIds.add(flight.path("flightId").asInt());
            }
        }
        return flightIds;
    }

    public Set<Integer> getOutgoingFlightIds(String iataCode, Date date) throws ParseException {
        return getFlightIds(iataCode, date, "departureAirportIATACode");
    }

    public Set<Integer> getIncomingFlightIds(String iataCode, Date date) throws ParseException {
        return getFlightIds(iataCode, date, "arrivalAirportIATACode");
    }

}

