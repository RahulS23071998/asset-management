package com.assetcircle.assetmanagement.employee.model;

import com.assetcircle.assetmanagement.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@DynamicUpdate
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false, unique = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = true, unique = false)
    private String lastName;

    @Column(name = "email_id", length = 100, nullable = false, unique = true)
    private String emailId;

    @Column(name = "employee_id", length = 20, nullable = false, unique = true)
    private String employeeId;

    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(name = "date_of_joining", nullable = true , unique = false)
    private LocalDate dateOfJoining;

    @Column(name = "contact_number", unique = true)
    private String contactNumber;

    @Column(name = "date_of_birth",nullable = true,unique = false)
    private LocalDate dateOfBirth;

    @Column(length = 50,unique = false,nullable = true)
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}

