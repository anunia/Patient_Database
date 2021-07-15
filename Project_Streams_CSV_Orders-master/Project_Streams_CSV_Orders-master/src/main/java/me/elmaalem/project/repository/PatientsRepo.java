package me.elmaalem.project.repository;

import me.elmaalem.project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepo extends JpaRepository<Patient,Long> {
}
