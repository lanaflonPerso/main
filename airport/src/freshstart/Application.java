package freshstart;

import freshstart.domain.aircraft.AircraftFactory;
import freshstart.domain.aircraft.Plane;
import freshstart.domain.airport.Airport;
import freshstart.domain.airport.AirportFactory;
import freshstart.domain.common.Actions;
import freshstart.domain.location.Route;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by aleonets on 21.08.2017.
 */
public class Application {
    private static List<Airport> knownAirports;
    private static List<Plane> knownPlanes;
    public static void main(String[] args){
        knownAirports = createAirports();

        /*Wait for airports initialization*/
        Actions.STANDBY_5SEC.doAction();

        knownPlanes = createPlanes();
    }

    private static List<Airport> createAirports(){
        List<Airport> airports = new ArrayList<>();

        airports.add(AirportFactory.getInstanceAirport("Borispol"));
        airports.add(AirportFactory.getInstanceAirport("Kyiv"));
        airports.add(AirportFactory.getInstanceAirport("Hitrow"));

        for (Airport airport : airports){
            airport.setLinkedAirports(airports);
        }

        return airports;
    }

    public static Airport getRandomKnownAirport(){
        Airport result = null;

        int randInt = new Random().nextInt(knownAirports.size());

        if (randInt >= 0){
            result = knownAirports.get(randInt);
        }

        return  result;
    }

    private static List<Plane> createPlanes(){
        List<Plane> planes = new ArrayList<>();
        Plane newPlane = AircraftFactory.getInstancePlane("APEK-005", knownAirports.get(0));
        newPlane.setRoute(new Route().setDestination(knownAirports.get(0), knownAirports.get(1), new Date()));
        planes.add(newPlane);

        return planes;
    }
}
