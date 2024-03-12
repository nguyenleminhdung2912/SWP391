package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.WalletEntity;
import com.swp391.webapp.Repository.WalletRepository;
import com.swp391.webapp.dto.WalletDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AccountUtils accountUtils;

    public List<WalletEntity> getAllWallets() {
        return walletRepository.findAll();
    }

    public Optional<WalletEntity> getWalletById(Integer walletId) {
        return walletRepository.findById(walletId);
    }

    public WalletEntity saveWallet(WalletEntity wallet) {
        walletRepository.save(wallet);
        return wallet;
    }

    public void deleteWallet(Integer walletId) {
        walletRepository.deleteById(walletId);
    }
    public void deleteWalletByAccountID(Integer id) {
        List<WalletEntity> list = walletRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccount().getAccountID() == id) {
                walletRepository.deleteById(list.get(i).getWalletID());
            }
        }
    }

    public void addMoneyThroughPayment(AccountEntity host, BigDecimal amount){
        WalletEntity wallet = walletRepository.findWalletByAccount(host);
        BigDecimal total = new BigDecimal(0);
        total = total.add(amount);
        total = total.add(wallet.getTotalMoney());
        wallet.setTotalMoney(total);
        walletRepository.save(wallet);
    }

    public WalletEntity getWalletByAccount() {
        AccountEntity account = accountUtils.getCurrentAccount();
        return walletRepository.findWalletByAccount(account);
    }
    public WalletEntity getWalletByAccount(AccountEntity account) {
        return walletRepository.findWalletByAccount(account);
    }

    public WalletEntity addMoneyToWallet(WalletDTO walletDTO){
        AccountEntity account = accountUtils.getCurrentAccount();
        WalletEntity wallet = walletRepository.findWalletByAccount(account);
        BigDecimal total = new BigDecimal(0);
        total = total.add(walletDTO.getAmount());
        total = total.add(wallet.getTotalMoney());
        wallet.setTotalMoney(total);
        return walletRepository.save(wallet);
    }

    //Chuyen tien vao wallet cua admin
    public void guestPayForOrder(OrderEntity orderEntity){
        //Tru tien trong tai khoan admin
        WalletEntity adminWallet = walletRepository.findById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() + orderEntity.getTotalPrice().longValue());
        adminWallet.setTotalMoney(total);
        walletRepository.save(adminWallet);
    }
}

