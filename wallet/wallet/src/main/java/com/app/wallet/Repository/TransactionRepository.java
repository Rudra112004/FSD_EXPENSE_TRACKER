package com.app.wallet.Repository;

import com.app.wallet.entity.Transaction;
import com.app.wallet.entity.wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWalletOrderByTransactionDateDesc(wallet w);
}
