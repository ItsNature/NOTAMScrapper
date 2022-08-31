package hr.lyra.notam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString // TODO
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
    private long startTime;
    private long endTime;

    private long description;
}
