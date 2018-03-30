package com.hackathonNerds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathonNerds.common.HospitalResourcesResponse;
import com.hackathonNerds.services.HospitalService;

@RestController
@RequestMapping(value = "/person")
public class UserController extends BaseController {

    @Autowired
    HospitalService hospitalService;

    @RequestMapping(value = "/hospital/resources", method = RequestMethod.GET)
    @ResponseBody
    public List<HospitalResourcesResponse> retrieveHospitalResource() {
        return hospitalService.retrieveHospitalsNeeds();
    }

}
