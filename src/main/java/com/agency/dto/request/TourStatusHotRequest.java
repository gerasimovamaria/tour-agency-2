package com.agency.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TourStatusHotRequest {
    @NotNull
    private Long tourId;

    @NotNull
    private Boolean isHot;
}