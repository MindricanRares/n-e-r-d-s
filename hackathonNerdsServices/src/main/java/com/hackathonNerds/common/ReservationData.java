package com.hackathonNerds.common;

import java.io.Serializable;

public class ReservationData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer hospitalId;
    private Integer bedNumber;
    private String name = "anonim";

    public ReservationData(Integer hospitalId, Integer bedNumber, String name) {
        super();
        this.hospitalId = hospitalId;
        this.bedNumber = bedNumber;
        this.name = name;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
