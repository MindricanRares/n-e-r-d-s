package com.hackathonNerds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackathonNerds.entity.Need;

public interface NeedRepository extends JpaRepository<Need, Integer> {

    @Query("SELECT n FROM Need n JOIN Hospital h ON h.id = n.hospital.id JOIN Resource r ON r.id=n.resource.id WHERE h.id = :hospitalId")
    public List<Need> findHospitalNeeds(@Param("hospitalId") Integer hospitalId);
}
