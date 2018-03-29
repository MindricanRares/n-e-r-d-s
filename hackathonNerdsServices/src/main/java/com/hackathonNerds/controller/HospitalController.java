package com.hackathonNerds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathonNerds.common.HospitalResourcesResponse;
import com.hackathonNerds.common.HospitalResponse;
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

    @RequestMapping(value = "/hospital/updateHsp", method = RequestMethod.PUT)
    public void updateHospitalResources(@RequestBody final HospitalResourcesResponse request) {

    }
}
