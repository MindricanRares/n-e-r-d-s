package com.hackathonNerds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathonNerds.entity.Need;

public interface NeedRepository extends JpaRepository<Need, Integer>, NeedRepositoryCustom {

}
