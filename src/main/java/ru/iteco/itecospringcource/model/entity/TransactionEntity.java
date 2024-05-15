package ru.iteco.itecospringcource.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name= "transaction", schema = "bank")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "source_bank_book_id", referencedColumnName = "id")
    private BankBookEntity sourceBankBookId;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "target_bank_book_id", referencedColumnName = "id")
    private BankBookEntity targetBankBookId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "initiation_date")
    private LocalDateTime initiationDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private StatusEntity status;

}
