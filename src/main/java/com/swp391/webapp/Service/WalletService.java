package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.*;
import com.swp391.webapp.Entity.Enum.TransactionStatus;
import com.swp391.webapp.Repository.WalletRepository;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Autowired
    private AccountService accountService;
    @Autowired
    @Lazy
    private TransactionService transactionService;

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

    public WalletEntity getWalletByAccountId(int accountId) {
        AccountEntity account = accountService.getAccountById(accountId).get();
        return walletRepository.findWalletByAccount(account);
    }

    public WalletEntity addMoneyToWallet(WalletTransactionEntity walletTransactionEntity){
        AccountEntity account = accountUtils.getCurrentAccount();
        WalletEntity wallet = walletRepository.findWalletByAccount(account);
        BigDecimal total = new BigDecimal(0);
        total = total.add(walletTransactionEntity.getTotalPrice());
        total = total.add(wallet.getTotalMoney());
        wallet.setTotalMoney(total);
        return walletRepository.save(wallet);
    }

    //Chuyen tien vao wallet cua admin
    public void guestPayForOrder(OrderEntity orderEntity, WalletEntity guestWallet){
        WalletEntity adminWallet = walletRepository.findById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() + orderEntity.getDepositedMoney().longValue());
        adminWallet.setTotalMoney(total);

        transactionService.saveTransaction(new TransactionEntity(orderEntity, guestWallet, orderEntity.getCreateAt(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));

        transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, orderEntity.getCreateAt(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));


        walletRepository.save(adminWallet);
    }

    public void guestPayForOrderThroughtWallet(OrderEntity orderEntity, WalletEntity guestWallet){
        //Lay vi admin them tien vao
        WalletEntity adminWallet = walletRepository.findById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() + orderEntity.getDepositedMoney().longValue());
        adminWallet.setTotalMoney(total);
        transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, orderEntity.getCreateAt(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));

        walletRepository.save(adminWallet);
        //Lay vi guest tru tien
        guestWallet.setTotalMoney(new BigDecimal(guestWallet.getTotalMoney().longValue() - orderEntity.getDepositedMoney().longValue()));

        transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, orderEntity.getCreateAt(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));


        transactionService.saveTransaction(new TransactionEntity(orderEntity, guestWallet, orderEntity.getCreateAt(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));

        walletRepository.save(guestWallet);

    }
}

