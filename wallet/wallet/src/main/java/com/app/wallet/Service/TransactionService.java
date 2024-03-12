package com.app.wallet.Service;

import com.app.wallet.Repository.TransactionRepository;
import com.app.wallet.Repository.WalletRepository;
import com.app.wallet.entity.Transaction;
import com.app.wallet.entity.wallet;
import com.app.wallet.exception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository w;

    @Autowired
    private WalletRepository walletRepository;
    public Transaction createOrUpdate(Long walletId, Transaction transaction)
    {
        Optional<wallet> wa = walletRepository.findById(walletId);

        if(wa.isPresent())
        {

            if(transaction.getType() == 2)
            {
                wa.get().setCurrentBalance(wa.get().getCurrentBalance()-transaction.getAmount());
            }
            else {
                wa.get().setCurrentBalance(wa.get().getCurrentBalance()+transaction.getAmount());
            }

            walletRepository.save(wa.get());
            transaction.setWallet(wa.get());
            transactionRepository.save(transaction);

            return transaction;
        }
            return null;
    }

    public boolean delete(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent())
        {
//            System.out.println("wal is present"+wal.get().getAmount());
            wallet w=transaction.get().getWallet();
            if(transaction.get().getType() == 2)
            {
                w.setCurrentBalance(w.getCurrentBalance()+transaction.get().getAmount());
            }
            else {
                w.setCurrentBalance(w.getCurrentBalance()-transaction.get().getAmount());
            }



            List<Transaction> t =w.getTransactions();

            t.remove(transaction.get());

            w.setTransactions(t);

            walletRepository.save(w);
            transactionRepository.delete(transaction.get());
            return true;
        }
        System.out.println("wal is not present");
        throw new WalletException("Wallet with"+ id +"not found");
    }


    public List<Transaction> getAll(Long walletId) {
        Optional<wallet> t = w.findById(walletId);

        return t.map(wallet -> transactionRepository.findAllByWalletOrderByTransactionDateDesc((wallet) wallet)).orElse(null);
    }

    public Transaction getById(Long id) {

        Optional<Transaction> wal = transactionRepository.findById(id);
        if(wal.isPresent())
        {
            return wal.get();
        }
        throw new WalletException("transaction with"+ id +"not found");
    }
}
