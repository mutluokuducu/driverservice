package com.hypothetical.repository;

import com.hypothetical.repository.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HypotheticalRepository extends JpaRepository<Driver, Long> {
}
