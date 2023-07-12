package com.pfa.smartV.models;


import jakarta.persistence.*;

@Entity
@Table(
        name = "agent",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq2_email" ,columnNames = "email"),
                @UniqueConstraint(name = "uniq2_cin", columnNames = "CIN")
        }
)
public class Agent {

    @Id
    @SequenceGenerator(
            name = "agent_sequence",
            sequenceName = "agent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agent_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "fullName",
            nullable = false
    )
    private String fullName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;


    @Column(
            name = "tel",
            nullable = false
    )
    private String tel;

    @Column(
            name = "CIN",
            nullable = false
    )
    private String CIN;

    @ManyToOne
    private Station station;

    public Agent() {
    }

    public Agent(Long id, String fullName, String email, String tel, String CIN, Station station) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.tel = tel;
        this.CIN = CIN;
        this.station = station;
    }
    public Agent(String fullName, String email, String tel, String CIN, Station station) {
        this.fullName = fullName;
        this.email = email;
        this.tel = tel;
        this.CIN = CIN;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", CIN='" + CIN + '\'' +
                ", station=" + station +
                '}';
    }
}
