package com.smartclinic.smart_clinic.service;

import com.smartclinic.smart_clinic.entity.*;
import com.smartclinic.smart_clinic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    // Book an appointment
    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime appointmentTime) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentTime(appointmentTime)
                .build();

        return appointmentRepository.save(appointment);
    }

    // Retrieve appointments for doctor on specific date
    public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return appointmentRepository.findByDoctorAndAppointmentTimeBetween(
                doctor,
                startOfDay,
                endOfDay
        );
    }
}