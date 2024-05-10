package ru.iteco.itecospringcource.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private String country;
    private String city;
    private String street;
    private String home;

}
