package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackDTO;
import com.swp391.webapp.Entity.GuestDTO;
import com.swp391.webapp.Service.FeedbackService;
import com.swp391.webapp.Service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<GuestDTO> guests = guestService.getAllGuest();
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<Optional<GuestDTO>> getGuestById(@PathVariable int guestId) {
        Optional<GuestDTO> guest = guestService.getGuestById(guestId);
        return ResponseEntity.ok(guest);
    }

    @PostMapping
    public ResponseEntity<GuestDTO> saveGuest(@RequestBody GuestDTO guest) {
        GuestDTO savedGuest = guestService.saveGuest(guest);
        return ResponseEntity.ok(savedGuest);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> deleteGuest(@PathVariable int guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.noContent().build();
    }

//    // Additional endpoint
//    @GetMapping("/account/{accountId}")
//    public ResponseEntity<List<GuestDTO>> getGuestsByAccountId(@PathVariable int accountId) {
//        List<GuestDTO> guests = guestService.getGuestsByAccountId(accountId);
//        return ResponseEntity.ok(guests);
//    }

}
