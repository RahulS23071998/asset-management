package com.assetcircle.assetmanagement.asset.model;

import com.assetcircle.assetmanagement.enums.AssetStatus;
import com.assetcircle.assetmanagement.enums.AssetType;
import com.assetcircle.assetmanagement.enums.AssignedStatus;
import com.assetcircle.assetmanagement.enums.OperationalStatus;
import com.assetcircle.assetmanagement.organization.model.BaseEntity;
import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetModel extends BaseEntity {

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

	@Enumerated(EnumType.STRING)
	private AssignedStatus assignedStatus;
	@Enumerated(EnumType.STRING)
	private OperationalStatus operationalStatus;
	@Enumerated(EnumType.STRING)
	private AssetType assetType;
	@Enumerated(EnumType.STRING)
	@Column(name = "asset_status", nullable = false)
	private AssetStatus assetStatus;

	@ManyToOne(targetEntity = EmployeeModel.class)
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private EmployeeModel employee;

}
