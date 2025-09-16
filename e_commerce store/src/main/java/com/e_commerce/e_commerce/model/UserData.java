package com.e_commerce.e_commerce.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;

import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must be at most 50 characters")
    @JsonProperty("user_name")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = ".*[A-Za-z].*", message = "Password must contain at least one letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
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

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
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
