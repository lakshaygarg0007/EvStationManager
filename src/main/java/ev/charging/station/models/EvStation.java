package ev.charging.station.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ev_station")
@Getter
@Setter
public class EvStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    private String stationName;

    //Here we Will Store Images in System Directory using Multipart and In Database we will Store path of file
    private String stationImages;

    private Double stationPricing;

    private String stationAddress;
}
