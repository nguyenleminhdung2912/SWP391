package com.swp391.webapp.Controller;

import com.swp391.webapp.Config.SecuredRestController;
import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.WalletEntity;
import com.swp391.webapp.ExceptionHandler.AlreadyExistedException;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.EmailService;
import com.swp391.webapp.Service.JWTService;
import com.swp391.webapp.Service.WalletService;
import com.swp391.webapp.dto.AccountUpdate;
import com.swp391.webapp.dto.LoginRequestDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController implements SecuredRestController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private MailController mailController = new MailController();
    @Autowired
    private PackageRepository packageRepository;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome XD";
    }

    @PostMapping("/login")
    public AccountEntity login(@RequestBody LoginRequestDTO loginRequestDTO)  {
        AccountEntity accountEntity = accountService.loadUserByUsername(loginRequestDTO.getEmail());
        if (accountEntity.getStatus().equals("Inactivated")) {
            throw new VerifyError("This account has not been verified");
        }
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
            System.out.println(authentication);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (authentication != null && authentication.isAuthenticated()) {
            AccountEntity account = (AccountEntity) authentication.getPrincipal();
            account.setTokens(jwtService.generateToken(account.getEmail()));
            return account;
            //return jwtService.generateToken(authRequest.getEmail());
        } else {
            System.out.println("Invalid");
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @GetMapping("/getAlluser")
    public List<AccountEntity> getAllUsers(){
        return accountService.getAllAcounts();
    }

    @GetMapping("/getAllHost")
    public List<AccountEntity> getAllHost(){
        return accountService.getAllHost();
    }

    @GetMapping("/getAllGuest")
    public List<AccountEntity> getAllGuest(){
        return accountService.getAllGuest();
    }

    @GetMapping("/getUser/{id}")
    public Optional<AccountEntity> getAllUsers(@PathVariable Integer id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/register")
    public AccountEntity addAccount(@RequestBody AccountEntity accountEntity){
        List<AccountEntity> listAccount = accountService.getAllAcounts();
        for (AccountEntity account : listAccount) {
            if (account.getEmail().equals(accountEntity.getEmail())) {
                throw new RuntimeException(new AlreadyExistedException("This email has been registered, please log in!"));
            }
        }
        accountEntity.setStatus("Inactivated");
        accountService.saveAccount(accountEntity);
        mailController.sendMail(accountEntity);
        BigDecimal a = new BigDecimal("0");
        WalletEntity walletEntity = new WalletEntity(accountEntity, a);
        walletService.saveWallet(walletEntity);
        return accountEntity;
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int accountId) {
        walletService.deleteWalletByAccountID(accountId);
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/refuse/{accountId}")
    public ResponseEntity<Void> refuseAccount(@PathVariable int accountId) {
        walletService.deleteWalletByAccountID(accountId);
        accountService.refuseAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(){
        return ResponseEntity.ok(accountUtils.getCurrentAccount());
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<Void> activateHostAccount(@PathVariable String email) {
        AccountEntity accountEntity = accountService.loadUserByUsername(email);
        accountEntity.setStatus("Activated");
        if (accountEntity.getRole().equals("Host")) {
            mailController.sendMail(accountEntity);
        }
        accountService.saveAccountStatus(accountEntity);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://birthdayblitzhub.online/login")).build();
    }

    @PatchMapping("/{id}")
    public AccountEntity updateEachFieldById(@PathVariable int id, Map<String, Objects> fields) {
        return accountService.updateEachFieldById(id, fields);
    }

    @PutMapping("/{id}")
    public AccountEntity getUserById(@PathVariable int id, @RequestBody AccountUpdate account) {
        return accountService.updateAccountByID(id, account);
    }

}
