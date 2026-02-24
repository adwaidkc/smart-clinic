package com.smartclinic.smart_clinic.repository;

import com.smartclinic.smart_clinic.entity.Appointment;
import com.smartclinic.smart_clinic.entity.Doctor;
import com.smartclinic.smart_clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorAndAppointmentTimeBetween(
            Doctor doctor,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Appointment> findByPatient(Patient patient);
}