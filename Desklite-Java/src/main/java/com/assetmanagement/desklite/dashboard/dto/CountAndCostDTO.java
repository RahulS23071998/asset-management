package com.assetmanagement.desklite.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountAndCostDTO {
    private Long totalCount;
    private Double totalCost;
}
