package com.example.demo.controller;

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
                                                   @Valid @RequestBody ActivityLog log) {
        ActivityLog saved = logService.logActivity(userId, typeId, log);
        return ResponseEntity.ok(saved);
    }

    // GET /api/logs/user/{userId} – list logs for user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityLog>> getLogsForUser(@PathVariable Long userId) {
        List<ActivityLog> logs = logService.getLogsByUser(userId);
        return ResponseEntity.ok(logs);
    }

    // GET /api/logs/user/{userId}/range?start=2025-01-01&end=2025-01-31
    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<ActivityLog>> getLogsForUserInRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<ActivityLog> logs = logService.getLogsByUserAndDate(userId, start, end);
        return ResponseEntity.ok(logs);
    }

    // GET /api/logs/{id} – get single log
    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getById(@PathVariable Long id) {
        ActivityLog log = logService.getLog(id);
        if (log == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(log);
    }
}
