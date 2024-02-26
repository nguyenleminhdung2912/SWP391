package com.swp391.webapp.Controller;

import com.swp391.webapp.Config.SecuredRestController;
import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Entity.AuthRequest;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.JWTService;
import com.swp391.webapp.utils.AccountUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    AccountUtils accountUtils;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome XD";
    }

    @PostMapping("/login")
    public AccountDTO login(@RequestBody AccountDTO accountDTO)  {
        System.out.println("-----------------------");
        System.out.println("Login information:");
        System.out.println("Email: "+ accountDTO.getEmail());
        System.out.println("Passowrd: "+ accountDTO.getPassword());
        System.out.println("-----------------------");
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword()));
            System.out.println(authentication);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Token: "+ jwtService.generateToken(accountDTO.getEmail()));
            AccountDTO account = accountService.loadUserByUsername(accountDTO.getEmail());
            account.setTokens(jwtService.generateToken(accountDTO.getEmail()));
            return account;
            //return jwtService.generateToken(authRequest.getEmail());
        } else {
            System.out.println("Invalid");
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @GetMapping("/getAlluser")
    public List<AccountDTO> getAllUsers(){
        return accountService.getAllAcounts();
    }
    @GetMapping("/getUser/{id}")
    public Optional<AccountDTO> getAllUsers(@PathVariable Integer id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/register")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO){
        return accountService.saveAccount(accountDTO);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(){
        return ResponseEntity.ok(accountUtils.getCurrentAccount());
    }
}
