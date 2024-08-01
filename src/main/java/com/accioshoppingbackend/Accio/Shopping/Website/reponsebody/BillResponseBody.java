package com.accioshoppingbackend.Accio.Shopping.Website.reponsebody;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillResponseBody {
    private List<SpecificProductOrderDetail> products;
    private int totalAmount;
}
