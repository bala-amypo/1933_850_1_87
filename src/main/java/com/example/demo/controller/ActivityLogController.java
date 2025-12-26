package com.example.demo.controller;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;
import com.example.demo.service.ActivityLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Activity Logs")
public class ActivityLogController {

    private final ActivityLogService logService;

    public ActivityLogController(ActivityLogService logService) {
        this.logService = logService;
    }

    // POST /api/logs/{userId}/{typeId} – log activity
    @PostMapping("/{userId}/{typeId}")
    public ResponseEntity<ActivityLog> logActivity(@PathVariable Long userId,
                                                   @PathVariable Long typeId,
                                                   @Valid @RequestBody ActivityLogRequest request) {
        ActivityLog log = logService.logActivity(userId, typeId, request);
        return ResponseEntity.ok(log);
    }

    // GET /api/logs/user/{userId} – list logs for user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityLog>> getLogsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(logService.getLogsForUser(userId));
    }

    // GET /api/logs/user/{userId}/range – list logs by date range
    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<ActivityLog>> getLogsForUserInRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(logService.getLogsForUserInRange(userId, from, to));
    }

    // GET /api/logs/{id} – get log
    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getById(@PathVariable Long id) {
        return logService.getLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
