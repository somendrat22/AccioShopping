package com.accioshoppingbackend.Accio.Shopping.Website.requestbody;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductRequestBody {
    private String productName;
    private int quantity;
}
