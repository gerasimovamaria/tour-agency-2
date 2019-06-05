package com.agency.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TourBuyRequest {

    @NotNull
    private Long tourId;
}
