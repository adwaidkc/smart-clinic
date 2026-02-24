package com.smartclinic.smart_clinic.repository;

import com.smartclinic.smart_clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Retrieve patient by email
    Optional<Patient> findByEmail(String email);

    // Retrieve patient by email OR phone
    Optional<Patient> findByEmailOrPhone(String email, String phone);
}