package com.smartclinic.smart_clinic.repository;

import com.smartclinic.smart_clinic.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}