package com.app.wallet.Service;

import com.app.wallet.Repository.WalletRepository;
import com.app.wallet.entity.wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository wrepo;
    public wallet createOrUpdate(wallet w)
    {
        if(w.getId() == null)
        {
            wrepo.save(w);
        }
        else {
            w.setBalance();
            wrepo.save(w);
        }

        return w;
    }

    public boolean delete(Long id) {
        Optional<wallet> wal = wrepo.findById(id);
        if(wal.isPresent())
        {
            wrepo.delete(wal.get());
            return true;
        }
        return false;
    }


    public List<wallet> getAll() {

        return wrepo.findAllByOrderByPriority();
    }

    public wallet getById(Long id) {

        Optional<wallet> wal = wrepo.findById(id);
        if(wal.isPresent())
        {
            return wal.get();
        }
        return null
                ;
    }
}
