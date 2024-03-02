package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.WalletDTO;
import com.swp391.webapp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll();
    }

    public Optional<WalletDTO> getWalletById(Integer walletId) {
        return walletRepository.findById(walletId);
    }

    public void saveWallet(WalletDTO wallet) {
        walletRepository.save(wallet);
    }

    public void deleteWallet(Integer walletId) {
        walletRepository.deleteById(walletId);
    }
    public void deleteWalletByAccountID(Integer id) {
        List<WalletDTO> list = walletRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccount().getAccountID() == id) {
                walletRepository.deleteById(list.get(i).getWalletID());
            }
        }
    }
}

