package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // up to 5 string fields to match many constructor calls
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;

    public User() {
    }

    // 2 args
    public User(Long id, String f1) {
        this.id = id;
        this.field1 = f1;
    }

    // 3 args
    public User(Long id, String f1, String f2) {
        this.id = id;
        this.field1 = f1;
        this.field2 = f2;
    }

    // 4 args
    public User(Long id, String f1, String f2, String f3) {
        this.id = id;
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
    }

    // 5 args
    public User(Long id, String f1, String f2, String f3, String f4) {
        this.id = id;
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
        this.field4 = f4;
    }

    // 6 args
    public User(Long id, String f1, String f2, String f3, String f4, String f5) {
        this.id = id;
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
        this.field4 = f4;
        this.field5 = f5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }
}
