package com.dexcoder.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dexcoder.demo.model.Scheduling;

public interface SchedulingRepositories extends JpaRepository<Scheduling, Long> {}
