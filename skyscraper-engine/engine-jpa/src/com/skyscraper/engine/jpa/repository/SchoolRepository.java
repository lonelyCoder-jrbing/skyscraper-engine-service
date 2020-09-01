package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}