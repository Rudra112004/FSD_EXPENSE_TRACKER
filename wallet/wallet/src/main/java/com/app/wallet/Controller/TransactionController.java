package com.app.wallet.Controller;

import com.app.wallet.Service.TransactionService;
import com.app.wallet.Service.ValidationService;
import com.app.wallet.Service.WalletService;
import com.app.wallet.entity.Transaction;
import com.app.wallet.entity.wallet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    @Autowired
    private TransactionService w;
    @Autowired
    private ValidationService v;

    @GetMapping("/{walletid}")
    public ResponseEntity<?> getAll(@PathVariable("walletid") Long walletId)
    {
        List<Transaction> p = w.getAll(walletId);

        return new ResponseEntity<List<Transaction>>(p, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{walletId}/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id, @PathVariable("walletId") Long walletId)
    {
//        System.out.println("****  "+id+"*******");
        Long id1 = Long.parseLong(id);
        Transaction p= w.getById(id1);

        if(p == null)
        {
            return new ResponseEntity<Transaction>(p, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Transaction>(p, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{walletId}")
    public ResponseEntity<?> create(@PathVariable("walletId") Long id,@Valid @RequestBody Transaction Wal, BindingResult result)
    {
        ResponseEntity<?> p= v.validate(result);
        System.out.println("hello create tran calling  "+id);
        if(p != null)
        {
            System.out.println("show error  "+id);
            return p;
        }
        else {
            Transaction ne = w.createOrUpdate(id, Wal);
        }
        return new ResponseEntity<Transaction>(Wal, HttpStatus.CREATED);
    }

    @PutMapping("/{walletId}/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @PathVariable("walletId") Long walletId,@Valid @RequestBody Transaction transaction, BindingResult result)
    {
        System.out.println("in update");
        ResponseEntity<?> p= v.validate(result);
        if(p != null)
        {
            return p;
        }
        transaction.setId(id);
        Transaction transaction1 = w.createOrUpdate(walletId, transaction);

        return new ResponseEntity<Transaction>(transaction1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
//        System.out.println("shu kare chhe la");
        boolean p = w.delete(id);

        if(p)
        {
//            System.out.println("******shu kare chhe la");
            return new ResponseEntity<Boolean> (p, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Boolean> (p, HttpStatus.BAD_REQUEST);
    }
}
