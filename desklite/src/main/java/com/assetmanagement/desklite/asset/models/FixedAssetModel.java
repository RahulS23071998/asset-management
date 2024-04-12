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
@Table(name = "fixedasset")
public class FixedAssetModel extends Auditable {


	@Serial
	private static final long serialVersionUID = 1302238787758825035L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String color;
	private String graphicsCard;
	private String ram;
	private String rom;
	private String processor;
	private String os ;
	private String osVersion;
	private String battery;
	private String chargerType;
	private String wireless;
	private String weight;
	private String dimension;
	private String ipaddress;
	private String connectorType;
	private String bluetoothVersion;
	private String chargingTime;
	private String capacity;
	private String size;
	private String watts;
	private String material;
	private String volt;
	private String length;
	private String simNumber;
	private String imeiNumber;
	private String generation;
	
	@OneToOne
	@JoinColumn(name = "asset_id",referencedColumnName = "assetId")
	private AssetModel assetModel;

}
