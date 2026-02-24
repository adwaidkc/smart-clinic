package com.smartclinic.smart_clinic.service;

import com.smartclinic.smart_clinic.entity.Doctor;
import com.smartclinic.smart_clinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Return available time slots for a doctor on a given date
    public List<String> getAvailableTimeSlots(Long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (doctor.isPresent()) {
            return doctor.get().getAvailableTimes();
        }

        return Collections.emptyList();
    }

    // Validate doctor login credentials
    public Map<String, Object> validateDoctorLogin(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if (doctor.isPresent() && doctor.get().getPassword().equals(password)) {
            response.put("status", "success");
            response.put("doctorId", doctor.get().getId());
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
        }

        return response;
    }
}