package com.hackathonNerds.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathonNerds.common.HospitalResourcesResponse;
import com.hackathonNerds.common.HospitalResponse;
import com.hackathonNerds.common.ReservationData;
import com.hackathonNerds.entity.Hospital;
import com.hackathonNerds.services.HospitalService;

@RestController
@RequestMapping(value = "/hospitals")
public class HospitalController extends BaseController {

    @Autowired
    HospitalService hospitalService;

    @RequestMapping(value = "/hospitalList", method = RequestMethod.GET)
    @ResponseBody
    public List<HospitalResponse> retrieveHospitalsList() {
        return hospitalService.retrieveHospitals();
    }

    @RequestMapping(value = "/hospital/{clientId}", method = RequestMethod.GET)
    @ResponseBody
    public Hospital retrieveHospital(@PathVariable("clientId") Integer clientId) {
        return hospitalService.retrieveHospitalById(clientId);
    }

    @RequestMapping(value = "/hospital/{clientId}/resources", method = RequestMethod.GET)
    @ResponseBody
    public HospitalResourcesResponse retrieveHospitalResources(@PathVariable("clientId") Integer clientId) {
        return hospitalService.retrieveHospitalNeeds(clientId);
    }

    @RequestMapping(value = "/hospital/updateHsp/{clientId}", method = RequestMethod.GET)
    public void updateHospitalResources(@PathVariable("clientId") Integer clientId,
            @RequestParam(required = false, value = "reservedBeds") Integer reservedBeds,
            @RequestParam(required = false, value = "totalBeds") Integer totalBeds,
            @RequestParam(required = false, value = "occupiedBeds") Integer occupiedBeds) {
        hospitalService.updateHospital(clientId, reservedBeds, totalBeds, occupiedBeds);
    }

    @RequestMapping(value = "/hospital/{clientId}/reserve/{nrBeds}", method = RequestMethod.GET)
    public void reserveHospitalBed(@PathVariable("clientId") Integer clientId, @PathVariable("nrBeds") Integer nrBeds,
            @RequestParam(required = false, value = "name") String name) throws SQLException {
        ReservationData request = new ReservationData(clientId, nrBeds, name);
        hospitalService.reserveHospitalBeds(request);
    }
}
