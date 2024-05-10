package ru.iteco.itecospringcource.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import ru.iteco.itecospringcource.validation.Create;
import ru.iteco.itecospringcource.validation.EmailValidity;
import ru.iteco.itecospringcource.validation.Update;

@Data
@Builder
public class UserDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotNull
    private String name;

    @EmailValidity
    private String email;

    private AddressDto address;

}
