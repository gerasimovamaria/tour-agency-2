package com.agency.dto.response;

import com.agency.model.Discount;
import lombok.Data;

@Data
public class UserForDiscountResponse {
    private String name;

    private Discount discount;


}
