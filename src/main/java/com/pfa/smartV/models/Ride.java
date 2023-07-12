package com.pfa.smartV.models;


import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;


@Entity
@Table(
        name = "ride"
)
public class Ride {
    @Id
    @SequenceGenerator(
            name = "ride_sequence",
            sequenceName = "ride_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ride_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "dateDep",
            nullable = false
    )
    private LocalTime dateDep;
    @Column(
            name = "dateArr",
            nullable = true
    )
    private LocalTime dateArr;

    @Column(
            name = "price",
            nullable = true
    )
    private float price;

    @ManyToOne
    private Trottinette trottinette;

    @ManyToOne
    private User user;
    @Transient
    private int duree;
    public Ride() {
    }

    public Ride(Long id, LocalTime dateDep, LocalTime dateArr, float price, Trottinette trottinette, User user) {
        this.id = id;
        this.dateDep = dateDep;
        this.dateArr = dateArr;
        this.price = price;
        this.trottinette = trottinette;
        this.user = user;
    }
    public Ride( LocalTime dateDep, LocalTime dateArr, float price, Trottinette trottinette, User user) {
        this.dateDep = dateDep;
        this.dateArr = dateArr;
        this.price = price;
        this.trottinette = trottinette;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getDateDep() {
        return dateDep;
    }

    public void setDateDep(LocalTime dateDep) {
        this.dateDep = dateDep;
    }

    public LocalTime getDateArr() {
        return dateArr;
    }

    public void setDateArr(LocalTime dateArr) {
        this.dateArr = dateArr;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Trottinette getTrottinette() {
        return trottinette;
    }

    public void setTrottinette(Trottinette trottinette) {
        this.trottinette = trottinette;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDuree() {
        LocalTime now = LocalTime.now();
        if(dateArr == null){
            this.duree = (int) Duration.between(this.dateDep,now).toMinutes();
            return duree;
        }else{
            this.duree = (int) Duration.between(this.dateDep,this.dateArr).toMinutes();
            return duree;
        }

    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", dateDep=" + dateDep +
                ", dateArr=" + dateArr +
                ", price=" + price +
                ", trottinette=" + trottinette +
                ", user=" + user +
                ", duree=" + duree +
                '}';
    }
}
