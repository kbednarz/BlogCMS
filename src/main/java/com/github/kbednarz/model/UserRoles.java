package com.github.kbednarz.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long userId;
    private String role;

    public UserRoles(){
    }

    public UserRoles(long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
