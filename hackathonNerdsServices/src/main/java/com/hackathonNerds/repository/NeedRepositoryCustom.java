package com.hackathonNerds.repository;

import java.util.List;

import com.hackathonNerds.entity.Need;

public interface NeedRepositoryCustom {
    List<Need> findHospitalNeeds(Integer hospitalId);
}
