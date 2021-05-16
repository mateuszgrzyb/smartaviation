import flight.FlightData;
import flight.FlightEngine;
import flight.FlightJSON;
import cargo.CargoData;
import cargo.CargoEngine;
import cargo.CargoJSON;
import misc.ISODateFormat;
import misc.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Main {

    public Main() throws IOException {
        format = new ISODateFormat();

        FlightJSON flightJSON = new FlightJSON("flight.json", format);
        CargoJSON cargoJSON = new CargoJSON("cargo.json");

        flightEngine = new FlightEngine(cargoJSON, flightJSON);
        cargoEngine = new CargoEngine(flightJSON, cargoJSON);
    }

    public static void main(String[] args) throws IOException, ParseException {
        Main main = new Main();
        main.testFlight();
    }

    private void test0() throws IOException, ParseException, JSONException {

        Date date = format.parse("2015-12-20T07:32:31 -01:00");

        CargoData cargoData = cargoEngine.getCargoData(7509, format.parse("2015-12-20T07:32:31 -01:00"));
        FlightData flightData = flightEngine.getFlightData("YYT", date);
        System.out.println(cargoData);
        System.out.println(flightData);

    }

    private void testFlight() throws ParseException {

        List<String> departureIataCodes = new ArrayList<>() {{
            add("YYT");
            add("YYZ");
            add("SEA");
            add("YYT");
            add("ANC");
        }};

        List<String> arrivalIataCodes = new ArrayList<>() {{
            add("GDN");
            add("MIT");
            add("GDN");
            add("PPX");
            add("KRK");
        }};

        List<Date> dates = new ArrayList<>() {{
            add(format.parse("2015-12-20T07:32:31 -01:00"));
            add(format.parse("2020-04-28T04:00:37 -02:00"));
            add(format.parse("2015-03-08T12:12:40 -01:00"));
            add(format.parse("2018-05-06T07:17:19 -02:00"));
            add(format.parse("2015-04-08T06:14:39 -02:00"));
        }};

        for (int i = 0; i < 5; i++) {
            System.out.println(
                flightEngine.getFlightData(
                    departureIataCodes.get(i),
                    dates.get(i)
                )
            );
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(
                flightEngine.getFlightData(
                    arrivalIataCodes.get(i),
                    dates.get(i)
                )
            );
        }
    }

    private final DateFormat format;
    private final FlightEngine flightEngine;
    private final CargoEngine cargoEngine;
}