package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\p{L}{2,30}", message = "Name should be between 2 and 30 characters")
    private String name;

    @Pattern(regexp = "\\p{L}{2,30}", message = "Last Name should be between 2 and 30 characters")
    private String lastName;

    @Min(value = 0, message = "Age cannot be negative")
    @Column(nullable = false)
    private int age;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, message = "Password should be greater than 4 symbols")
    private String password;

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }

    @Transient
    private Set<String> roleNames = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String lastName, int age, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public @Pattern(regexp = "\\p{L}{2,30}", message = "Name should be between 2 and 30 characters") String getName() {
        return name;
    }

    public void setName(@Pattern(regexp = "\\p{L}{2,30}", message = "Name should be between 2 and 30 characters")
                        String name) {
        this.name = name;
    }

    public @Pattern(regexp = "\\p{L}{2,30}", message = "Last Name should be between 2 and 30 characters")
    String getLastName() {
        return lastName;
    }

    public void setLastName(@Pattern(regexp = "\\p{L}{2,30}",
            message = "Last Name should be between 2 and 30 characters") String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password cannot be empty") @Size(min = 4,
            message = "Password should be greater than 4 symbols") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password cannot be empty") @Size(min = 4,
            message = "Password should be greater than 4 symbols") String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

