package com.assetmanagement.desklite.asset.models;

import com.assetmanagement.desklite.base.jpaauditing.Auditable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itasset")
public class ITAssetModel extends Auditable {
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
