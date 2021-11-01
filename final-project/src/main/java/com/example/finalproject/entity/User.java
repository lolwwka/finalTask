package com.example.finalproject.entity;

import com.example.finalproject.dto.UserDto;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Validated
@Entity
@Table(name = "visitor")
@NamedQuery(name = "User.findByKeyCode",
        query = "select u from User u where u.keyCode = :keyCode")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Email
    private String email;
    @NotBlank
    private String password;
    private String keyCode;
    private boolean authenticated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "visitor_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Bet> bets;

    public User() {
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean removeRole(Role role) {
        return roles.remove(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(UserDto userDto) {
        this.email = userDto.getEmail();
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String key_code) {
        this.keyCode = key_code;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}

