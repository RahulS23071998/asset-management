package com.assetmanagement.desklite.asset.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.OperationalStatus;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.base.jpaauditing.Auditable;
import com.assetmanagement.desklite.login.models.EmployeeModel;


import com.assetmanagement.desklite.organization.model.BaseEntity;
import com.assetmanagement.desklite.organization.model.OrganizationModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetModel extends BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -8526807757120956682L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assetId;
	private String assetName;
	private String description;
	private String serialNumber;
	private String modelNumber;
	private String brand;
	private double cost;
	private LocalDate purchaseDate;
	private String assetCode;

	@Enumerated(EnumType.STRING)
	private AssignedStatus assignedStatus;
	@Enumerated(EnumType.STRING)
	private OperationalStatus operationalStatus;
	@Enumerated(EnumType.STRING)
	private AssetType assetType;
	@Enumerated(EnumType.STRING)
	@Column(name = "asset_status", nullable = false)
	private AssetStatus assetStatus;
	
	@ManyToOne(targetEntity = OrganizationModel.class)
	@JoinColumn(name = "organization_id",referencedColumnName = "organizationId")
	private OrganizationModel organization;
 
	@ManyToOne(targetEntity = EmployeeModel.class)
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private EmployeeModel employee;

}
