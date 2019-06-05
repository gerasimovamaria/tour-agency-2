package com.agency.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourRequest {

    private String tourType;

    private String departurePlace;

    private String destinationPlace;

    private String name;

    private Integer price;
}
