package com.hackathonNerds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackathonNerds.entity.Have;

public interface HaveRepository extends JpaRepository<Have, Integer> {
    @Query("SELECT n FROM Have n JOIN Hospital h ON h.id = n.hospital.id JOIN Resource r ON r.id=n.resource.id WHERE h.id = :hospitalId")
    public List<Have> findHospitalHaves(@Param("hospitalId") Integer hospitalId);
}
