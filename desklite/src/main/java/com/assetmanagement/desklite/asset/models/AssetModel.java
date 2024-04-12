package com.assetmanagement.desklite.asset.models;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.OperationalStatus;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.base.jpaauditing.Auditable;
import com.assetmanagement.desklite.login.models.CompanyDetailsModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;


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
public class AssetModel extends Auditable {

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
	
	@ManyToOne(targetEntity = CompanyDetailsModel.class)
	@JoinColumn(name = "company_id",referencedColumnName = "id")
	private CompanyDetailsModel company;
 
	@ManyToOne(targetEntity = EmployeeModel.class)
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private EmployeeModel employee;

}
