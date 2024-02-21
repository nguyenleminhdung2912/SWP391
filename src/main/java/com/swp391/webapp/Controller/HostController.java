package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.HostDTO;
import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    @Autowired
    private HostService hostService;

    @GetMapping
    public ResponseEntity<List<HostDTO>> getAllHosts() {
        List<HostDTO> hosts = hostService.getListHost();
        return ResponseEntity.ok(hosts);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<Optional<HostDTO>> getHostById(@PathVariable int hostId) {
        Optional<HostDTO> host = hostService.getHostById(hostId);
        return ResponseEntity.ok(host);
    }

    @PostMapping
    public ResponseEntity<HostDTO> saveHost(@RequestBody HostDTO host) {
        HostDTO savedHost = hostService.saveHost(host);
        return ResponseEntity.ok(savedHost);
    }

    @DeleteMapping("/{hostId}")
    public ResponseEntity<Void> deleteHost(@PathVariable int hostId) {
        hostService.deleteHost(hostId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints
    @GetMapping("/{hostId}/services")
    public ResponseEntity<List<ServiceDTO>> getServicesByHostId(@PathVariable int hostId) {
        List<ServiceDTO> services = hostService.getServicesByHostId(hostId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{hostId}/packages")
    public ResponseEntity<List<ServiceDTO>> getServicesByHostId(@PathVariable int hostId) {
        List<ServiceDTO> services = hostService.getPackagesByHostId(hostId);
        return ResponseEntity.ok(services);
    }
}
