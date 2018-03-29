package com.hackathonNerds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hackathonNerds.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
