package hr.lyra.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Airport information pulled from:
 * https://en.wikipedia.org/wiki/List_of_airports_in_Croatia
 */

@Getter
@RequiredArgsConstructor
public enum Airport {

    BRAC(AirportType.CIVIL, "LDSB", "BWK", "Brač Airport", 43.285833D, 16.679722D),
    DUBROVNIK(AirportType.CIVIL, "LDDU", "DBV", "Dubrovnik Airport", 42.562238D, 18.265759D),
    MALI_LOSINJ(AirportType.CIVIL, "LDLO", "LSZ", "Lošinj Airport", 44.565504D, 14.397987D),
    OSIJEK(AirportType.CIVIL, "LDOS", "OSI", "Osijek Airport", 45.4589D, 18.823295D),
    PULA(AirportType.CIVIL, "LDPL", "PUY", "Pula Airport", 44.892685D, 13.91662D),
    RIJEKA(AirportType.CIVIL, "LDRI", "RJK", "Rijeka Airport", 45.219891D, 14.567489D),
    SPLIT(AirportType.CIVIL, "LDSP", "SPU", "Split Airport", 43.538509D, 16.29853D),
    ZADAR(AirportType.CIVIL, "LDZD", "ZAD", "Zadar Airport", 44.094443D, 15.352874D),
    ZAGREB(AirportType.CIVIL, "LDZA", "ZAG", "Zagreb Airport", 45.738356D, 16.060673D),

    CAKOVEC(AirportType.SPORT, "LDVC", "Sport Airport Čakovec"),
    CEPIN(AirportType.SPORT, "LDOC", "Sport Airport Čepin"),
    GROBNIK(AirportType.SPORT, "LDRG", "Sport Airport Grobničko Polje"),
    HVAR(AirportType.SPORT, "LDSH", "Sport Airport Hvar"),
    IVANIC_GRAD(AirportType.SPORT, null, "Sport Airport Ivanić"),
    KOPRIVNICA(AirportType.SPORT, "LDVK", "Sport Airport Koprivnica"),
    LUCKO(AirportType.SPORT, "LDZL", "Lučko Airfield"),
    OTOCAC(AirportType.SPORT, "LDRO", "Sport Airport Otočac"),
    SINJ(AirportType.SPORT, "LDSS", "Sport Airport Sinj"),
    SLAVONSKI_BROD(AirportType.SPORT, "LDOR", "Sport Airport Slavonski Brod"),
    VARAZDIN(AirportType.SPORT, "LDVA", "Sport Airport Varaždin"),
    VRSAR(AirportType.SPORT, "LDPV", "Sport Airport Vrsar"),
    ZABOK(AirportType.SPORT, "LDZK", "Sport Airport Zabok"),

    DIVULJE(AirportType.MILITARY, "LDHD", null),
    UDBINA(AirportType.MILITARY, "LDZU", "Udbina Airport"),
    ZATON(AirportType.MILITARY, null, "Šepurine Training Base");

    private final AirportType type;

    private final String icao;
    private final String iata;
    private final String name;

    private final double latitude;
    private final double longitude;

    Airport(AirportType type, String icao, String name) {
        this.type = type;
        this.icao = icao;
        this.name = name;

        this.iata = null;
        this.latitude = Integer.MIN_VALUE;
        this.longitude = Integer.MIN_VALUE;
    }

    public String getIcao() {
        return this.icao == null ? "Unknown" : this.icao;
    }

    public String getIata() {
        return this.iata == null ? "Unknown" : this.iata;
    }

    public String getName() {
        return this.name == null ? "Unknown" : this.name;
    }

    public static Airport getByIcao(String icao) {
        return ICAO_TO_AIRPORT.get(icao);
    }

    private static final Map<String, Airport> ICAO_TO_AIRPORT;

    static {
        ICAO_TO_AIRPORT = new HashMap<>();

        for(var airport : Airport.values()) {
            ICAO_TO_AIRPORT.put(airport.getIcao(), airport);
        }
    }
}
