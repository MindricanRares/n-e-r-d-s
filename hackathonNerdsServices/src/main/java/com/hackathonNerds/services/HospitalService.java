package com.hackathonNerds.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathonNerds.common.HospitalResourcesResponse;
import com.hackathonNerds.common.HospitalResponse;
import com.hackathonNerds.entity.Hospital;
import com.hackathonNerds.entity.Need;
import com.hackathonNerds.repository.HospitalRepository;
import com.hackathonNerds.repository.NeedRepository;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private NeedRepository needRepository;

    public List<HospitalResponse> retrieveHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalResponse> hospRps =
                hospitals.stream().map(hosp -> new HospitalResponse(hosp.getId(), hosp.getName(), hosp.getCoord()))
                        .collect(Collectors.toList());
        return hospRps;
    }

    public Hospital retrieveHospitalById(Integer hospitalId) {
        return hospitalRepository.getOne(hospitalId);
    }

    public HospitalResourcesResponse retrieveHospitalNeeds(Integer hospitalId) {
        List<Need> needsList = needRepository.findHospitalNeeds(hospitalId);
        Hospital hospitals = hospitalRepository.getOne(hospitalId);

        HospitalResourcesResponse response = new HospitalResourcesResponse();
        response.setNeeds(needsList);
        response.setHospital(hospitals);

        return response;
    }
}
