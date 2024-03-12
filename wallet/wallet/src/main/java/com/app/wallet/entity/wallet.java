package com.app.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't null")
    @Size(max=30)
    private String name;

    @Size(min = 2, max=30)
    private String accountNumber;
    @Size(min = 2,max=30)
    private  String description;

    @Min(1)
    @Max(3)
    private Integer priority;
    private Double currentBalance;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet", orphanRemoval = true)
    private List<Transaction> transactions;

    @PrePersist
    public void setBalance()
    {
        this.currentBalance = Double.parseDouble("0");
    }
}
