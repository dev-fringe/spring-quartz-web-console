package dev.fringe.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fringe.scheduler.model.Scheduling;

public interface SchedulingRepositories extends JpaRepository<Scheduling, Long> {}
