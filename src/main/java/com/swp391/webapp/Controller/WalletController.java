package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.OrderStatus;
import com.swp391.webapp.Entity.Schedule;
import com.swp391.webapp.Entity.WalletEntity;
import com.swp391.webapp.Service.WalletService;
import com.swp391.webapp.dto.OrderDTO;
import com.swp391.webapp.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping()
    public WalletEntity getWalletByAccount() {
        return walletService.getWalletByAccount();
    }

    @PostMapping
    public ResponseEntity<WalletEntity> createWallet(@RequestBody WalletEntity wallet) {
        walletService.saveWallet(wallet);
        return ResponseEntity.ok(wallet);
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<WalletEntity> updateWallet(@PathVariable int walletId, @RequestBody WalletEntity updatedWallet) {
        return walletService.getWalletById(walletId)
                .map(existingWallet -> {
                    existingWallet.setTotalMoney(updatedWallet.getTotalMoney());
                    walletService.saveWallet(existingWallet);
                    return ResponseEntity.ok(existingWallet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/addMoney/")
//    public WalletEntity addMoneyToWallet(@RequestBody BigDecimal amount) {
//        return walletService.addMoneyToWallet(amount);
//    }

    @PostMapping("/addMoney-payment")
    public ResponseEntity createUrl(@RequestBody WalletDTO walletDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);


        String tmnCode = "II9036T8";
        String secretKey = "AFWMAKAMRNPUQTQWFDCGXTXPQBQKFIRF";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://localhost:5173/success";

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", "1");
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + "1");
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", String.valueOf(100 * walletDTO.getAmount().longValue()));
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "http://birthdayblitzhub.online/");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        walletService.addMoneyToWallet(walletDTO);

        return ResponseEntity.ok(urlBuilder.toString());
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable int walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }
}