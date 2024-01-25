package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.HostDTO;
import com.swp391.webapp.Repository.HostRepository;

import java.util.List;
import java.util.Optional;

public class HostService {
    private HostRepository hostRepository;

    public HostDTO saveAccount(HostDTO HostDTO) {
        return hostRepository.save(HostDTO);
    }

    public List<HostDTO> getListUser() {
        return hostRepository.findAll();
    }

    public Optional<HostDTO> getUserById(int id) {
        return hostRepository.findById(id);
    }
}
