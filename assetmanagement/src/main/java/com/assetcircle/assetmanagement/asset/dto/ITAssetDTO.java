package com.assetcircle.assetmanagement.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ITAssetDTO {

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

}