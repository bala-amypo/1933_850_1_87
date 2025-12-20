// ActivityType.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ActivityType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String typeName;

    @ManyToOne(optional = false)
    private ActivityCategory category;

    @Column(nullable = false)
    private String unit;

    private LocalDateTime createdAt;

    public ActivityType() {}

    public ActivityType(Long id, String typeName,
                        ActivityCategory category, String unit,
                        LocalDateTime createdAt) {
        this.id = id;
        this.typeName = typeName;
        this.category = category;
        this.unit = unit;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // getters and setters
}
