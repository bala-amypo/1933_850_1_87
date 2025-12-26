package com.example.demo.controller;

import com.example.demo.entity.EmissionFactor;
import com.example.demo.service.EmissionFactorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factors")
@Tag(name = "Emission Factors")
public class EmissionFactorController {

    private final EmissionFactorService factorService;

    public EmissionFactorController(EmissionFactorService factorService) {
        this.factorService = factorService;
    }

    // POST /api/factors/{activityTypeId} – create emission factor
    @PostMapping("/{activityTypeId}")
    public ResponseEntity<EmissionFactor> create(@PathVariable Long activityTypeId,
                                                 @Valid @RequestBody EmissionFactor factor) {
        EmissionFactor created = factorService.createFactor(activityTypeId, factor);
        return ResponseEntity.ok(created);
    }

    // GET /api/factors/{id} – get factor
    @GetMapping("/{id}")
    public ResponseEntity<EmissionFactor> getById(@PathVariable Long id) {
        return factorService.getFactorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/factors/type/{activityTypeId} – get factor by type
    @GetMapping("/type/{activityTypeId}")
    public ResponseEntity<EmissionFactor> getByType(@PathVariable Long activityTypeId) {
        return factorService.getFactorByActivityType(activityTypeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/factors/ – list all factors
    @GetMapping
    public ResponseEntity<List<EmissionFactor>> listAll() {
        return ResponseEntity.ok(factorService.getAllFactors());
    }
}
