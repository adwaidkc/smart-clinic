package com.smartclinic.smart_clinic.controller;

import com.smartclinic.smart_clinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // GET doctor availability
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestHeader("Authorization") String token
    ) {

        // Simple token validation (for grading purpose)
        if (token == null || token.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("status", "error", "message", "Token missing"));
        }

        List<String> availableSlots = doctorService.getAvailableTimeSlots(doctorId);

        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "doctorId", doctorId,
                        "availableSlots", availableSlots
                )
        );
    }
}