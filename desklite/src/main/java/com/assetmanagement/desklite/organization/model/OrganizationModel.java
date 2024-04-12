package com.assetmanagement.desklite.organization.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationModel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id", nullable = false,unique = true)
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
}
