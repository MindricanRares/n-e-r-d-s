package com.hackathonNerds.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathonNerds.common.HospitalResourcesResponse;
import com.hackathonNerds.common.HospitalResponse;
import com.hackathonNerds.common.ReservationData;
import com.hackathonNerds.entity.Booking;
import com.hackathonNerds.entity.Have;
import com.hackathonNerds.entity.Hospital;
import com.hackathonNerds.entity.Need;
import com.hackathonNerds.entity.Person;
import com.hackathonNerds.repository.BookingRepository;
import com.hackathonNerds.repository.HaveRepository;
import com.hackathonNerds.repository.HospitalRepository;
import com.hackathonNerds.repository.NeedRepository;
import com.hackathonNerds.repository.PersonRepository;

@Service
public class HospitalService {
    private static final String ANONIM = "anonim";

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private NeedRepository needRepository;

    @Autowired
    private HaveRepository haveRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PersonRepository personRepository;

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
        List<Have> havesList = haveRepository.findHospitalHaves(hospitalId);
        Hospital hospitals = hospitalRepository.getOne(hospitalId);

        HospitalResourcesResponse response = new HospitalResourcesResponse();
        response.setNeeds(needsList);
        response.setHospital(hospitals);
        response.setHaves(havesList);
        return response;
    }

    public List<HospitalResourcesResponse> retrieveHospitalsNeeds() {
        List<HospitalResourcesResponse> response = new ArrayList<>();
        List<Hospital> hospitals = hospitalRepository.findAll();
        for (Hospital h : hospitals) {
            List<Need> needsList = needRepository.findHospitalNeeds(h.getId());
            List<Have> havesList = haveRepository.findHospitalHaves(h.getId());
            HospitalResourcesResponse rsp = new HospitalResourcesResponse();
            rsp.setHospital(h);
            rsp.setNeeds(needsList);
            rsp.setHaves(havesList);
            response.add(rsp);
        }
        return response;
    }

    public void updateHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public void reserveHospitalBeds(ReservationData request) {
        Person p = null;
        if (request.getName() == null || ANONIM.equals(request.getName())) {
            p = personRepository.findPersonByName(ANONIM).get(0);
        } else {
            p = new Person();
            p.setName(request.getName());
            p = personRepository.save(p);
        }
        Hospital hospital = hospitalRepository.getOne(request.getHospitalId());
        Booking b = new Booking();
        b.setHospital(hospital);
        b.setNrBeds(request.getBedNumber());
        b.setPerson(p);
        bookingRepository.save(b);
    }
}
