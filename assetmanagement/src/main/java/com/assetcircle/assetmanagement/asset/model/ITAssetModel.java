package com.assetcircle.assetmanagement.asset.model;

import com.assetcircle.assetmanagement.organization.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itasset")
public class ITAssetModel extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 6869769120152901498L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String hostName;
	private String totalPort;
	private String managementPortInfo;
	private String defaultGateWay;
	private String firewallType;
	private String firewallIpAddress;
	private String macAddress;
	private String serviceTag;
	private String os;
	private String processor;
	private String raidCard;
	private String hardDisk;
	private String networkCard;
	private String smps;
	private String vmtype;
	private String diskDetails;
	private String graphicsCard;
	private boolean isPrinterLinked;

	@OneToOne
	@JoinColumn(name = "asset_id",referencedColumnName = "assetId")
	private AssetModel assetModel;

}
