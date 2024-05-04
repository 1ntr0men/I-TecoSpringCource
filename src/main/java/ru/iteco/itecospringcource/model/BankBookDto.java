package ru.iteco.itecospringcource.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import ru.iteco.itecospringcource.validation.Create;
import ru.iteco.itecospringcource.validation.Currency;
import ru.iteco.itecospringcource.validation.Update;

import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto{

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;
    private Integer userId;
    @NotBlank(message = "Not blanc!")
    private String number;
    @Min(value = 0L)
    private BigDecimal amount;
    @Currency
    private String currency;

}
