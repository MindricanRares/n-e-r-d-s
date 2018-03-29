package com.hackathonNerds.common;

import java.util.List;

import com.hackathonNerds.entity.Have;
import com.hackathonNerds.entity.Hospital;
import com.hackathonNerds.entity.Need;

public class HospitalResourcesResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private Hospital hospital;
    private List<Need> needs;
    private List<Have> haves;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Need> getNeeds() {
        return needs;
    }

    public void setNeeds(List<Need> needs) {
        this.needs = needs;
    }

    public List<Have> getHaves() {
        return haves;
    }

    public void setHaves(List<Have> haves) {
        this.haves = haves;
    }
}
