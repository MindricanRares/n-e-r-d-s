package com.hackathonNerds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hackathonNerds.entity.Have;

public interface HaveRepository extends JpaRepository<Have, Integer> {
}
