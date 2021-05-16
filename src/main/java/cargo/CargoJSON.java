package cargo;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import misc.JSON;
import misc.JSONException;
import misc.WeightConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class CargoJSON extends JSON {
    public CargoJSON(String resource) throws IOException {
        super(resource);
    }

    public CargoJSON(InputStream stream) throws IOException {
        super(stream);
    }

    private int getWeight(int flightId, String type) throws JSONException {
        int result = 0;
        for (JsonNode cargo : getArray()) {
            int jsonFlightId = cargo.path("flightId").asInt();
            if (jsonFlightId == flightId) {
                for (JsonNode baggage : cargo.path(type)) {
                    int weight = baggage.path("weight").asInt();
                    String weightUnit = baggage.path("weightUnit").asText();
                    if (weightUnit.equals("lb")) {
                        result += WeightConverter.convertLbToKg(weight);
                    } else {
                        result += weight;
                    }
                }
                return result;

            }
        }
        throw new JSONException(
            String.format("Cargo with flight id %d does not exists", flightId)
        );
    }

    public int getBaggageWeight(int flightId) throws JSONException {
        return getWeight(flightId, "baggage");
    }

    public int getCargoWeight(int flightId) throws JSONException {
        return getWeight(flightId, "flight");
    }


    public int getTotalBaggage(Set<Integer> outgoingFlightIds) {
        int totalPieces = 0;
        for (JsonNode cargo: getArray()) {
            int flightId = cargo.path("flightId").asInt();
            if (outgoingFlightIds.contains(flightId)) {
                JsonNode baggage = cargo.path("baggage");
                for (JsonNode baggagePart: baggage) {
                    totalPieces += baggagePart.path("pieces").asInt();
                }
            }
        }
        return totalPieces;
    }
}
