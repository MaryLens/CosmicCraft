package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntry, Long> {
}
