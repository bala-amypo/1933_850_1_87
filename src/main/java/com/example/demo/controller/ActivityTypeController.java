package com.example.demo.controller;

import com.example.demo.entity.ActivityType;
import com.example.demo.service.ActivityTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@Tag(name = "Activity Types")
public class ActivityTypeController {

    private final ActivityTypeService typeService;

    public ActivityTypeController(ActivityTypeService typeService) {
        this.typeService = typeService;
    }

    // POST /api/types/category/{categoryId} – create type under a category
    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ActivityType> createUnderCategory(@PathVariable Long categoryId,
                                                            @Valid @RequestBody ActivityType type) {
        ActivityType created = typeService.createType(categoryId, type);
        return ResponseEntity.ok(created);
    }

    // GET /api/types/category/{categoryId} – list types by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ActivityType>> listByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(typeService.getTypesByCategory(categoryId));
    }

    // GET /api/types/{id} – get type
    @GetMapping("/{id}")
    public ResponseEntity<ActivityType> getById(@PathVariable Long id) {
        return typeService.getTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
