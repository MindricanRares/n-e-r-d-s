package com.hackathonNerds.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Hospital")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id = null;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "gpscoord")
    private String coord;

    @NotNull
    @Column(name = "totalbeds")
    private Integer totalBeds;

    @NotNull
    @Column(name = "reservedbeds")
    private Integer reservedbeds;

    @NotNull
    @Column(name = "occupiedbeds")
    private Integer occupiedbeds;

    @OneToMany(mappedBy = "id")
    private List<Need> needlist;

    @OneToMany(mappedBy = "id")
    private List<Have> haveList;

    @OneToMany(mappedBy = "id")
    private List<Booking> bookinglist;

    public Hospital() {

    }

    public Hospital(Integer id, @NotNull String name, @NotNull String adress, @NotNull String coord,
            @NotNull Integer totalBeds, @NotNull Integer reservedbeds, @NotNull Integer occupiedbeds,
            List<Need> needList, List<Have> haveList, List<Booking> bookingList) {
        super();
        this.id = id;
        this.name = name;
        this.address = adress;
        this.coord = coord;
        this.totalBeds = totalBeds;
        this.reservedbeds = reservedbeds;
        this.occupiedbeds = occupiedbeds;
        this.needlist = needList;
        this.haveList = haveList;
        this.bookinglist = bookingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public Integer getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(Integer totalBeds) {
        this.totalBeds = totalBeds;
    }

    public Integer getReservedBeds() {
        return reservedbeds;
    }

    public void setReservedBeds(Integer rezervedBeds) {
        this.reservedbeds = rezervedBeds;
    }

    public Integer getOccupiedBeds() {
        return occupiedbeds;
    }

    public void setOccupiedBeds(Integer occupiedBeds) {
        this.occupiedbeds = occupiedBeds;
    }

    @JsonIgnore
    public List<Need> getNeedlist() {
        return needlist;
    }

    public void setNeedlist(List<Need> needlist) {
        this.needlist = needlist;
    }

    @JsonIgnore
    public List<Have> getHaveList() {
        return haveList;
    }

    public void setHaveList(List<Have> haveList) {
        this.haveList = haveList;
    }

}
