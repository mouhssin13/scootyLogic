package com.pfa.smartV.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "Station",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_name" ,columnNames = "name")
        }
)
public class Station {

    @Id
    @SequenceGenerator(
            name = "station_sequence",
            sequenceName = "station_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "station_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "X",
            nullable = false
    )
    private double X;
    @Column(
            name = "Y",
            nullable = false
    )
    private double Y;

    @Column(
            name = "nombre_Trottinette",
            nullable = true
    )
    private int nbr_trottinette;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Trottinette> trottinettes;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Agent> agents;



    public Station() {

    }

    public Station(Long id, String name, double x, double y, int nbr_trottinette) {
        this.id = id;
        this.name = name;
        X = x;
        Y = y;
        this.nbr_trottinette = nbr_trottinette;
    }

    public Station(String name, double x, double y, int nbr_trottinette) {
        this.name = name;
        X = x;
        Y = y;
        this.nbr_trottinette = nbr_trottinette;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public int getNbr_trottinette() {
        return nbr_trottinette;
    }

    public void setNbr_trottinette(int nbr_trottinette) {
        this.nbr_trottinette = nbr_trottinette;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                ", nbr_trottinette=" + nbr_trottinette +
                '}';
    }
}
