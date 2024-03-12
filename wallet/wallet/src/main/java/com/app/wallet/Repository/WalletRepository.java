package com.app.wallet.Repository;

import com.app.wallet.entity.wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<wallet, Long> {
    List<wallet> findAllByOrderByPriority();
}
