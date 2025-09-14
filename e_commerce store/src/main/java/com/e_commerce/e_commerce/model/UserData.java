package com.e_commerce.e_commerce.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Entity
@Scope("prototype")
@Table(name = "user_data",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email")
        })
public class UserData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @JsonProperty("user_name")
    @Column(name = "user_name")
    private String userName;


    @JsonProperty("password")
    @Column(name = "password")
    private String passWord;

    @Transient
    @OneToMany(mappedBy = "userData")
    private List<OrderDetails> orders;


    @JsonProperty("user_role")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role userRole;

    @JsonProperty("email")
    @Column(name = "email")
    private String email;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                '}';
    }
}
