package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.GuestDTO;
import com.swp391.webapp.Repository.GuestRepository;

import java.util.List;
import java.util.Optional;

public class GuestService {
    private GuestRepository guestRepository;

    public GuestDTO saveAccount(GuestDTO GuestDTO) {
        return guestRepository.save(GuestDTO);
    }

    public List<GuestDTO> getListUser() {
        return guestRepository.findAll();
    }

    public Optional<GuestDTO> getUserById(int id) {
        return guestRepository.findById(id);
    }
}
