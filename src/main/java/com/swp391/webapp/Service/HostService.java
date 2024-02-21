package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.HostDTO;
import com.swp391.webapp.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    public HostDTO saveHost(HostDTO HostDTO) {
        return hostRepository.save(HostDTO);
    }

    public List<HostDTO> getListHost() {
        return hostRepository.findAll();
    }

    public Optional<HostDTO> getHostById(int id) {
        return hostRepository.findById(id);
    }

    public void deleteHost(int hostId) {
        hostRepository.deleteById(hostId);
    }
}
