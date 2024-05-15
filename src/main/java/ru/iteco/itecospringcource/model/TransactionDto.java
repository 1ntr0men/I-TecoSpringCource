package ru.iteco.itecospringcource.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    @Null
    private Integer id;
    @NotNull
    private Integer sourceBankBookId;
    @NotNull
    private Integer targetBankBookId;
    @Min(value = 0L)
    private BigDecimal amount;
    @NotNull
    private LocalDateTime initiationDate;
    private LocalDateTime completionDate;
    @NotNull
    private Integer status;

}
