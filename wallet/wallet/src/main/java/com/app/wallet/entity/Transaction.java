package com.app.wallet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(0)
    @NotNull(message = "amount can't be null")
    private Double amount;

    private String description;

    @Min(1)
    @Max(3)
    private int type;

    private  String category;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name ="wallet_id", nullable = false)
    private wallet wallet;

    @PrePersist
    public void setTransaxtionDate()
    {
        this.transactionDate = (transactionDate != null) ? this.transactionDate:new Date();
    }
}
