package com.accioshoppingbackend.Accio.Shopping.Website.reponsebody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseBody {
    private String productName;
    private int price;
    private int quantity;
    private Double rating;
    private String category;
    private UserResponseBody userResponseBody;
}
