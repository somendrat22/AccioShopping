package com.accioshoppingbackend.Accio.Shopping.Website.reponsebody;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecificProductOrderDetail {
    private UUID productID;
    private String productName;
    private int unitPrice;
    private int totalPurchasedPrice;
}
