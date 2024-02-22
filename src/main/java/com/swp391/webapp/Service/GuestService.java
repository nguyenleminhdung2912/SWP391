package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.GuestDTO;
import com.swp391.webapp.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public GuestDTO saveGuest(GuestDTO GuestDTO) {
        return guestRepository.save(GuestDTO);
    }

    public List<GuestDTO> getAllGuest() {
        return guestRepository.findAll();
    }

    public Optional<GuestDTO> getGuestById(int id) {
        return guestRepository.findById(id);
    }

    public void deleteGuest(int guestId) {
        guestRepository.deleteById(guestId);
    }

//    public List<GuestDTO> getGuestsByAccountId(int accountId) {
//        return guestRepository.findByAccountId(accountId);
//    }
}
