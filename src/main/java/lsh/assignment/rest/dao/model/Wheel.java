package lsh.assignment.rest.dao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wheel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long size;

    private String manufacturer;

    private BigDecimal pressure;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;
}
