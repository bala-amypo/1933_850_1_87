// ActivityCategory.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ActivityCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String categoryName;

    private String description;

    private LocalDateTime createdAt;

    public ActivityCategory() {}

    public ActivityCategory(Long id, String categoryName,
                            String description, LocalDateTime createdAt) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
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
