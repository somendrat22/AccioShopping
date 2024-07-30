package com.accioshoppingbackend.Accio.Shopping.Website.reponsebody;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseBody {
    private String name;
    private int age;
    private String email;
    private Long phoneNumber;
    private String address;
}
