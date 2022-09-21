package hr.lyra.notam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Notam {

    private String id;
    private String fir;
    private String subject;
    private String status;

    private String traffic;
    private String purpose;
    private String scope;

    private String minimumAltitude;
    private String maximumAltitude;
    private String position;
    private String radius;
    private String icao;

    private String schedule;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    private List<String> description = new ArrayList<>();

    public String getInfo() {
        return String.join(" ", this.description);
    }

    @Override
    public String toString() {
        return String.format(
                "id=%s, fir=%s, subject=%s, status=%s, " + "traffic=%s, purpose=%s, scope=%s, minAlt=%s, " +
                "maxAlt=%s, pos=%s, radius=%s, icao=%s, " + "schedule=%s, start=%s, end=%s, info=%s",
            this.id, this.fir, this.subject, this.status, this.traffic, this.purpose, this.scope, this.minimumAltitude,
            this.maximumAltitude, this.position, this.radius, this.icao, this.schedule, this.startTime.toString(), this.endTime.toString(), this.getInfo()
        );
    }
}
