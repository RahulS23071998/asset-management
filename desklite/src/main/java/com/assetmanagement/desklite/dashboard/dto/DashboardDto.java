package com.assetmanagement.desklite.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {

    private Double totalCost;
    private Long count;
    private LocalDate purchasedDate;

}
