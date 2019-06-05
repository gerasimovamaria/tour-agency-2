package com.agency.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class DiscountRequest {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long tourId;

    @NotNull
    private Boolean isPercent;

    @NotNull
    private Integer amount;
}
