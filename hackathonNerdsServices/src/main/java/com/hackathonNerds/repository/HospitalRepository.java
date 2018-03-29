package com.hackathonNerds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hackathonNerds.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
