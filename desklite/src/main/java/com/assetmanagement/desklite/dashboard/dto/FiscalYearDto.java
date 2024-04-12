package com.assetmanagement.desklite.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiscalYearDto {
    private LocalDate fiscalYear;
    private Long assetCount;
    private Double cost;
}
