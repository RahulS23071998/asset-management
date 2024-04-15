package com.assetmanagement.desklite.organization.model;

import com.assetmanagement.desklite.base.jpaauditing.Auditable;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class OrganizationModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizationId", nullable = false, unique = true)
    private Long organizationId;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "organization_email_id", nullable = true, unique = true)
    private String organizationEmailId;

    @Column(name = "contact_number",nullable = false,unique = true)
    private String contactNumber;

    @Column(name = "location",nullable = true,unique = true)
    private String location;

    @Column(name = "organization_code", nullable = false, unique = true)
    private String organizationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrganizationStatus status;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EmployeeModel> employees;


}
