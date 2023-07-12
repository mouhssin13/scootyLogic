package com.pfa.smartV.models;


import com.pfa.smartV.models.Station;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name="trottinette"
)
public class Trottinette {

    @Id
    @SequenceGenerator(
            name = "trottinette_sequence",
            sequenceName = "trottinette_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trottinette_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "x",
            nullable = true
    )
    private double x;

    @Column(
            name = "y",
            nullable = true
    )
    private double y;

    @ManyToOne
    private Station station;

    @OneToMany(mappedBy = "trottinette", cascade = CascadeType.ALL)
    private List<Ride> rides;

    public Trottinette() {
    }

    public Trottinette(Long id, double x, double y, Station station) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.station = station;
    }

    public Trottinette(double x, double y, Station station) {
        this.x = x;
        this.y = y;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
