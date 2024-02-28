package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.WalletDTO;
import com.swp391.webapp.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        List<WalletDTO> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDTO> getWalletById(@PathVariable int walletId) {
        return walletService.getWalletById(walletId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO wallet) {
        walletService.saveWallet(wallet);
        return ResponseEntity.ok(wallet);
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<WalletDTO> updateWallet(@PathVariable int walletId, @RequestBody WalletDTO updatedWallet) {
        return walletService.getWalletById(walletId)
                .map(existingWallet -> {
                    existingWallet.setTotalMoney(updatedWallet.getTotalMoney());
                    walletService.saveWallet(existingWallet);
                    return ResponseEntity.ok(existingWallet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable int walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }
}