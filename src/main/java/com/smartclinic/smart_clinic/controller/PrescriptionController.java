package com.smartclinic.smart_clinic.controller;

import com.smartclinic.smart_clinic.entity.*;
import com.smartclinic.smart_clinic.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription
    ) {

        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", "error", "message", "Token missing"));
        }

        Prescription saved = prescriptionRepository.save(prescription);

        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "prescriptionId", saved.getId()
                )
        );
    }
}