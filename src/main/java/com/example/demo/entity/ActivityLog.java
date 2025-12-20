package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ActivityType activityType;

    @ManyToOne(optional = false)
    private User user;

    private Double quantity;
    private LocalDate activityDate;
    private LocalDateTime loggedAt;
    private Double estimatedEmission;

    public ActivityLog() {
    }

    public ActivityLog(Long id, ActivityType activityType, User user,
                       Double quantity, LocalDate activityDate,
                       LocalDateTime loggedAt, Double estimatedEmission) {
        this.id = id;
        this.activityType = activityType;
        this.user = user;
        this.quantity = quantity;
        this.activityDate = activityDate;
        this.loggedAt = loggedAt;
        this.estimatedEmission = estimatedEmission;
    }

    @PrePersist
    public void onCreate() {
        if (loggedAt == null) {
            loggedAt = LocalDateTime.now();
        }
    }

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ActivityType getActivityType() { return activityType; }
    public void setActivityType(ActivityType activityType) { this.activityType = activityType; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public LocalDate getActivityDate() { return activityDate; }
    public void setActivityDate(LocalDate activityDate) { this.activityDate = activityDate; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }

    public Double getEstimatedEmission() { return estimatedEmission; }
    public void setEstimatedEmission(Double estimatedEmission) { this.estimatedEmission = estimatedEmission; }
}
