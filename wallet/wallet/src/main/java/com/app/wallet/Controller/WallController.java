package com.app.wallet.Controller;

import com.app.wallet.Service.ValidationService;
import com.app.wallet.Service.WalletService;
import com.app.wallet.entity.wallet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WallController {
    @Autowired
    private WalletService w;
    @Autowired
    private ValidationService v;

    @GetMapping()
    public ResponseEntity<?> getAll()
    {
        List<wallet> p = w.getAll();

        return new ResponseEntity<List<wallet>>(p, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id)
    {
        System.out.println("****  "+id+"*******");
        Long id1 = Long.parseLong(id);
        wallet p= w.getById(id1);

        if(p == null)
        {
            return new ResponseEntity<wallet>(p, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<wallet>(p, HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody wallet Wal, BindingResult result)
    {
        ResponseEntity<?> p= v.validate(result);
        if(p != null)
        {
            return p;
        }
        else {
            wallet ne = w.createOrUpdate(Wal);
        }
        return new ResponseEntity<wallet>(Wal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody wallet Wal, BindingResult result)
    {
        System.out.println("in update");
        ResponseEntity<?> p= v.validate(result);
        if(p != null)
        {
            return p;
        }
        else {
            Wal.setId(id);
            wallet ne = w.createOrUpdate(Wal);
        }
        return new ResponseEntity<wallet>(Wal, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        boolean p = w.delete(id);

        if(p)
        {
            return new ResponseEntity<Boolean> (p, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Boolean> (p, HttpStatus.BAD_REQUEST);
    }

}
